
import { useEffect, useState } from "react";

function GenerateBill({ onBack }) {
  const [rooms, setRooms] = useState([]);
  const [selectedRoom, setSelectedRoom] = useState("");
  const [bill, setBill] = useState(null);
  const [message, setMessage] = useState("");

  // Load occupied room numbers when the screen opens
  useEffect(() => {
    fetch("http://localhost:8080/api/rooms/occupied/numbers")
      .then((response) => {
        if (!response.ok) {
          throw new Error("Could not load occupied rooms");
        }
        return response.json();
      })
      .then((data) => setRooms(data))
      .catch((error) => setMessage(error.message));
  }, []);

  // Generate bill for the selected room
  async function handleGenerateBill() {
    if (!selectedRoom) {
      setMessage("No occupied room selected.");
      return;
    }

    try {
      const response = await fetch(
        `http://localhost:8080/api/bookings/bill/${selectedRoom}`
      );

      if (!response.ok) {
        throw new Error("Bill could not be generated.");
      }

      const data = await response.json();
      setBill(data);
      setMessage("");
    } catch (error) {
      setBill(null);
      setMessage(error.message);
    }
  }

  // Checkout after generating the bill
  async function handleCheckout() {
    if (!bill) {
      setMessage("Generate bill first.");
      return;
    }

    try {
      const response = await fetch(
        `http://localhost:8080/api/bookings/${bill.bookingId}/checkout`,
        {
          method: "PATCH",
        }
      );

      if (!response.ok) {
        throw new Error("Checkout failed.");
      }

      setMessage("Checkout Successful.");
      setBill(null);
      setSelectedRoom("");

      // Refresh occupied rooms
      const roomsResponse = await fetch(
        "http://localhost:8080/api/rooms/occupied/numbers"
      );

      if (roomsResponse.ok) {
        const data = await roomsResponse.json();
        setRooms(data);
      }

      // Return to dashboard after successful checkout
      onBack();
    } catch (error) {
      setMessage(error.message);
    }
  }

  return (
    <div>
      <h2>GENERATE BILL</h2>

      <label>Select Room </label>

      <select
        value={selectedRoom}
        onChange={(event) => {
          setSelectedRoom(event.target.value);
          setBill(null);
          setMessage("");
        }}
      >
        <option value="">-- Select occupied room --</option>

        {rooms.map((room) => (
          <option key={room} value={room}>
            {room}
          </option>
        ))}
      </select>

      <br />
      <br />

      <button onClick={handleGenerateBill}>BILL</button>

      <br />
      <br />

      <textarea
        readOnly
        rows="10"
        cols="40"
        value={
          bill
            ? `========== HOTEL BILL ==========

Customer Name : ${bill.customerName}
Phone         : ${bill.phone}
Address       : ${bill.address}

------------------------------
Room Number   : ${bill.roomNo}
Days Stayed   : ${bill.days}
Rate / Day    : $${bill.price}
------------------------------
TOTAL BILL    : $${bill.total}

==============================`
            : ""
        }
      />

      <br />
      <br />

      <button onClick={handleCheckout}>CHECKOUT</button>

      <button onClick={onBack}>BACK</button>

      {message && <p>{message}</p>}
    </div>
  );
}

export default GenerateBill;
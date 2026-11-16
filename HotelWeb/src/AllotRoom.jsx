
import { useEffect, useState } from "react";

function AllotRoom({ onBack }) {
  const [customers, setCustomers] = useState([]);
  const [rooms, setRooms] = useState([]);

  const [selectedCustomer, setSelectedCustomer] = useState("");
  const [selectedRoom, setSelectedRoom] = useState("");

  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);

  useEffect(() => {
    async function loadData() {
      try {
        const [customerResponse, roomResponse] = await Promise.all([
          fetch("http://localhost:8080/api/customers"),
          fetch("http://localhost:8080/api/rooms/available/numbers"),
        ]);

        if (!customerResponse.ok || !roomResponse.ok) {
          throw new Error("Could not load customers or rooms.");
        }

        const customerData = await customerResponse.json();
        const roomData = await roomResponse.json();

        setCustomers(customerData);
        setRooms(roomData);
      } catch (error) {
        console.error(error);
        setMessage("Cannot connect to the server or load data.");
      } finally {
        setLoading(false);
      }
    }

    loadData();
  }, []);

  async function handleAllot(event) {
    event.preventDefault();
    setMessage("");

    if (!selectedCustomer || !selectedRoom) {
      setMessage("Please select both a customer and a room.");
      return;
    }

    setSubmitting(true);

    try {
      const response = await fetch(
        "http://localhost:8080/api/bookings/allot",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            customerId: Number(selectedCustomer),
            roomNo: Number(selectedRoom),
          }),
        }
      );

      const responseMessage = await response.text();

      if (!response.ok) {
        setMessage(responseMessage || "Room allotment failed.");
        return;
      }

      alert( "Room allotted successfully." || responseMessage);
      onBack();
    } catch (error) {
      console.error(error);
      setMessage("Cannot connect to the server.");
    } finally {
      setSubmitting(false);
    }
  }

  return (
    <div>
      <h2>ROOM ALLOTMENT</h2>

      {loading ? (
        <p>Loading customers and available rooms...</p>
      ) : (
        <form onSubmit={handleAllot}>
          <div>
            <label htmlFor="customer">Select Customer</label>{" "}
            <select
              id="customer"
              value={selectedCustomer}
              onChange={(event) =>
                setSelectedCustomer(event.target.value)
              }
            >
              <option value="">-- Select Customer --</option>

              {customers.map((customer) => (
                <option
                  key={customer.customerId}
                  value={customer.customerId}
                >
                  {customer.customerId} - {customer.customerName}
                </option>
              ))}
            </select>
          </div>

          <br />

          <div>
            <label htmlFor="room">Select Room</label>{" "}
            <select
              id="room"
              value={selectedRoom}
              onChange={(event) =>
                setSelectedRoom(event.target.value)
              }
            >
              <option value="">-- Select Room --</option>

              {rooms.map((roomNo) => (
                <option key={roomNo} value={roomNo}>
                  {roomNo}
                </option>
              ))}
            </select>
          </div>

          <br />

          <button type="submit" disabled={submitting}>
            {submitting ? "Allotting..." : "ALLOT"}
          </button>
        </form>
      )}

      {message && <p>{message}</p>}

      <br />

      <button onClick={onBack}>Dashboard</button>
    </div>
  );
}

export default AllotRoom;
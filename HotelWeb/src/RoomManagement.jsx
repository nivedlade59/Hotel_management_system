
import { useState } from "react";

function RoomManagement({ onBack }) {
  const [rooms, setRooms] = useState([]);
  const [message, setMessage] = useState("");

  async function handleShowAvailableRooms() {
    try {
      const response = await fetch(
        "http://localhost:8080/api/rooms/available"
      );

      if (!response.ok) {
        throw new Error("Failed to retrieve available rooms.");
      }

      const data = await response.json();
      setRooms(data);
      setMessage(
        data.length === 0 ? "No available rooms." : ""
      );
    } catch (error) {
      setMessage("Cannot connect to the server.");
      console.error(error);
    }
  }

  return (
    <div>
      <h2>ROOM MANAGEMENT</h2>

      <button onClick={handleShowAvailableRooms}>
        AVAILABLE ROOMS
      </button>

      <br />
      <br />

      {message && <p>{message}</p>}

      {rooms.map((room) => (
        <div key={room.roomNo}>
          Room Number: {room.roomNo} | Price: {room.price}
        </div>
      ))}

      <br />

      <button onClick={onBack}>Dashboard</button>
    </div>
  );
}

export default RoomManagement;
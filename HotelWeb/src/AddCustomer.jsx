
import { useState } from "react";

function AddCustomer({ onBack }) {
  const [name, setName] = useState("");
  const [phone, setPhone] = useState("");
  const [aadhaarId, setAadhaarId] = useState("");
  const [address, setAddress] = useState("");
  const [days, setDays] = useState("");

  async function handleAdd(event) {
    event.preventDefault();

    if (
      name.trim() === "" ||
      phone.trim() === "" ||
      aadhaarId.trim() === "" ||
      address.trim() === "" ||
      days === ""
    ) {
      alert("Please fill in all fields.");
      return;
    }

    const numberOfDays = Number(days);

    if (!Number.isInteger(numberOfDays) || numberOfDays <= 0) {
      alert("Please enter a valid number of days.");
      return;
    }

    const customer = {
      customerName: name,
      phone: phone,
      idProof: aadhaarId,
      address: address,
      days: numberOfDays,
    };

    try {
      const response = await fetch(
        "http://localhost:8080/api/customers",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(customer),
        }
      );

      if (response.ok) {
        alert("Customer Added Successfully!");

        setName("");
        setPhone("");
        setAadhaarId("");
        setAddress("");
        setDays("");

        onBack();
      } else {
        const message = await response.text();
        alert(message || "Failed to Add Customer!");
      }
    } catch (error) {
      alert("Cannot connect to the server.");
      console.error(error);
    }
  }

  return (
    <div>
      <h2>ADD CUSTOMER</h2>

      <form onSubmit={handleAdd}>
        <div>
          <label htmlFor="name">Name</label>{" "}
          <input
            id="name"
            value={name}
            onChange={(event) => setName(event.target.value)}
          />
        </div>

        <br />

        <div>
          <label htmlFor="phone">Phone Number</label>{" "}
          <input
            id="phone"
            value={phone}
            onChange={(event) => setPhone(event.target.value)}
          />
        </div>

        <br />

        <div>
          <label htmlFor="aadhaarId">Aadhaar ID</label>{" "}
          <input
            id="aadhaarId"
            value={aadhaarId}
            onChange={(event) => setAadhaarId(event.target.value)}
          />
        </div>

        <br />

        <div>
          <label htmlFor="address">Address</label>{" "}
          <input
            id="address"
            value={address}
            onChange={(event) => setAddress(event.target.value)}
          />
        </div>

        <br />

        <div>
          <label htmlFor="days">Number of days</label>{" "}
          <input
            id="days"
            type="number"
            min="1"
            value={days}
            onChange={(event) => setDays(event.target.value)}
          />
        </div>

        <br />

        <button type="submit">ADD</button>{" "}
        <button type="button" onClick={onBack}>
          Back
        </button>
      </form>
    </div>
  );
}

export default AddCustomer;
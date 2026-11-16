
import { useState } from "react";
import Dashboard from "./Dashboard";
import AddCustomer from "./AddCustomer";
import RoomManagement from "./RoomManagement";
import AllotRoom from "./AllotRoom";
import GenerateBill from "./GenerateBill";
import EmployeeDetails from "./EmployeeDetails";
import ReportGeneration from "./ReportGeneration";

function App() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [currentScreen, setCurrentScreen] = useState("dashboard");

  
  async function handleLogin(event) {
    event.preventDefault();

    if (username.trim() === "" || password === "") {
      alert("Please enter both username and password.");
      return;
    }

    try {
      const response = await fetch(
        "http://localhost:8080/api/users/login",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            username: username,
            password: password,
          }),
        }
      );

      const message = await response.text();

      if (response.ok) {
        setIsLoggedIn(true);
      } else {
        alert(message || "Login failed.");
      }
    } catch (error) {
      alert("Cannot connect to the server. Check whether Spring Boot is running.");
      console.error(error);
    }
  }

  if (isLoggedIn) {
    if (currentScreen === "customer") {
      return (
        <AddCustomer
          onBack={() => setCurrentScreen("dashboard")}
        />
      );
    }

    if (currentScreen === "rooms") {
      return (
        <RoomManagement
          onBack={() => setCurrentScreen("dashboard")}
        />
      );
    }

    if (currentScreen === "allot") {
      return (
        <AllotRoom
          onBack={() => setCurrentScreen("dashboard")}
        />
      );
    }

    if (currentScreen === "bill") {
      return (
        <GenerateBill
          onBack={() => setCurrentScreen("dashboard")} 
        />
      );
    }

    if (currentScreen === "employees") {
      return (
        <EmployeeDetails
          onBack={() => setCurrentScreen("dashboard")} 
        />
      );
    }

    if (currentScreen === "report") {
      return (
        <ReportGeneration
          onBack={() => setCurrentScreen("dashboard")} 
        />
      );
    }

    return (
      <Dashboard
        onCustomerRegistration={() => setCurrentScreen("customer")}
        onRoomAvailability={() => setCurrentScreen("rooms")}
        onAllotRoom={() => setCurrentScreen("allot")}
        onGenerateBill={() => setCurrentScreen("bill")}
        onEmployeeDetails={() => setCurrentScreen("employees")}
        onReportGeneration={() => setCurrentScreen("report")}
        onLogout={() => {
          setIsLoggedIn(false);
          setUsername("");
          setPassword("");
          setCurrentScreen("dashboard");
        }}
      />
    );
}

  return (
    <div>
      <h2>Hotel Management</h2>
      <h3>Login</h3>

      <form onSubmit={handleLogin}>
        <div>
          <label htmlFor="username">Username</label>{" "}
          <input
            id="username"
            type="text"
            value={username}
            onChange={(event) => setUsername(event.target.value)}
          />
        </div>

        <br />

        <div>
          <label htmlFor="password">Password</label>{" "}
          <input
            id="password"
            type="password"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
          />
        </div>

        <br />

        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default App;

import { useEffect, useState } from "react";

function EmployeeDetails({ onBack }) {
  const [employees, setEmployees] = useState([]);
  const [message, setMessage] = useState("Loading employees...");

  useEffect(() => {
    async function loadEmployees() {
      try {
        const response = await fetch(
          "http://localhost:8080/api/employees"
        );

        if (!response.ok) {
          throw new Error("Failed to load employees");
        }

        const data = await response.json();

        setEmployees(data);
        setMessage(
          data.length === 0 ? "No Employees Found." : ""
        );
      } catch (error) {
        setMessage("Could not load employees.");
        console.error(error);
      }
    }

    loadEmployees();
  }, []);

  return (
    <div>
      <h2>EMPLOYEE DETAILS</h2>

      {employees.length === 0 ? (
        <p>{message}</p>
      ) : (
        <textarea
          readOnly
          rows={10}
          cols={35}
          value={employees
            .map(
              (employee) =>
                `Employee ID : ${employee.employeeId}\n` +
                `Name        : ${employee.employeeName}\n` +
                `Phone       : ${employee.phone}\n` +
                `Designation : ${employee.designation}\n` +
                `---------------------------------------\n`
            )
            .join("\n")}
        />
      )}

      <br />

      <button onClick={onBack}>Dashboard</button>
    </div>
  );
}

export default EmployeeDetails;

import { useEffect, useState } from "react";

function ReportGeneration({ onBack }) {
  const [logs, setLogs] = useState([]);
  const [message, setMessage] = useState("Loading login history...");

  useEffect(() => {
    async function loadLogs() {
      try {
        const response = await fetch(
          "http://localhost:8080/api/activity-logs"
        );

        if (!response.ok) {
          throw new Error("Failed to load login history");
        }

        const data = await response.json();

        setLogs(data);
        setMessage(
          data.length === 0 ? "No Login Activity Found." : ""
        );
      } catch (error) {
        setMessage("Could not load login history.");
        console.error(error);
      }
    }

    loadLogs();
  }, []);

  const reportText = logs
    .map(
      (log) =>
        `Log ID     : ${log.logId}\n` +
        `Username   : ${log.username}\n` +
        `Login Time : ${log.loginTime}\n` +
        `--------------------------------------\n`
    )
    .join("\n");

  return (
    <div>
      <h2>GENERATE REPORT</h2>

      {logs.length === 0 ? (
        <p>{message}</p>
      ) : (
        <textarea
          readOnly
          rows={10}
          cols={40}
          value={
            "========== LOGIN HISTORY ==========\n\n" +
            reportText
          }
        />
      )}

      <br />

      <button onClick={onBack}>Dashboard</button>
    </div>
  );
}

export default ReportGeneration;
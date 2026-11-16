
function Dashboard({  onCustomerRegistration, onRoomAvailability, onAllotRoom, onGenerateBill, onEmployeeDetails, onReportGeneration, onLogout }) {
  return (
    <div>
      <h2>Hotel Management</h2>

      <div>
        <button  onClick={onCustomerRegistration}>CUSTOMER REGISTRATION</button>
      </div>

      <br />

      <div>
        <button onClick={onRoomAvailability}>CHECK ROOM AVAILAIBILITY</button>
      </div>

      <br />

      <div>
        <button onClick={onAllotRoom}>ALLOT ROOM</button>
      </div>

      <br />

      <div>
        <button onClick={onGenerateBill}>GENERATE BILL</button>
      </div>

      <br />

      <div>
        <button onClick={onEmployeeDetails}>EMPLOYEE DETAILS</button>
      </div>

      <br />

      <div>
        <button onClick={onReportGeneration}>REPORT GENERATION</button>
      </div>

      <br />

      <button onClick={onLogout}>LogOut</button>
    </div>
  );
}

export default Dashboard;
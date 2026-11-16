/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmanagement.model;

/**
 *
 * @author Nived
 */

public class Employee {

    private int employeeId;
    private String employeeName;
    private String phone;
    private String designation;

    public Employee() {
    }

    public Employee(int employeeId, String employeeName, String phone,
                    String designation) {

        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.phone = phone;
        this.designation = designation;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return employeeName;
    }

}

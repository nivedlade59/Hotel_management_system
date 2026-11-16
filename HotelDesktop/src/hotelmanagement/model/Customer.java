/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmanagement.model;

/**
 *
 * @author Nived
 */

public class Customer {

    private int customerId;
    private String customerName;
    private String phone;
    private String idProof;
    private String address;
    private int days;

    public Customer() {
    }

    public Customer(int customerId,
                    String customerName,
                    String phone,
                    String idProof,
                    String address,
                    int days) {

        this.customerId = customerId;
        this.customerName = customerName;
        this.phone = phone;
        this.idProof = idProof;
        this.address = address;
        this.days = days;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdProof() {
        return idProof;
    }

    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return customerName;
    }
}
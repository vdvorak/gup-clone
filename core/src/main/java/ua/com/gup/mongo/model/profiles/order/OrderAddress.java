package ua.com.gup.mongo.model.profiles.order;

import ua.com.gup.mongo.model.enumeration.TransportCompany;

public class OrderAddress {
    String name;
    String address;
    String phoneNumber;
    TransportCompany transportCompany;

    public String getName() {
        return name;
    }

    public OrderAddress setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderAddress setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public OrderAddress setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public OrderAddress setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
        return this;
    }

    @Override
    public String toString() {
        return "OrderAddress{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", transportCompany=" + transportCompany +
                '}';
    }
}

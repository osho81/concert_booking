package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Address")
public class Address {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "address_id")
    private int addressId;
    @Basic
    @Column(name = "street")
    private String street;
    @Basic
    @Column(name = "house_no")
    private String houseNo;
    @Basic
    @Column(name = "postalcode")
    private String postalcode;
    @Basic
    @Column(name = "city")
    private String city;

    // Default constructor
    public Address() {
    }

    // Parameterized constructor
    public Address(String street, String houseNo, String postalcode, String city) {
        this.street = street;
        this.houseNo = houseNo;
        this.postalcode = postalcode;
        this.city = city;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return addressId == address.addressId && Objects.equals(street, address.street) && Objects.equals(houseNo, address.houseNo) && Objects.equals(postalcode, address.postalcode) && Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, street, houseNo, postalcode, city);
    }
}

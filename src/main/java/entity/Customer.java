package entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Customer")
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_id")
    private int customerId;
    @Basic
    @Column(name = "firstname")
    private String firstname;
    @Basic
    @Column(name = "lastname")
    private String lastname;
    @Basic
    @Column(name = "birthday")
    private Date birthday;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "customer_address")
    private Integer customerAddress;
    @ManyToOne
    @JoinColumn(name = "customer_address", referencedColumnName = "address_id", insertable = false, updatable = false)
    private Address addressByCustomerAddress;

    // Default constructor
    public Customer() {
    }

    // Parameterized constructor
    public Customer(String firstname, String lastname, Date birthday, String phone, Integer customerAddress) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.phone = phone;
        this.customerAddress = customerAddress;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(Integer customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId && Objects.equals(firstname, customer.firstname) && Objects.equals(lastname, customer.lastname) && Objects.equals(birthday, customer.birthday) && Objects.equals(phone, customer.phone) && Objects.equals(customerAddress, customer.customerAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstname, lastname, birthday, phone, customerAddress);
    }

    public Address getAddressByCustomerAddress() {
        return addressByCustomerAddress;
    }

    public void setAddressByCustomerAddress(Address addressByCustomerAddress) {
        this.addressByCustomerAddress = addressByCustomerAddress;
    }
}

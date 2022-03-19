package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Booking")
public class Booking {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "booking_id")
    private int bookingId;
    @Basic
    @Column(name = "concert")
    private Integer concert;
    @Basic
    @Column(name = "customer")
    private Integer customer;
    @Basic
    @Column(name = "number_of_tickets")
    private Integer numberOfTickets;

    // Default constructor
    public Booking() {
    }

    // Parameterized constructor
    public Booking(Integer concert, Integer customer, Integer numberOfTickets) {
        this.concert = concert;
        this.customer = customer;
        this.numberOfTickets = numberOfTickets;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getConcert() {
        return concert;
    }

    public void setConcert(Integer concert) {
        this.concert = concert;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return bookingId == booking.bookingId && Objects.equals(concert, booking.concert) && Objects.equals(customer, booking.customer) && Objects.equals(numberOfTickets, booking.numberOfTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, concert, customer, numberOfTickets);
    }
}

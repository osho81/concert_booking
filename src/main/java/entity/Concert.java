package entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Concert")
public class Concert {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "concert_id")
    private int concertId;
    @Basic
    @Column(name = "artist")
    private String artist;
    @Basic
    @Column(name = "concert_date")
    private Date concertDate;
    @Basic
    @Column(name = "ticket_price")
    private BigInteger ticketPrice;
    @Basic
    @Column(name = "concert_arena")
    private int concertArena;
    @ManyToOne
    @JoinColumn(name = "concert_arena", referencedColumnName = "arena_id", nullable = false, insertable = false, updatable = false)
    private Arena arenaByConcertArena; // Added insert=false, update=false

    // Default constructor
    public Concert() {
    }

    // Parameterized constructor
    public Concert(String artist, Date concertDate, BigInteger ticketPrice, int concertArena) {
        this.artist = artist;
        this.concertDate = concertDate;
        this.ticketPrice = ticketPrice;
        this.concertArena = concertArena;
    }

    public int getConcertId() {
        return concertId;
    }

    public void setConcertId(int concertId) {
        this.concertId = concertId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Date getConcertDate() {
        return concertDate;
    }

    public void setConcertDate(Date concertDate) {
        this.concertDate = concertDate;
    }

    public BigInteger getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigInteger ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getConcertArena() {
        return concertArena;
    }

    public void setConcertArena(int concertArena) {
        this.concertArena = concertArena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concert concert = (Concert) o;
        return concertId == concert.concertId && concertArena == concert.concertArena && Objects.equals(artist, concert.artist) && Objects.equals(concertDate, concert.concertDate) && Objects.equals(ticketPrice, concert.ticketPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(concertId, artist, concertDate, ticketPrice, concertArena);
    }

    public Arena getArenaByConcertArena() {
        return arenaByConcertArena;
    }

    public void setArenaByConcertArena(Arena arenaByConcertArena) {
        this.arenaByConcertArena = arenaByConcertArena;
    }
}

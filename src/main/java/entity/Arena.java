package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Arena")
public class Arena {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "arena_id")
    private int arenaId;
    @Basic
    @Column(name = "arena_name")
    private String arenaName;
    @Basic
    @Column(name = "arena_address")
    private Integer arenaAddress;
    @Basic
    @Column(name = "outside")
    private String outside;
    @ManyToOne
    @JoinColumn(name = "arena_address", referencedColumnName = "address_id", insertable = false, updatable = false)
    private Address addressByArenaAddress; // Added insert=false, update=false

    // Default constructor
    public Arena() {
    }

    // Parameterized constructor
    public Arena(String arenaName, Integer arenaAddress, String outside) {
        this.arenaName = arenaName;
        this.arenaAddress = arenaAddress;
        this.outside = outside;
    }

    public int getArenaId() {
        return arenaId;
    }

    public void setArenaId(int arenaId) {
        this.arenaId = arenaId;
    }

    public String getArenaName() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public Integer getArenaAddress() {
        return arenaAddress;
    }

    public void setArenaAddress(Integer arenaAddress) {
        this.arenaAddress = arenaAddress;
    }

    public Object getOutside() {
        return outside;
    }

    public void setOutside(String outside) {
        this.outside = outside;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arena arena = (Arena) o;
        return arenaId == arena.arenaId && Objects.equals(arenaName, arena.arenaName) && Objects.equals(arenaAddress, arena.arenaAddress) && Objects.equals(outside, arena.outside);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arenaId, arenaName, arenaAddress, outside);
    }

    public Address getAddressByArenaAddress() {
        return addressByArenaAddress;
    }

    public void setAddressByArenaAddress(Address addressByArenaAddress) {
        this.addressByArenaAddress = addressByArenaAddress;
    }
}

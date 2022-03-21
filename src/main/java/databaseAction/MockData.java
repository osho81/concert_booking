package databaseAction;

import entity.Address;
import entity.Arena;
import entity.Concert;
import entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// Class for creating mock data, if/when needed.

public class MockData {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    public void createMockData() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            /////----------- Creates 5 mock CUSTOMERS including ADDRESS -----------/////

            // Format and create date for customer birthdate
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date birthday1 = new java.sql.Date(dateFormat.parse("1970-01-01").getTime());

            // Create address for address table, to add to this customer (FK)
            Address address1 = new Address("Main street", "10", "12345", "London");
            entityManager.persist(address1);

            // Create customer, using created date/birthday and address
            Customer customer1 = new Customer("Donald", "Duck", birthday1, "0700777888", address1.getAddressId());
            entityManager.persist(customer1);

            java.sql.Date birthday2 = new java.sql.Date(dateFormat.parse("1975-08-01").getTime()); // Second customer
            Address address2 = new Address("First street", "20", "23456", "Paris");
            entityManager.persist(address2);
            Customer customer2 = new Customer("Mickey", "Mouse", birthday2, "0700555666", address2.getAddressId());
            entityManager.persist(customer2);

            java.sql.Date birthday3 = new java.sql.Date(dateFormat.parse("1978-10-01").getTime()); // Third customer (same address as 2)
            Customer customer3 = new Customer("Minnie", "Mouse", birthday3, "0700888666", address2.getAddressId());
            entityManager.persist(customer3);

            java.sql.Date birthday4 = new java.sql.Date(dateFormat.parse("1963-04-26").getTime()); // Fourth customer
            Address address3 = new Address("Second street", "40", "45678", "Beijing");
            entityManager.persist(address3);
            Customer customer4 = new Customer("Jet ", "Li", birthday4, "0700444333", address3.getAddressId());
            entityManager.persist(customer4);

            java.sql.Date birthday5 = new java.sql.Date(dateFormat.parse("1954-04-07").getTime()); // Fifth customer
            Address address4 = new Address("Cool street", "50", "56789", "Hong Kong");
            entityManager.persist(address4);
            Customer customer5 = new Customer("Jackie", "Chan", birthday5, "0700222555", address4.getAddressId());
            entityManager.persist(customer5);

            /////----------- Creates 5 mock ARENAS including ADDRESS -----------/////
            Address address5 = new Address("Some street", "5", "98765", "Cairo");
            entityManager.persist(address5);
            Arena arena1 = new Arena("Main arena", address5.getAddressId(), "indoor");
            entityManager.persist(arena1);

            Address address6 = new Address("Big street", "7", "87654", "Dubai");
            entityManager.persist(address6); // These two arenas have same address
            Arena arena2 = new Arena("Big arena", address6.getAddressId(), "indoor");
            entityManager.persist(arena2);
            Arena arena3 = new Arena("Amazing arena", address6.getAddressId(), "outdoor");
            entityManager.persist(arena3);

            Address address7 = new Address("Awsome street", "7", "76543", "Stockholm");
            entityManager.persist(address7); // These two arenas have same address
            Arena arena4 = new Arena("Fantastic arena", address7.getAddressId(), "outdoor");
            entityManager.persist(arena4);
            Arena arena5 = new Arena("Bombastic arena", address7.getAddressId(), "indoor");
            entityManager.persist(arena5);

            /////----------- Creates 5 mock CONCERTS -----------/////
            java.sql.Date concertDate1 = new java.sql.Date(dateFormat.parse("2023-02-02").getTime());
            Concert concert1 = new Concert("Micheal Jackson", concertDate1, BigInteger.valueOf(500), arena5.getArenaId());
            entityManager.persist(concert1);
            java.sql.Date concertDate2 = new java.sql.Date(dateFormat.parse("2022-03-03").getTime());
            Concert concert2 = new Concert("Metallica", concertDate2, BigInteger.valueOf(600), arena4.getArenaId());
            entityManager.persist(concert2);
            java.sql.Date concertDate3 = new java.sql.Date(dateFormat.parse("2022-10-04").getTime());
            Concert concert3 = new Concert("Assala Nasri", concertDate3, BigInteger.valueOf(700), arena3.getArenaId());
            entityManager.persist(concert3);
            java.sql.Date concertDate4 = new java.sql.Date(dateFormat.parse("2022-03-15").getTime());
            Concert concert4 = new Concert("Kadim Al Sahir", concertDate4, BigInteger.valueOf(800), arena2.getArenaId());
            entityManager.persist(concert4);
            java.sql.Date concertDate5 = new java.sql.Date(dateFormat.parse("2023-01-01").getTime());
            Concert concert5 = new Concert("Cheb Khaled", concertDate5, BigInteger.valueOf(900), arena1.getArenaId());
            entityManager.persist(concert5);


            transaction.commit();

        } catch (ParseException e) { // This catch is related to parse date
            e.printStackTrace();

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }


    }
}

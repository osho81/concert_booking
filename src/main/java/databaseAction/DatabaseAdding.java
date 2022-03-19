package databaseAction;

import appLogic.Main;
import entity.*;
import javax.persistence.*;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/*
 * Class for creating/inserting records/rows into db tables, via Hibernate/entityManager
 * Only customer and concerts can be added here
 * Bookings are made in own class
 * Address and Arena are created either in mock data or indirectly while creating customer
 */

public class DatabaseAdding {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    public void addData() {
        System.out.println("What to add:");
        System.out.println("1. Booking\n2. Customer");
        int userChoice = Main.validateUserIntegerChoice(2);
        if (userChoice == 1)
            addConcert();
        else if (userChoice == 2)
            addCustomer();
    }

    public void addCustomer() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // User choose personal information
            System.out.println("Enter first name");
            String chosenFirstName = Main.scan.next();
            System.out.println("Enter last name");
            String chosenLastName = Main.scan.next();
            System.out.println("Enter phone number");
            String chosenPhoneNo = Main.scan.next();

            // Format and create date for customer birthdate
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("Enter birthdate (yyyy-MM-dd)");
            String chosenBirthdate = Main.scan.next(); // Parse this date input into chosen format
            java.sql.Date birthday = new java.sql.Date(dateFormat.parse(chosenBirthdate).getTime());

            // Create address for address table, to add to this customer (FK)
            System.out.println("Enter street name");
            String chosenStreet = Main.scan.next();
            System.out.println("Enter house no");
            String chosenHouseNo = Main.scan.next();
            System.out.println("Enter postal code");
            String chosenPostalCode = Main.scan.next();
            System.out.println("Enter city name");
            String chosenCity = Main.scan.next();
            chosenCity += Main.scan.nextLine();

            Address address = new Address(chosenStreet, chosenHouseNo, chosenPostalCode, chosenCity);
            entityManager.persist(address);

            // Create customer, using user input info, created address and date/birthday
            Customer customer = new Customer(chosenFirstName, chosenLastName, birthday, chosenPhoneNo, address.getAddressId());
            entityManager.persist(customer);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

    }

    public void addConcert() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // User choose concert information
            System.out.println("\nEnter concert artist");
            String chosenArtist = Main.scan.next();
            System.out.println("Enter ticket price");
            int chosenPrice = Main.scan.nextInt();
            System.out.println("Enter arena name");
            String chosenArenaName = Main.scan.next();
            chosenArenaName += Main.scan.nextLine(); // Include full line

            // Find arena ID by chosen arena name
            Query query = entityManager.createNativeQuery("SELECT * FROM Arena a WHERE a.arena_name = ?1");
            query.setParameter(1, chosenArenaName);
            List<Object[]> arenaObject = query.getResultList(); // List of arena-objects with provided arena name
            int chosenArenaID = (int) arenaObject.get(0)[0]; // Access any of the arenas (refer to same arena ID)

            // Format and create date for customer birthdate
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("\nEnter concert date (yyyy-MM-dd)");
            String chosenConcertDate = Main.scan.next(); // Parse this date input into chosen format
            java.sql.Date concertDate = new java.sql.Date(dateFormat.parse(chosenConcertDate).getTime());

            // Create concert, using user chosen information, found arena ID (fk), and the created concert date
            Concert concert = new Concert(chosenArtist, concertDate, BigInteger.valueOf(chosenPrice), chosenArenaID);
            entityManager.persist(concert);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

    }

}


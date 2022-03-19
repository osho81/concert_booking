package DatabaseAction;

import appLogic.Main;
import entity.Booking;
import entity.Concert;
import entity.Customer;
import javax.persistence.*;
import java.util.List;

// Class for managing booking related db actions via Hibernate/entityManager

public class DatabaseBooking {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    public void bookConcert() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Retrieve and display available concerts
            System.out.println("\nLooking fo concerts anywhere or in a specific city?");
            System.out.println("1. Anywhere\n2. Specific city");
            int chosenScope = Main.scan.nextInt();

            if (chosenScope == 1) {
                // Retrieve all concerts with "native sql" using entityManager
                Query query = entityManager.createNativeQuery("SELECT * FROM Concert c");
                List<Object[]> concertObject = query.getResultList(); // Could also use toString in entity-classes
                for (Object[] c : concertObject)
                    System.out.println(c[0] + "\t" + c[1] + "\t\t" + c[2] + "\t" + c[3] + "\t" + c[4]);

            } else if (chosenScope == 2) {
                // Retrieve all concerts in a specific city
                System.out.println("\nEnter city name");
                String chosenCity = Main.scan.next();
                Query query = entityManager.createNativeQuery("SELECT * FROM Concert c JOIN Arena a on c.concert_arena = a.arena_id " +
                        "JOIN Address ad on a.arena_address = ad.address_id WHERE ad.city = :p");
                query.setParameter("p", chosenCity); // "Placeholder"
                List<Object[]> concertObject = query.getResultList(); // Could also use toString in entity-classes
                for (Object[] c : concertObject)
                    System.out.println(c[0] + "\t" + c[1] + "\t\t" + c[2] + "\t" + c[3] + "\t" + c[4]);
            } else {
                System.out.println("Invalid choice");
                bookConcert();
            }

            // User can choose to book one of the concerts displayed
            System.out.println("\nEnter concert ID from the list to book a ticket\nOr press 0 to go back to menu");
            int chosenConcertID = Main.scan.nextInt();
            if (chosenConcertID == 0) {
                System.out.println("Going back to main menu");
                Main.menuLogic();
            } else {
                // Input customer ID
                System.out.println("\nEnter custoemr ID");
                int chosenCustomerID = Main.scan.nextInt();

                // Find customer & concert with the chosen IDs
                Customer chosenCustomer = entityManager.find(Customer.class, chosenCustomerID);
                Concert chosenConcert = entityManager.find(Concert.class, chosenConcertID);

                System.out.println("\nHow many tickets do you wish to book? (max 10)");
                int chosenNumOfTickets = Main.validateUserIntegerChoice(10);

                // Create current booking
                Booking booking = new Booking(chosenConcert.getConcertId(), chosenCustomer.getCustomerId(), chosenNumOfTickets);
                entityManager.persist(booking);
            }

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

package databaseAction;

import appLogic.Main;
import javax.persistence.*;
import java.util.List;

// Class for selecting rows or tables to display, via Hibernate/entityManager

public class DatabaseDisplay {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    public void displayData() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            System.out.println("Which table to display data from?");
            System.out.println("1. Booking\n2. Customer\n3. Concert\n4. Arena\n5. Address");
            int userChoice = Main.validateUserIntegerChoice(5);
            int rowID;
            switch (userChoice) {
                case 1 -> {
                    System.out.println("Enter booking-ID for the booking to be displayed\nOR enter zero (0) to display all bookings");
                    rowID = Main.scan.nextInt();
                    if (rowID == 0) {
                        Query query = entityManager.createNativeQuery("SELECT * FROM Booking b");
                        List<Object[]> bookingObject = query.getResultList();
                        if (bookingObject.size() == 0) {
                            System.out.println("There are no bookings in the database");
                        } else {
                            for (Object[] c : bookingObject)
                                System.out.println(c[0] + "\t" + c[1] + "\t\t" + c[2] + "\t" + c[3]);
                        }
                    } else {
                        Query query = entityManager.createNativeQuery("SELECT * FROM Booking b WHERE b.booking_id = :id");
                        query.setParameter("id", rowID);
                        List<Object[]> bookingObject = query.getResultList();
                        if (bookingObject.size() == 0) {
                            System.out.println("No customer with id " + rowID);
                        } else {
                            for (Object[] c : bookingObject)
                                System.out.println(c[0] + "\t" + c[1] + "\t\t" + c[2] + "\t" + c[3]);
                        }
                    }
                }
                case 2 -> {
                    System.out.println("Enter customer-ID for the customer to be displayed\nOR enter zero (0) to display all customers");
                    rowID = Main.scan.nextInt();
                    if (rowID == 0) {
                        Query query = entityManager.createNativeQuery("SELECT * FROM Customer c");
                        List<Object[]> customerObject = query.getResultList();
                        if (customerObject.size() == 0) {
                            System.out.println("There are no customers in the database");
                        } else {
                            for (Object[] c : customerObject)
                                System.out.println(c[0] + "\t" + c[1] + "\t\t" + c[2] + "\t" + c[3] + "\t" + c[4] + "\t" + c[5]);
                        }
                    } else {
                        Query query = entityManager.createNativeQuery("SELECT * FROM Customer c WHERE c.customer_id = :id");
                        query.setParameter("id", rowID);
                        List<Object[]> customerObject = query.getResultList();
                        if (customerObject.size() == 0) {
                            System.out.println("No customer with id " + rowID);
                        } else {
                            for (Object[] c : customerObject)
                                System.out.println(c[0] + "\t" + c[1] + "\t\t" + c[2] + "\t" + c[3] + "\t" + c[4] + "\t" + c[5]);
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Enter concert-ID for the concert to be displayed\nOR enter zero (0) to display all concerts");
                    rowID = Main.scan.nextInt();
                    if (rowID == 0) {
                        Query query = entityManager.createNativeQuery("SELECT * FROM Concert c");
                        List<Object[]> concertObject = query.getResultList();
                        if (concertObject.size() == 0) {
                            System.out.println("There are no concerts in the database");
                        } else {
                            for (Object[] c : concertObject)
                                System.out.println(c[0] + "\t" + c[1] + "\t\t" + c[2] + "\t" + c[3] + "\t" + c[4]);
                        }
                    } else {
                        Query query = entityManager.createNativeQuery("SELECT * FROM Concert c WHERE c.concert_id = :id");
                        query.setParameter("id", rowID);
                        List<Object[]> concertObject = query.getResultList();
                        if (concertObject.size() == 0) {
                            System.out.println("No concert with id " + rowID);
                        } else {
                            for (Object[] c : concertObject)
                                System.out.println(c[0] + "\t" + c[1] + "\t\t" + c[2] + "\t" + c[3] + "\t" + c[4]);
                        }
                    }
                }
                case 4 -> {
                    System.out.println("Enter arena-ID for the arena to be displayed\nOR enter zero (0) to display all arenas");
                    rowID = Main.scan.nextInt();
                    if (rowID == 0) {
                        Query query = entityManager.createNativeQuery("SELECT * FROM Arena c");
                        List<Object[]> arenaObject = query.getResultList();
                        if (arenaObject.size() == 0) {
                            System.out.println("There are no arenas in the database");
                        } else {
                            for (Object[] c : arenaObject)
                                System.out.println(c[0] + "\t" + c[1] + "\t\t" + c[2] + "\t" + c[3]);
                        }
                    } else {
                        Query query = entityManager.createNativeQuery("SELECT * FROM Arena a WHERE a.arena_id = :id");
                        query.setParameter("id", rowID);
                        List<Object[]> arenaObject = query.getResultList();
                        if (arenaObject.size() == 0) {
                            System.out.println("No arena with id " + rowID);
                        } else {
                            for (Object[] c : arenaObject)
                                System.out.println(c[0] + "\t" + c[1] + "\t\t" + c[2] + "\t" + c[3]);
                        }
                    }
                }
                case 5 -> {
                    System.out.println("Enter address-ID for the address to be displayed\nOR enter zero (0) to display all addresses");
                    rowID = Main.scan.nextInt();
                    if (rowID == 0) {
                        Query query = entityManager.createNativeQuery("SELECT * FROM Address a");
                        List<Object[]> addressObject = query.getResultList();
                        if (addressObject.size() == 0) {
                            System.out.println("There are no addresses in the database");
                        } else {
                            for (Object[] c : addressObject)
                                System.out.println(c[0] + "\t" + c[1] + "\t\t" + c[2] + "\t" + c[3] + "\t\t" + c[4]);
                        }
                    } else {
                        Query query = entityManager.createNativeQuery("SELECT * FROM Address a WHERE a.address_id = :id");
                        query.setParameter("id", rowID);
                        List<Object[]> addressObject = query.getResultList();
                        if (addressObject.size() == 0) {
                            System.out.println("No address with id " + rowID);
                        } else {
                            for (Object[] c : addressObject)
                                System.out.println(c[0] + "\t" + c[1] + "\t\t" + c[2] + "\t" + c[3] + "\t\t" + c[4]);
                        }
                    }
                }
                default -> System.out.println("Going back to main menu");
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

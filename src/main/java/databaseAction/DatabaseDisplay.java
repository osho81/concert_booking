package databaseAction;

import appLogic.Main;
import entity.Concert;
import entity.Customer;

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
                    System.out.println("1. Search by booking-ID\n2. Search by customer-ID\n3. Search by concert-ID\nOR enter zero (0) to display all bookings");
                    int searchBy = Main.scan.nextInt();
                    if (searchBy == 0) { // Display all
                        Query query = entityManager.createNativeQuery("SELECT * FROM Booking b");
                        List<Object[]> bookingObject = query.getResultList();
                        if (bookingObject.size() == 0) {
                            System.out.println("There are no bookings in the database");
                        } else {
                            String header = String.format("%-5s%-12s%-20s%-14s%-20s%-14s", "ID", "Concert-ID", "Artist", "Customer-ID", "Customer name", "No of tickets");
                            System.out.println(header);
                            Concert tempConcert;
                            Customer tempCustomer;
                            for (Object[] b : bookingObject) {
                                tempConcert = entityManager.find(Concert.class, b[1]); // Find booked concert
                                tempCustomer = entityManager.find(Customer.class, b[2]); // Find booking customer
                                String currentRow = String.format("%-5s%-12s%-20s%-14s%-20s%-14s", b[0], b[1], tempConcert.getArtist(),
                                        b[2], tempCustomer.getFirstname() + " " + tempCustomer.getLastname(), b[3]);
                                System.out.println(currentRow);
                            }
                        }
                    } else if (searchBy == 1) {
                        System.out.println("Enter booking-ID for the booking to be displayed");
                        rowID = Main.scan.nextInt();
                        Query query = entityManager.createNativeQuery("SELECT * FROM Booking b WHERE b.booking_id = :id");
                        query.setParameter("id", rowID);
                        List<Object[]> bookingObject = query.getResultList();
                        if (bookingObject.size() == 0) {
                            System.out.println("No booking with id " + rowID);
                        } else {
                            String header = String.format("%-5s%-12s%-20s%-14s%-20s%-14s", "ID", "Concert-ID", "Artist", "Customer-ID", "Customer name", "No of tickets");
                            System.out.println(header);
                            Concert tempConcert;
                            Customer tempCustomer;
                            for (Object[] b : bookingObject) {
                                tempConcert = entityManager.find(Concert.class, b[1]);
                                tempCustomer = entityManager.find(Customer.class, b[2]);
                                String currentRow = String.format("%-5s%-12s%-20s%-14s%-20s%-14s", b[0], b[1], tempConcert.getArtist(),
                                        b[2], tempCustomer.getFirstname() + " " + tempCustomer.getLastname(), b[3]);
                                System.out.println(currentRow);
                            }
                        }
                    } else if (searchBy == 2) {
                        System.out.println("Enter customer-ID for the booking(s) to be displayed");
                        rowID = Main.scan.nextInt();
                        Query query = entityManager.createNativeQuery("SELECT * FROM Booking b WHERE b.customer = :id");
                        query.setParameter("id", rowID);
                        List<Object[]> bookingObject = query.getResultList();
                        if (bookingObject.size() == 0) {
                            System.out.println("No booking with customer-id " + rowID);
                        } else {
                            String header = String.format("%-5s%-12s%-20s%-14s%-20s%-14s", "ID", "Concert-ID", "Artist", "Customer-ID", "Customer name", "No of tickets");
                            System.out.println(header);
                            Concert tempConcert;
                            Customer tempCustomer;
                            for (Object[] b : bookingObject) {
                                tempConcert = entityManager.find(Concert.class, b[1]);
                                tempCustomer = entityManager.find(Customer.class, b[2]);
                                String currentRow = String.format("%-5s%-12s%-20s%-14s%-20s%-14s", b[0], b[1], tempConcert.getArtist(),
                                        b[2], tempCustomer.getFirstname() + " " + tempCustomer.getLastname(), b[3]);
                                System.out.println(currentRow);
                            }
                        }
                    }  else if (searchBy == 3) {
                        System.out.println("Enter concert-ID for the booking(s) to be displayed");
                        rowID = Main.scan.nextInt();
                        Query query = entityManager.createNativeQuery("SELECT * FROM Booking b WHERE b.concert = :id");
                        query.setParameter("id", rowID);
                        List<Object[]> bookingObject = query.getResultList();
                        if (bookingObject.size() == 0) {
                            System.out.println("No booking with concert-id " + rowID);
                        } else {
                            String header = String.format("%-5s%-12s%-20s%-14s%-20s%-14s", "ID", "Concert-ID", "Artist", "Customer-ID", "Customer name", "No of tickets");
                            System.out.println(header);
                            Concert tempConcert;
                            Customer tempCustomer;
                            for (Object[] b : bookingObject) {
                                tempConcert = entityManager.find(Concert.class, b[1]);
                                tempCustomer = entityManager.find(Customer.class, b[2]);
                                String currentRow = String.format("%-5s%-12s%-20s%-14s%-20s%-14s", b[0], b[1], tempConcert.getArtist(),
                                        b[2], tempCustomer.getFirstname() + " " + tempCustomer.getLastname(), b[3]);
                                System.out.println(currentRow);
                            }
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
                            String header = String.format("%-5s%-20s%-14s%-14s%-5s", "ID", "Name", "Birthday", "Phone", "Address-ID");
                            System.out.println(header);
                            for (Object[] c : customerObject) {
                                String currentRow = String.format("%-5s%-20s%-14s%-14s%-5s", c[0], c[1] + " " + c[2], c[3], c[4], c[5]);
                                System.out.println(currentRow);
                            }
                        }
                    } else {
                        Query query = entityManager.createNativeQuery("SELECT * FROM Customer c WHERE c.customer_id = :id");
                        query.setParameter("id", rowID);
                        List<Object[]> customerObject = query.getResultList();
                        if (customerObject.size() == 0) {
                            System.out.println("No customer with id " + rowID);
                        } else {
                            String header = String.format("%-5s%-20s%-14s%-14s%-5s", "ID", "Name", "Birthday", "Phone", "Address-ID");
                            System.out.println(header);
                            for (Object[] c : customerObject) {
                                String currentRow = String.format("%-5s%-20s%-14s%-14s%-5s", c[0], c[1] + " " + c[2], c[3], c[4], c[5]);
                                System.out.println(currentRow);
                            }
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
                            String header = String.format("%-5s%-20s%-14s%-12s%-5s", "ID", "Artist", "Date", "Price", "Arena-ID");
                            System.out.println(header);
                            for (Object[] c : concertObject) {
                                String currentRow = String.format("%-5s%-20s%-14s%-12s%-5s", c[0], c[1], c[2], c[3], c[4]);
                                System.out.println(currentRow);
                            }
                        }
                    } else {
                        Query query = entityManager.createNativeQuery("SELECT * FROM Concert c WHERE c.concert_id = :id");
                        query.setParameter("id", rowID);
                        List<Object[]> concertObject = query.getResultList();
                        if (concertObject.size() == 0) {
                            System.out.println("No concert with id " + rowID);
                        } else {
                            String header = String.format("%-5s%-20s%-14s%-12s%-5s", "ID", "Artist", "Date", "Price", "Arena-ID");
                            System.out.println(header);
                            for (Object[] c : concertObject) {
                                String currentRow = String.format("%-5s%-20s%-14s%-12s%-5s", c[0], c[1], c[2], c[3], c[4]);
                                System.out.println(currentRow);
                            }
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
                            String header = String.format("%-5s%-20s%-12s%-12s", "ID", "Arena name", "Address-ID", "In/Outdoor");
                            System.out.println(header);
                            for (Object[] a : arenaObject) {
                                String currentRow = String.format("%-5s%-20s%-12s%-12s", a[0], a[1], a[2], a[3]);
                                System.out.println(currentRow);
                            }
                        }
                    } else {
                        Query query = entityManager.createNativeQuery("SELECT * FROM Arena a WHERE a.arena_id = :id");
                        query.setParameter("id", rowID);
                        List<Object[]> arenaObject = query.getResultList();
                        if (arenaObject.size() == 0) {
                            System.out.println("No arena with id " + rowID);
                        } else {
                            String header = String.format("%-5s%-20s%-12s%-12s", "ID", "Arena name", "Address-ID", "In/Outdoor");
                            System.out.println(header);
                            for (Object[] a : arenaObject) {
                                String currentRow = String.format("%-5s%-20s%-12s%-12s", a[0], a[1], a[2], a[3]);
                                System.out.println(currentRow);
                            }
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
                            String header = String.format("%-5s%-20s%-8s%-14s%-20s", "ID", "Street", "No", "Postal code", "City");
                            System.out.println(header);
                            for (Object[] a : addressObject) {
                                String currentRow = String.format("%-5s%-20s%-8s%-14s%-20s", a[0], a[1], a[2], a[3], a[4]);
                                System.out.println(currentRow);
                            }
                        }
                    } else {
                        Query query = entityManager.createNativeQuery("SELECT * FROM Address a WHERE a.address_id = :id");
                        query.setParameter("id", rowID);
                        List<Object[]> addressObject = query.getResultList();
                        if (addressObject.size() == 0) {
                            System.out.println("No address with id " + rowID);
                        } else {
                            String header = String.format("%-5s%-20s%-8s%-14s%-20s", "ID", "Street", "No", "Postal code", "City");
                            System.out.println(header);
                            for (Object[] a : addressObject) {
                                String currentRow = String.format("%-5s%-20s%-8s%-14s%-20s", a[0], a[1], a[2], a[3], a[4]);
                                System.out.println(currentRow);
                            }
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

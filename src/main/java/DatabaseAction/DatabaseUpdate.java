package DatabaseAction;

import appLogic.Main;
import entity.*;
import javax.persistence.*;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

// Class for updating db tables, via Hibernate/entityManager

public class DatabaseUpdate {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    public void updateData() {
        System.out.println("What to edit/update:");
        System.out.println("1. Booking\n2. Customer\n3. Concert\n4. Arena\n5. Address");
        int userChoice = Main.validateUserIntegerChoice(5);
        switch (userChoice) {
            case 1 -> updateBooking();
            case 2 -> updateCustomer();
            case 3 -> updateConcert();
            case 4 -> updateArena();
            case 5 -> updateAddress();
        }
    }

    private void updateBooking() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            System.out.println("Enter booking-ID of the booking to be edited");
            int chosenBookingID = Main.scan.nextInt();
            Booking chosenBooking = entityManager.find(Booking.class, chosenBookingID);

            System.out.println("Which part to edit?");
            System.out.println("\n1. Concert\n2. Customer\n3. Number of tickets");
            int userChoice = Main.validateUserIntegerChoice(3);
            int tempID;
            switch (userChoice) {
                case 1 -> { // for the chosen booking
                    System.out.println("Current concert-ID is " + chosenBooking.getConcert() + " for the chosen booking");
                    System.out.println("Enter new concert-ID");
                    tempID = Main.scan.nextInt();
                    chosenBooking.setConcert(tempID);
                }
                case 2 -> {
                    System.out.println("Current customer-ID is " + chosenBooking.getCustomer() + " for the chosen booking");
                    System.out.println("Enter new customer-ID");
                    tempID = Main.scan.nextInt();
                    chosenBooking.setCustomer(tempID);
                }
                case 3 -> {
                    System.out.println("Current number of tickets is " + chosenBooking.getNumberOfTickets() + " for the chosen booking");
                    System.out.println("Enter new number of tickets");
                    tempID = Main.validateUserIntegerChoice(10);
                    chosenBooking.setNumberOfTickets(tempID);
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

    private void updateCustomer() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            System.out.println("Enter customer-ID of the booking to be edited");
            int chosenCustomerID = Main.scan.nextInt();
            Customer chosenCustomer = entityManager.find(Customer.class, chosenCustomerID);

            System.out.println("Which part to edit?");
            System.out.println("1. First name\n2. Last name\n3. Birthday\n4. Phone no\n5. Address");
            int userChoice = Main.validateUserIntegerChoice(5);
            String tempStr;
            switch (userChoice) {
                case 1 -> {
                    System.out.println("Current first name is " + chosenCustomer.getFirstname() + " for the chosen customer");
                    System.out.println("Enter new first name");
                    tempStr = Main.scan.next();
                    chosenCustomer.setFirstname(tempStr);
                }
                case 2 -> {
                    System.out.println("Current last name is " + chosenCustomer.getLastname() + " for the chosen customer");
                    System.out.println("Enter new last name");
                    tempStr = Main.scan.next();
                    chosenCustomer.setLastname(tempStr);
                }
                case 3 -> {
                    System.out.println("Current birthday is " + chosenCustomer.getBirthday() + " for the chosen customer");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    System.out.println("Enter new birthdate (yyyy-MM-dd)");
                    String chosenBirthday = Main.scan.next(); // Parse this date input into chosen format
                    java.sql.Date newBirthday = new java.sql.Date(dateFormat.parse(chosenBirthday).getTime());
                    chosenCustomer.setBirthday(newBirthday);
                }
                case 4 -> {
                    System.out.println("Current phone number is " + chosenCustomer.getPhone() + " for the chosen customer");
                    System.out.println("Enter new phone number");
                    tempStr = Main.scan.next();
                    chosenCustomer.setPhone(tempStr);
                }
                case 5 -> {
                    System.out.println("Current address is " + chosenCustomer.getCustomerAddress() + " for the chosen customer");
                    System.out.println("Enter new address-ID");
                    int tempID = Main.scan.nextInt();
                    chosenCustomer.setCustomerAddress(tempID);
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

    private void updateConcert() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            System.out.println("Enter concert-ID of the concert to be edited");
            int chosenConcertID = Main.scan.nextInt();
            Concert chosenConcert = entityManager.find(Concert.class, chosenConcertID);

            System.out.println("Which part to edit?");
            System.out.println("1. Artist name\n2. Concert date\n3. Ticket price\n4. Concert arena");
            int userChoice = Main.validateUserIntegerChoice(4);
            String tempStr;
            int tempID;
            switch (userChoice) {
                case 1 -> {
                    System.out.println("Current artist name is " + chosenConcert.getArtist() + " for the chosen concert");
                    System.out.println("Enter new artist name");
                    tempStr = Main.scan.next();
                    chosenConcert.setArtist(tempStr);
                }
                case 2 -> {
                    System.out.println("Current concert date is " + chosenConcert.getConcertDate() + " for the chosen concert");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    System.out.println("Enter new concert date (yyyy-MM-dd)");
                    String chosenConcertDate = Main.scan.next(); // Parse this date input into chosen format
                    java.sql.Date newConcertDate = new java.sql.Date(dateFormat.parse(chosenConcertDate).getTime());
                    chosenConcert.setConcertDate(newConcertDate);
                }
                case 3 -> {
                    System.out.println("Current ticket price is " + chosenConcert.getTicketPrice() + " for the chosen concert");
                    System.out.println("Enter new ticket price");
                    tempID = Main.scan.nextInt();
                    chosenConcert.setTicketPrice(BigInteger.valueOf(tempID));
                }
                case 4 -> {
                    System.out.println("Current concert arena-ID is " + chosenConcert.getConcertArena() + " for the chosen concert");
                    System.out.println("Enter new concert arena-ID");
                    tempID = Main.scan.nextInt();
                    chosenConcert.setConcertArena(tempID);
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

    private void updateArena() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            System.out.println("Enter arena-ID of the arena to be edited");
            int chosenArenaID = Main.scan.nextInt();
            Arena chosenArena = entityManager.find(Arena.class, chosenArenaID);

            System.out.println("Which part to edit?");
            System.out.println("1. Arena name\n2. Address-ID\n3. If is an outside arena");
            int userChoice = Main.validateUserIntegerChoice(3);
            switch (userChoice) {
                case 1 -> {
                    System.out.println("Current arena name is " + chosenArena.getArenaName() + " for the chosen arena");
                    System.out.println("Enter new arena name");
                    String tempStr = Main.scan.next();
                    chosenArena.setArenaName(tempStr);
                }
                case 2 -> {
                    System.out.println("Current arena address-ID is " + chosenArena.getArenaAddress() + " for the chosen arena");
                    System.out.println("Enter new arena address-ID");
                    int tempID = Main.scan.nextInt();
                    chosenArena.setArenaAddress(tempID);
                }
                case 3 -> {
                    System.out.println("Is an outside arena is currently set to  \"" + chosenArena.getOutside() + "\"");
                    System.out.println("Enter new answer (only \"yes\" or \"no\" are possible)");
                    String tempStr = Main.scan.next();
                    chosenArena.setOutside(tempStr);
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

    private void updateAddress() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            System.out.println("Enter address-ID of the address to be edited");
            int chosenAddressID = Main.scan.nextInt();
            Address chosenAddress= entityManager.find(Address.class, chosenAddressID);

            System.out.println("Which part to edit?");
            System.out.println("1. Street name\n2. House no\n3. Postal code\n4. City name");
            int userChoice = Main.validateUserIntegerChoice(4);
            String tempStr;
            switch (userChoice) {
                case 1 -> {
                    System.out.println("Current street name is " + chosenAddress.getStreet() + " for the chosen address");
                    System.out.println("Enter new street name");
                    tempStr = Main.scan.next();
                    chosenAddress.setStreet(tempStr);
                }
                case 2 -> {
                    System.out.println("Current house no is " + chosenAddress.getHouseNo() + " for the chosen address");
                    System.out.println("Enter new house no");
                    tempStr = Main.scan.next();
                    chosenAddress.setHouseNo(tempStr);
                }
                case 3 -> {
                    System.out.println("Current postal code is " + chosenAddress.getPostalcode() + " for the chosen address");
                    System.out.println("Enter new postal code");
                    tempStr = Main.scan.next();
                    chosenAddress.setPostalcode(tempStr);
                }
                case 4 -> {
                    System.out.println("Current city name is " + chosenAddress.getCity() + " for the chosen address");
                    System.out.println("Enter new city name");
                    tempStr = Main.scan.next();
                    chosenAddress.setCity(tempStr);
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

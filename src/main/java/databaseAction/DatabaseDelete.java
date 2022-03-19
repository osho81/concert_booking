package databaseAction;

import appLogic.Main;
import javax.persistence.*;

// Class for db deletion or data-emptying actions, via Hibernate/entityManager

public class DatabaseDelete {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    public void deleteData() {
        System.out.println("What to edit/update:");
        System.out.println("1. Remove record/row\n2. Empty/truncate table");
        int userChoice = Main.validateUserIntegerChoice(2);
        switch (userChoice) {
            case 1 -> removeRecord();
            case 2 -> emptyTables();
        }
    }

    private void removeRecord() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            System.out.println("Which table to delete from?");
            System.out.println("1. Booking\n2. Customer\n3. Concert\n4. Arena\n5. Address");
            int userChoice = Main.validateUserIntegerChoice(5);
            int rowID;
            switch (userChoice) {
                case 1 -> {
                    System.out.println("Enter booking-ID for the booking to be deleted");
                    rowID = Main.scan.nextInt();
                    Query query = entityManager.createNativeQuery("DELETE FROM Booking b WHERE b.booking_id = :id");
                    query.setParameter("id", rowID).executeUpdate();
                }
                case 2 -> {
                    System.out.println("Enter customer-ID for the customer to be deleted");
                    rowID = Main.scan.nextInt();
                    Query query = entityManager.createNativeQuery("DELETE FROM Customer c WHERE c.customer_id = :id");
                    query.setParameter("id", rowID).executeUpdate();
                }
                case 3 -> {
                    System.out.println("Enter concert-ID for the concert to be deleted");
                    rowID = Main.scan.nextInt();
                    Query query = entityManager.createNativeQuery("DELETE FROM Concert c WHERE c.concert_id = :id");
                    query.setParameter("id", rowID).executeUpdate();
                }
                case 4 -> {
                    System.out.println("Enter arena-ID for the arena to be deleted");
                    rowID = Main.scan.nextInt();
                    Query query = entityManager.createNativeQuery("DELETE FROM Arena a WHERE a.arena_id = :id");
                    query.setParameter("id", rowID).executeUpdate();
                }
                case 5 -> {
                    System.out.println("Enter address-ID for the address to be deleted");
                    rowID = Main.scan.nextInt();
                    Query query = entityManager.createNativeQuery("DELETE FROM Address a WHERE a.address_id = :id");
                    query.setParameter("id", rowID).executeUpdate();
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

    private void emptyTables() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            System.out.println("Which table to empty/truncate?");
            System.out.println("1. Booking\n2. Customer\n3. Concert\n4. Arena\n5. Address\n6. All tables (Warning: all db content will be removed)");
            int userChoice = Main.validateUserIntegerChoice(6);
            switch (userChoice) {
                case 1 -> {
                    Query disableConstraint = entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0");
                    disableConstraint.executeUpdate();
                    Query query = entityManager.createNativeQuery("TRUNCATE TABLE Booking");
                    query.executeUpdate();
                    Query enableConstraint = entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1");
                    enableConstraint.executeUpdate();
                }
                case 2 -> {
                    Query disableConstraint = entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0");
                    disableConstraint.executeUpdate();
                    Query query = entityManager.createNativeQuery("TRUNCATE TABLE Customer");
                    query.executeUpdate();
                    Query enableConstraint = entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1");
                    enableConstraint.executeUpdate();
                }
                case 3 -> {
                    Query disableConstraint = entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0");
                    disableConstraint.executeUpdate();
                    Query query = entityManager.createNativeQuery("TRUNCATE TABLE Concert");
                    query.executeUpdate();
                    Query enableConstraint = entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1");
                    enableConstraint.executeUpdate();
                }
                case 4 -> {
                    Query disableConstraint = entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0");
                    disableConstraint.executeUpdate();
                    Query query = entityManager.createNativeQuery("TRUNCATE TABLE Arena");
                    query.executeUpdate();
                    Query enableConstraint = entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1");
                    enableConstraint.executeUpdate();
                }
                case 5 -> {
                    Query disableConstraint = entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0");
                    disableConstraint.executeUpdate();
                    Query query = entityManager.createNativeQuery("TRUNCATE TABLE Address");
                    query.executeUpdate();
                    Query enableConstraint = entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1");
                    enableConstraint.executeUpdate();
                }
                case 6 -> {
                    Query disableConstraint = entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0");
                    disableConstraint.executeUpdate();
                    Query query1 = entityManager.createNativeQuery("TRUNCATE TABLE Booking");
                    query1.executeUpdate();
                    Query query2 = entityManager.createNativeQuery("TRUNCATE TABLE Customer");
                    query2.executeUpdate();
                    Query query3 = entityManager.createNativeQuery("TRUNCATE TABLE Concert");
                    query3.executeUpdate();
                    Query query4 = entityManager.createNativeQuery("TRUNCATE TABLE Arena");
                    query4.executeUpdate();
                    Query query5 = entityManager.createNativeQuery("TRUNCATE TABLE Address");
                    query5.executeUpdate();
                    Query enableConstraint = entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1");
                    enableConstraint.executeUpdate();
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

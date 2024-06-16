
import enums.UserType;
import services.UserAuthentication;
import controllers.BidManagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the bidding system!");
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    loginMenu();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nUser Registration:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (UserAuthentication.loginUser(username)) {
            System.out.println("User already exists. Please choose another username.");
            return;
        }
        System.out.println("Select user type:");
        System.out.println("1. Farmer");
        System.out.println("2. Retailer (Individual)");
        System.out.println("3. Retailer (Company)");
        System.out.print("Enter user type number: ");
        int userTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        UserType userType = null;
        switch (userTypeChoice) {
            case 1:
                userType = UserType.FARMER;
                break;
            case 2:
                userType = UserType.RETAILER_INDIVIDUAL;
                break;
            case 3:
                userType = UserType.RETAILER_COMPANY;
                break;
            default:
                System.out.println("Invalid user type choice. Registration failed.");
                return;
        }
        UserAuthentication.registerUser(username, userType);
        System.out.println("User registered successfully.");
    }

    private static void loginMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (UserAuthentication.loginUser(username)) {
            UserType userType = UserAuthentication.getUserType(username);
            switch (userType) {
                case FARMER:
                    farmerMenu(username);
                    break;
                case RETAILER_INDIVIDUAL:
                case RETAILER_COMPANY:
                    retailerMenu(username);
                    break;
                default:
                    System.out.println("Invalid user type.");
            }
        }
    }

    public static void farmerMenu(String farmerId) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nFarmer Menu:");
            System.out.println("1. Create Base Bid");
            System.out.println("2. View Base Bids");
            System.out.println("3. Close Bid");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    System.out.print("Enter bid ID: ");
                    String bidId = scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter base price: ");
                    double basePrice = scanner.nextDouble();
                    BidManagement.createBaseBid(bidId, farmerId, name, quantity, basePrice);
                    break;
                case 2:
                    BidManagement.viewBaseBids();
                    break;
                case 3:
                    System.out.print("Enter bid ID to close: ");
                    String bidToClose = scanner.nextLine();
                    System.out.print("Enter farmer ID : ");
                    String fid = scanner.nextLine();
                    BidManagement.closeBid(bidToClose, fid);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void retailerMenu(String retailerId) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nRetailer Menu:");
            System.out.println("1. View Base Bids");
            System.out.println("2. View Retailer Bids");
            System.out.println("3. Place Bid");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    BidManagement.viewBaseBids();
                    break;
                case 2:
                    BidManagement.viewRetailerBids();
                    break;
                case 3:
                    System.out.print("Enter bid ID: ");
                    String bidId = scanner.nextLine();
                    System.out.print("Enter bid price: ");
                    double bidPrice = scanner.nextDouble();
                    BidManagement.placeRetailerBid(bidId, retailerId, bidPrice);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

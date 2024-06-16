package controllers;

import models.Bid;

import java.io.*;
import java.util.*;

// Class to manage bids
public class BidManagement {
    private static final String BASE_BIDS_FILE = "E:/JAVA PT/Basics_Of_OOPs/FarmerRetailerAuction/files/base_bids.txt";
    private static final String RETAILER_BIDS_FILE = "E:/JAVA PT/Basics_Of_OOPs/FarmerRetailerAuction/files/retailer_bids.txt";
    private static final String HISTORY_FILE = "E:/JAVA PT/Basics_Of_OOPs/FarmerRetailerAuction/files/history.txt";

    private static List<Bid> baseBids = new ArrayList<>();
    private static Map<String, List<String>> retailerBids = new HashMap<>();

    static {
        loadBaseBids();
        loadRetailerBids();
    }

    @SuppressWarnings("unchecked")
    private static void loadBaseBids() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BASE_BIDS_FILE))) {
            baseBids = (List<Bid>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading base bids: " + e.getMessage());
        }
    }

    private static void saveBaseBids() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BASE_BIDS_FILE))) {
            oos.writeObject(baseBids);
        } catch (IOException e) {
            System.out.println("Error saving base bids: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadRetailerBids() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RETAILER_BIDS_FILE))) {
            retailerBids = (Map<String, List<String>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading retailer bids: " + e.getMessage());
        }
    }

    private static void saveRetailerBids() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RETAILER_BIDS_FILE))) {
            oos.writeObject(retailerBids);
        } catch (IOException e) {
            System.out.println("Error saving retailer bids: " + e.getMessage());
        }
    }

    public static void createBaseBid(String bidId, String farmerId, String name, int quantity, double basePrice) {
        Bid bid = new Bid(bidId, farmerId, name, quantity, basePrice);
        System.out.println(bid);
        baseBids.add(bid);
        saveBaseBids();
        System.out.println("Base bid created successfully.");
    }

    public static void placeRetailerBid(String bidId, String retailerId, double price) {
        if (retailerBids.containsKey(bidId)) {
            retailerBids.get(bidId).add(retailerId + "," + price);
        } else {
            List<String> bids = new ArrayList<>();
            bids.add(retailerId + "," + price);
            retailerBids.put(bidId, bids);
        }
        saveRetailerBids();
        System.out.println("Retailer bid placed successfully.");
    }

    public static void closeBid(String bidId, String fid) {
        // Check if the bid ID exists in base bids
        Bid selectedBaseBid = null;
        for (Bid bid : baseBids) {
            if (bid.getBidId().equals(bidId) && bid.getFarmerId().equals(fid)) {
                selectedBaseBid = bid;
                break;
            }
        }

        if (selectedBaseBid == null) {
            System.out.println("Bid not found in base bids.");
            return;
        }

        // Check if there are any retailer bids for the selected base bid
        List<String> retailerBidsList = retailerBids.get(bidId);
        if (retailerBidsList == null || retailerBidsList.isEmpty()) {
            System.out.println("No retailer bids found for this base bid.");
            return;
        }

        // Display base bid details
        System.out.println("Base Bid Details: " + selectedBaseBid.toString());

        // Display retailer bids for the selected base bid
        System.out.println("Retailer Bids:");
        for (int i = 0; i < retailerBidsList.size(); i++) {
            System.out.println((i + 1) + ". " + retailerBidsList.get(i));
        }

        // Ask the farmer to select the retailer bid to be included in history
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the index of the winning retailer bid (0 to cancel): ");
        int selectedRetailerIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (selectedRetailerIndex == 0 || selectedRetailerIndex > retailerBidsList.size()) {
            System.out.println("Closing bid canceled.");
            return;
        }

        // Remove the bid from retailer bids
        retailerBids.remove(bidId);
        baseBids.remove(selectedBaseBid);

        // Append bid details to history
        try (FileWriter writer = new FileWriter(HISTORY_FILE, true)) {
            writer.write("Base Bid Details: " + selectedBaseBid.toString() + "\n");
            writer.write("Winning Retailer Bid Details:\n");
            writer.write("\t" + retailerBidsList.get(selectedRetailerIndex - 1) + "\n\n");
        } catch (IOException e) {
            System.out.println("Error appending bid to history: " + e.getMessage());
        }

        // Save changes
        saveBaseBids();
        saveRetailerBids();
        System.out.println("Bid closed successfully.");
    }

    public static void viewBaseBids() {
        // loadBaseBids();
        if (baseBids.isEmpty()) {

            System.out.println("No base bids available.");
        } else {
            System.out.println("All Base Bids:");
            for (Bid bid : baseBids) {
                System.out.println(bid);
            }
        }
    }

    public static void viewRetailerBids() {
        if (retailerBids.isEmpty()) {
            System.out.println("No retailer bids available.");
        } else {
            System.out.println("All Retailer Bids:");
            for (Map.Entry<String, List<String>> entry : retailerBids.entrySet()) {
                String bidId = entry.getKey();
                List<String> bids = entry.getValue();
                System.out.println("Bid ID: " + bidId);
                for (String bid : bids) {
                    System.out.println("\t" + bid);
                }
            }
        }
    }
}

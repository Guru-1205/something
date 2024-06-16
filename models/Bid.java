package models;

import java.io.Serializable;

// Class to represent a bid
public class Bid implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bidId;
    private String farmerId;
    private String name;
    private int quantity;
    private double basePrice;

    public Bid(String bidId, String farmerId, String name, int quantity, double basePrice) {
        this.bidId = bidId;
        this.farmerId = farmerId;
        this.name = name;
        this.quantity = quantity;
        this.basePrice = basePrice;
    }

    public String getBidId() {
        return bidId;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public String toString() {
        return "Bid ID: " + bidId + ", Farmer ID: " + farmerId + ", Name: " + name + ", Quantity: " + quantity
                + ", Base Price: " + basePrice;
    }
}

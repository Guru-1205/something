package services;

import enums.UserType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Class to manage user registration and login
public class UserAuthentication {
    private static final String USERS_FILE = "E:/JAVA PT/Basics_Of_OOPs/FarmerRetailerAuction/files/users.txt";

    public static void registerUser(String username, UserType type) {
        try (FileWriter writer = new FileWriter(USERS_FILE, true)) {
            writer.write(username + "," + type + "\n");
            System.out.println("User registered successfully.");
        } catch (IOException e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
    }

    public static boolean loginUser(String username) {
        try (Scanner scanner = new Scanner(new File(USERS_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    System.out.println("Login successful.");
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Users file not found.");
        }
        System.out.println("Error: User not found.");
        return false;
    }

    public static UserType getUserType(String username) {
        try (Scanner scanner = new Scanner(new File(USERS_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    return UserType.valueOf(parts[1]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Users file not found.");
        }
        return null;
    }
}

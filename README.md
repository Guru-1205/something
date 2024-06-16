# Farmer-Retailer Auction Application 🌾🛒

Greetings everyone! 👋

It's me, Gc, 🎉

I'm excited to introduce my latest project, the Farmer-Retailer Auction Application. This Java-based platform facilitates auctions where farmers can list their agricultural products as base bids, and retailers can bid on these listings. It's designed to streamline the process of agricultural product procurement and sales.

## Features 🚀

### User Registration and Login
- **User Types:** The application caters to three user roles:
  - **Farmers:** Can create base bids for their agricultural products.
  - **Retailers (Individual):** Have the ability to view and bid on base bids.
  - **Retailers (Company):** Similar privileges as individual retailers, ideal for corporate entities. 🏢

- **Registration:** New users can register by selecting a username and specifying their user type.
- **Login:** Secure login functionality ensures authenticated access based on user credentials. 🔐

### Bid Management
- **Create Base Bids:** Farmers can easily list their products for auction by providing details such as bid ID, product name, available quantity, and base price.
- **View Base Bids:** Both farmers and retailers can view all active base bids to monitor available products and ongoing auctions.
- **Place Retailer Bids:** Retailers can bid on existing base bids, specifying their offered price for purchasing specified quantities of agricultural products. 💰

### Bid Closing and History
- **Close Bid:** Farmers can close auctions after a bidding period, selecting the winning bid from all retailer submissions. The chosen bid details are recorded and stored.
- **Bid History:** The application maintains a comprehensive history of closed bids, preserving details of each auction outcome in a dedicated `history.txt` file. 📜

### Persistence
- **Data Storage:** User profiles, base bids, retailer bids, and bid histories are securely stored using file-based storage. This ensures data integrity and persistence across application sessions.

## Installation 🛠️

To run the application locally, ensure Java is installed on your system. Clone the repository and compile the Java files using your preferred IDE or command line:

```bash
git clone https://github.com/your-username/farmer-retailer-auction-app.git
cd farmer-retailer-auction-app
javac *.java
```

## Usage 📋

1. **Compile the Code:**
   - Compile all Java files in the project.

2. **Run the Application:**
   - Execute the `Main` class to launch the bidding system.
   ```bash
   java Main
   ```

3. **Navigate the Application:**
   - Follow the on-screen prompts to register or log in, create base bids as a farmer, view available bids, place bids as a retailer, and close auctions as a farmer.

## Contributing 🤝

Contributions are welcome! If you encounter any issues or have suggestions for enhancements, please feel free to open an issue or submit a pull request.



Feel free to customize this README further with additional sections, screenshots, or specific instructions to better fit your project's unique features and setup. Happy coding and managing auctions! 🎉

Happy coding and enjoy the process!

Cheers,
Gc

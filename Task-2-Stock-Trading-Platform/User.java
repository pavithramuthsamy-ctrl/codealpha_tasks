import java.util.HashMap;
import java.util.ArrayList;

    public class User {

        private String name;
        private double balance;

        private HashMap<String, Integer> portfolio;
        private ArrayList<Transaction> transactions;

        public User(String name, double balance) {

            this.name = name;
            this.balance = balance;

            portfolio = new HashMap<>();
            transactions = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public double getBalance() {
            return balance;
        }

        // BUY STOCK
        public boolean buyStock(Stock stock, int quantity) {

            if (quantity <= 0) {
                System.out.println("Invalid quantity!");
                return false;
            }

            double total = stock.getPrice() * quantity;

            if (total > balance) {
                System.out.println("Insufficient balance!");
                return false;
            }

            balance -= total;

            int oldQuantity =
                    portfolio.getOrDefault(stock.getSymbol(), 0);

            portfolio.put(
                    stock.getSymbol(),
                    oldQuantity + quantity
            );

            transactions.add(
                    new Transaction("BUY", stock, quantity, total)
            );

            System.out.println();
            System.out.println("Purchase successful!");
            System.out.println("Stock: " + stock.getSymbol());
            System.out.println("Company: " + stock.getCompanyName());
            System.out.println("Quantity: " + quantity);

            System.out.printf(
                    "Price per Share: ₹%.2f%n",
                    stock.getPrice()
            );

            System.out.printf(
                    "Total Cost: ₹%.2f%n",
                    total
            );

            System.out.printf(
                    "Remaining Balance: ₹%.2f%n",
                    balance
            );

            return true;
        }

        // SELL STOCK
        public boolean sellStock(Stock stock, int quantity) {

            if (quantity <= 0) {
                System.out.println("Invalid quantity!");
                return false;
            }

            int owned =
                    portfolio.getOrDefault(stock.getSymbol(), 0);

            if (quantity > owned) {
                System.out.println(
                        "You do not have enough shares!"
                );
                return false;
            }

            double total = stock.getPrice() * quantity;

            balance += total;

            int remaining = owned - quantity;

            if (remaining == 0) {
                portfolio.remove(stock.getSymbol());
            } else {
                portfolio.put(stock.getSymbol(), remaining);
            }

            transactions.add(
                    new Transaction("SELL", stock, quantity, total)
            );

            System.out.println();
            System.out.println("Sale successful!");
            System.out.println("Stock: " + stock.getSymbol());
            System.out.println("Quantity: " + quantity);

            System.out.printf(
                    "Amount Received: ₹%.2f%n",
                    total
            );

            System.out.printf(
                    "Available Balance: ₹%.2f%n",
                    balance
            );

            return true;
        }

        // GET QUANTITY OF A STOCK
        public int getQuantity(String symbol) {
            return portfolio.getOrDefault(symbol, 0);
        }

        // CALCULATE PORTFOLIO VALUE
        public double calculatePortfolioValue(
                HashMap<String, Stock> market) {

            double totalValue = 0;

            for (String symbol : portfolio.keySet()) {

                int quantity = portfolio.get(symbol);

                Stock stock = market.get(symbol);

                if (stock != null) {
                    totalValue +=
                            quantity * stock.getPrice();
                }
            }

            return totalValue;
        }

        // DISPLAY PORTFOLIO
        public void displayPortfolio(
                HashMap<String, Stock> market) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("              PORTFOLIO");
            System.out.println("========================================");

            if (portfolio.isEmpty()) {

                System.out.println("No stocks in portfolio.");

            } else {

                for (String symbol : portfolio.keySet()) {

                    Stock stock = market.get(symbol);

                    int quantity = portfolio.get(symbol);

                    double value =
                            quantity * stock.getPrice();

                    System.out.printf(
                            "%-10s - %d shares - ₹%.2f%n",
                            symbol,
                            quantity,
                            value
                    );
                }
            }

            double stockValue =
                    calculatePortfolioValue(market);

            System.out.println("----------------------------------------");

            System.out.printf(
                    "Total Stock Value: ₹%.2f%n",
                    stockValue
            );

            System.out.printf(
                    "Available Balance: ₹%.2f%n",
                    balance
            );

            System.out.printf(
                    "Total Portfolio Value: ₹%.2f%n",
                    balance + stockValue
            );

            System.out.println("========================================");
        }

        // DISPLAY TRANSACTION HISTORY
        public void displayTransactions() {

            System.out.println();
            System.out.println("========================================");
            System.out.println("        TRANSACTION HISTORY");
            System.out.println("========================================");

            if (transactions.isEmpty()) {

                System.out.println("No transactions yet.");

            } else {

                for (int i = 0; i < transactions.size(); i++) {

                    transactions.get(i)
                            .displayTransaction(i + 1);
                }
            }

            System.out.println("========================================");
        }
    }


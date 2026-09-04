import java.util.HashMap;
import java.util.Scanner;

public class StockTradePlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        HashMap<String, Stock> market = new HashMap<>();

        market.put(
                "RELIANCE",
                new Stock(
                        "RELIANCE",
                        "Reliance Industries",
                        2900
                )
        );

        market.put(
                "TCS",
                new Stock(
                        "TCS",
                        "Tata Consultancy Services",
                        3500
                )
        );

        market.put(
                "INFY",
                new Stock(
                        "INFY",
                        "Infosys",
                        1800
                )
        );

        market.put(
                "HDFCBANK",
                new Stock(
                        "HDFCBANK",
                        "HDFC Bank",
                        1700
                )
        );

        market.put(
                "ICICIBANK",
                new Stock(
                        "ICICIBANK",
                        "ICICI Bank",
                        1300
                )
        );

        market.put(
                "SBIN",
                new Stock(
                        "SBIN",
                        "State Bank of India",
                        850
                )
        );

        market.put(
                "ITC",
                new Stock(
                        "ITC",
                        "ITC Limited",
                        500
                )
        );

        market.put(
                "WIPRO",
                new Stock(
                        "WIPRO",
                        "Wipro",
                        550
                )
        );
        User user = new User("Pavithra", 10000);
        while (true) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("       STOCK TRADING PLATFORM");
            System.out.println("========================================");

            System.out.println(
                    "Welcome, " + user.getName() + "!"
            );

            System.out.printf(
                    "Available Balance: ₹%.2f%n",
                    user.getBalance()
            );

            System.out.println();
            System.out.println("--------------- MENU ------------------");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.println("----------------------------------------");

            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            if (choice == 1) {

                System.out.println();
                System.out.println("========================================");
                System.out.println("              MARKET DATA");
                System.out.println("========================================");

                System.out.printf(
                        "%-12s %-30s %s%n",
                        "Symbol",
                        "Company Name",
                        "Price"
                );

                System.out.println(
                        "------------------------------------------------------------"
                );

                for (Stock stock : market.values()) {
                    stock.displayStock();
                }

                System.out.println(
                        "========================================"
                );
            }
            else if (choice == 2) {

                System.out.println();
                System.out.println(
                        "----------- BUY STOCK ----------------"
                );

                System.out.print(
                        "Enter stock symbol: "
                );

                String symbol =
                        sc.next().toUpperCase();

                Stock stock = market.get(symbol);

                if (stock == null) {

                    System.out.println(
                            "Stock not found!"
                    );

                } else {

                    System.out.print(
                            "Enter quantity: "
                    );

                    int quantity = sc.nextInt();

                    user.buyStock(stock, quantity);
                }
            }
            else if (choice == 3) {

                System.out.println();
                System.out.println(
                        "----------- SELL STOCK ----------------"
                );

                System.out.print(
                        "Enter stock symbol: "
                );

                String symbol =
                        sc.next().toUpperCase();

                Stock stock = market.get(symbol);

                if (stock == null) {

                    System.out.println(
                            "Stock not found!"
                    );

                } else {

                    System.out.print(
                            "Enter quantity: "
                    );

                    int quantity = sc.nextInt();

                    user.sellStock(stock, quantity);
                }
            }
            else if (choice == 4) {

                user.displayPortfolio(market);
            }
            else if (choice == 5) {

                user.displayTransactions();
            }
            else if (choice == 6) {

                System.out.println();
                System.out.println(
                        "Thank you for using Stock Trading Platform!"
                );

                break;
            }
            else {

                System.out.println(
                        "Invalid choice! Please try again."
                );
            }
        }

        sc.close();
    }
}
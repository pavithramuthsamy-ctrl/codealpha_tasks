import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

    public class Transaction {

        private String type;
        private Stock stock;
        private int quantity;
        private double totalAmount;
        private LocalDateTime dateTime;

        public Transaction(
                String type,
                Stock stock,
                int quantity,
                double totalAmount) {

            this.type = type;
            this.stock = stock;
            this.quantity = quantity;
            this.totalAmount = totalAmount;
            this.dateTime = LocalDateTime.now();
        }

        public String getType() {
            return type;
        }

        public Stock getStock() {
            return stock;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public String getDateTime() {

            DateTimeFormatter format =
                    DateTimeFormatter.ofPattern(
                            "dd-MM-yyyy HH:mm"
                    );

            return dateTime.format(format);
        }

        public void displayTransaction(int number) {

            System.out.printf(
                    "%d. %-4s %-10s %5d shares ₹%.2f  %s%n",
                    number,
                    type,
                    stock.getSymbol(),
                    quantity,
                    totalAmount,
                    getDateTime()
            );
        }
    }


package streams_basics.app_example;

import java.util.Arrays;
import java.util.List;

public class OrdersTraditionalApp {
    public static void main(String[] args) {

        List<Order> orders = Arrays.asList(
            new Order("01", 100, true),
            new Order("02", 200, false),
            new Order("03", 300, true)
        );

        double total = 0;
        for (Order order : orders) {
            if (order.paid()) {
                total += order.amount();
            }
        }

        System.out.println("Total paid amount: " + total);  // 400.0
    }
}
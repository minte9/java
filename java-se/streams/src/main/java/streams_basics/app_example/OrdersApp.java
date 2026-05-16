package streams_basics.app_example;

import java.util.Arrays;
import java.util.List;

public class OrdersApp {
    public static void main(String[] args) {

        List<Order> orders = Arrays.asList(
            new Order("01", 100, true),
            new Order("02", 200, false),
            new Order("03", 300, true)
        );

        double total = orders.stream()
                .filter(Order::paid)
                .mapToDouble(Order::amount)
                .sum();

        System.out.println("Total paid amount: " + total);  // 400.0
    }
}
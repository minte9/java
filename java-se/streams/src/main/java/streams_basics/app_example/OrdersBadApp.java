package streams_basics.app_example;

import java.util.Arrays;
import java.util.List;

public class OrdersBadApp {
    public static void main(String[] args) {

        List<Order> orders = Arrays.asList(
            new Order("01", 100, true),
            new Order("02", 200, false),
            new Order("03", 300, true)
        );

        final double[] total = {0};  // hack for mutation in lambda

        orders.stream()
                .filter(order -> {
                    return order.paid();
                })
                .forEach(order -> {
                    total[0] += order.amount();  // ❌ side effect
                });

        System.out.println("Total paid amount: " + total[0]);  // 400.0
    }
}
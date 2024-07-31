import com.github.javafaker.Faker;
import entities.Customer;
import entities.Order;
import entities.Product;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        System.out.println("------------------------esercizio 1-------------------------");
        Faker f = new Faker(Locale.ITALIAN);
        Supplier<Product> bookProducts = () -> new Product(f.book().title(), "book", Double.parseDouble(f.commerce().price(40.00, 300.00).replace(",", ".")));
        Supplier<List<Product>> randomBooks = () -> {
            List<Product> booksProductList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                booksProductList.add(bookProducts.get());
            }
            return booksProductList;
        };
        List<Product> randomBooksProducts = randomBooks.get();
        System.out.println(randomBooksProducts + "\n");
        randomBooksProducts.stream().filter(book -> book.getPrice() < 100).forEach(System.out::println);


        System.out.println("------------------------esercizio 2-------------------------");
        Supplier<Product> babyProducts = () -> new Product(f.name().firstName(), "baby", Double.parseDouble(f.commerce().price(40.00, 300.00).replace(",", ".")));

        Supplier<List<Product>> randomBaby = () -> {
            List<Product> babyProductList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                babyProductList.add(babyProducts.get());
            }
            return babyProductList;
        };
        List<Product> randomBabyProducts = randomBaby.get();
//


        Supplier<Order> babyOrder = () -> {
            Calendar startCal1 = Calendar.getInstance();
            startCal1.set(2024, Calendar.JANUARY, 1, 0, 0, 0);
            Calendar endCal1 = Calendar.getInstance();
            endCal1.set(2024, Calendar.JULY, 30, 23, 59, 59);

            Calendar startCal2 = Calendar.getInstance();
            startCal2.set(2024, Calendar.MARCH, 1, 0, 0, 0);
            Calendar endCal2 = Calendar.getInstance();
            endCal2.set(2024, Calendar.JULY, 30, 23, 59, 59);

            // Genera date casuali per startDate e endDate
            Date randomStartDate = f.date().between(startCal1.getTime(), endCal1.getTime());
            Date randomEndDate = f.date().between(startCal2.getTime(), endCal2.getTime());

            // Conversione da Date a LocalDate
            LocalDate startDate = randomStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = randomEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return new Order("in corso", startDate, endDate, randomBabyProducts, new Customer(f.name().firstName()));
        };

        Supplier<List<Order>> randomBabyOrders = () -> {
            List<Order> babyOrderList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                babyOrderList.add(babyOrder.get());
            }
            return babyOrderList;
        };
        List<Order> randomBabyOrdersList = randomBabyOrders.get();
        System.out.println(randomBabyOrdersList);


        Supplier<Product> babyProducts = () -> new Product(f.name().firstName(), "baby", Double.parseDouble(f.commerce().price(40.00, 300.00).replace(",", ".")));

        Supplier<List<Product>> randomBaby = () -> {
            List<Product> babyProductList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                babyProductList.add(babyProducts.get());
            }
            return babyProductList;
        };
        List<Product> randomBabyProducts = randomBaby.get();
        List<Product> randomBabyAndBookProducts = new ArrayList<>(randomBaby.get());
        randomBabyAndBookProducts.addAll(randomBooks.get());


//        Supplier<Order> babyAndBookOrder = () -> {
//            Calendar startCal1 = Calendar.getInstance();
//            startCal1.set(2024, Calendar.JANUARY, 1, 0, 0, 0);
//            Calendar endCal1 = Calendar.getInstance();
//            endCal1.set(2024, Calendar.JULY, 30, 23, 59, 59);
//
//            Calendar startCal2 = Calendar.getInstance();
//            startCal2.set(2024, Calendar.MARCH, 1, 0, 0, 0);
//            Calendar endCal2 = Calendar.getInstance();
//            endCal2.set(2024, Calendar.JULY, 30, 23, 59, 59);
//
//            // Genera date casuali per startDate e endDate
//            Date randomStartDate = f.date().between(startCal1.getTime(), endCal1.getTime());
//            Date randomEndDate = f.date().between(startCal2.getTime(), endCal2.getTime());
//
//            // Conversione da Date a LocalDate
//            LocalDate startDate = randomStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate endDate = randomEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            return new Order("in corso", startDate, endDate, randomBabyAndBookProducts, new Customer(f.name().firstName()));
//        };
//
//        Supplier<List<Order>> randomBabyAndBookOrders = () -> {
//            List<Order> babyAndBookOrderList = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                babyAndBookOrderList.add(babyAndBookOrder.get());
//            }
//            return babyAndBookOrderList;
//        };
//        List<Order> randomBabyOrdersList = randomBabyAndBookOrders.get();
//        List<Order> babyOrderLists = randomBabyOrdersList.stream().filter(order -> order.getProduct().stream().allMatch(product -> Objects.equals(product.getCategory(), "baby"))).collect(Collectors.toList());
//        System.out.println(randomBabyOrdersList);
//        System.out.println(babyOrderLists);


        System.out.println("-----------------------esercizio 3----------------------------");
        Supplier<Product> boyProducts = () -> {
            return new Product(f.name().firstName(), "boy", Double.parseDouble(f.commerce().price(40.00, 300.00).replace(",", ".")));
        };
        Supplier<List<Product>> randomBoy = () -> {
            List<Product> boyProductList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                boyProductList.add(boyProducts.get());
            }
            return boyProductList;
        };
        List<Product> randomBoyProducts = randomBoy.get();
//        System.out.println(randomBoyProducts);

        List<Product> randomProducts = new ArrayList<>(randomBoyProducts);
        randomProducts.addAll(randomBabyProducts);
        randomProducts.addAll(randomBooksProducts);
        System.out.println("lista con tutte le categorie: " + randomProducts);

        List<Product> filteredBoyList = randomProducts.stream().filter(product -> product.getCategory() == "boy").toList();
        System.out.println("lista con tutte la categoria boy: " + filteredBoyList);
        filteredBoyList.forEach(product -> product.sconto10(product.getPrice()));
        System.out.println("lista con tutte la categoria boy + sconto del 10%: " + filteredBoyList);


        System.out.println("-----------------------esercizio 4----------------------------");

        Supplier<Order> randomOrder = () -> {
            Calendar startCal1 = Calendar.getInstance();
            startCal1.set(2024, Calendar.JANUARY, 1, 0, 0, 0);
            Calendar endCal1 = Calendar.getInstance();
            endCal1.set(2024, Calendar.JULY, 30, 23, 59, 59);

            Calendar startCal2 = Calendar.getInstance();
            startCal2.set(2024, Calendar.MARCH, 1, 0, 0, 0);
            Calendar endCal2 = Calendar.getInstance();
            endCal2.set(2024, Calendar.JULY, 30, 23, 59, 59);

            // Genera date casuali per startDate e endDate
            Date randomStartDate = f.date().between(startCal1.getTime(), endCal1.getTime());
            Date randomEndDate = f.date().between(startCal2.getTime(), endCal2.getTime());

            // Conversione da Date a LocalDate
            LocalDate startDate = randomStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = randomEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return new Order("in corso", startDate, endDate, randomProducts, new Customer(f.name().firstName()));
        };

        Supplier<List<Order>> randomOrders = () -> {
            List<Order> OrderList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                OrderList.add(randomOrder.get());
            }
            return OrderList;
        };
        List<Order> randomOrdersList = randomOrders.get();
        System.out.println("Lista completa con boy-baby-book " + randomOrdersList);
        List<Order> filteredOrderDate = randomOrdersList.stream().filter(order -> order.getCustomer().getTier() == 2).filter(order -> order.getOrderDate().isAfter(LocalDate.parse("2024-02-01")) && order.getOrderDate().isBefore(LocalDate.parse("2024-04-01"))).toList();
        System.out.println("Lista completa con boy-baby-book filtrata per data ordine: " + filteredOrderDate);
    }
}

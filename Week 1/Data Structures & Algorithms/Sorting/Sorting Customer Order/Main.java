public class Main {
    public static void main(String[] args) {
        Order[] orders = {
            new Order(
                    101,
                    "Nataraj",
                    4500),
            new Order(
                    102,
                    "Rahul",
                    1200),
            new Order(
                    103,
                    "Anjali",
                    6700),
            new Order(
                    104,
                    "Priya",
                    3000)
        };
        System.out.println(
                "----- Bubble Sort -----");
        Order[] bubbleOrders =
                orders.clone();
        BubbleSort.sort(bubbleOrders);
        for(Order order : bubbleOrders) {
            System.out.println(order);
        }
        System.out.println(
                "\n----- Quick Sort -----");
        Order[] quickOrders =
                orders.clone();
        QuickSort.sort(
                quickOrders,
                0,
                quickOrders.length - 1);
        for(Order order : quickOrders) {
            System.out.println(order);
        }
    }
}
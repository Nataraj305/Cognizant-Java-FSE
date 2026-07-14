public class Main {
    public static void main(String[] args) {
        Product[] products = {
            new Product(
                    101,
                    "Laptop",
                    "Electronics"),
            new Product(
                    102,
                    "Mouse",
                    "Accessories"),
            new Product(
                    103,
                    "Keyboard",
                    "Accessories"),
            new Product(
                    104,
                    "Monitor",
                    "Electronics"),
            new Product(
                    105,
                    "Printer",
                    "Office")
        };
        System.out.println(
                "----- Linear Search -----");
        Product result1 =
                SearchAlgorithms.linearSearch(
                        products,
                        104);
        if(result1 != null) {
            System.out.println(
                    "Found: " + result1);
        }
        System.out.println(
                "\n----- Binary Search -----");
        Product result2 =
                SearchAlgorithms.binarySearch(
                        products,
                        104);
        if(result2 != null) {
            System.out.println(
                    "Found: " + result2);
        }
    }
}
public class Main {

    public static void main(String[] args) {

        InventoryManager manager =
                new InventoryManager();

        Product p1 =
                new Product(
                        101,
                        "Laptop",
                        50,
                        45000);

        Product p2 =
                new Product(
                        102,
                        "Mouse",
                        200,
                        500);

        Product p3 =
                new Product(
                        103,
                        "Keyboard",
                        100,
                        1200);

        // Add Products
        manager.addProduct(p1);
        manager.addProduct(p2);
        manager.addProduct(p3);

        System.out.println(
                "\n----- Inventory -----");

        manager.displayInventory();

        // Search
        System.out.println(
                "\n----- Search Product -----");

        manager.searchProduct(102);

        // Update
        System.out.println(
                "\n----- Update Product -----");

        manager.updateProduct(
                101,
                60,
                44000);

        // Delete
        System.out.println(
                "\n----- Delete Product -----");

        manager.deleteProduct(103);

        System.out.println(
                "\n----- Updated Inventory -----");

        manager.displayInventory();
    }
}

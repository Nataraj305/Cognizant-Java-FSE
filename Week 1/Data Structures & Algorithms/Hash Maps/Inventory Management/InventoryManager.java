import java.util.HashMap;

public class InventoryManager {

    private HashMap<Integer, Product> inventory;

    public InventoryManager() {
        inventory = new HashMap<>();
    }

    // Add Product
    public void addProduct(Product product) {

        inventory.put(product.getProductId(), product);

        System.out.println(
                product.getProductName()
                + " added successfully.");
    }

    // Search Product
    public void searchProduct(int productId) {

        Product product = inventory.get(productId);

        if(product != null) {
            System.out.println(product);
        }
        else {
            System.out.println("Product not found.");
        }
    }

    // Update Product
    public void updateProduct(int productId,
                              int quantity,
                              double price) {

        Product product = inventory.get(productId);

        if(product != null) {

            product.setQuantity(quantity);
            product.setPrice(price);

            System.out.println(
                    "Product updated successfully.");
        }
        else {
            System.out.println("Product not found.");
        }
    }

    // Delete Product
    public void deleteProduct(int productId) {

        if(inventory.remove(productId) != null) {

            System.out.println(
                    "Product deleted successfully.");
        }
        else {
            System.out.println("Product not found.");
        }
    }

    // Display Inventory
    public void displayInventory() {

        if(inventory.isEmpty()) {

            System.out.println(
                    "Inventory is empty.");
            return;
        }

        for(Product product : inventory.values()) {
            System.out.println(product);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();
        Observer mobile = new MobileApp("Mobile App");
        Observer web = new WebApp("Web App");
        stockMarket.registerObserver(mobile);
        stockMarket.registerObserver(web);
        System.out.println("Stock Price Changed to 5000");
        stockMarket.setStockPrice(5000);
        System.out.println();
        System.out.println("Stock Price Changed to 6500");
        stockMarket.setStockPrice(6500);
    }
}

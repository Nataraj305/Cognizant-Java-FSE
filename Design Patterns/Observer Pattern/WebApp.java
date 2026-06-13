public class WebApp implements Observer {
    private String websiteName;
    public WebApp(String websiteName) {
        this.websiteName = websiteName;
    }
    @Override
    public void update(double stockPrice) {
        System.out.println(websiteName +
                " received stock update: Rs." + stockPrice);
    }
}

public class Main {

    public static void main(String[] args) {

        PaymentProcessor paypal =
                new PaymentAdapter(new PayPalGateway());

        PaymentProcessor stripe =
                new PaymentAdapter(new StripeGateway());

        paypal.processPayment(1000);

        stripe.processPayment(2500);
    }
}

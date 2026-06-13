public class PaymentAdapter implements PaymentProcessor {
    private final Object gateway;
    public PaymentAdapter(Object gateway) {
        this.gateway = gateway;
    }
    @Override
public void processPayment(double amount) {
    switch (gateway) {
        case PayPalGateway paypal -> paypal.sendPayment(amount);
        case StripeGateway stripe -> stripe.makePayment(amount);
        default -> throw new IllegalArgumentException("Unknown gateway");
    }
}
}
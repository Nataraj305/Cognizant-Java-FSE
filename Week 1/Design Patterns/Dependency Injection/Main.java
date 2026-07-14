public class Main {
    public static void main(String[] args) {
        CustomerRepository repository =
                new CustomerRepositoryImpl();
        CustomerService service =
                new CustomerService(repository);
        Customer customer =
                service.getCustomer(101);
        System.out.println("Customer Details");
        System.out.println("----------------");
        System.out.println("ID   : " + customer.getId());
        System.out.println("Name : " + customer.getName());
    }
}
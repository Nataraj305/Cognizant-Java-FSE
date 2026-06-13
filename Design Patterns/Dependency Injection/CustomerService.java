public class CustomerService {
    private CustomerRepository repository;
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }
    public Customer getCustomer(int id) {
        return repository.findCustomerById(id);
    }
}

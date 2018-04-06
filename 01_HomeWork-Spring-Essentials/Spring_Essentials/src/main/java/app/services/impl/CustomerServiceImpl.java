package app.services.impl;

import app.models.bindingModels.customers.CustomerEditBindingModel;
import app.models.orm.Customer;
import app.models.viewModels.customer.CustomerEditOrAddViewModel;
import app.models.viewModels.customer.CustomerTableViewModel;
import app.parser.ModelParser;
import app.repositories.CustomerRepository;
import app.services.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService
{

    private final CustomerRepository customerRepository;
    private final ModelParser modelParser;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelParser modelParser)
    {
        this.customerRepository = customerRepository;
        this.modelParser = modelParser;
    }

    @Override
    public List<CustomerTableViewModel> getAllCustomers(String order)
    {
        List<CustomerTableViewModel> customers = new ArrayList<>();
        switch (order)
        {
            case "none":
                customers = this.customerRepository.findAllCustomers().stream().map(e -> this.modelParser.parse(e, CustomerTableViewModel.class)).collect(Collectors.toList());
                break;
            case "ascending":
                customers = this.customerRepository.findAllByBirthDateAscending().stream().map(e -> this.modelParser.parse(e, CustomerTableViewModel.class)).collect(Collectors.toList());
                break;
            case "descending":
                customers = this.customerRepository.findAllByBirthDateDescending().stream().map(e -> this.modelParser.parse(e, CustomerTableViewModel.class)).collect(Collectors.toList());
                break;
        }
        return customers;
    }

    @Override
    public CustomerEditOrAddViewModel findById(long id)
    {
        Customer one = this.customerRepository.findOne(id);
        CustomerEditOrAddViewModel customer = this.modelParser.parse(one, CustomerEditOrAddViewModel.class);
        return customer;
    }

    @Override
    @Transactional
    public void updateById(CustomerEditBindingModel customerEditBindingModel, long id)
    {
        String name = customerEditBindingModel.getName();
        boolean isYoungDriver = customerEditBindingModel.isYoungDriver();
        Date date = customerEditBindingModel.getBirthDate();
        this.customerRepository.updateById(id, name, isYoungDriver, date);
    }

    @Override
    public void addCustomer(CustomerEditBindingModel customerEditBindingModel)
    {
        Customer customer = this.modelParser.parse(customerEditBindingModel, Customer.class);
        this.customerRepository.save(customer);
    }
}

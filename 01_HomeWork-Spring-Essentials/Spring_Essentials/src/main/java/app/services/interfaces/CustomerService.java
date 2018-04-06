package app.services.interfaces;

import app.models.bindingModels.customers.CustomerEditBindingModel;
import app.models.viewModels.customer.CustomerEditOrAddViewModel;
import app.models.viewModels.customer.CustomerTableViewModel;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerService
{
    List<CustomerTableViewModel> getAllCustomers(String order);

    CustomerEditOrAddViewModel findById(long id);

    @Transactional
    void updateById(CustomerEditBindingModel customerEditBindingModel, long id);

    void addCustomer(CustomerEditBindingModel customerEditBindingModel);
}

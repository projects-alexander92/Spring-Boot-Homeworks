package app.controllers;

import app.models.bindingModels.customers.CustomerEditBindingModel;
import app.models.viewModels.customer.CustomerEditOrAddViewModel;
import app.models.viewModels.customer.CustomerTableViewModel;
import app.services.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class CustomerController
{
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    @GetMapping("/customers/all/{order}")
    public String menageCustomersTable(@PathVariable String order, Model model)
    {
        List<CustomerTableViewModel> customers = this.customerService.getAllCustomers(order);
        model.addAttribute("customers", customers);
        return "customers/customer-home-view";
    }

    @GetMapping("/customers/edit/{id}")
    public String showEditCustomer(@PathVariable long id, Model model)
    {
        CustomerEditOrAddViewModel customer = this.customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customers/customer-edit-view";
    }

    @PostMapping("/customers/edit/{id}")
    public String submitCustomerEditFrom(@PathVariable long id, @ModelAttribute CustomerEditBindingModel customerEditBindingModel)
    {
        boolean isYoungDriver = this.isYoungDriver(customerEditBindingModel.getBirthDate());
        customerEditBindingModel.setYoungDriver(isYoungDriver);
        this.customerService.updateById(customerEditBindingModel, id);
        return "redirect:/customers/all/ascending";
    }

    @GetMapping("/customers/add")
    public String showAddCustomerView(Model model)
    {
        CustomerEditOrAddViewModel customer = new CustomerEditOrAddViewModel();
        model.addAttribute("customer", customer);
        return "customers/customer-add-view";
    }

    @PostMapping("customers/add")
    public String submitCustomerAddForm(@ModelAttribute CustomerEditBindingModel customerEditBindingModel)
    {
        customerEditBindingModel.setYoungDriver(this.isYoungDriver(customerEditBindingModel.getBirthDate()));
        this.customerService.addCustomer(customerEditBindingModel);
        return "redirect:/customers/all/ascending";
    }

    private boolean isYoungDriver(Date birthDate)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        LocalDate pdate = LocalDate.of(year, month, date);
        LocalDate now = LocalDate.now();
        Period diff = Period.between(pdate, now);
        return diff.getYears() < 20;
    }
}

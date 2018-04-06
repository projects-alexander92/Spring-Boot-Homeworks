package app.controllers;

import app.models.viewModels.suppliers.SupplierTableViewModel;
import app.services.interfaces.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SupplierController
{
    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService)
    {
        this.supplierService = supplierService;
    }

    @GetMapping("suppliers/{modifier}")
    public String getSupplierView(@PathVariable(required = false) String modifier, Model model)
    {
        List<SupplierTableViewModel> allSuppliers;
        if (modifier.equals("none"))
        {
            allSuppliers = this.supplierService.getAllSuppliers();
        } else
        {
            allSuppliers = this.supplierService.getAllSuppliersByModifier(modifier);
        }
        model.addAttribute("suppliers", allSuppliers);
        return "suppliers/supplier-home-view";
    }
}

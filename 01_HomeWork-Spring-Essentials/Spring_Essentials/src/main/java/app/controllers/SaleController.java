package app.controllers;

import app.models.viewModels.sales.SaleTableViewModel;
import app.services.impl.SalesServiceImpl;
import app.services.interfaces.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SaleController
{
    private final SalesService salesService;

    @Autowired
    public SaleController(SalesService salesService)
    {
        this.salesService = salesService;
    }

    @GetMapping("/sales/{modifier}")
    public String showSalesView(Model model, @PathVariable(required = false) String modifier)
    {
        List<SaleTableViewModel> saleTableViewModels = new ArrayList<>();
        if (modifier.equals("all"))
        {
            saleTableViewModels = this.salesService.getAllSales();
        } else if (modifier.equals("discounted"))
        {
            saleTableViewModels = this.salesService.getAllSalesWithDiscount();
        }
        model.addAttribute("sales", saleTableViewModels);
        return "sales/sales-table-view";
    }

    @PostMapping("/sales/{modifier}")
    public String showSalesByDiscount(@RequestParam(name = "discount", required = false) double discount, Model model)
    {
        List<SaleTableViewModel> allSalesWithFixedDiscount = this.salesService.getAllSalesWithFixedDiscount(discount);
        model.addAttribute("sales", allSalesWithFixedDiscount);
        return "sales/sales-table-view";
    }
}

package app.services.interfaces;

import app.models.viewModels.sales.SaleTableViewModel;

import java.util.List;

public interface SalesService
{
    List<SaleTableViewModel> getAllSales();

    List<SaleTableViewModel> getAllSalesWithDiscount();

    List<SaleTableViewModel> getAllSalesWithFixedDiscount(double discount);

}

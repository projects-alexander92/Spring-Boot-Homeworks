package app.services.impl;

import app.models.orm.Sale;
import app.models.viewModels.sales.SaleTableViewModel;
import app.repositories.SalesRepository;
import app.services.interfaces.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesServiceImpl implements SalesService
{
    private final SalesRepository salesRepository;

    @Autowired
    public SalesServiceImpl(SalesRepository salesRepository)
    {
        this.salesRepository = salesRepository;

    }

    @Override
    public List<SaleTableViewModel> getAllSales()
    {
        List<Sale> sales = this.salesRepository.getAllBy();
        List<SaleTableViewModel> viewModels = new ArrayList<>();
        for (Sale sale : sales)
        {
            viewModels.add(this.parseSale(sale));
        }
        return viewModels;
    }

    @Override
    public List<SaleTableViewModel> getAllSalesWithDiscount()
    {
        List<Sale> sales = this.salesRepository.getAllWithDiscount();
        List<SaleTableViewModel> viewModels = new ArrayList<>();
        for (Sale sale : sales)
        {
            viewModels.add(this.parseSale(sale));
        }
        return viewModels;
    }

    @Override
    public List<SaleTableViewModel> getAllSalesWithFixedDiscount(double discount)
    {
        List<Sale> sales = this.salesRepository.getAllWithFixedDiscount(discount);
        List<SaleTableViewModel> viewModels = new ArrayList<>();
        for (Sale sale : sales)
        {
            viewModels.add(this.parseSale(sale));
        }
        return viewModels;
    }

    private SaleTableViewModel parseSale(Sale sale)
    {
        SaleTableViewModel saleTableViewModel = new SaleTableViewModel();
        saleTableViewModel.setDiscount(sale.getDiscount());
        saleTableViewModel.setCarMake(sale.getCar().getMake());
        saleTableViewModel.setCustomerName(sale.getCustomer().getName());
        saleTableViewModel.setId(sale.getId());
        return saleTableViewModel;
    }
}

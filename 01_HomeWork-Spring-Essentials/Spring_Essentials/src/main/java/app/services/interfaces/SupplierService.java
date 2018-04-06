package app.services.interfaces;

import app.models.viewModels.suppliers.SupplierTableViewModel;

import java.util.List;

public interface SupplierService
{
    List<SupplierTableViewModel> getAllSuppliers();

    List<SupplierTableViewModel> getAllSuppliersByModifier(String modifier);
}

package app.services.impl;

import app.models.viewModels.suppliers.SupplierTableViewModel;
import app.parser.ModelParser;
import app.repositories.SupplierRepository;
import app.services.interfaces.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService
{
    private final SupplierRepository supplierRepository;
    private final ModelParser modelParser;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelParser modelParser)
    {
        this.supplierRepository = supplierRepository;
        this.modelParser = modelParser;
    }

    @Override
    public List<SupplierTableViewModel> getAllSuppliers()
    {
        List<SupplierTableViewModel> collect = this.supplierRepository.getAllBy().stream().map(e ->
        {
            SupplierTableViewModel parse = this.modelParser.parse(e, SupplierTableViewModel.class);
            parse.setNumberOfParts(e.getParts().size());
            return parse;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<SupplierTableViewModel> getAllSuppliersByModifier(String modifier)
    {
        List<SupplierTableViewModel> suppliers = new ArrayList<>();
        if (modifier.equals("local"))
        {
            suppliers = this.supplierRepository.getByIsImporter(false).stream().map(e ->
            {
                SupplierTableViewModel parse = this.modelParser.parse(e, SupplierTableViewModel.class);
                parse.setNumberOfParts(e.getParts().size());
                return parse;
            }).collect(Collectors.toList());
        } else if (modifier.equals("importers"))
        {
            suppliers = this.supplierRepository.getByIsImporter(true).stream().map(e ->
            {
                SupplierTableViewModel parse = this.modelParser.parse(e, SupplierTableViewModel.class);
                parse.setNumberOfParts(e.getParts().size());
                return parse;
            }).collect(Collectors.toList());
        }
        return suppliers;
    }
}

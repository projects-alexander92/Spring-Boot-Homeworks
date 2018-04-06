package app.services.impl;

import app.models.bindingModels.parts.PartEditBindingModel;
import app.models.orm.Part;
import app.models.viewModels.parts.PartDeleteViewModel;
import app.models.viewModels.parts.PartViewForTable;
import app.parser.ModelParser;
import app.repositories.PartRepository;
import app.services.interfaces.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl implements PartService
{
    private final PartRepository partRepository;
    private final ModelParser modelParser;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, ModelParser modelParser)
    {
        this.partRepository = partRepository;
        this.modelParser = modelParser;
    }

    @Override
    public List<PartViewForTable> getAllParts()
    {

        List<PartViewForTable> collect = this.partRepository.getAll().stream().map(e -> this.modelParser.parse(e, PartViewForTable.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public PartEditBindingModel findPartToEditById(long id)
    {
        Part one = this.partRepository.findOne(id);
        PartEditBindingModel part = this.modelParser.parse(one, PartEditBindingModel.class);
        return part;
    }

    @Override
    @Transactional
    public void editPart(PartEditBindingModel partEditBindingModel, long id)
    {
        double price = partEditBindingModel.getPrice();
        int quantity = partEditBindingModel.getQuantity();
        this.partRepository.editById(price, quantity, id);
    }

    @Override
    public PartDeleteViewModel findPartToDeleteById(long id)
    {
        Part one = this.partRepository.findOne(id);
        PartDeleteViewModel parse = this.modelParser.parse(one, PartDeleteViewModel.class);
        return parse;
    }

    @Override
    @Transactional
    public void deleteById(long id)
    {
        this.partRepository.delete(id);
    }

    @Override
    @Transactional
    public void addPart(PartEditBindingModel partEditBindingModel)
    {
        Part part = this.modelParser.parse(partEditBindingModel, Part.class);
        this.partRepository.save(part);
    }
}

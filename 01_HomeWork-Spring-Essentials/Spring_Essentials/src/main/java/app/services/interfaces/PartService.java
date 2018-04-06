package app.services.interfaces;

import app.models.bindingModels.parts.PartEditBindingModel;
import app.models.viewModels.parts.PartDeleteViewModel;
import app.models.viewModels.parts.PartViewForTable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PartService
{
    List<PartViewForTable> getAllParts();

    PartEditBindingModel findPartToEditById(long id);

    @Transactional
    void editPart(PartEditBindingModel partEditBindingModel, long id);

    PartDeleteViewModel findPartToDeleteById(long id);

    @Transactional
    void deleteById(long id);

    @Transactional
    void addPart(PartEditBindingModel partEditBindingModel);
}

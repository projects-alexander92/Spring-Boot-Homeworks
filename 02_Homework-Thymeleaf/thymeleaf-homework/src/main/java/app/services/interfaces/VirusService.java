package app.services.interfaces;

import app.entities.dto.bindingModels.VirusBindingModel;
import app.entities.dto.bindingModels.VirusEditBindingModel;
import app.entities.dto.viewModels.VirusDeleteViewModel;
import app.entities.dto.viewModels.VirusTableViewModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VirusService
{
    @Transactional
    void save(VirusBindingModel virusBindingModel);

    List<VirusTableViewModel> getAll();

    VirusEditBindingModel getVirusForEdit(long id);

    void editVirus(VirusEditBindingModel virusEditBindingModel, long id);

    void deleteVirus(long id);

    VirusDeleteViewModel getVirusForDelete(long id);

    String getAsGeoJson();
}

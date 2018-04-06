package app.controllers;

import app.entities.dto.bindingModels.viruses.VirusBindingModel;
import app.entities.dto.bindingModels.viruses.VirusEditBindingModel;
import app.entities.dto.viewModels.VirusDeleteViewModel;
import app.entities.dto.viewModels.VirusTableViewModel;
import app.entities.orm.enums.Magnitude;
import app.entities.orm.enums.Mutation;
import app.services.interfaces.CapitalService;
import app.services.interfaces.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VirusController
{
    private final VirusService virusService;
    private final CapitalService capitalService;

    @Autowired
    public VirusController(VirusService virusService, CapitalService capitalService)
    {
        this.virusService = virusService;
        this.capitalService = capitalService;
    }

    @ModelAttribute(name = "mutations")
    public List<String> getMutations()
    {
        List<String> mutationList = new ArrayList<>();
        Mutation[] mutations = Mutation.values();
        for (Mutation mutation : mutations)
        {
            mutationList.add(mutation.toString());
        }

        return mutationList;
    }

    @ModelAttribute(name = "magnitudes")
    public List<String> getMagnitudes()
    {
        List<String> magnitudesList = new ArrayList<>();
        Magnitude[] magnitudes = Magnitude.values();
        for (Magnitude magnitude : magnitudes)
        {
            magnitudesList.add(magnitude.toString());
        }
        return magnitudesList;

    }

    @ModelAttribute(name = "capitals")
    public List<String> getCapitalNames()
    {
        return this.capitalService.getAllCapitalNames();
    }

    @GetMapping("/viruses/add")
    public String showVirusAddView(@ModelAttribute VirusBindingModel virusBindingModel)
    {
        return "viruses/virus-add-view";
    }

    @PostMapping("/viruses/add")
    public String submitAddVirusForm(@Valid @ModelAttribute VirusBindingModel virusBindingModel, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "viruses/virus-add-view";
        } else
        {
            this.virusService.save(virusBindingModel);
            return "redirect:/viruses/show";
        }
    }

    @GetMapping("/viruses/show")
    public String showVirusTableView(Model model)
    {
        List<VirusTableViewModel> all = this.virusService.getAll();
        model.addAttribute("viruses", all);
        return "viruses/virus-table-view";
    }

    @GetMapping("/viruses/edit/{id}")
    public String showVirusEditView(@PathVariable long id, Model model)
    {

        VirusEditBindingModel virusForEdit = this.virusService.getVirusForEdit(id);
        model.addAttribute("virusEditBindingModel", virusForEdit);
        return "viruses/virus-edit-view";
    }

    @PostMapping("/viruses/edit/{id}")
    public String showVirusEditView(@Valid @ModelAttribute VirusEditBindingModel virusEditBindingModel, BindingResult bindingResult, @PathVariable long id)
    {
        if (bindingResult.hasErrors())
        {
            return "viruses/virus-edit-view";
        } else
        {
            this.virusService.editVirus(virusEditBindingModel, id);
            return "redirect:/viruses/show";
        }
    }

    @GetMapping("/viruses/delete/{id}")
    public String showVirusDeleteView(@PathVariable long id, Model model)
    {
        VirusDeleteViewModel virusDeleteViewModel = this.virusService.getVirusForDelete(id);
        model.addAttribute("virus", virusDeleteViewModel);
        return "viruses/virus-delete-view";
    }
    @PostMapping("/viruses/delete/{id}")
    public String submitVirusDeleteForm(@PathVariable long id)
    {
        this.virusService.deleteVirus(id);
        return "redirect:/viruses/show";
    }
}

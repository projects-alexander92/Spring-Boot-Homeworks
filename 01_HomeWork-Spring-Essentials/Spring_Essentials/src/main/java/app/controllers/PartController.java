package app.controllers;

import app.models.bindingModels.parts.PartEditBindingModel;
import app.models.viewModels.parts.PartDeleteViewModel;
import app.models.viewModels.parts.PartViewForTable;
import app.services.interfaces.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PartController
{
    private final PartService partService;

    @Autowired
    public PartController(PartService partService)
    {
        this.partService = partService;
    }

    @GetMapping("/parts/all")
    public String getPartView(Model model)
    {
        List<PartViewForTable> allParts = this.partService.getAllParts();
        model.addAttribute("parts", allParts);
        return "parts/part-home-view";
    }

    @GetMapping("/parts/edit/{id}")
    public String showPartEditView(Model model, @PathVariable long id)
    {
        PartEditBindingModel part = this.partService.findPartToEditById(id);
        model.addAttribute("part", part);
        return "parts/part-edit-view";
    }

    @PostMapping("/parts/edit/{id}")
    public String submitCarEditForm(@ModelAttribute PartEditBindingModel partEditBindingModel, @PathVariable long id)
    {
        this.partService.editPart(partEditBindingModel, id);
        return "redirect:/parts/all";
    }

    @GetMapping("/parts/delete/{id}")
    public String showPartDeleteView(@PathVariable long id, Model model)
    {
        PartDeleteViewModel partToDeleteById = this.partService.findPartToDeleteById(id);
        model.addAttribute("part", partToDeleteById);
        return "parts/part-delete-view";
    }

    @PostMapping("/parts/delete/{id}")
    public String submitPartDeleteForm(@PathVariable long id)
    {
        this.partService.deleteById(id);
        return "redirect:/parts/all";
    }

    @GetMapping("/parts/add")
    public String showAddPartView(Model model)
    {
        model.addAttribute("part", new PartEditBindingModel());
        return "parts/part-add-view";
    }

    @PostMapping("/parts/add")
    public String submitAddPartView(@ModelAttribute PartEditBindingModel partEditBindingModel)
    {
        this.partService.addPart(partEditBindingModel);
        return "redirect:/parts/all";
    }
}

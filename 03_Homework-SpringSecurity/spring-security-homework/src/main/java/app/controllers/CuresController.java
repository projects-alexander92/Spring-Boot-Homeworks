package app.controllers;

import app.entities.dto.viewModels.VirusDeleteViewModel;
import app.services.interfaces.VirusService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CuresController
{
    private final VirusService virusService;

    public CuresController(VirusService virusService)
    {
        this.virusService = virusService;
    }

    @GetMapping("/cures/all")
    public String showCuresTable(Model model)
    {
        model.addAttribute("viruses", this.virusService.getAll());
        return "cures/cure-table-view";
    }

    @GetMapping("cures/delete/{id}")
    public String showDeleteCureView(@PathVariable long id, Model model)
    {
        VirusDeleteViewModel virusForDelete = this.virusService.getVirusForDelete(id);
        model.addAttribute("virus", virusForDelete);
        return "viruses/virus-delete-view";
    }

    @PostMapping("cures/delete/{id}")
    public String submitVirusDeleteForm(@PathVariable long id)
    {
        this.virusService.deleteVirus(id);
        return "redirect:/cures/all";
    }
}

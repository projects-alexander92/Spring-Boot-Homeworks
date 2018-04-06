package app.services.impl;

import app.entities.dto.bindingModels.viruses.VirusBindingModel;
import app.entities.dto.bindingModels.viruses.VirusEditBindingModel;
import app.entities.dto.viewModels.VirusDeleteViewModel;
import app.entities.dto.viewModels.VirusTableViewModel;
import app.entities.orm.Capital;
import app.entities.orm.Virus;
import app.parser.ModelParser;
import app.repositories.CapitalRepository;
import app.repositories.VirusRepository;
import app.services.interfaces.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl implements VirusService
{
    private final VirusRepository virusRepository;
    private final ModelParser modelParser;
    private final CapitalRepository capitalRepository;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository, ModelParser modelParser, CapitalRepository capitalRepository)
    {
        this.virusRepository = virusRepository;
        this.modelParser = modelParser;
        this.capitalRepository = capitalRepository;
    }

    @Override
    @Transactional
    public void save(VirusBindingModel virusBindingModel)
    {
        Virus virus = this.modelParser.parse(virusBindingModel, Virus.class);
        List<Capital> capitals = this.capitalRepository.findByNameIn(virusBindingModel.getCapitals());
        virus.setCapitals(capitals);
        this.virusRepository.save(virus);
        System.out.println("asdas");
    }

    @Override
    public List<VirusTableViewModel> getAll()
    {
        List<Virus> all = this.virusRepository.getAllBy();
        List<VirusTableViewModel> viruses = all.stream().map(e -> this.modelParser.parse(e, VirusTableViewModel.class)).collect(Collectors.toList());
        return viruses;
    }

    @Override
    public VirusEditBindingModel getVirusForEdit(long id)
    {
        Virus one = this.virusRepository.findOne(id);
        VirusEditBindingModel virus = this.modelParser.parse(one, VirusEditBindingModel.class);
        virus.setCapitals(new ArrayList<>());
        for (Capital capital : one.getCapitals())
        {
            virus.getCapitals().add(capital.getName());
        }
        return virus;
    }

    @Override
    public void editVirus(VirusEditBindingModel virusEditBindingModel, long id)
    {
        Virus virus = this.modelParser.parse(virusEditBindingModel, Virus.class);
        Date releasedOn = this.virusRepository.findOne(id).getReleasedOn();
        virus.setId(id);
        virus.setCapitals(new ArrayList<>());
        virus.setReleasedOn(releasedOn);
        List<Capital> capitals = this.capitalRepository.findByNameIn(virusEditBindingModel.getCapitals());
        virus.setCapitals(capitals);
        this.virusRepository.save(virus);
    }

    @Override
    public void deleteVirus(long id)
    {
        this.virusRepository.delete(id);
    }

    @Override
    public VirusDeleteViewModel getVirusForDelete(long id)
    {
        Virus one = this.virusRepository.findOne(id);
        VirusDeleteViewModel virus = this.modelParser.parse(one, VirusDeleteViewModel.class);
        return virus;
    }

    @Override
    public String getAsGeoJson()
    {
        List<Virus> viruses = this.virusRepository.getAllBy();
        StringBuilder geoJson = new StringBuilder();
        String header = "{\n" +
                "    \"type\": \"FeatureCollection\",\n" +
                "    \"features\": [\n";
        String footer = "]\n" +
                "}\n";
        geoJson.append(header);
        StringJoiner joiner = new StringJoiner(",");
        for (Virus virus : viruses)
        {
            String color = "#f00";
            int magnitude = 0;
            switch (virus.getMagnitude())
            {
                case LOW:
                    magnitude = 4;
                    break;
                case Medium:
                    magnitude = 5;
                    break;
                case HIGH:
                    magnitude = 6;
                    break;
            }

            for (Capital capital : virus.getCapitals())
            {
                String body = String.format("{\n" +
                        "        \"type\": \"Feature\",\n" +
                        "        \"properties\": {\n" +
                        "            \"mag\": %d,\n" +
                        "            \"color\": \"%s\"\n" +
                        "        },\n" +
                        "        \"geometry\": {\n" +
                        "            \"type\": \"Point\",\n" +
                        "            \"coordinates\": [\n" +
                        "                %f,\n" +
                        "                %f\n" +
                        "            ]\n" +
                        "        }\n" +
                        "    }\n", magnitude, color, capital.getLatitude(), capital.getLongitude());
                joiner.add(body);
            }
        }

        geoJson.append(joiner);
        geoJson.append(footer);

        return geoJson.toString();
    }
}

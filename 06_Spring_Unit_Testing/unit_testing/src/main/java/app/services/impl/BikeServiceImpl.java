package app.services.impl;

import app.entities.orm.Bike;
import app.entities.viewModels.BikeViewModel;
import app.errors.BikeNotFoundException;
import app.parser.ModelParser;
import app.repositories.BikeRepository;
import app.services.interfaces.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BikeServiceImpl implements BikeService
{

    private final ModelParser modelParser;

    private final BikeRepository bikeRepository;

    @Autowired
    public BikeServiceImpl(ModelParser modelParser, BikeRepository bikeRepository)
    {
        this.modelParser = modelParser;
        this.bikeRepository = bikeRepository;
    }

    @Override
    public BikeViewModel findById(long id)
    {
        Bike bike = this.bikeRepository.findOne(id);
        if (bike == null)
        {
            throw new BikeNotFoundException();
        }
        BikeViewModel bikeViewModel = this.modelParser.parse(bike, BikeViewModel.class);
        return bikeViewModel;
    }

    @Override
    public List<BikeViewModel> findAll()
    {
        List<BikeViewModel> bikeViewModels = this.bikeRepository.findAll().stream().map(e -> this.modelParser.parse(e, BikeViewModel.class)).collect(Collectors.toList());
        return bikeViewModels;
    }

    @Override
    public Page<BikeViewModel> findAll(Pageable pageable)
    {
        Page<Bike> bikes = this.bikeRepository.findAll(pageable);
        List<BikeViewModel> bikeViewModelList = new ArrayList<>();
        for (Bike bike : bikes)
        {
            bikeViewModelList.add(this.modelParser.parse(bike,BikeViewModel.class));
        }
        Page<BikeViewModel> bikeViewModels = new PageImpl<>(bikeViewModelList, pageable, bikes.getTotalElements());
        return bikeViewModels;
    }
}

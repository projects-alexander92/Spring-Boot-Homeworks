package app.services.interfaces;

import app.entities.viewModels.BikeViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BikeService
{

    BikeViewModel findById(long id);

    List<BikeViewModel> findAll();

    Page<BikeViewModel> findAll(Pageable pageable);
}

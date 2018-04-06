package app.services.impl;

import app.repositories.CapitalRepository;
import app.services.interfaces.CapitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapitalServiceImpl implements CapitalService
{
    private final CapitalRepository capitalRepository;

    @Autowired
    public CapitalServiceImpl(CapitalRepository capitalRepository)
    {
        this.capitalRepository = capitalRepository;
    }

    @Override
    public List<String> getAllCapitalNames()
    {
        return this.capitalRepository.getCapitalNames();
    }
}

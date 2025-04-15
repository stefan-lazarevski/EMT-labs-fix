package mk.ukim.finki.labb.service.application.impl;

import mk.ukim.finki.labb.dto.CreateHostDto;
import mk.ukim.finki.labb.dto.CreateHousingDto;
import mk.ukim.finki.labb.dto.DisplayHostDto;
import mk.ukim.finki.labb.dto.DisplayHousingDto;
import mk.ukim.finki.labb.model.domain.Host;
import mk.ukim.finki.labb.repository.HostRepository;
import mk.ukim.finki.labb.service.application.HostApplicationService;
import mk.ukim.finki.labb.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostService hostService;

    public HostApplicationServiceImpl(HostService hostService) {
        this.hostService = hostService;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return hostService.findAll()
                .stream()
                .map(DisplayHostDto::from)
                .toList();
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id)
                .map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto host) {
        return hostService.save(host.toHost())
                .map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto host) {
        return hostService.update(id, host.toHost())
                .map(DisplayHostDto::from);
    }

    @Override
    public void deleteById(Long id) {
        hostService.deleteById(id);
    }


}

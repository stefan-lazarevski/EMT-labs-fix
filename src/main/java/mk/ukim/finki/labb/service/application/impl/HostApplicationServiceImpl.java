package mk.ukim.finki.labb.service.application.impl;

import mk.ukim.finki.labb.dto.CreateHostDto;
import mk.ukim.finki.labb.dto.DisplayHostDto;
import mk.ukim.finki.labb.events.HostChangedEvent;
import mk.ukim.finki.labb.service.application.HostApplicationService;
import mk.ukim.finki.labb.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostService hostService;
    private final ApplicationEventPublisher eventPublisher;

    public HostApplicationServiceImpl(HostService hostService, ApplicationEventPublisher eventPublisher) {
        this.hostService = hostService;
        this.eventPublisher = eventPublisher;
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
//        return hostService.save(host.toHost())
//                .map(DisplayHostDto::from);

        Optional<DisplayHostDto> result = hostService.save(host.toHost())
                .map(DisplayHostDto::from);
        result.ifPresent(r -> eventPublisher.publishEvent(new HostChangedEvent(this)));
        return result;
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto host) {
//        return hostService.update(id, host.toHost())
//                .map(DisplayHostDto::from);

        Optional<DisplayHostDto> result = hostService.update(id, host.toHost())
                .map(DisplayHostDto::from);
        result.ifPresent(r -> eventPublisher.publishEvent(new HostChangedEvent(this)));
        return result;
    }

    @Override
    public void deleteById(Long id) {
        hostService.deleteById(id);
        eventPublisher.publishEvent(new HostChangedEvent(this));
    }


}

package mk.ukim.finki.labb.service.domain.Impl;

import mk.ukim.finki.labb.model.domain.Host;
import mk.ukim.finki.labb.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    @Override
    public List<Host> findAll() {
        return null;
    }

    @Override
    public Optional<Host> save(Host host) {
        return Optional.empty();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}

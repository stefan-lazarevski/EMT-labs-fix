package mk.ukim.finki.labb.service.domain.Impl;

import mk.ukim.finki.labb.model.domain.Host;
import mk.ukim.finki.labb.repository.HostCountViewRepository;
import mk.ukim.finki.labb.repository.HostRepository;
import mk.ukim.finki.labb.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final HostCountViewRepository hostCountViewRepository;

    public HostServiceImpl(HostRepository hostRepository, HostCountViewRepository hostCountViewRepository) {
        this.hostRepository = hostRepository;
        this.hostCountViewRepository = hostCountViewRepository;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> save(Host host) {
        Optional<Host> savedHost = Optional.empty();
        this.refreshMaterializedView();
        return savedHost;
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return hostRepository.findById(id).map(existingHost -> {
            if(host.getName() != null) {
                existingHost.setName(host.getName());
            }
            if(host.getSurname() != null) {
                existingHost.setSurname(host.getSurname());
            }
            if(host.getCountry() != null) {
                existingHost.setCountry(host.getCountry());
            }
            Host updateHost = hostRepository.save(existingHost);
            this.refreshMaterializedView();
//           return hostRepository.save(existingHost);
            return updateHost;
        });
    }

    @Override
    public void deleteById(Long id) {
        hostRepository.deleteById(id);
    }

    @Override
    public void refreshMaterializedView() {
        hostCountViewRepository.refreshMaterializedView();
    }
}

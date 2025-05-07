package mk.ukim.finki.labb.listeners;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mk.ukim.finki.labb.events.HostChangedEvent;
import mk.ukim.finki.labb.repository.HostCountViewRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostListener {

    private final HostCountViewRepository hostCountViewRepository;

    public HostListener(HostCountViewRepository hostCountViewRepository) {
        this.hostCountViewRepository = hostCountViewRepository;
    }

    @EventListener
    public void onHostChanged(HostChangedEvent event) {
        hostCountViewRepository.refreshMaterializedView();
    }
}

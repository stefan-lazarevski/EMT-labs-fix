package mk.ukim.finki.labb.repository;

import mk.ukim.finki.labb.model.domain.Host;
import mk.ukim.finki.labb.model.projections.HostNameView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    List<HostNameView> findAllBy(); // Uses projection automatically
}

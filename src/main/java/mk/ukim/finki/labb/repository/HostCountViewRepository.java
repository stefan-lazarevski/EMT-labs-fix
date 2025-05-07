package mk.ukim.finki.labb.repository;

import mk.ukim.finki.labb.model.views.HostCountView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HostCountViewRepository extends JpaRepository<HostCountView, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW host_count_by_country", nativeQuery = true)
    void refreshMaterializedView();
}

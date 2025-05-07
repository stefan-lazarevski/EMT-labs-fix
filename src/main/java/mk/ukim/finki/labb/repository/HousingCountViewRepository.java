package mk.ukim.finki.labb.repository;


import mk.ukim.finki.labb.model.views.HousingCountView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public interface HousingCountViewRepository extends JpaRepository<HousingCountView, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW housing_count_by_host", nativeQuery = true)
    void refreshMaterializedView();

}

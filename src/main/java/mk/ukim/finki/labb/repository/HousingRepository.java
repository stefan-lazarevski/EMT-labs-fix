package mk.ukim.finki.labb.repository;

import mk.ukim.finki.labb.model.domain.Housing;
import mk.ukim.finki.labb.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HousingRepository extends JpaRepository<Housing, Long> {

    @Query("SELECT h FROM Housing h WHERE " +
            "(:name IS NULL OR LOWER(h.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:category IS NULL OR h.category = :category) AND " +
            "(:hostId IS NULL OR h.host.id = :hostId) AND " +
            "(:numRooms IS NULL OR h.numRooms = :numRooms)")
    List<Housing> findByFilters(
            @Param("name") String name,
            @Param("category") Category category,
            @Param("hostId") Long hostId,
            @Param("numRooms") Integer numRooms);
}

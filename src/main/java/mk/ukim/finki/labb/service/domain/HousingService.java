package mk.ukim.finki.labb.service.domain;

import mk.ukim.finki.labb.model.domain.Housing;

import java.util.List;
import java.util.Optional;

public interface HousingService {


    List<Housing> findAll();

    Optional<Housing> findById(Long id);

    Optional<Housing> update(Long id, Housing housing);

    Optional<Housing> save(Housing housing);

    void deleteById(Long id);

    Optional<Housing> rentHouse(Long houseId);

}

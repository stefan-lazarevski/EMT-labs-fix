package mk.ukim.finki.labb.service;
import mk.ukim.finki.labb.model.Housing;
import mk.ukim.finki.labb.model.dto.HousingDto;

import java.util.List;
import java.util.Optional;

public interface HousingService {

    List<Housing> findAll();

    Optional<Housing> findById(Long id);

    Optional<Housing> update(Long id, HousingDto housing);

    Optional<Housing> save(HousingDto housing);

    void deleteById(Long id);

    Optional<Housing> rentHouse(Long houseId);
}

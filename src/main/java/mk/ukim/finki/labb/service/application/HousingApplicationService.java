package mk.ukim.finki.labb.service.application;
import mk.ukim.finki.labb.dto.CreateHousingDto;
import mk.ukim.finki.labb.dto.DisplayHousingDto;

import java.util.List;
import java.util.Optional;

public interface HousingApplicationService {

    List<DisplayHousingDto> findAll();

    Optional<DisplayHousingDto> findById(Long id);

    Optional<DisplayHousingDto> update(Long id, CreateHousingDto housing);

    Optional<DisplayHousingDto> save(CreateHousingDto housing);

    void deleteById(Long id);

    Optional<DisplayHousingDto> rentHouse(Long houseId);
}

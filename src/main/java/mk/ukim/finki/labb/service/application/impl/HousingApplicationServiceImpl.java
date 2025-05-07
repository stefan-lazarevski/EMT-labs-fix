package mk.ukim.finki.labb.service.application.impl;

import mk.ukim.finki.labb.dto.CreateHousingDto;
import mk.ukim.finki.labb.dto.DisplayHousingDto;
import mk.ukim.finki.labb.model.enums.Category;
import mk.ukim.finki.labb.service.application.HousingApplicationService;
import mk.ukim.finki.labb.service.domain.HousingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HousingApplicationServiceImpl implements HousingApplicationService {

    private final HousingService housingService;

    public HousingApplicationServiceImpl(HousingService housingService) {
        this.housingService = housingService;
    }

    @Override
    public List<DisplayHousingDto> findAll() {
        return housingService.findAll()
                .stream()
                .map(DisplayHousingDto::from)
                .toList();
    }

    @Override
    public Optional<DisplayHousingDto> findById(Long id) {
        return housingService.findById(id)
                .map(DisplayHousingDto::from);
    }

    @Override
    public Optional<DisplayHousingDto> save(CreateHousingDto housing) {
        return housingService.save(housing.toHousing())
                .map(DisplayHousingDto::from);
    }

    @Override
    public Optional<DisplayHousingDto> update(Long id, CreateHousingDto housing) {
        return housingService.update(id, housing.toHousing())
                .map(DisplayHousingDto::from);
    }

    @Override
    public Optional<DisplayHousingDto> rentHouse(Long houseId) {
        return housingService.rentHouse(houseId)
                .map(DisplayHousingDto::from);
    }

    @Override
    public void deleteById(Long id) {
        housingService.deleteById(id);
    }

    @Override
    public List<DisplayHousingDto> search(String name, Category category, Long hostId, Integer numRooms) {
        return housingService.findByFilters(name, category, hostId, numRooms)
                .stream()
                .map(DisplayHousingDto::from)
                .toList();
    }
}

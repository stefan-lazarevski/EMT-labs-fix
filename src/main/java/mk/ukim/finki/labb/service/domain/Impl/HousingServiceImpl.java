package mk.ukim.finki.labb.service.domain.Impl;


import mk.ukim.finki.labb.model.domain.Host;
import mk.ukim.finki.labb.model.domain.Housing;
import mk.ukim.finki.labb.repository.CountryRepository;
import mk.ukim.finki.labb.repository.HostRepository;
import mk.ukim.finki.labb.repository.HousingRepository;
import mk.ukim.finki.labb.service.domain.HousingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HousingServiceImpl implements HousingService {

    private final HousingRepository housingRepository;
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;


    public HousingServiceImpl(HousingRepository housingRepository, HostRepository hostRepository, CountryRepository countryRepository) {
        this.housingRepository = housingRepository;
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Housing> findAll() {
        return this.housingRepository.findAll();
    }

    @Override
    public Optional<Housing> findById(Long id) {
        return this.housingRepository.findById(id);
    }

    @Override
    public Optional<Housing> update(Long id, Housing housingDto) {
        return housingRepository.findById(id).map(existingHouse -> {
            if (housingDto.getName() != null) {
                existingHouse.setName(housingDto.getName());
            }
            if (housingDto.getCategory() != null) {
                existingHouse.setCategory(housingDto.getCategory());
            }
            if (housingDto.getHost().getId() != null && hostRepository.findById(housingDto.getHost().getId()).isPresent()) {
                existingHouse.setHost(hostRepository.findById(housingDto.getHost().getId()).get());
            }
            if (housingDto.getNumRooms() != null) {
                existingHouse.setNumRooms(housingDto.getNumRooms());
            }

            return housingRepository.save(existingHouse);
        });
    }

    @Override
    public Optional<Housing> save(Housing housing) {
        Optional<Host> host = hostRepository.findById(housing.getHost().getId());
        return host.map(houseHost -> housingRepository.save
                (new Housing(housing.getName(), housing.getCategory(),hostRepository.findById(housing.getHost().getId()).get(), housing.getNumRooms())));
    }

    @Override
    public Optional<Housing> rentHouse(Long houseId) {
        return housingRepository.findById(houseId).map(house -> {
            if (house.getNumRooms() > 0) {
                house.setNumRooms(house.getNumRooms() - 1);
                housingRepository.save(house);
                return house;
            } else {
                throw new IllegalStateException("No available houses left.");
            }
        });
    }


    @Override
    public void deleteById(Long id) {
        housingRepository.deleteById(id);
    }
}

package mk.ukim.finki.labb.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.labb.model.Country;
import mk.ukim.finki.labb.model.Host;
import mk.ukim.finki.labb.model.Housing;
import mk.ukim.finki.labb.repository.CountryRepository;
import mk.ukim.finki.labb.repository.HostRepository;
import mk.ukim.finki.labb.repository.HousingRepository;
import org.springframework.stereotype.Component;
import mk.ukim.finki.labb.model.enumerations.Category;

import java.util.List;

@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final HousingRepository housingRepository;

    public DataInitializer(CountryRepository countryRepository, HostRepository hostRepository, HousingRepository housingRepository) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.housingRepository = housingRepository;
    }

    @PostConstruct
    public void initData() {
        if (countryRepository.count() == 0) {
            Country usa = new Country("USA", "North America");
            Country uk = new Country("United Kingdom", "Europe");
            Country macedonia = new Country("Macedonia", "Europe");
            countryRepository.saveAll(List.of(usa, uk, macedonia));

            Host host1 = new Host("Stefan", "Lazarevski", usa);
            Host host2 = new Host("John", "Smith", uk);
            Host host3 = new Host("Deji", "Olatunji", macedonia);
            hostRepository.saveAll(List.of(host1, host2, host3));

            Housing housing1 = new Housing("The Blues", Category.ROOM, host1, 50);
            Housing housing2 = new Housing("Aqua paradise", Category.HOUSE, host2, 20);
            Housing housing3 = new Housing("Summer Heaven", Category.FLAT, host3, 10);
            housingRepository.saveAll(List.of(housing1, housing2, housing3));
        }
    }
}

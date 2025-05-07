package mk.ukim.finki.labb.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.labb.dto.CreateCountryDto;
import mk.ukim.finki.labb.dto.DisplayCoutryDto;
import mk.ukim.finki.labb.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    private final CountryApplicationService countryService;

    public CountryController(CountryApplicationService countryService) {
        this.countryService = countryService;
    }

    @Operation(summary = "Get all countries", description = "Returns a list of all available countries.")
    @GetMapping
    public List<DisplayCoutryDto> findall() {
        return this.countryService.findAll();
    }

    @Operation(
            summary = "Find country by ID",
            description = "Returns the country with the specified ID. Responds with 404 if not found."
    )
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCoutryDto> findById(@PathVariable Long id) {
        return countryService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create a new country",
            description = "Adds a new country to the system."
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayCoutryDto> save(@RequestBody CreateCountryDto country) {
        return countryService.save(country)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update a country",
            description = "Updates a country's information using the provided ID."
    )
    @PatchMapping("/update/{id}")
    public ResponseEntity<DisplayCoutryDto> update(@PathVariable Long id, @RequestBody CreateCountryDto country) {
        return countryService.update(id, country)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete a country",
            description = "Deletes a country from the system using its ID."
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (countryService.findById(id).isPresent()) {
            countryService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

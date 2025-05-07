package mk.ukim.finki.labb.web.controllers;


import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.labb.dto.CreateHousingDto;
import mk.ukim.finki.labb.dto.DisplayHousingDto;
import mk.ukim.finki.labb.model.enums.Category;
import mk.ukim.finki.labb.model.views.HousingCountView;
import mk.ukim.finki.labb.repository.HousingCountViewRepository;
import mk.ukim.finki.labb.service.application.HousingApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/housings")
public class HousingController {

    private final HousingApplicationService housingService;
    private final HousingCountViewRepository housingCountViewRepository;

    public HousingController(HousingApplicationService housingService, HousingCountViewRepository housingCountViewRepository) {
        this.housingService = housingService;
        this.housingCountViewRepository = housingCountViewRepository;
    }

    @Operation(
            summary = "Get all housing entries",
            description = "Returns a list of all available housing listings."
    )
    @GetMapping
    public List<DisplayHousingDto> findall(){
        return this.housingService.findAll();
    }

    @Operation(
            summary = "Find housing by ID",
            description = "Returns a specific housing entry by its ID. Responds with 404 if not found."
    )
    @GetMapping("/{id}")
    public ResponseEntity<DisplayHousingDto> findById(@PathVariable Long id) {
        return housingService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create new housing entry",
            description = "Adds a new housing listing with the provided information."
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayHousingDto> save(@RequestBody CreateHousingDto housing) {
        return housingService.save(housing)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Rent a housing unit",
            description = "Rents one room from the housing unit with the given ID. If no rooms are left, returns a bad request."
    )
    @PatchMapping("/{id}/rent")
    public ResponseEntity<?> rent(@PathVariable Long id) {
        try {
            DisplayHousingDto bookedHousing = housingService.rentHouse(id).orElseThrow();
            return ResponseEntity.ok(bookedHousing);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @Operation(
            summary = "Update a housing entry",
            description = "Updates the details of a housing listing by ID."
    )
    @PatchMapping("/update/{houseId}")
    public ResponseEntity<DisplayHousingDto> update(@PathVariable Long houseId,@RequestBody CreateHousingDto housing) {
        return housingService.update(houseId, housing)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete a housing entry",
            description = "Deletes the housing listing with the specified ID. Returns 404 if not found."
    )
    @DeleteMapping("/delete/{houseId}")
    public ResponseEntity<Void> delete(@PathVariable Long houseId) {
        if (housingService.findById(houseId).isPresent()) {
            housingService.deleteById(houseId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    @Operation(
            summary = "Search housing entries",
            description = "Returns housing entries filtered by name, category, host, and number of rooms. All parameters are optional."
    )
    @GetMapping("/search")
    public List<DisplayHousingDto> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Long hostId,
            @RequestParam(required = false) Integer numRooms) {
        return housingService.search(name, category, hostId, numRooms);
    }

    @GetMapping("/by-host")
    public List<HousingCountView> getHousingByHost() {
        return housingCountViewRepository.findAll();
    }

}

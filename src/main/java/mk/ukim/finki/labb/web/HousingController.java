package mk.ukim.finki.labb.web;


import mk.ukim.finki.labb.model.Housing;
import mk.ukim.finki.labb.model.dto.HousingDto;
import mk.ukim.finki.labb.service.HousingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/housing")
public class HousingController {

    private final HousingService housingService;

    public HousingController(HousingService housingService) {
        this.housingService = housingService;
    }

    @GetMapping
    public List<Housing> findall(){
        return this.housingService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Housing> findById(@PathVariable Long id) {
        return housingService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Housing> save(@RequestBody HousingDto housing) {
        return housingService.save(housing)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @PatchMapping("/{houseId}/rent")
//    public ResponseEntity<Housing> rent(@PathVariable Long houseId) {
//        return housingService.rentHouse(houseId).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
//    }


    @PatchMapping("/{id}/rent")
    public ResponseEntity<?> rent(@PathVariable Long id) {
        try {
            Housing bookedHousing = housingService.rentHouse(id).orElseThrow();
            return ResponseEntity.ok(bookedHousing);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PatchMapping("/update/{houseId}")
    public ResponseEntity<Housing> update(@PathVariable Long houseId,@RequestBody HousingDto housing) {
        return housingService.update(houseId, housing)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{houseId}")
    public ResponseEntity<Void> delete(@PathVariable Long houseId) {
        if (housingService.findById(houseId).isPresent()) {
            housingService.deleteById(houseId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}

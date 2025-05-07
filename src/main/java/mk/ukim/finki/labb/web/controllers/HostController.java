package mk.ukim.finki.labb.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.labb.dto.CreateHostDto;
import mk.ukim.finki.labb.dto.DisplayHostDto;
import mk.ukim.finki.labb.model.projections.HostNameView;
import mk.ukim.finki.labb.model.views.HostCountView;
import mk.ukim.finki.labb.repository.HostCountViewRepository;
import mk.ukim.finki.labb.repository.HostRepository;
import mk.ukim.finki.labb.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/host")
public class HostController {

    private final HostApplicationService hostService;
    private final HostCountViewRepository hostCountViewRepository;
    private final HostRepository hostRepository;

    public HostController(HostApplicationService hostService, HostCountViewRepository hostCountViewRepository, HostRepository hostRepository) {
        this.hostService = hostService;
        this.hostCountViewRepository = hostCountViewRepository;
        this.hostRepository = hostRepository;
    }

    @Operation(
            summary = "Get all hosts",
            description = "Returns a list of all registered hosts in the system."
    )
    @GetMapping
    public List<DisplayHostDto> findall(){
        return this.hostService.findAll();
    }

    @Operation(
            summary = "Find host by ID",
            description = "Returns a specific host by their ID. Responds with 404 if the host is not found."
    )
    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return hostService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create a new host",
            description = "Registers a new host with the provided details."
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto host) {
        return hostService.save(host)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update host by ID",
            description = "Updates the information of an existing host based on the given ID."
    )
    @PatchMapping("/update/{id}")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id, @RequestBody CreateHostDto host) {
        return hostService.update(id, host)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete a host",
            description = "Deletes a host from the system using their ID. Returns 404 if the host does not exist."
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (hostService.findById(id).isPresent()) {
            hostService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/by-country")
    public List<HostCountView> getHostCountByCountry() {
        return hostCountViewRepository.findAll();
    }

    @GetMapping("/names")
    public List<HostNameView> getHostNames() {
        return hostRepository.findAllBy();
    }
}

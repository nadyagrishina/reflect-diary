package com.nadyagrishina.pro2project.controller;

import com.nadyagrishina.pro2project.DTO.EntryRequestDTO;
import com.nadyagrishina.pro2project.DTO.EntryResponseDTO;
import com.nadyagrishina.pro2project.service.EntryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/entries")
public class EntryController {

    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping
    public List<EntryResponseDTO> getAllEntries() {
        return entryService.findAllEntries();
    }

    @GetMapping("/{id}")
    public EntryResponseDTO getEntryById(@PathVariable Long id) {
        return entryService.findById(id);
    }

    @PostMapping
    public EntryResponseDTO createEntry(@RequestBody @Valid EntryRequestDTO entryRequestDTO) {
        return entryService.save(entryRequestDTO);
    }

    @PutMapping("/{id}")
    public EntryResponseDTO updateEntry(@PathVariable Long id, @RequestBody @Valid EntryRequestDTO entryRequestDTO) {
        return entryService.updateEntry(id, entryRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id) {
        entryService.deleteById(id);
    }

    @GetMapping("/users/{userId}")
    public List<EntryResponseDTO> findAllEntriesByUserId(@PathVariable Long userId) {
        return entryService.findByUserId(userId);
    }

//    @GetMapping("/my")
//    public List<EntryResponseDTO> getMyEntries(Authentication auth) {
//        String username = auth.getName();
//        User user = userService.findByUsername(username);
//        return entryService.findByUserId(user.getId());
//    }

}

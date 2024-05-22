package com.example.prak15.controllers;

import com.example.prak15.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerRepository ownerRepository;
    private final ItemRepository itemRepository;

    @PostMapping("/owner/new")
    public Owner addOwner(@RequestBody Owner owner) {
        log.info("Saving owner");
        Long itemId = owner.getItem().getId();
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
        owner.setItem(item);
        return ownerRepository.saveAndFlush(owner);
    }

    @GetMapping("/owner/{id}")
    public Owner getOwnerById(@PathVariable Long id) {
        log.info("Showing owner by id");
        return ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
    }

    @GetMapping("/owners")
    public List<OwnerDTO> allOwners() {
        log.info("Showing owners");
        return ownerRepository.findAll().stream()
                .map(owner -> new OwnerDTO(owner.getId(), owner.getName()))
                .collect(Collectors.toList());
    }

}

package com.example.prak15;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OwnerController {

    private final OwnerRepository ownerRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public OwnerController(OwnerRepository ownerRepository, ItemRepository itemRepository) {
        this.ownerRepository = ownerRepository;
        this.itemRepository = itemRepository;
    }

    @PostMapping("/owner/new")
    public Owner addOwner(@RequestBody Owner owner) {
        Long itemId = owner.getItem().getId();
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
        owner.setItem(item);
        return ownerRepository.saveAndFlush(owner);
    }

    @GetMapping("/owner/{id}")
    public Owner getOwnerById(@PathVariable Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
    }

    @GetMapping("/owners")
    public List<OwnerDTO> allOwners() {
        return ownerRepository.findAll().stream()
                .map(owner -> new OwnerDTO(owner.getId(), owner.getName()))
                .collect(Collectors.toList());
    }

}

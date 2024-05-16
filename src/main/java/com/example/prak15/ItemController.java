package com.example.prak15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ItemController {

    private final ItemRepository itemRepository;
    private final OwnerRepository ownerRepository;
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);


    @Autowired
    public ItemController(ItemRepository itemRepository, OwnerRepository ownerRepository) {
        this.itemRepository = itemRepository;
        this.ownerRepository = ownerRepository;
    }

    @GetMapping("/items")
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @PostMapping("/items/new")
    public Item addItem(@RequestBody Item item) {
        return itemRepository.saveAndFlush(item);
    }

    @GetMapping("/items/{itemId}/owners")
    public List<OwnerDTO> getOwnersByItemId(@PathVariable Long itemId) {
        List<Owner> owners = ownerRepository.findByItemId(itemId);
        List<OwnerDTO> ownerDTOs = owners.stream()
                .map(owner -> new OwnerDTO(owner.getId(), owner.getName()))
                .collect(Collectors.toList());
        return ownerDTOs;
    }

}

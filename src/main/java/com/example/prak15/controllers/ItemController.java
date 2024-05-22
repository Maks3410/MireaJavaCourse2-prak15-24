package com.example.prak15.controllers;

import com.example.prak15.*;
import com.example.prak15.services.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemRepository itemRepository;
    private final OwnerRepository ownerRepository;
    private final ItemService itemService;
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @GetMapping("/items")
    @ResponseBody
    public List<Item> getItems(@RequestParam Map<String, Object> params) {
        log.info("Showing items");
        return itemService.filterItems(params);
    }


    @PostMapping("/items/new")
    public Item addItem(@RequestBody Item item) {
        log.info("Saving item");
        return itemRepository.saveAndFlush(item);
    }

    @GetMapping("/items/{itemId}/owners")
    public List<OwnerDTO> getOwnersByItemId(@PathVariable Long itemId) {
        log.info("Showing owners of item");
        List<Owner> owners = ownerRepository.findByItemId(itemId);
        List<OwnerDTO> ownerDTOs = owners.stream()
                .map(owner -> new OwnerDTO(owner.getId(), owner.getName()))
                .collect(Collectors.toList());
        return ownerDTOs;
    }

}

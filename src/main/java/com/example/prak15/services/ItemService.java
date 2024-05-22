package com.example.prak15.services;


import com.example.prak15.Item;
import com.example.prak15.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> filterItems(Map<String, Object> params) {
        return itemRepository.findByCriteria(params);
    }
}

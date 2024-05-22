package com.example.prak15;

import java.util.List;
import java.util.Map;

public interface ItemRepositoryWithFilter {
    List<Item> findByCriteria(Map<String, Object> params);
}

package com.example.prak15;



import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemRepositoryWithFilterImpl implements ItemRepositoryWithFilter {

    private final EntityManager entityManager;

    public ItemRepositoryWithFilterImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Item> findByCriteria(Map<String, Object> params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> query = cb.createQuery(Item.class);
        Root<Item> item = query.from(Item.class);

        List<Predicate> predicates = new ArrayList<>();

        params.forEach((field, value) -> {
            if (value != null) {
                switch (field) {
                    case "price" -> predicates.add(cb.equal(item.get(field), Double.valueOf((String) value)));
                    case "creationDate" -> predicates.add(cb.equal(item.get(field), (String) value));
                    default -> predicates.add(cb.like(item.get(field), "%" + value + "%"));
                }
            }
        });

        query.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }
}

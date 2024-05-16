package com.example.prak15;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
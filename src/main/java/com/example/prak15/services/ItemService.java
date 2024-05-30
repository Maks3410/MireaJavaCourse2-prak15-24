package com.example.prak15.services;


import com.example.prak15.Item;
import com.example.prak15.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;
    private final EmailService emailService;

    public List<Item> filterItems(Map<String, Object> params) {
//        log.info("Sending email....");
//        try {
//            emailService.sendEmail("maksim.maksimum@gmail.com", "Found items", itemRepository.findByCriteria(params).toString());
//        } catch (jakarta.mail.MessagingException e) {
//            e.printStackTrace();
//        }
        return itemRepository.findByCriteria(params);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}

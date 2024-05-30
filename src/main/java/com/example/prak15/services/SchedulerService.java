package com.example.prak15.services;

import com.example.prak15.Item;
import com.example.prak15.Owner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerService {

    @Value("${export.directory}")
    private String exportDirectory;

    private final ItemService itemService;
    private final OwnerService ownerService;

    public void clearDirectory() throws IOException {
        Files.walk(Paths.get(exportDirectory))
                .filter(Files::isRegularFile)
                .map(java.nio.file.Path::toFile)
                .forEach(File::delete);
    }

    // Метод для экспорта данных из базы данных в файлы
    public void exportData() throws IOException {
        // получение данных из базы данных
        List<Item> items = itemService.getAllItems();
        List<Owner> owners = ownerService.getAllOwners();

        // сохранение данных в файлы
        for (Item item : items) {
            String fileName = exportDirectory + File.separator + "Item" + item.getId() + ".txt";
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(item.toString());
            }
        }

        for (Owner owner : owners) {
            String fileName = exportDirectory + File.separator + "Owner" + owner.getId() + ".txt";
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(owner.toString());
            }
        }
    }

    @Scheduled(fixedRate = 30000)
    public void scheduledExport() throws IOException {
        clearDirectory();
        exportData();
        log.info("Data was exported");
    }

    public void exportDataViaJmx() throws IOException {
        clearDirectory();
        exportData();
    }
}
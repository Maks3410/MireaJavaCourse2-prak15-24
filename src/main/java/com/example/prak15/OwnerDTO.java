package com.example.prak15;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerDTO {
    private Long id;
    private String name;

    public OwnerDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

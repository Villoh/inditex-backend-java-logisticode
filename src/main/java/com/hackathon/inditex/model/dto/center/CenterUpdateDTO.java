package com.hackathon.inditex.model.dto.center;

import com.hackathon.inditex.model.dto.Coordinates;
import lombok.Data;

@Data
public class CenterUpdateDTO {
    private String name;
    private String capacity;
    private String status;
    private Integer currentLoad;
    private Integer maxCapacity;
    private Coordinates coordinates;
}

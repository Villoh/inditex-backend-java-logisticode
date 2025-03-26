package com.hackathon.inditex.model.dto.center;

import com.hackathon.inditex.model.dto.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CenterCreationDTO {
    private String name;
    private String capacity;
    private String status;
    private Integer currentLoad;
    private Integer maxCapacity;
    private Coordinates coordinates;
}

package com.hackathon.inditex.model.dto.center;

import com.hackathon.inditex.model.dto.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CenterDTO {
    private Long id;
    private String name;
    private String capacity;
    private String status;
    private Integer currentLoad;
    private Integer maxCapacity;
    private Coordinates coordinates;
}

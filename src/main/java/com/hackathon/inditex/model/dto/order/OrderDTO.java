package com.hackathon.inditex.model.dto.order;

import com.hackathon.inditex.model.dto.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private Long customerId;
    private String size;
    private String status;
    private String assignedCenter;
    private Coordinates coordinates;
}

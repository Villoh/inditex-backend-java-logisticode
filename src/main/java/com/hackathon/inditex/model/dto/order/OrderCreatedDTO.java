package com.hackathon.inditex.model.dto.order;

import com.hackathon.inditex.model.dto.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedDTO {

    private Long orderId;
    private Long customerId;
    private String size;
    private String assignedLogisticsCenter;
    private Coordinates coordinates;
    private String status;
    private String message;
}

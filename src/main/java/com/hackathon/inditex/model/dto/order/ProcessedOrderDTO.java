package com.hackathon.inditex.model.dto.order;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "distance", "orderId", "assignedLogisticsCenter", "message", "status" })
public class ProcessedOrderDTO {

    private Double distance;
    private Long orderId;
    private String assignedLogisticsCenter;
    private String status;
    private String message;
}

package com.hackathon.inditex.model.dto.order;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProcessedOrderFailedDTO extends ProcessedOrderDTO {
    private String message;

    public ProcessedOrderFailedDTO(Double distance, Long orderId, String assignedLogisticsCenter, String status, String message) {
        super(distance, orderId, assignedLogisticsCenter, status);
        this.message = message;
    }
}

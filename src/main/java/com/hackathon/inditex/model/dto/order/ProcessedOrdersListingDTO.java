package com.hackathon.inditex.model.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProcessedOrdersListingDTO {

    @JsonProperty("processed-orders")
    private List<ProcessedOrderDTO> processedOrders;
}

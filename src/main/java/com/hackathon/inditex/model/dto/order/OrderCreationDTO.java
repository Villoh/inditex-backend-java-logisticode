package com.hackathon.inditex.model.dto.order;

import com.hackathon.inditex.model.dto.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreationDTO {

    private Long customerId;
    private String size;
    private Coordinates coordinates;
}

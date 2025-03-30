package com.hackathon.inditex.controller;

import com.hackathon.inditex.model.dto.ResponseMessageDTO;
import com.hackathon.inditex.model.dto.center.CenterCreationDTO;
import com.hackathon.inditex.model.dto.center.CenterDTO;
import com.hackathon.inditex.model.dto.center.CenterUpdateDTO;
import com.hackathon.inditex.service.CenterService;
import com.hackathon.inditex.util.Constant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for managing centers.
 */
@RestController
@RequestMapping(Constant.Api.CENTERS_ENDPOINT)
@RequiredArgsConstructor
@Tag(name = "Center Controller", description = "Endpoints for managing centers")
public class CenterController {

    private final CenterService centerService;

    /**
     * Creates a new center.
     * @param centerCreationDto the center data
     * @return response message
     */
    @PostMapping
    @Operation(
            summary = "Create a center",
            description = "Creates a new center with the given details",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Center created successfully"),
                    @ApiResponse(responseCode = "500", description = "Invalid input data")
            }
    )
    public ResponseEntity<ResponseMessageDTO> createCenter(@RequestBody CenterCreationDTO centerCreationDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(centerService.createCenter(centerCreationDto));
    }

    /**
     * Retrieves all centers.
     * @return list of centers
     */
    @GetMapping
    @Operation(
            summary = "Get all centers",
            description = "Retrieves a list of all centers",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of centers retrieved successfully")
            }
    )
    public ResponseEntity<List<CenterDTO>> getAllCenters(){
        return ResponseEntity.ok(centerService.getAllCenters());
    }

    /**
     * Updates a center.
     * @param id the ID of the center to update
     * @param centerUpdateDto the new center data
     * @return response message
     */
    @PatchMapping("/{id}")
    @Operation(
            summary = "Update a center",
            description = "Updates an existing center with new data",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Center updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Center not found"),
                    @ApiResponse(responseCode = "500", description = "Invalid input data")
            }
    )
    public ResponseEntity<ResponseMessageDTO> updateCenter(@PathVariable Long id, @RequestBody CenterUpdateDTO centerUpdateDto){
        return ResponseEntity.ok(centerService.updateCenter(id, centerUpdateDto));
    }

    /**
     * Deletes a center.
     * @param id the ID of the center to delete
     * @return response message
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a center",
            description = "Deletes a center by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Center deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Center not found")
            }
    )
    public ResponseEntity<ResponseMessageDTO> deleteCenter(@PathVariable Long id){
        return ResponseEntity.ok(centerService.deleteCenter(id));
    }
}

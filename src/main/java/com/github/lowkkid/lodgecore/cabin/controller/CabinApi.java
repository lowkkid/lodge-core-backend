package com.github.lowkkid.lodgecore.cabin.controller;

import com.github.lowkkid.lodgecore.cabin.model.CabinCreateRequest;
import com.github.lowkkid.lodgecore.cabin.model.CabinDTO;
import com.github.lowkkid.lodgecore.cabin.model.CabinEditRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "Cabins", description = "API for managing lodge cabins")
public interface CabinApi {

    @Operation(
            summary = "Get all cabins",
            description = "Retrieves a list of all cabins with their details"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved list of cabins",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CabinDTO.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Invalid or missing authentication token",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            )
    })
    ResponseEntity<List<CabinDTO>> getAll();

    @Operation(
            summary = "Create a new cabin",
            description = "Creates a new cabin with the provided details. Optionally uploads an image to object MinIO "
                    + "The image URL will be automatically generated and stored with the cabin record."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Cabin successfully created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CabinDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request - validation failed (e.g., missing required fields, invalid values)",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Invalid or missing authentication token",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "503",
                    description = "Storage service unavailable - Failed to upload cabin image",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            )
    })
    ResponseEntity<CabinDTO> create(
            @Parameter(description = "Cabin creation request with cabin details and optional image file")
            CabinCreateRequest request
    );

    @Operation(
            summary = "Update an existing cabin",
            description = "Updates an existing cabin with the provided details. If a new image is provided, "
                    + "the old image is deleted from storage and replaced with the new one. "
                    + "If no image is provided, the existing image is preserved."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cabin successfully updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CabinDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request - validation failed (e.g., missing required fields, invalid values)",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Invalid or missing authentication token",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cabin not found with the specified ID",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "503",
                    description = "Storage service unavailable - Failed to upload/delete cabin image",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            )
    })
    ResponseEntity<CabinDTO> update(
            @Parameter(description = "Unique identifier of the cabin to update", required = true, example = "1")
            Long id,
            @Parameter(description = "Cabin update request with updated details and optional new image file")
            CabinEditRequest request
    );

    @Operation(
            summary = "Delete a cabin",
            description = "Deletes an existing cabin by its ID. If the cabin has an associated image, "
                    + "it will also be deleted from object storage."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Cabin successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Invalid or missing authentication token",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cabin not found with the specified ID",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "503",
                    description = "Storage service unavailable - Failed to delete cabin image",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            )
    })
    ResponseEntity<Void> delete(
            @Parameter(description = "Unique identifier of the cabin to delete", required = true, example = "1")
            Long id
    );
}

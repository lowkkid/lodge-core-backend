package com.github.lowkkid.lodgecore.setting.controller;

import com.github.lowkkid.lodgecore.setting.model.SettingDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Settings", description = "API for managing lodge application settings")
public interface SettingApi {

    @Operation(
            summary = "Get application settings",
            description = "Retrieves the current application settings including booking constraints "
                    + "(minimum/maximum booking length, maximum guests per booking) and breakfast pricing. "
                    + "The system maintains a single settings record that applies globally to all bookings."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved application settings",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SettingDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Invalid or missing authentication token",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Settings not found - No settings have been configured in the system",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            )
    })
    ResponseEntity<SettingDTO> get();

    @Operation(
            summary = "Update application settings",
            description = "Updates the application settings with the provided values. "
                    + "All fields are validated: minimum and maximum booking lengths must be at least 1 day, "
                    + "maximum guests per booking must be at least 1, and breakfast price cannot be negative. "
                    + "The existing settings record is updated in place, preserving the settings ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Settings successfully updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SettingDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request - validation failed (e.g. values less than minimum)",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Invalid or missing authentication token",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Settings not found - No settings exist to update",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "string"))
            )
    })
    ResponseEntity<SettingDTO> update(
            @RequestBody(
                    description = "Updated settings values. All fields except 'id' and 'createdAt' can be modified.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = SettingDTO.class))
            )
            SettingDTO settingDTO
    );
}

package com.github.lowkkid.lodgecore.guest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Guest information")
public class GuestDTO {

    @Schema(description = "Unique guest identifier", example = "1")
    private Long id;

    @Schema(description = "Full name of the guest", example = "John Doe")
    private String fullName;

    @Schema(description = "Email address of the guest", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Country information of the guest")
    private CountryDto country;

    @Schema(description = "National identification number", example = "AB1234567")
    private String nationalId;

    @Schema(description = "Guest record creation timestamp", example = "2025-01-15T09:30:00")
    private LocalDateTime createdAt;
}


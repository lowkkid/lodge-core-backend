package com.github.lowkkid.lodgecore.cabin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Cabin information")
public class CabinDTO {

    @Schema(description = "Unique cabin identifier", example = "1")
    private Long id;

    @Schema(description = "Cabin name", example = "Cabin 001")
    private String name;

    @Schema(description = "Cabin creation timestamp", example = "2025-01-01T12:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Maximum number of guests the cabin can accommodate", example = "4", minimum = "1")
    private Short maxCapacity;

    @Schema(description = "Regular price per night", example = "250.00")
    private BigDecimal regularPrice;

    @Schema(description = "Discount percentage applied to the regular price", example = "10", minimum = "0", maximum = "100")
    private Short discount;

    @Schema(description = "Detailed cabin description", example = "Cozy cabin with mountain views and fireplace")
    private String description;

    @Schema(description = "URL to the cabin image", example = "https://storage.example.com/cabin-001.jpg")
    private String image;
}


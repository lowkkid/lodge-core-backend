package com.github.lowkkid.lodgecore.cabin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request payload for creating a new cabin")
public class CabinCreateRequest {

    @NotBlank(message = "Name is required")
    @Schema(
            description = "Name of the cabin",
            example = "Cabin 001",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;

    @NotNull(message = "Max capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Schema(
            description = "Maximum number of guests the cabin can accommodate",
            example = "4",
            minimum = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Short maxCapacity;

    @NotNull(message = "Regular price capacity is required")
    @Min(value = 1, message = "Regular price  must be at least 1")
    @Schema(
            description = "Regular price per night in the default currency",
            example = "250.00",
            minimum = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private BigDecimal regularPrice;

    @Schema(
            description = "Discount percentage applied to the regular price (0-100)",
            example = "10",
            minimum = "0",
            maximum = "100"
    )
    private Short discount;

    @Schema(
            description = "Detailed description of the cabin including amenities and features",
            example = "Cozy cabin with mountain views, fireplace, and private hot tub"
    )
    private String description;

    @Schema(
            description = "Image file for the cabin (JPEG, PNG). Will be uploaded to object storage.",
            type = "string",
            format = "binary"
    )
    private MultipartFile image;
}

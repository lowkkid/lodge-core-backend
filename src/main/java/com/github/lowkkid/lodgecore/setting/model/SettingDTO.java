package com.github.lowkkid.lodgecore.setting.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
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
@Schema(description = "Application settings for lodge booking constraints and pricing")
public class SettingDTO {

    @Schema(
            description = "Unique identifier of the settings record",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Minimum number of nights required for a booking",
            example = "1",
            minimum = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Min(value = 1, message = "Minimum booking length can't be less than 1")
    private Short minBookingLength;

    @Schema(
            description = "Maximum number of nights allowed for a booking",
            example = "90",
            minimum = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Min(value = 1, message = "Maximum booking length can't be less than 1")
    private Short maxBookingLength;

    @Schema(
            description = "Maximum number of guests allowed per booking",
            example = "10",
            minimum = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Min(value = 1, message = "Amount of guests can't be less than 1")
    private Short maxGuestsPerBooking;

    @Schema(
            description = "Price per person per day for breakfast service",
            example = "15.00",
            minimum = "0",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Min(value = 0, message = "Breakfast price can't be less than 0")
    private BigDecimal breakfastPrice;

    @Schema(
            description = "Timestamp when the settings record was created",
            example = "2024-01-15T10:30:00",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime createdAt;
}

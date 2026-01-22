package com.github.lowkkid.lodgecore.booking.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request payload for checking in a booking")
public class CheckinRequest {

    @NotNull
    @Schema(description = "Whether to add breakfast to the booking. If true, extrasPrice and totalPrice must be provided",
            example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean addBreakfast;

    @Min(value = 0, message = "Extras price can not be less than 0")
    @Schema(description = "Updated extras price if adding breakfast. Required when addBreakfast is true",
            example = "75.00", minimum = "0")
    private BigDecimal extrasPrice;

    @Min(value = 0, message = "Total price can not be less than 0")
    @Schema(description = "Updated total price including breakfast. Required when addBreakfast is true",
            example = "575.00", minimum = "0")
    private BigDecimal totalPrice;
}

package com.github.lowkkid.lodgecore.cabin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Schema(description = "Request payload for updating an existing cabin")
public class CabinEditRequest extends CabinBaseRequest {

    @Schema(
            description = "Cabin identifier (optional, ID from path parameter takes precedence)",
            example = "1",
            hidden = true
    )
    private Long id;
}

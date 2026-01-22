package com.github.lowkkid.lodgecore.guest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Country information")
public class CountryDto {

    @Schema(description = "Unique country identifier", example = "1")
    private Long id;

    @Schema(description = "Country name", example = "United States")
    private String name;

    @Schema(description = "Country flag emoji or URL", example = "https://flagcdn.com/fi.svg")
    private String flag;

}

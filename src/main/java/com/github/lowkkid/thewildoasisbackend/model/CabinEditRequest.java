package com.github.lowkkid.thewildoasisbackend.model;

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
public class CabinEditRequest {

    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Max capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    private Short maxCapacity;
    @NotNull(message = "Regular price capacity is required")
    @Min(value = 1, message = "Regular price must be at least 1")
    private BigDecimal regularPrice;
    private Short discount;
    private String description;
    private MultipartFile image;
}

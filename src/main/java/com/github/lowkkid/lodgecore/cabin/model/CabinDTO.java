package com.github.lowkkid.lodgecore.cabin.model;

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
public class CabinDTO {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private Short maxCapacity;
    private BigDecimal regularPrice;
    private Short discount;
    private String description;
    private String image;
}


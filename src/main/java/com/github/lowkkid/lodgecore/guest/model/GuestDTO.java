package com.github.lowkkid.lodgecore.guest.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestDTO {
    private Long id;
    private String fullName;
    private String email;
    private CountryDto country;
    private String nationalId;
    private LocalDateTime createdAt;
}


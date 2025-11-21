package com.github.lowkkid.thewildoasisbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestDTO {
    private Long id;
    private String fullName;
    private String email;
    private String nationality;
    private String nationalId;
    private LocalDateTime createdAt;
}


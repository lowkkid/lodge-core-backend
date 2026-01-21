package com.github.lowkkid.lodgecore.cabin.domain.entity;

import com.github.lowkkid.lodgecore.common.domain.entity.Tracked;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "cabins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Cabin extends Tracked {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "max_capacity", nullable = false)
    private Short maxCapacity;

    @Column(name = "regular_price", nullable = false)
    private BigDecimal regularPrice;

    @Column(nullable = false)
    @Builder.Default
    private Short discount = 0;

    @Column(nullable = false)
    private String description;

    @Column
    private String image;
}


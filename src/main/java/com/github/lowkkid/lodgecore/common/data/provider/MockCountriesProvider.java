package com.github.lowkkid.lodgecore.common.data.provider;

import com.github.lowkkid.lodgecore.guest.domain.entity.Country;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockCountriesProvider {
    public static final Country PORTUGAL = Country.builder()
            .name("Portugal")
            .flag("https://flagcdn.com/pt.svg")
            .build();

    public static final Country GREAT_BRITAIN = Country.builder()
            .name("Great Britain")
            .flag("https://flagcdn.com/gb.svg")
            .build();

    public static final Country FINLAND = Country.builder()
            .name("Finland")
            .flag("https://flagcdn.com/fi.svg")
            .build();

    public static final Country GERMANY = Country.builder()
            .name("Germany")
            .flag("https://flagcdn.com/de.svg")
            .build();

    public static final Country BOLIVIA = Country.builder()
            .name("Bolivia (Plurinational State of)")
            .flag("https://flagcdn.com/bo.svg")
            .build();

    public static final Country USA = Country.builder()
            .name("USA")
            .flag("https://flagcdn.com/us.svg")
            .build();

    public static final Country EGYPT = Country.builder()
            .name("Egypt")
            .flag("https://flagcdn.com/eg.svg")
            .build();

    public static final Country SPAIN = Country.builder()
            .name("Spain")
            .flag("https://flagcdn.com/es.svg")
            .build();

    public static final Country CHINA = Country.builder()
            .name("China")
            .flag("https://flagcdn.com/cn.svg")
            .build();

    public static final Country SUDAN = Country.builder()
            .name("Sudan")
            .flag("https://flagcdn.com/sd.svg")
            .build();

    public static final Country BRAZIL = Country.builder()
            .name("Brazil")
            .flag("https://flagcdn.com/br.svg")
            .build();

    public static final Country MEXICO = Country.builder()
            .name("Mexico")
            .flag("https://flagcdn.com/mx.svg")
            .build();

    public static final Country PAKISTAN = Country.builder()
            .name("Pakistan")
            .flag("https://flagcdn.com/pk.svg")
            .build();

    public static final Country AUSTRALIA = Country.builder()
            .name("Australia")
            .flag("https://flagcdn.com/au.svg")
            .build();

    public static final Country FRANCE = Country.builder()
            .name("France")
            .flag("https://flagcdn.com/fr.svg")
            .build();

    public static final Country INDIA = Country.builder()
            .name("India")
            .flag("https://flagcdn.com/in.svg")
            .build();

    public static final Country KUWAIT = Country.builder()
            .name("Kuwait")
            .flag("https://flagcdn.com/kw.svg")
            .build();

    public static final Country SOUTH_AFRICA = Country.builder()
            .name("South Africa")
            .flag("https://flagcdn.com/za.svg")
            .build();

    public static final Country JAPAN = Country.builder()
            .name("Japan")
            .flag("https://flagcdn.com/jp.svg")
            .build();

    public static final Country SAUDI_ARABIA = Country.builder()
            .name("Saudi Arabia")
            .flag("https://flagcdn.com/sa.svg")
            .build();

    public static final Country VIETNAM = Country.builder()
            .name("Vietnam")
            .flag("https://flagcdn.com/vn.svg")
            .build();

    public static final Country SOUTH_KOREA = Country.builder()
            .name("South Korea")
            .flag("https://flagcdn.com/kr.svg")
            .build();

    public static final Country COLOMBIA = Country.builder()
            .name("Colombia")
            .flag("https://flagcdn.com/co.svg")
            .build();

    public static final Country CANADA = Country.builder()
            .name("Canada")
            .flag("https://flagcdn.com/ca.svg")
            .build();

    public static final Country ARGENTINA = Country.builder()
            .name("Argentina")
            .flag("https://flagcdn.com/ar.svg")
            .build();

    public static final Country NIGERIA = Country.builder()
            .name("Nigeria")
            .flag("https://flagcdn.com/ng.svg")
            .build();

    public static final List<Country> ALL_COUNTRIES = Arrays.asList(
            PORTUGAL,
            GREAT_BRITAIN,
            FINLAND,
            GERMANY,
            BOLIVIA,
            USA,
            EGYPT,
            SPAIN,
            CHINA,
            SUDAN,
            BRAZIL,
            MEXICO,
            PAKISTAN,
            AUSTRALIA,
            FRANCE,
            INDIA,
            KUWAIT,
            SOUTH_AFRICA,
            JAPAN,
            SAUDI_ARABIA,
            VIETNAM,
            SOUTH_KOREA,
            COLOMBIA,
            CANADA,
            ARGENTINA,
            NIGERIA
    );
}
package com.github.lowkkid.lodgecore.common.data.provider;

import com.github.lowkkid.lodgecore.cabin.domain.entity.Cabin;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockCabinsProvider {
    public static final Cabin CABIN_001 = Cabin.builder()
            .name("001")
            .maxCapacity((short) 2)
            .regularPrice(new BigDecimal("250.00"))
            .discount((short) 0)
            .description("""
                    Discover the ultimate luxury getaway for couples in the cozy wooden cabin 001. \
                    Nestled in a picturesque forest, this stunning cabin offers a secluded and intimate \
                    retreat. Inside, enjoy modern high-quality wood interiors, a comfortable seating area, \
                    a fireplace and a fully-equipped kitchen. The plush king-size bed, dressed in fine \
                    linens guarantees a peaceful nights sleep. Relax in the spa-like shower and unwind on \
                    the private deck with hot tub.""")
            .image(null)
            .createdAt(LocalDateTime.now().minusDays(90))
            .build();

    public static final Cabin CABIN_002 = Cabin.builder()
            .name("002")
            .maxCapacity((short) 2)
            .regularPrice(new BigDecimal("350.00"))
            .discount((short) 25)
            .description("""
                    Escape to the serenity of nature and indulge in luxury in our cozy cabin 002. \
                    Perfect for couples, this cabin offers a secluded and intimate retreat in the heart \
                    of a picturesque forest. Inside, you will find warm and inviting interiors crafted \
                    from high-quality wood, a comfortable living area, a fireplace and a fully-equipped \
                    kitchen. The luxurious bedroom features a plush king-size bed and spa-like shower. \
                    Relax on the private deck with hot tub and take in the beauty of nature.""")
            .image(null)
            .createdAt(LocalDateTime.now().minusDays(90))
            .build();

    public static final Cabin CABIN_003 = Cabin.builder()
            .name("003")
            .maxCapacity((short) 4)
            .regularPrice(new BigDecimal("300.00"))
            .discount((short) 0)
            .description("""
                    Experience luxury family living in our medium-sized wooden cabin 003. Perfect for \
                    families of up to 4 people, this cabin offers a comfortable and inviting space with \
                    all modern amenities. Inside, you will find warm and inviting interiors crafted from \
                    high-quality wood, a comfortable living area, a fireplace, and a fully-equipped \
                    kitchen. The bedrooms feature plush beds and spa-like bathrooms. The cabin has a \
                    private deck with a hot tub and outdoor seating area, perfect for taking in the \
                    natural surroundings.""")
            .image(null)
            .createdAt(LocalDateTime.now().minusDays(90))
            .build();

    public static final Cabin CABIN_004 = Cabin.builder()
            .name("004")
            .maxCapacity((short) 4)
            .regularPrice(new BigDecimal("500.00"))
            .discount((short) 50)
            .description("""
                    Indulge in the ultimate luxury family vacation in this medium-sized cabin 004. \
                    Designed for families of up to 4, this cabin offers a sumptuous retreat for the \
                    discerning traveler. Inside, the cabin boasts of opulent interiors crafted from the \
                    finest quality wood, a comfortable living area, a fireplace, and a fully-equipped \
                    gourmet kitchen. The bedrooms are adorned with plush beds and spa-inspired en-suite \
                    bathrooms. Step outside to your private deck and soak in the natural surroundings \
                    while relaxing in your own hot tub.""")
            .image(null)
            .createdAt(LocalDateTime.now().minusDays(90))
            .build();

    public static final Cabin CABIN_005 = Cabin.builder()
            .name("005")
            .maxCapacity((short) 6)
            .regularPrice(new BigDecimal("350.00"))
            .discount((short) 0)
            .description("""
                    Enjoy a comfortable and cozy getaway with your group or family in our spacious \
                    cabin 005. Designed to accommodate up to 6 people, this cabin offers a secluded \
                    retreat in the heart of nature. Inside, the cabin features warm and inviting \
                    interiors crafted from quality wood, a living area with fireplace, and a \
                    fully-equipped kitchen. The bedrooms are comfortable and equipped with en-suite \
                    bathrooms. Step outside to your private deck and take in the natural surroundings \
                    while relaxing in your own hot tub.""")
            .image(null)
            .createdAt(LocalDateTime.now().minusDays(90))
            .build();

    public static final Cabin CABIN_006 = Cabin.builder()
            .name("006")
            .maxCapacity((short) 6)
            .regularPrice(new BigDecimal("800.00"))
            .discount((short) 100)
            .description("""
                    Experience the epitome of luxury with your group or family in our spacious wooden \
                    cabin 006. Designed to comfortably accommodate up to 6 people, this cabin offers a \
                    lavish retreat in the heart of nature. Inside, the cabin features opulent interiors \
                    crafted from premium wood, a grand living area with fireplace, and a fully-equipped \
                    gourmet kitchen. The bedrooms are adorned with plush beds and spa-like en-suite \
                    bathrooms. Step outside to your private deck and soak in the natural surroundings \
                    while relaxing in your own hot tub.""")
            .image(null)
            .createdAt(LocalDateTime.now().minusDays(90))
            .build();

    public static final Cabin CABIN_007 = Cabin.builder()
            .name("007")
            .maxCapacity((short) 8)
            .regularPrice(new BigDecimal("600.00"))
            .discount((short) 100)
            .description("""
                    Accommodate your large group or multiple families in the spacious and grand wooden \
                    cabin 007. Designed to comfortably fit up to 8 people, this cabin offers a secluded \
                    retreat in the heart of beautiful forests and mountains. Inside, the cabin features \
                    warm and inviting interiors crafted from quality wood, multiple living areas with \
                    fireplace, and a fully-equipped kitchen. The bedrooms are comfortable and equipped \
                    with en-suite bathrooms. The cabin has a private deck with a hot tub and outdoor \
                    seating area, perfect for taking in the natural surroundings.""")
            .image(null)
            .createdAt(LocalDateTime.now().minusDays(90))
            .build();

    public static final Cabin CABIN_008 = Cabin.builder()
            .name("008")
            .maxCapacity((short) 10)
            .regularPrice(new BigDecimal("1400.00"))
            .discount((short) 0)
            .description("""
                    Experience the epitome of luxury and grandeur with your large group or multiple \
                    families in our grand cabin 008. This cabin offers a lavish retreat that caters to \
                    all your needs and desires. The cabin features an opulent design and boasts of \
                    high-end finishes, intricate details and the finest quality wood throughout. Inside, \
                    the cabin features multiple grand living areas with fireplaces, a formal dining area, \
                    and a gourmet kitchen that is a chef's dream. The bedrooms are designed for ultimate \
                    comfort and luxury, with plush beds and en-suite spa-inspired bathrooms. Step outside \
                    and immerse yourself in the beauty of nature from your private deck, featuring a \
                    luxurious hot tub and ample seating areas for ultimate relaxation and enjoyment.""")
            .image(null)
            .createdAt(LocalDateTime.now().minusDays(90))
            .build();

    public static final List<Cabin> ALL_CABINS = Arrays.asList(
            CABIN_001,
            CABIN_002,
            CABIN_003,
            CABIN_004,
            CABIN_005,
            CABIN_006,
            CABIN_007,
            CABIN_008
    );
}

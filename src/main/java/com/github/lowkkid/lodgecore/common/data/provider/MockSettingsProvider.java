package com.github.lowkkid.lodgecore.common.data.provider;

import com.github.lowkkid.lodgecore.setting.domain.entity.Setting;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockSettingsProvider {

    public static Setting SETTING = Setting.builder()
            .minBookingLength((short) 3)
            .maxBookingLength((short) 90)
            .maxGuestsPerBooking((short) 8)
            .breakfastPrice(new BigDecimal("15.00"))
            .build();
}

package com.github.lowkkid.thewildoasisbackend.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BookingStatus {

    CHECKED_IN("checked-in"),
    CHECKED_OUT("checked-out"),
    UNCONFIRMED("unconfirmed");

    private final String value;

    @Override
    public String toString() {
        return value;
    }
}

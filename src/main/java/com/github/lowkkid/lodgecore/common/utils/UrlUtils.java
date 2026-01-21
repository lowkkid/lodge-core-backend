package com.github.lowkkid.lodgecore.common.utils;

import java.net.URI;
import java.nio.file.Paths;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UrlUtils {

    public static String extractFileName(String url) {
        try {
            URI uri = new URI(url);
            String path = uri.getPath();
            return Paths.get(path).getFileName().toString();
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse URL", e);
        }
    }
}

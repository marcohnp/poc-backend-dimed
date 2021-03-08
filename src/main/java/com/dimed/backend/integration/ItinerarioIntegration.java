package com.dimed.backend.integration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItinerarioIntegration {

    public static String catchUriItinerario(String id) {
        return UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("www.poatransporte.com.br")
                .path("php/facades/process.php")
                .queryParam("a", "il")
                .queryParam("p", "" + id + "")
                .build()
                .toString();
    }
}

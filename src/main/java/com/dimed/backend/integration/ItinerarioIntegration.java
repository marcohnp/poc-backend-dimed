package com.dimed.backend.integration;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class ItinerarioIntegration {

    public String catchUriItinerario(String id) {
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

package com.dimed.backend.integration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LinhaOnibusIntegration {

    public static String catchUriLinhaOnibus() {

        return UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("www.poatransporte.com.br")
                .path("php/facades/process.php")
                .queryParam("a", "nc")
                .queryParam("p", "%")
                .queryParam("t", "o")
                .build()
                .toString();
    }


}

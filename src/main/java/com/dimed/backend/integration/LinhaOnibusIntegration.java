package com.dimed.backend.integration;

import lombok.NoArgsConstructor;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@NoArgsConstructor
public class LinhaOnibusIntegration {

    public String catchUriLinhaOnibus() {

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

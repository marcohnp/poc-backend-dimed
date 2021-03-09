package com.dimed.backend.service;

import com.dimed.backend.dto.LinhaOnibusDTO;
import com.dimed.backend.integration.ItinerarioIntegration;
import com.dimed.backend.integration.LinhaOnibusIntegration;
import com.dimed.backend.model.Coordendas;
import com.dimed.backend.model.Itinerario;
import com.dimed.backend.model.LinhaOnibus;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ApiExternaService {

    public List<LinhaOnibusDTO> getResponseLinhaOnibus() {
        RestTemplate rest = getRestTemplate();
        ResponseEntity<List<LinhaOnibusDTO>> response = rest.exchange(LinhaOnibusIntegration.catchUriLinhaOnibus(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<LinhaOnibusDTO>>() {
        });
        return response.getBody();
    }

    public List<LinhaOnibus> createListLinhaOnibus() {
        return getResponseLinhaOnibus().stream()
                .map(dto ->
                        LinhaOnibus
                                .builder()
                                .id(Long.parseLong(dto.getId()))
                                .nome(dto.getNome())
                                .codigo(dto.getCodigo())
                                .build())
                .collect(Collectors.toList());
    }

    public List<LinhaOnibus> getListLinhaOnibusByName(String name) {
        return getResponseLinhaOnibus().stream()
                .map(dto -> LinhaOnibus
                        .builder()
                        .id(Long.parseLong(dto.getId()))
                        .nome(dto.getNome())
                        .codigo(dto.getCodigo())
                        .build())
                        .filter(linhaOnibus -> linhaOnibus.getNome().contains(name.toUpperCase()))
                .collect(Collectors.toList());
    }


    private String getReponseItinerario(String uri) {
        RestTemplate rest = getRestTemplate();
        ResponseEntity<String> response = rest.exchange(uri, HttpMethod.GET, null, String.class);
        return  response.getBody();
    }

    private RestTemplate getRestTemplate() {
        RestTemplate rest = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
        rest.getMessageConverters().add(converter);
        return rest;
    }

    private JsonNode getJsonNode(String uri) {
        try {
            String string = getReponseItinerario(uri);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(string);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }

    }

    public Itinerario populateItinerario(String uri) {
        try {
            JsonNode actualObj = getJsonNode(uri);
            JsonNode jnIdLinha = actualObj.get("idlinha");
            JsonNode jnNome = actualObj.get("nome");
            JsonNode jnCodigo = actualObj.get("codigo");

            Long idLinha = jnIdLinha.asLong();
            String nome = jnNome.asText();
            String codigo = jnCodigo.asText();

            return new Itinerario(idLinha, nome, codigo, null);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }

    public Itinerario getItinerario(String uri)  {
        JsonNode actualObj = getJsonNode(uri);
        Itinerario itinerario = populateItinerario(uri);

        List<Coordendas> listCoord = new ArrayList<>();

        for (int i = 0; i < (actualObj.size() - 3); i++) {
            Coordendas coord = new Coordendas();
            JsonNode lat = actualObj.get("" + i + "").path("lat");
            JsonNode lng = actualObj.get("" + i + "").path("lng");
            double latitude = lat.asDouble();
            double longitude = lng.asDouble();
            coord.setLatitude(latitude);
            coord.setLongitude(longitude);
            Itinerario it = new Itinerario();
            it.setIdlinha(itinerario.getIdlinha());
            coord.setItinerario(it);
            listCoord.add(coord);
        }
        itinerario.setCoordendas(listCoord);
        return  itinerario;
    }

    public Collection<LinhaOnibus> linhasPorRaio(double lat, double lng, double raio) {
        List<LinhaOnibus> linhas = createListLinhaOnibus();

       return  linhas.stream().filter(linha -> {
            Itinerario itinerario = getItinerario(ItinerarioIntegration.catchUriItinerario(linha.getId().toString()));
            setThread();
            return itinerario.getCoordendas().stream().anyMatch(coordenadas -> {
                double result = distance(lat, coordenadas.getLatitude(),
                        lng,coordenadas.getLongitude(), 0.0, 0.0);
                return result <= raio;
            });
        }).collect(Collectors.toList());
    }

    private void setThread() {
       try {
           Thread.sleep(109);
        } catch (InterruptedException e) {
           Thread.currentThread().interrupt();
           throw new RuntimeException(e);
       }
    }

    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000;

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

}

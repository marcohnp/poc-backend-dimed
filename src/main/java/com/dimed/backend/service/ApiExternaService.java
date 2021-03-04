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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ApiExternaService {

    private final LinhaOnibusIntegration linhaOnibusIntegration;
    private final ItinerarioIntegration itinerarioIntegration;

    private RestTemplate getRestTemplate() {
        RestTemplate rest = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
        rest.getMessageConverters().add(converter);
        return rest;
    }

    public List<LinhaOnibusDTO> getResponseLinhaOnibus() {
        RestTemplate rest = getRestTemplate();
        ResponseEntity<List<LinhaOnibusDTO>> response = rest.exchange(linhaOnibusIntegration.catchUriLinhaOnibus(),
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

    public Itinerario getItinerario(String uri) throws JsonParseException, IOException {

        String string = getReponseItinerario(uri);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(string);

        JsonNode jnIdLinha = actualObj.get("idlinha");
        JsonNode jnNome = actualObj.get("nome");
        JsonNode jnCodigo = actualObj.get("codigo");

        Long idLinha = jnIdLinha.asLong();
        String nome = jnNome.asText();
        String codigo = jnCodigo.asText();

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
            it.setIdlinha(idLinha);
            coord.setItinerario(it);

            listCoord.add(coord);
        }

        return  new Itinerario(idLinha, nome, codigo, listCoord);

    }

    private String getReponseItinerario(String uri) {
        RestTemplate rest = getRestTemplate();
        ResponseEntity<String> response = rest.exchange(uri, HttpMethod.GET, null, String.class);
        return  response.getBody();
    }

    public List<LinhaOnibus> getListLinhaOnibusByName(String name) {
        return getResponseLinhaOnibus().stream()
                .map(dto -> LinhaOnibus
                        .builder()
                        .id(Long.parseLong(dto.getId()))
                        .nome(dto.getNome())
                        .codigo(dto.getCodigo())
                        .build())
                .collect(Collectors.toList());
    }

    public Collection<LinhaOnibus> linhasPorRaio(double lat, double lng, double raio) throws IOException, InterruptedException {
        List<LinhaOnibus> list = createListLinhaOnibus();
        List<String> idList = new ArrayList<>();
        List<Itinerario> itinList = new ArrayList<>();
        Set<LinhaOnibus> listResultCoord = new HashSet<LinhaOnibus>();

        for (LinhaOnibus l : list) {
            idList.add(l.getId().toString());
        }

        for (int i = 0; i < idList.size(); i++) {
            Itinerario itinerario = new Itinerario();
            itinerario = getItinerario(itinerarioIntegration.catchUriItinerario(idList.get(i)));
            Thread.sleep(70);
            itinList.add(itinerario);

        }

        for (Itinerario li : itinList) {
            for (int i = 0; i < li.getCoordendas().size(); i++) {
                double result = 0;
                result = distance(lat, li.getCoordendas().get(i).getLatitude(), lng, li.getCoordendas().get(i).getLongitude(), 0.0, 0.0);
                if (result <= raio) {
                    LinhaOnibus linhaOnibus = new LinhaOnibus();
                    linhaOnibus.setId(li.getIdlinha());
                    linhaOnibus.setNome(li.getNome());
                    linhaOnibus.setCodigo(li.getCodigo());
                    listResultCoord.add(linhaOnibus);
                }
            }
        }

        return listResultCoord;
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

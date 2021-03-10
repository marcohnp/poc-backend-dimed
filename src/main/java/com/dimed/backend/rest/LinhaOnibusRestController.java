package com.dimed.backend.rest;

import com.dimed.backend.facade.LinhaOnibusFacade;
import com.dimed.backend.model.LinhaOnibus;
import com.dimed.backend.service.LinhaOnibusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;


@Api(value = "POC Dimed BackEnd")
@RequiredArgsConstructor
@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/linhaonibus")
public class LinhaOnibusRestController {

    private final LinhaOnibusService linhaOnibusService;

    private final LinhaOnibusFacade linhaOnibusFacade;


    @ApiOperation(value = "Retorna uma lista de linhas de onibus presentes na API PoaTransporte e a " +
            "salva no H2 Database.")
    @GetMapping
    public List<LinhaOnibus> getAllLinhasOnibus() {
        return linhaOnibusFacade.findAll();
    }

    @ApiOperation(value = "Retorna uma linha de onibus do H2 Database, a partir de seu ID.")
    @GetMapping(value = "/{id}")
    public LinhaOnibus findById(@PathVariable Long id) {
        return linhaOnibusFacade.findById(id);
    }

    @ApiOperation(value = "Retorna uma linha de onibus a partir da passagem de um nome como parâmetro.")
    @GetMapping(value = "/linha")
    public List<LinhaOnibus> getLinhasOnibusByName(@RequestParam String nome) {
        return linhaOnibusFacade.findByName(nome);
    }

    @ApiOperation(value = "Retorna as linha de onibus da API PoaTransporte a partir das coordendas passadas dentro " +
            "de um raio. Parâmetros: Latitude(lat), Longitude(lng) e raio. A requisição leva em torno de 4 minutos.")
    @GetMapping(value = "/coord")
    public List<LinhaOnibus> getLinhasOnibusByCoordinates(@RequestParam double lat, @RequestParam double lng,
                                                          @RequestParam double raio) {
        return linhaOnibusFacade.findByCoordinates(lat, lng, raio);
    }

    @ApiOperation(value = "Salva uma linha de onibus no H2 Database.")
    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveLinhaOnibus(@Valid @RequestBody LinhaOnibus linhaOnibus) {
       linhaOnibusFacade.save(linhaOnibus);
    }

    @ApiOperation(value = "Atualiza uma linha de onibus no H2 Database.")
    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLinhaOnibus(@Valid @RequestBody LinhaOnibus linhaOnibus) {
        linhaOnibusFacade.update(linhaOnibus);
    }

    @ApiOperation(value = "Deleta linha de onibus do H2 Database a partir de seu ID.")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void deleteLinhaOnibus(@PathVariable("id") Long id) {
        linhaOnibusFacade.delete(id);
    }
}

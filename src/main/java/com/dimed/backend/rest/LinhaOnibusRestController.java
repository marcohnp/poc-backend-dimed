package com.dimed.backend.rest;

import com.dimed.backend.facade.LinhaOnibusFacade;
import com.dimed.backend.model.LinhaOnibus;
import com.dimed.backend.service.LinhaOnibusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collection;
import java.util.List;


@Api(value="POC Dimed BackEnd")
@RequiredArgsConstructor
@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/linhaonibus")
public class LinhaOnibusRestController {

    private final LinhaOnibusService linhaOnibusService;

    private final LinhaOnibusFacade linhaOnibusFacade;


    @ApiOperation(value="Retorna uma lista de linhas de onibus presentes na API PoaTransporte e a salva no H2 Database.")
    @GetMapping
    public List<LinhaOnibus> getAllLinhasOnibus() {
        return linhaOnibusFacade.findAll();
    }
    @ApiOperation(value="Retorna uma linha de onibus do H2 Database, a partir de seu ID.")
    @GetMapping(value = "/{id}")
    public LinhaOnibus findById(@PathVariable Long id) {
        return linhaOnibusFacade.findById(id);
    }

    @ApiOperation(value="Retorna uma linha de onibus do H2 Database, a partir da passagem de um nome como parâmetro.")
    @GetMapping(value = "/linha")
    public List<LinhaOnibus> getLinhasOnibusByName(@RequestParam String nome) {
        return linhaOnibusFacade.findByName(nome);
    }
    @ApiOperation(value="Salva ou atualiza uma linha de onibus no H2 Database. Se o ID for existente, atualiza nome e código. Se não houver o ID informado, cadastra uma nova linha.")
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveLinhaOnibus(@RequestBody LinhaOnibus linhaOnibus) {
        Collection<LinhaOnibus> list = linhaOnibusService.findAll();
        boolean exist = false;
        for (LinhaOnibus l : list) {
            if (l.getId().equals(linhaOnibus.getId())) {
                exist = true;
            }
        }

        LinhaOnibus lo = new LinhaOnibus();

        if (exist == false) {
            if(!linhaOnibus.getNome().isEmpty() && !linhaOnibus.getCodigo().isEmpty()){
                lo = linhaOnibusService.save(linhaOnibus);
                return new ResponseEntity<LinhaOnibus>(lo,HttpStatus.CREATED);
            }
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        } else {
            if(!linhaOnibus.getNome().isEmpty() && !linhaOnibus.getCodigo().isEmpty()){
                lo = linhaOnibusService.save(linhaOnibus);
                return new ResponseEntity<LinhaOnibus>(lo, HttpStatus.OK);
            }
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        }
    }
    @ApiOperation(value="Deleta linha de onibus do H2 Database a partir de seu ID.")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @Transactional
    public ResponseEntity<Void> deleteLinhaOnibus(@PathVariable("id") Long id){
        LinhaOnibus linhaOnibus = this.linhaOnibusService.findById(id);
        if(linhaOnibus == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        this.linhaOnibusService.delete(linhaOnibus);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value="Retorna as linha de onibus da API PoaTransporte a partir das coordendas passadas dentro de um raio. Parâmetros: Latitude(lat), Longitude(lng) e raio. A requisição leva em torno de 2 minutos.")
    @RequestMapping(value = "/coord", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<LinhaOnibus>> getListLinhaOnibusByCoord(@RequestParam double lat, @RequestParam double lng, @RequestParam double raio ) throws IOException, InterruptedException {
        Collection<LinhaOnibus> list = linhaOnibusService.findByCoord(lat, lng, raio);

        return new ResponseEntity<Collection<LinhaOnibus>>(list, HttpStatus.OK);
    }
}

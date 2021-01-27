package com.dimed.backend.rest;

import com.dimed.backend.model.LinhaOnibus;
import com.dimed.backend.model.PontoTaxi;
import com.dimed.backend.service.LinhaOnibusService;
import com.dimed.backend.service.PontoTaxiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Api(value="POC Dimed BackEnd")
@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/pontotaxi")
public class PontoTaxiRestController {

    @Autowired
    private PontoTaxiService pontoTaxiService;

    @ApiOperation(value="Salva ou atualiza um ponto de taxi no H2 Database. Se o ID for existente, atualiza nome e código. Se não houver o ID informado, cadastra uma nova linha.")
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> savePontoTaxi(@RequestBody PontoTaxi pontoTaxi) {
        Collection<PontoTaxi> list = pontoTaxiService.findAll();
        boolean exist = false;
        for (PontoTaxi l : list) {
            if (l.getId().equals(pontoTaxi.getId())) {
                exist = true;
            }
        }

        PontoTaxi pt = new PontoTaxi();

        if (exist == false) {
            if(!pontoTaxi.getNomeDoPonto().isEmpty() && pontoTaxi.getLatitude() != null && pontoTaxi.getLongitude() != null )  {
                pt = pontoTaxiService.save(pontoTaxi);
                return new ResponseEntity<PontoTaxi>(pt,HttpStatus.CREATED);
            }
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        } else {
            if(!pontoTaxi.getNomeDoPonto().isEmpty() && pontoTaxi.getLatitude() != null && pontoTaxi.getLongitude() != null ){
                pt = pontoTaxiService.save(pontoTaxi);
                return new ResponseEntity<PontoTaxi>(pt, HttpStatus.OK);
            }
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        }
    }
    @ApiOperation(value="Deleta ponto de taxi do H2 Database a partir de seu ID.")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @Transactional
    public ResponseEntity<Void> deletePontoTaxi(@PathVariable("id") Long id){
        PontoTaxi pontoTaxi = this.pontoTaxiService.findById(id);
        if(pontoTaxi == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        this.pontoTaxiService.delete(pontoTaxi);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value="Retorna uma lista de pontos de taxi que foram salvos no H2 Database.")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<PontoTaxi>> getAllPontoTaxi() {
        Collection<PontoTaxi> list = new ArrayList<>();
        list = pontoTaxiService.findAll();
        if (list == null) {
            return new ResponseEntity<Collection<PontoTaxi>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Collection<PontoTaxi>>(list, HttpStatus.OK);
    }
    @ApiOperation(value="Retorna uma lista de pontos de taxi que foram salvos no H2 Database, a partir de seu ID.")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PontoTaxi> findById(@PathVariable Long id) throws IOException {
        PontoTaxi pontoTaxiById = new PontoTaxi();
        pontoTaxiById = pontoTaxiService.findById(id);

        pontoTaxiService.readPontoTaxiTxt();

        return new ResponseEntity<PontoTaxi>(pontoTaxiById, HttpStatus.OK);
    }

    @ApiOperation(value="Retorna lista de pontos de taxi existente no arquivo TXT.")
    @RequestMapping(value = "/txt", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<String>> getAllTxtPontoTaxi() throws IOException {
        Collection<String> list = new ArrayList<>();

        list = pontoTaxiService.readPontoTaxiTxt();

        return new ResponseEntity<Collection<String>>(list, HttpStatus.OK);
    }

    @ApiOperation(value="Insere ponto de taxi na lista de pontos existentes no TXT e a retorna a lista atualizada. Parâmetro - String no formato: NOME_DO_PONTO#LATITUDE#LONGITUDE#DATA_HORA_CADASTRO.")
    @RequestMapping(value = "/txt/save", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Collection<String>> getTxtPontoTaxi(@RequestParam String pt) throws IOException {
        Collection<String> list = new ArrayList<>();

        list =  pontoTaxiService.writePontoTaxiTxt(pt);
        list = pontoTaxiService.readPontoTaxiTxt();

        return new ResponseEntity<Collection<String>>(list, HttpStatus.OK);
    }
}

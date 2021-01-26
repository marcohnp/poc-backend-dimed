package com.dimed.backend.rest;

import com.dimed.backend.model.Coordendas;
import com.dimed.backend.model.Itinerario;
import com.dimed.backend.model.LinhaOnibus;
import com.dimed.backend.service.ItinerarioService;
import com.dimed.backend.service.LinhaOnibusService;
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
@RequestMapping("/api/itinerario")
public class ItinerarioRestController {

    @Autowired
    private ItinerarioService itinerarioService;

    @ApiOperation(value="Retorna um itinerario da API PoaTransporte a partir de um ID informado. Também salva o itinerario no database H2.")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Itinerario> getItinerario(@RequestParam String id) throws IOException {
        Itinerario itinerario = new Itinerario();
        itinerario = itinerarioService.findItinerario(id);
        if (itinerario == null) {
            return new ResponseEntity<Itinerario>(HttpStatus.NOT_FOUND);
        }

        itinerarioService.save(itinerario);

        return new ResponseEntity<Itinerario>(itinerario, HttpStatus.OK);
    }
    @ApiOperation(value="Retorna todos os itinerario salvos no database H2.")
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collection<Itinerario>> findAll() {
        Collection<Itinerario> list = new ArrayList<>();
        list = itinerarioService.findAll();
        if (list == null) {
            return new ResponseEntity<Collection<Itinerario>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Collection<Itinerario>>(list, HttpStatus.OK);
    }

    @ApiOperation(value="Retorna um itinerario do database H2 a partir de seu ID.")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Itinerario> findById(@PathVariable Long id) {
        Itinerario itinerarioById = new Itinerario();
        itinerarioById = itinerarioService.findById(id);

        return new ResponseEntity<Itinerario>(itinerarioById, HttpStatus.OK);
    }

    @ApiOperation(value="Salva ou atualiza um itinerario no database H2. Se o ID for existente, atualiza nome e código. Se não houver o ID informado, cadastra um novo itinerario")
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveItinerario(@RequestBody Itinerario itinerario) {
        Collection<Itinerario> list = itinerarioService.findAll();
        boolean exist = false;
        for (Itinerario l : list) {
            if (l.getIdlinha().equals(itinerario.getIdlinha())) {
                exist = true;
            }
        }

        Itinerario it = new Itinerario();

        if (exist == false) {
            if(!itinerario.getNome().isEmpty() && !itinerario.getCodigo().isEmpty()){
                it = itinerarioService.save(itinerario);
                return new ResponseEntity<Itinerario>(it, HttpStatus.CREATED);
            }
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        } else {
            if(!itinerario.getNome().isEmpty() && !itinerario.getCodigo().isEmpty()){
                it = itinerarioService.save(itinerario);
                return new ResponseEntity<Itinerario>(it, HttpStatus.OK);
            }
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        }
    }

    @ApiOperation(value="Deleta itinerario do database H2 a partir de seu ID.")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @Transactional
    public ResponseEntity<Void> deleteLinhaOnibus(@PathVariable("id") Long id){
        Itinerario itinerario = this.itinerarioService.findById(id);
        if(itinerario == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        this.itinerarioService.delete(itinerario);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}

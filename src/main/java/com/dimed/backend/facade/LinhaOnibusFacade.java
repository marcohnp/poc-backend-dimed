package com.dimed.backend.facade;

import com.dimed.backend.model.LinhaOnibus;
import com.dimed.backend.service.LinhaOnibusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class LinhaOnibusFacade {

    private final LinhaOnibusService linhaOnibusService;

    public List<LinhaOnibus> findAll(){
        return linhaOnibusService.findAll();
    }

    public LinhaOnibus findById(Long id){
        return linhaOnibusService.findById(id);
    }

    public List<LinhaOnibus> findByName(String name){
        return linhaOnibusService.findByName(name);
    }

    public List<LinhaOnibus> findByCoordinates(double lat, double lng, double radius){
        return linhaOnibusService.findByCoordinates(lat, lng, radius);
    }

    public void save(LinhaOnibus linhaOnibus) { linhaOnibusService.validateSave(linhaOnibus); }

    public void update(LinhaOnibus linhaOnibus) { linhaOnibusService.validateUpdate(linhaOnibus); }

    public void delete(Long id) { linhaOnibusService.delete(id); }
}

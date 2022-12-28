package com.devsuperior.bds04.Controller.citycontroller;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.dto.RoleDTO;
import com.devsuperior.bds04.dto.UserDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.services.cityservice.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CiryCintroller {
    @Autowired
    private CityService service;

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll() {
        List<CityDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> findById(@PathVariable Long id) {
         CityDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping
    public ResponseEntity<CityDTO> insert(@Valid @RequestBody CityDTO dto){
     CityDTO newDTO=service.save(dto);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> update(@PathVariable Long id,@RequestBody @Valid CityDTO cityDTO){
        CityDTO newDto=service.upDate(id,cityDTO);
        return ResponseEntity.ok().body(newDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.devsuperior.bds04.Controller.rolecontroller;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.dto.RoleDTO;
import com.devsuperior.bds04.services.roleservice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/roles")
public class RoleControler {
    @Autowired
    private RoleService service;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> findAll() {
        List<RoleDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable Long id) {
      RoleDTO  dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping
    public ResponseEntity<RoleDTO> insert(@Valid @RequestBody RoleDTO dto){
        RoleDTO newDTO=service.save(dto);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable Long id,@RequestBody @Valid RoleDTO dto){
        RoleDTO newDto=service.upDate(id,dto);
        return ResponseEntity.ok().body(newDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delet(id);
        return ResponseEntity.noContent().build();
    }
}

package org.example.kaddem.controller;

import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestEquipeDTO;
import org.example.kaddem.dtos.ResponseEquipeDTO;
import org.example.kaddem.services.IEquipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipes")
public class EquipeController {
    private final IEquipeService equipeService;

    public EquipeController(IEquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @PostMapping("/saveEquipeWithDetails")
    public ResponseEntity<GlobalResponse<ResponseEquipeDTO>> addEquipe(@RequestBody RequestEquipeDTO requestEquipeDTO){
        return ResponseEntity.status(200).body(equipeService.addEquipeWithDetails(requestEquipeDTO));
    }

    @GetMapping("/getAllEquipes")
    public ResponseEntity<GlobalResponse<List<ResponseEquipeDTO>>> getAll(){
        return ResponseEntity.status(200).body(equipeService.retrieveAllEquipes());
    }

    @PutMapping("/updateEquipe/{equipeId}")
    public ResponseEntity<GlobalResponse<ResponseEquipeDTO>> updateEquipe(@RequestBody RequestEquipeDTO requestEquipeDTO,@PathVariable String equipeId){
        return ResponseEntity.status(200).body(equipeService.updateEquipe(requestEquipeDTO, equipeId));
    }

    @GetMapping("/getEquipeById/{equipeId}")
    public ResponseEntity<GlobalResponse<ResponseEquipeDTO>> getEquipeById(@PathVariable String equipeId){
        return ResponseEntity.status(200).body(equipeService.retrieveEquipe(equipeId));
    }


}

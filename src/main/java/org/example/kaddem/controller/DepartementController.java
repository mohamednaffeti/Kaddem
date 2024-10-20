package org.example.kaddem.controller;

import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestDepartementDTO;
import org.example.kaddem.dtos.ResponseDepartementDTO;
import org.example.kaddem.services.IDepartementService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/departements")
public class DepartementController {

    private final IDepartementService departementService;

    public DepartementController(IDepartementService departementService) {
        this.departementService = departementService;
    }

    @PostMapping("/saveDepartement")
    public ResponseEntity<GlobalResponse<ResponseDepartementDTO>> saveDepartemement(@RequestBody RequestDepartementDTO requestDepartementDTO){
        return ResponseEntity.status(200).body(departementService.addDepartement(requestDepartementDTO));
    }

    @PutMapping("/updateDepartement")
    public ResponseEntity<GlobalResponse<ResponseDepartementDTO>> updateDepartemement(@RequestBody RequestDepartementDTO requestDepartementDTO,String departementId){
        return ResponseEntity.status(200).body(departementService.updateDepartement(requestDepartementDTO,departementId));
    }

    @GetMapping("/getAllDepartement")
    public ResponseEntity<GlobalResponse<List<ResponseDepartementDTO>>> getAllDepartements(){
        return ResponseEntity.status(200).body(departementService.retreiveAllDepartements());
    }

    @GetMapping("/getDeptById/{departementId}")
    public ResponseEntity<GlobalResponse<ResponseDepartementDTO>> getDepartementById(@PathVariable String departementId){
        return ResponseEntity.status(200).body(departementService.retreiveById(departementId));
    }

    @DeleteMapping("/deleteDeptById/{departementId}")
    public ResponseEntity<GlobalResponse<Boolean>> deleteDepartementById(@PathVariable String departementId){
        return ResponseEntity.status(200).body(departementService.deleteById(departementId));
    }




}

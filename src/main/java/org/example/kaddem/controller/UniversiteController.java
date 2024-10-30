package org.example.kaddem.controller;

import jakarta.servlet.http.PushBuilder;
import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestUniversiteDTO;
import org.example.kaddem.dtos.ResponseUniversiteDTO;
import org.example.kaddem.entities.Universite;
import org.example.kaddem.services.IUniversiteService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/universite")
public class UniversiteController {

    private final IUniversiteService universiteService;

    public UniversiteController(IUniversiteService universiteService) {
        this.universiteService = universiteService;
    }

    @GetMapping("/getAllUniversite")
    public ResponseEntity<GlobalResponse<List<ResponseUniversiteDTO>>> getAllUniversites(){
        return ResponseEntity.status(200).body(universiteService.retrieveAllUnversities());
    }

    @PostMapping("/addNewUniversite")
    public ResponseEntity<GlobalResponse<ResponseUniversiteDTO>> addNewUniversite(@RequestBody RequestUniversiteDTO requestUniversiteDTO){
        return ResponseEntity.status(200).body(universiteService.addUniversite(requestUniversiteDTO));
    }

    @PutMapping("/updateUniversity/{universityId}")
    public ResponseEntity<GlobalResponse<ResponseUniversiteDTO>> updateUniversite(@RequestBody RequestUniversiteDTO requestUniversiteDTO,@PathVariable String universityId){
        return ResponseEntity.status(200).body(universiteService.updateUniversite(requestUniversiteDTO,universityId));
    }

    @GetMapping("getById/{universityId}")
    public ResponseEntity<GlobalResponse<ResponseUniversiteDTO>> getUniversityById(@PathVariable String universityId){
        return ResponseEntity.status(200).body(universiteService.retrieveUniversityById(universityId));
    }



}

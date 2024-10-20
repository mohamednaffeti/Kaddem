package org.example.kaddem.controller;

import lombok.AllArgsConstructor;
import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestEtudiantDTO;
import org.example.kaddem.dtos.ResponseEtudiantDTO;
import org.example.kaddem.services.IEtudiantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
@AllArgsConstructor
public class EtudiantController {

    private final IEtudiantService etudiantService;

    @PostMapping("/saveStudent")
    public ResponseEntity<GlobalResponse<ResponseEtudiantDTO>> saveEtudiant(@RequestBody RequestEtudiantDTO etudiantDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(etudiantService.addEtudiant(etudiantDTO));
    }

    @GetMapping("/getAllStudent")
    public ResponseEntity<GlobalResponse<List<ResponseEtudiantDTO>>> getAllStudent(){
        return ResponseEntity.status((HttpStatus.OK)).body(etudiantService.getAll());
    }

    @PutMapping("/updateStudent/{etudiantId}")
    public ResponseEntity<GlobalResponse<ResponseEtudiantDTO>> updateStudent(@RequestBody RequestEtudiantDTO etudiantDTO,@PathVariable String etudiantId){
        return ResponseEntity.status(HttpStatus.OK).body(etudiantService.updateEtudiant(etudiantDTO,etudiantId));
    }

    @GetMapping("/getStudentById/{etudiantId}")
    public ResponseEntity<GlobalResponse<ResponseEtudiantDTO>> getStudentById(@PathVariable String etudiantId){
            return ResponseEntity.status(HttpStatus.OK).body(etudiantService.retrieveEtudiantById(etudiantId));

    }

    @DeleteMapping("/delete/{etudiantId}")
    public ResponseEntity<GlobalResponse<Boolean>> deleteStudentById(@PathVariable String etudiantId){
        return ResponseEntity.status(HttpStatus.OK).body(etudiantService.removeEtudiant(etudiantId));
    }

}

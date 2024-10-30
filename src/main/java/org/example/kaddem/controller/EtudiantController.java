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

    @GetMapping("/AffectStudentToDepartment/{etudiantId}/{departmentId}")
    public ResponseEntity<GlobalResponse<Boolean>> AffectStudentToDepartment(@PathVariable String etudiantId, @PathVariable String departmentId){
        return ResponseEntity.status(200).body(etudiantService.assignEtudiantToDepartement(etudiantId,departmentId));
    }

    @PostMapping("/addAndAssignEtudiantToEquipeAndContract/{idcontract}/{idequipe}")
    public ResponseEntity<GlobalResponse<ResponseEtudiantDTO>> addAndAssignEtudiantToEquipeAndContract(
            @RequestBody RequestEtudiantDTO requestEtudiantDTO,
            String idcontract,
            String idequipe  ){
        return ResponseEntity.status(200).body(etudiantService.addAndAssignEtudiantToEquipeAndContract(
                requestEtudiantDTO,idcontract,idequipe
        ));
    }

    @GetMapping("/GetStudentByDepartment/{departmentId}")
    public ResponseEntity<GlobalResponse<List<ResponseEtudiantDTO>>> GetStudentByDepartment(@PathVariable String departmentId){
        return ResponseEntity.status(200).body(etudiantService.getByDepartement(departmentId));
    }
}

package org.example.kaddem.controller;

import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestContratDTO;
import org.example.kaddem.dtos.ResponseContratDTO;
import org.example.kaddem.services.IContratService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/contrats")
public class ContratController {


    private final IContratService contratService;

    public ContratController(IContratService contratService) {
        this.contratService = contratService;
    }

    @PostMapping("/save")
    public ResponseEntity<GlobalResponse<ResponseContratDTO>> addContrat(@RequestBody RequestContratDTO requestContratDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(contratService.addContratOfEtudiant(requestContratDTO));

    }

    @PostMapping("/update/{contractId}")
    public ResponseEntity<GlobalResponse<ResponseContratDTO>> updateContract(@RequestBody RequestContratDTO requestContratDTO, @PathVariable String contractId){
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(contratService.updateContract(requestContratDTO,contractId));
    }


    @GetMapping("/getAllContract")
    public ResponseEntity<GlobalResponse<List<ResponseContratDTO>>> getAllContract(){
        return ResponseEntity.status(200).body(contratService.getAllContracts());
    }
    @GetMapping("/getContractById/{contractId}")
    public ResponseEntity<GlobalResponse<ResponseContratDTO>> getContractById(@PathVariable String contractId){
        return ResponseEntity.status(200).body(contratService.retreiveContractById(contractId));
    }

    @DeleteMapping("/deleteContractById/{contractId}")
    public ResponseEntity<GlobalResponse<Boolean>> deleteById(@PathVariable String contractId){
        return ResponseEntity.status(200).body(contratService.deleteContractById(contractId));
    }

    @GetMapping("/affectContractToStudent/{idContrat}/{nomE}/{prenomE}")
    public ResponseEntity<GlobalResponse<ResponseContratDTO>> affectContractToStudent(@PathVariable String idContrat ,@PathVariable String nomE,@PathVariable String prenomE){
        return ResponseEntity.status(200).body(contratService.affectContratToEtudiant(idContrat,nomE,prenomE));
    }

    @GetMapping("/findMtPerSpecialite/{dateDebut}/{dateFin}")
    public ResponseEntity<GlobalResponse<List<String>>> findMtPerSpecialite(LocalDate dateDebut,LocalDate dateFin){
        return ResponseEntity.status(200).body(contratService.findMontantParSpecialite(dateDebut,dateFin));
    }


    @GetMapping("/nbrContractValides/{dateDebut}/{dateFin}")
    public ResponseEntity<GlobalResponse<Long>> nbrContractValides(LocalDate dateDebut,LocalDate dateFin){
        return ResponseEntity.status(200).body(contratService.nbContratsValides(dateDebut,dateFin));
    }

    @GetMapping("/contratValidesParEtudiant")
    public ResponseEntity<GlobalResponse<Map<String,List<Object[]>>>> contratValidesParEtudiant(){
        return ResponseEntity.status(200).body(contratService.findContractActiveByStudent());
    }
}

package org.example.kaddem.services;

import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestContratDTO;
import org.example.kaddem.dtos.ResponseContratDTO;
import org.example.kaddem.dtos.ResponseEtudiantDTO;
import org.example.kaddem.entities.Contrat;
import org.example.kaddem.entities.Etudiant;
import org.example.kaddem.enums.Specialite;
import org.example.kaddem.prefixConstantes.P_CONSTANTES;
import org.example.kaddem.repositories.ContratRepository;
import org.example.kaddem.repositories.EtudiantRepository;
import org.example.kaddem.utils.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ContratServiceImpl implements IContratService {

    private final ContratRepository contratRepository;
    private final EtudiantRepository etudiantRepository;
    private final IEtudiantService etudiantService;
    private final Util util;

    public ContratServiceImpl(ContratRepository contratRepository, EtudiantRepository etudiantRepository, IEtudiantService etudiantService, Util util) {
        this.contratRepository = contratRepository;
        this.etudiantRepository = etudiantRepository;
        this.etudiantService = etudiantService;
        this.util = util;
    }

    @Override
    public GlobalResponse<ResponseContratDTO> addContratOfEtudiant(RequestContratDTO requestContratDTO)  {

       Contrat contrat = Contrat.toContract(requestContratDTO);
       contrat.setId(util.generateId(P_CONSTANTES.PREFIXContrat.concat(String.valueOf(this.getAllContracts().getData().size()))));
       if(requestContratDTO.getEtudiantId() != null){
          Etudiant etudiant = etudiantRepository.findById(requestContratDTO.getEtudiantId()).orElse(null);
          if(etudiant != null){
              contrat.setEtudiant(etudiant);
              return new GlobalResponse<>(ResponseContratDTO.toResponseContratDTO(contratRepository.save(contrat)),true);
          }
      }else {
          return new GlobalResponse<>(ResponseContratDTO.toResponseContratDTO(contratRepository.save(contrat)),true);
      }

       return new GlobalResponse<>(null,false);
       }



    @Override
    public GlobalResponse<List<ResponseContratDTO>> getAllContracts() {

        return new GlobalResponse<>(contratRepository.findAll().stream().map(ResponseContratDTO::toResponseContratDTO).toList());

    }

    @Override
    public GlobalResponse<ResponseContratDTO> updateContract(RequestContratDTO requestContratDTO,String contactId) {
        GlobalResponse<ResponseContratDTO> globalResponseContract = this.retreiveContractById(contactId);
        if(!globalResponseContract.isSucces()){
            return new GlobalResponse<>(null,false);
        }
        Contrat contrat = Contrat.toContract(requestContratDTO);
        GlobalResponse<ResponseEtudiantDTO> globalResponseEtudiant = etudiantService.retrieveEtudiantById(requestContratDTO.getEtudiantId());
        if(! globalResponseEtudiant.isSucces()){
            return new GlobalResponse<>(null,false);
        }
        contrat.setEtudiant(Etudiant.toEtudiantFromResponse(globalResponseEtudiant.getData()));
        return new GlobalResponse<>(ResponseContratDTO.toResponseContratDTO(contrat),true);

    }

    @Override
    public GlobalResponse<ResponseContratDTO> retreiveContractById(String contractId) {

        return (contratRepository.findById(contractId).isPresent()) ? new GlobalResponse<>(ResponseContratDTO.toResponseContratDTO(contratRepository.findById(contractId).get()),true)
                : new GlobalResponse<>(null,false);
    }

    @Override
    public GlobalResponse<Boolean> deleteContractById(String contractId) {
        contratRepository.deleteById(contractId);

        return (this.retreiveContractById(contractId).isSucces()) ? new GlobalResponse<>(true) :
                new GlobalResponse<>(false);
    }

    @Override
    public GlobalResponse<ResponseContratDTO> affectContratToEtudiant(String idContrat, String nomE, String prenomE) {
        Optional<Etudiant> etudiant = etudiantRepository.getByFullName(nomE,prenomE);
        if(etudiant.isPresent()){
            contratRepository.affectContractToStudent(etudiant.get(),idContrat);
            return new GlobalResponse<>(null,true,"affectation réussi");
        }else{
            return new GlobalResponse<>(null,false,"etudiant n'existe pas");
        }
    }

    @Override
    public GlobalResponse<List<String>> findMontantParSpecialite(LocalDate dateDebut, LocalDate dateFin) {
        List<String> mtsParSpecialite = new ArrayList<>();
        List<Object[]> results = contratRepository.findMontantParSpecialite(LocalDate.of(2024,10,29),LocalDate.of(2024,11,29));

        for(Object[] row : results){
            Specialite specialite = (Specialite) row[0];
            long totalMontant = (long) row[1];
            String ch = "Pour un contrat dont la spécialité est "+ specialite+ " : "+totalMontant+"DT";
            mtsParSpecialite.add(ch);
        }
        return new GlobalResponse<>(mtsParSpecialite,true);
    }

    @Override
    public GlobalResponse<Long> nbContratsValides(LocalDate startDate, LocalDate endDate) {
       long nbrContracts = contratRepository.nbContratsValides(startDate,endDate);
       return new GlobalResponse<>(nbrContracts,true);
    }

    @Override
    public GlobalResponse<Map<String, List<Object[]>>> findContractActiveByStudent() {
        List<Object[]> objects = contratRepository.findActifContract();
        Map<String, List<Object[]>> activeContractPerStudent ;
       activeContractPerStudent = objects.stream()
               .collect(Collectors.groupingBy(o -> String.valueOf(o[0]) ));
       return new GlobalResponse<>(activeContractPerStudent,true);
    }

}

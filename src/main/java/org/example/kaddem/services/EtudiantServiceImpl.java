package org.example.kaddem.services;

import jakarta.transaction.Transactional;
import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestEtudiantDTO;
import org.example.kaddem.dtos.ResponseEtudiantDTO;
import org.example.kaddem.entities.Contrat;
import org.example.kaddem.entities.Departement;
import org.example.kaddem.entities.Equipe;
import org.example.kaddem.entities.Etudiant;
import org.example.kaddem.enums.Option;
import org.example.kaddem.exceptions.DataNotFoundException;
import org.example.kaddem.prefixConstantes.Message_Constant;
import org.example.kaddem.prefixConstantes.P_CONSTANTES;
import org.example.kaddem.repositories.ContratRepository;
import org.example.kaddem.repositories.DepartementRepository;
import org.example.kaddem.repositories.EquipeRepository;
import org.example.kaddem.repositories.EtudiantRepository;
import org.example.kaddem.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EtudiantServiceImpl implements IEtudiantService {


    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private ContratRepository contratRepository;
    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    Util util;

    @Override
    public GlobalResponse<ResponseEtudiantDTO> addEtudiant(RequestEtudiantDTO etudiantDTO) {
        Etudiant etudiant = Etudiant.toEtudiantFromRequest(etudiantDTO);
        etudiant.setIdEtudiant(util.generateId(P_CONSTANTES.PREFIXETUD.concat(String.valueOf(this.getAll().getData().size()))));
        return new GlobalResponse<>(ResponseEtudiantDTO.toEtudiantDTOResponse(etudiantRepository.save(etudiant)),true);
    }
    @Override
    public GlobalResponse<ResponseEtudiantDTO> updateEtudiant(RequestEtudiantDTO etudiantDTO, String etudiantId) {
        Etudiant etudiant = Etudiant.toEtudiantFromRequest(etudiantDTO);
        GlobalResponse<ResponseEtudiantDTO> responseEtudiantDTO = retrieveEtudiantById(etudiantId);
        if(responseEtudiantDTO.getData() == null){
           ResponseEtudiantDTO responseEtudiantDTO1 = new ResponseEtudiantDTO();
          // responseEtudiantDTO1.setSucces(false);
           return new GlobalResponse<>(responseEtudiantDTO1,false);
        }
        etudiant.setIdEtudiant(etudiantId);
        return new GlobalResponse<>(ResponseEtudiantDTO.toEtudiantDTOResponse(etudiantRepository.save(etudiant)),true);
    }

    @Override
    public GlobalResponse<ResponseEtudiantDTO> retrieveEtudiantById(String etudiantId) {
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElse(null);
        return (etudiant!= null) ? new GlobalResponse<>(ResponseEtudiantDTO.toEtudiantDTOResponse(etudiant),true) :
                new GlobalResponse<>(null,false, Message_Constant.messageNotFound);
    }

    @Override
    public GlobalResponse<Boolean> removeEtudiant(String etudiantId) {
         etudiantRepository.deleteById(etudiantId);
         return (! this.retrieveEtudiantById(etudiantId).isSucces()) ? new GlobalResponse<>(false)
                 : new GlobalResponse<>(true);
    }

    @Override
    public GlobalResponse<List<ResponseEtudiantDTO>> getAll() {
        List<ResponseEtudiantDTO> etudiantDTOS = etudiantRepository.findAll()
                .stream()
                .map(ResponseEtudiantDTO::toEtudiantDTOResponse)
                .toList();
        return new GlobalResponse<>(etudiantDTOS,true);
    }

    @Override
    public GlobalResponse<Boolean> assignEtudiantToDepartement(String etudiantId, String departementId) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(etudiantId);
        if(etudiant.isEmpty()){
            System.out.println("empty");
        }
        Optional<Departement> departement = departementRepository.findById(departementId);
        if (etudiant.isPresent() && departement.isPresent()){
            etudiantRepository.assignEtudiantToDepartement(departement.get(),etudiantId);
            return new GlobalResponse<>(true);
        }else{
            return new GlobalResponse<>(false);
        }
    }

    @Override
    @Transactional
    public GlobalResponse<ResponseEtudiantDTO> addAndAssignEtudiantToEquipeAndContract(RequestEtudiantDTO e, String idContrat, String idEquipe) {
        Optional<Equipe> equipe = equipeRepository.findById(idEquipe);
        Optional<Contrat> contrat = contratRepository.findById(idContrat);
        if(equipe.isPresent() && contrat.isPresent()){
            Etudiant etudiant = Etudiant.toEtudiantFromRequest(e);
            etudiant.setIdEtudiant(util.generateId(P_CONSTANTES.PREFIXETUD.concat(String.valueOf(this.getAll().getData().size()))));
            List<Equipe> equipes = Arrays.asList(equipe.get());
            etudiant.setEquipes(equipes);
            List<Contrat> contrats = Arrays.asList(contrat.get());
            etudiant.setContrats(contrats);
            Etudiant etudiant1 = etudiantRepository.save(etudiant);
            equipe.get().getEtudiants().add(etudiant);
            contrat.get().setEtudiant(etudiant1);
            return new GlobalResponse<>(ResponseEtudiantDTO.toEtudiantDTOResponse(etudiant1));
        }else{
            return new GlobalResponse<>(null,false,"etuduiant non ajouté");
        }

    }

    @Override
    public GlobalResponse<List<ResponseEtudiantDTO>> getByDepartement(String idDepartement) {
        List<ResponseEtudiantDTO> responseEtudiantDTOS =  etudiantRepository.getAllByDepartement(idDepartement)
                .stream()
                .map(ResponseEtudiantDTO::toEtudiantDTOResponse)
                .toList();
        return new GlobalResponse<>(responseEtudiantDTOS,true,"List des etudiant pour dept "+idDepartement);
    }
}

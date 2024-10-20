package org.example.kaddem.services;

import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestContratDTO;
import org.example.kaddem.dtos.ResponseContratDTO;
import org.example.kaddem.dtos.ResponseEtudiantDTO;
import org.example.kaddem.entities.Contrat;
import org.example.kaddem.entities.Etudiant;
import org.example.kaddem.prefixConstantes.P_CONSTANTES;
import org.example.kaddem.repositories.ContratRepository;
import org.example.kaddem.repositories.EtudiantRepository;
import org.example.kaddem.utils.Util;
import org.springframework.stereotype.Service;

import java.util.List;

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
       Etudiant etudiant = etudiantRepository.findById(requestContratDTO.getEtudiantId()).orElse(null);
       if(etudiant != null){
           contrat.setEtudiant(etudiant);
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

}

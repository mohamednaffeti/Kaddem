package org.example.kaddem.services;

import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestEtudiantDTO;
import org.example.kaddem.dtos.ResponseEtudiantDTO;
import org.example.kaddem.entities.Etudiant;
import org.example.kaddem.prefixConstantes.Message_Constant;
import org.example.kaddem.prefixConstantes.P_CONSTANTES;
import org.example.kaddem.repositories.EtudiantRepository;
import org.example.kaddem.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantServiceImpl implements IEtudiantService {


    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    Util util;

    @Override
    public GlobalResponse<ResponseEtudiantDTO> addEtudiant(RequestEtudiantDTO etudiantDTO) {
        Etudiant etudiant = Etudiant.toEtudiantFromRequest(etudiantDTO);
        etudiant.setIdEtudiant(util.generateId(P_CONSTANTES.PREFIXETUD.concat(String.valueOf(this.getAll().getData().size()))));
        return new GlobalResponse<>(ResponseEtudiantDTO.toEtudiantDTOResponse(etudiant),true);
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
}

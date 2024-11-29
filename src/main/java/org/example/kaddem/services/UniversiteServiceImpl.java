package org.example.kaddem.services;

import org.example.kaddem.dtos.*;
import org.example.kaddem.entities.Departement;
import org.example.kaddem.entities.Universite;
import org.example.kaddem.prefixConstantes.Message_Constant;
import org.example.kaddem.prefixConstantes.P_CONSTANTES;
import org.example.kaddem.repositories.UniversiteRepository;
import org.example.kaddem.utils.Util;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UniversiteServiceImpl implements IUniversiteService {

    private final UniversiteRepository universiteRepository;
    private final IDepartementService departementService;
    private final Util util;

    public UniversiteServiceImpl(UniversiteRepository universiteRepository,IDepartementService departementRepository, Util util) {
        this.universiteRepository = universiteRepository;
        this.departementService=departementRepository;
        this.util=util;
    }

    @Override
    public GlobalResponse<ResponseUniversiteDTO> addUniversite(RequestUniversiteDTO requestUniversiteDTO) {
        Universite universite = Universite.fromRequestUniversiteDtoToUniversite(requestUniversiteDTO);
        if(requestUniversiteDTO.getDepartements() != null){
            for(RequestDepartementDTO requestDepartementDTO : requestUniversiteDTO.getDepartements()){
                Departement departement = Departement.fromDTORequestToDepartement(requestDepartementDTO);
                departement.setIdDepartement(util.generateId(P_CONSTANTES.PREFIXDEPT).concat(String.valueOf(departementService.retreiveAllDepartements().getData().size())));
                universite.getDepartements().add(departement);
                departement.setUniversite(universite);
            }
        }


        universite.setIdUniversite(util.generateId(P_CONSTANTES.PREFIXUNIV).concat(String.valueOf(retrieveAllUnversities().getData().size())));
        return new GlobalResponse<>(ResponseUniversiteDTO.fromEntityToResponseUniversity(universiteRepository.save(universite)),true);
    }

    @Override
    public GlobalResponse<ResponseUniversiteDTO> updateUniversite(RequestUniversiteDTO requestUniversiteDTO, String universityId) {
        if(retrieveAllUnversities().getData().stream().anyMatch(university -> university.getIdUniversite().equals(universityId))){
            Universite universite = Universite.fromRequestUniversiteDtoToUniversite(requestUniversiteDTO);
            universite.setIdUniversite(universityId);
            List<ResponseDepartementDTO> departementsForUniversity = retrieveAllUnversities()
                    .getData().stream()
                    .filter(universite1-> universite1.getIdUniversite().equals(universityId))
                    .flatMap(responseUniversiteDTO -> responseUniversiteDTO.getDepartements().stream())
                    .toList();
            List<Departement> departements = new ArrayList<>();
            for(ResponseDepartementDTO departementDTO : departementsForUniversity){
                Departement departement = Departement.builder()
                                .idDepartement(departementDTO.getIdDepartement())
                                        .nomDepartement(departementDTO.getNomDepartement()).build();
                departements.add(departement);
                universite.setDepartements(departements);
            }

            return new GlobalResponse<>(ResponseUniversiteDTO.fromEntityToResponseUniversity(universiteRepository.save(universite)),true);
        }else{
            return new GlobalResponse<>(null,false, Message_Constant.messageNotFound);
        }
    }

    @Override
    public GlobalResponse<List<ResponseUniversiteDTO>> retrieveAllUnversities() {
        return new GlobalResponse<>(universiteRepository.findAll().stream().map(ResponseUniversiteDTO::fromEntityToResponseUniversity).toList(),true);
    }

    @Override
    public GlobalResponse<ResponseUniversiteDTO> retrieveUniversityById(String universityId) {
        return (universiteRepository.findById(universityId).isPresent())
                ? new GlobalResponse<>(ResponseUniversiteDTO.fromEntityToResponseUniversity(universiteRepository.findById(universityId).get()))
                : new GlobalResponse<>(null,false,Message_Constant.messageNotFound);
    }

    @Override
    public GlobalResponse<Boolean> deleteUniversity(String universityId) {
        universiteRepository.deleteById(universityId);

        return (this.retrieveUniversityById(universityId).isSucces()) ? new GlobalResponse<>(true) :
                new GlobalResponse<>(false);
    }
}

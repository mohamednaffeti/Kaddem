package org.example.kaddem.services;

import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestDepartementDTO;
import org.example.kaddem.dtos.ResponseDepartementDTO;
import org.example.kaddem.entities.Departement;
import org.example.kaddem.entities.Universite;
import org.example.kaddem.prefixConstantes.P_CONSTANTES;
import org.example.kaddem.repositories.DepartementRepository;
import org.example.kaddem.repositories.UniversiteRepository;
import org.example.kaddem.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartementServiceImpl implements IDepartementService {


    private final DepartementRepository departementRepository;
    private final UniversiteRepository universiteRepository;
    private final Util util;

    public DepartementServiceImpl(DepartementRepository departementRepository, UniversiteRepository universiteRepository, Util util) {
        this.departementRepository = departementRepository;
        this.universiteRepository = universiteRepository;
        this.util = util;
    }

    @Override
    public GlobalResponse<List<ResponseDepartementDTO>> retreiveAllDepartements() {
        return new GlobalResponse<>(
                departementRepository.findAll()
                        .stream()
                        .map(ResponseDepartementDTO::fromDeptToResponseDepartementDTO)
                        .toList(),
                true);
    }

    @Override
    public GlobalResponse<ResponseDepartementDTO> addDepartement(RequestDepartementDTO requestDepartementDTO) {
        Departement departement = Departement.fromDTORequestToDepartement(requestDepartementDTO);
        departement.setIdDepartement(util.generateId(P_CONSTANTES.PREFIXDEPT.concat(String.valueOf(this.retreiveAllDepartements().getData().size()))));
        return new GlobalResponse<>(ResponseDepartementDTO.fromDeptToResponseDepartementDTO(departementRepository.save(departement)),true,"Ajout de département avec succès");

    }

    @Override
    public GlobalResponse<ResponseDepartementDTO> updateDepartement(RequestDepartementDTO requestDepartementDTO, String departementId) {
        GlobalResponse<ResponseDepartementDTO> response = retreiveById(departementId);
        if(response.getData() == null) {
            return new GlobalResponse<>(null,false);
        }
        Departement departement = Departement.fromDTORequestToDepartement(requestDepartementDTO);
        departement.setIdDepartement(departementId);
        return new GlobalResponse<>(ResponseDepartementDTO.fromDeptToResponseDepartementDTO(departementRepository.save(departement)),true);
    }

    @Override
    public GlobalResponse<ResponseDepartementDTO> retreiveById(String departementId) {

        return (departementRepository.findById(departementId).isPresent())
                ? new GlobalResponse<>(ResponseDepartementDTO.fromDeptToResponseDepartementDTO(departementRepository.findById(departementId).get()),true)
                : new GlobalResponse<>(null,false);
    }

    @Override
    public GlobalResponse<Boolean> deleteById(String departementId) {
        departementRepository.deleteById(departementId);
        return (retreiveById(departementId).isSucces())
                ? new GlobalResponse<>(false)
                : new GlobalResponse<>(true);
    }

    @Override
    public GlobalResponse<Boolean> assignUniversiteToDepartement(String universityId, String departementId) {
        Optional<Departement> departement = departementRepository.findById(departementId);
        Optional<Universite> universite = universiteRepository.findById(universityId);
        if(departement.isPresent() && universite.isPresent()){
            departementRepository.assignUniversiteToDepartement(universite.get(),departementId);
            return new GlobalResponse<>(true,"affectation avec succès");
        }else {
            return new GlobalResponse<>(false,"affectation k.o");
        }
    }

    @Override
    public GlobalResponse<List<ResponseDepartementDTO>> retreiveDepartementsByUniversite(String universityId) {
        return new GlobalResponse<>(departementRepository.getdepartementsByUniv(universityId)
                .stream()
                .map(ResponseDepartementDTO::fromDeptToResponseDepartementDTO)
                .toList(),true,"departements pour l'universite "+universityId);
    }

    @Override
    public GlobalResponse<Map<String, List<Object[]>>> groupeByDepartement() {
        return new GlobalResponse<>( departementRepository.groupeByDepartement()
                .stream().collect(Collectors.groupingBy(o ->(String) o[0])),true);
    }
}

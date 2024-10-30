package org.example.kaddem.services;

import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestDepartementDTO;
import org.example.kaddem.dtos.ResponseContratDTO;
import org.example.kaddem.dtos.ResponseDepartementDTO;

import java.util.List;
import java.util.Map;

public interface IDepartementService {

    GlobalResponse<List<ResponseDepartementDTO>> retreiveAllDepartements();

    GlobalResponse<ResponseDepartementDTO> addDepartement(RequestDepartementDTO requestDepartementDTO);

    GlobalResponse<ResponseDepartementDTO> updateDepartement(RequestDepartementDTO requestDepartementDTO,String departementId);

    GlobalResponse<ResponseDepartementDTO> retreiveById(String departementId);

    GlobalResponse<Boolean> deleteById(String departementId);
    GlobalResponse<Boolean> assignUniversiteToDepartement (String universityId, String departementId) ;

    GlobalResponse<List<ResponseDepartementDTO>> retreiveDepartementsByUniversite(String universityId);

    GlobalResponse<Map<String,List<Object[]>>> groupeByDepartement();
}

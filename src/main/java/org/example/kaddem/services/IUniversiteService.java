package org.example.kaddem.services;

import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestUniversiteDTO;
import org.example.kaddem.dtos.ResponseEtudiantDTO;
import org.example.kaddem.dtos.ResponseUniversiteDTO;
import java.util.List;

public interface IUniversiteService {

    GlobalResponse<ResponseUniversiteDTO> addUniversite(RequestUniversiteDTO requestUniversiteDTO);
    GlobalResponse<ResponseUniversiteDTO> updateUniversite(RequestUniversiteDTO requestUniversiteDTO,String universityId);

    GlobalResponse<List<ResponseUniversiteDTO>> retrieveAllUnversities();

    GlobalResponse<ResponseUniversiteDTO> retrieveUniversityById(String universityId);

    GlobalResponse<Boolean> deleteUniversity(String universityId);


}

package org.example.kaddem.services;

import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestEquipeDTO;
import org.example.kaddem.dtos.ResponseEquipeDTO;
import org.example.kaddem.enums.Niveau;

import java.util.List;

public interface IEquipeService {

    GlobalResponse<ResponseEquipeDTO> addEquipeWithDetails(RequestEquipeDTO requestEquipeDTO);

    GlobalResponse<List<ResponseEquipeDTO>> retrieveAllEquipes();

    GlobalResponse<ResponseEquipeDTO> updateEquipe(RequestEquipeDTO requestEquipeDTO,String equipeId);

    GlobalResponse<ResponseEquipeDTO> retrieveEquipe(String equipeId);

    GlobalResponse<List<ResponseEquipeDTO>> findByNiveau(Niveau niveau);


}

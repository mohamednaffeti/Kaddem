package org.example.kaddem.services;

import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestEquipeDTO;
import org.example.kaddem.dtos.ResponseEquipeDTO;

import java.util.List;

public interface IEquipeService {

    GlobalResponse<ResponseEquipeDTO> addEquipeWithDetails(RequestEquipeDTO requestEquipeDTO);

    GlobalResponse<List<ResponseEquipeDTO>> retrieveAllEquipes();


}

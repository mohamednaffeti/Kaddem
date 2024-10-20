package org.example.kaddem.services;


import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.ResponseDetailsEquipeDTO;

import java.util.List;

public interface IDetailsEquipeService {

    GlobalResponse<List<ResponseDetailsEquipeDTO>> getDetailsEquipeByIdEquipe(String equipeId);

    GlobalResponse<Long> getCountOfDetailsEquipe();

}

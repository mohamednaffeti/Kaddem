package org.example.kaddem.services;

import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.ResponseDetailsEquipeDTO;
import org.example.kaddem.repositories.DetailsEquipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailsEquipeServiceImpl implements IDetailsEquipeService {
    private final DetailsEquipeRepository detailsEquipeRepository;

    public DetailsEquipeServiceImpl(DetailsEquipeRepository detailsEquipeRepository) {
        this.detailsEquipeRepository = detailsEquipeRepository;
    }

    @Override
    public GlobalResponse<List<ResponseDetailsEquipeDTO>> getDetailsEquipeByIdEquipe(String equipeId) {
        return null;
    }

    @Override
    public GlobalResponse<Long> getCountOfDetailsEquipe() {
        return new GlobalResponse<>(detailsEquipeRepository.countRowsInDetailsEquipe());
    }
}

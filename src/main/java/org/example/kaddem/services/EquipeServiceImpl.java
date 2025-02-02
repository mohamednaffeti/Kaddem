package org.example.kaddem.services;

import jakarta.transaction.Transactional;
import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestEquipeDTO;
import org.example.kaddem.dtos.ResponseEquipeDTO;
import org.example.kaddem.entities.DetailsEquipe;
import org.example.kaddem.entities.Equipe;
import org.example.kaddem.enums.Niveau;
import org.example.kaddem.prefixConstantes.P_CONSTANTES;
import org.example.kaddem.repositories.EquipeRepository;
import org.example.kaddem.repositories.UniversiteRepository;
import org.example.kaddem.utils.Util;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipeServiceImpl implements IEquipeService {

    private final EquipeRepository equipeRepository;
    private final IDetailsEquipeService detailsEquipeService;
    private final Util util;


    public EquipeServiceImpl(EquipeRepository equipeRepository, IDetailsEquipeService detailsEquipeService, Util util) {
        this.equipeRepository = equipeRepository;
        this.detailsEquipeService = detailsEquipeService;
        this.util = util;
    }

    @Override
    @Transactional
    public GlobalResponse<ResponseEquipeDTO> addEquipeWithDetails(RequestEquipeDTO requestEquipeDTO) {
        Equipe equipe = Equipe.fromRequestDTOtoEntity(requestEquipeDTO);
        equipe.setIdEquipe(util.generateId(P_CONSTANTES.PREFIXEQUIP.concat(String.valueOf(retrieveAllEquipes().getData().size()))));
        DetailsEquipe detailsEquipe = DetailsEquipe.fromRequestEquipeDTOTODetailsEquipe(requestEquipeDTO);
        detailsEquipe.setIdDetailEquipe(util.generateId(P_CONSTANTES.PREFIXEQDET.concat(String.valueOf(detailsEquipeService.getCountOfDetailsEquipe().getData()))));
        equipe.setDetailsEquipe(detailsEquipe);
        //detailsEquipe.setEquipe(equipe);
        return new GlobalResponse<>(ResponseEquipeDTO.fromEntityToResponseEquipeDTO(equipeRepository.save(equipe)),true);
    }

    @Override
    public GlobalResponse<List<ResponseEquipeDTO>> retrieveAllEquipes() {
        List<Equipe > list = equipeRepository.findAll();
        return new GlobalResponse<>(
                equipeRepository.findAll()
                        .stream()
                        .map(ResponseEquipeDTO::fromEntityToResponseEquipeDTO)
                        .toList()
                ,true);
    }

    @Override
    public GlobalResponse<ResponseEquipeDTO> updateEquipe(RequestEquipeDTO requestEquipeDTO,String equipeId) {
        GlobalResponse<ResponseEquipeDTO> equipe = retrieveEquipe(equipeId);
        if(! equipe.isSucces()){
            return new GlobalResponse<>(null,false,"id equipe n'existe pas "+ equipeId);
        }
        Equipe equipe1 = Equipe.fromRequestDTOtoEntity(requestEquipeDTO);
        equipe1.setIdEquipe(equipeId);
        DetailsEquipe detailsEquipe = DetailsEquipe.fromRequestEquipeDTOTODetailsEquipe(requestEquipeDTO);
        equipe1.setDetailsEquipe(detailsEquipe);
        return new GlobalResponse<>(ResponseEquipeDTO.fromEntityToResponseEquipeDTO(equipeRepository.save(equipe1)),true);
    }

    @Override
    public GlobalResponse<ResponseEquipeDTO> retrieveEquipe(String equipeId) {
        return (equipeRepository.findById(equipeId).isPresent())
                ? new GlobalResponse<>(ResponseEquipeDTO.fromEntityToResponseEquipeDTO(equipeRepository.findById(equipeId).get()),true)
                : new GlobalResponse<>(null,false);
    }

    @Override
    public GlobalResponse<List<ResponseEquipeDTO>> findByNiveau(Niveau niveau) {
        return new GlobalResponse<>(equipeRepository.findWithNiveau(niveau).stream()
                .map(ResponseEquipeDTO::fromEntityToResponseEquipeDTO).toList());
    }


}

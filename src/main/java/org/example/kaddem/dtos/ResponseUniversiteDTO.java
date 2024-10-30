package org.example.kaddem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kaddem.entities.Departement;
import org.example.kaddem.entities.Universite;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUniversiteDTO {
    private String idUniversite;
    private String nomUniversite;
    private List<ResponseDepartementDTO> departements = new ArrayList<>();


    public static ResponseUniversiteDTO fromEntityToResponseUniversity(Universite universite){
        List<ResponseDepartementDTO> departementDTOS = new ArrayList<>();
        ResponseUniversiteDTO responseUniversiteDTO =  ResponseUniversiteDTO.builder()
                .idUniversite(universite.getIdUniversite())
                .nomUniversite(universite.getNomUniversite())
                .build();

        for(Departement dept : universite.getDepartements()){

            departementDTOS.add(ResponseDepartementDTO.fromDeptToResponseDepartementDTO(dept));

        }
        responseUniversiteDTO.setDepartements(departementDTOS);
        return responseUniversiteDTO;
    }
    // a testerrr
}

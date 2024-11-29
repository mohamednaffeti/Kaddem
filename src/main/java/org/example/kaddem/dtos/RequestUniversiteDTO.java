package org.example.kaddem.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUniversiteDTO {

    private String nomUniversite;
    private List<RequestDepartementDTO> departements;
}

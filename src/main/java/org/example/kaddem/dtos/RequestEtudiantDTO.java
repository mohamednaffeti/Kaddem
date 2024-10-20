package org.example.kaddem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kaddem.enums.Option;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestEtudiantDTO {
    private String prenomE;
    private String nomE;
    private Option option;



}

package org.example.kaddem.services;

import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestContratDTO;
import org.example.kaddem.dtos.ResponseContratDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IContratService {

    GlobalResponse<ResponseContratDTO> addContratOfEtudiant(RequestContratDTO requestContratDTO);

    GlobalResponse<List<ResponseContratDTO>> getAllContracts();

    GlobalResponse<ResponseContratDTO> updateContract(RequestContratDTO requestContratDTO ,String contactId);

    GlobalResponse<ResponseContratDTO> retreiveContractById(String contractId);

    GlobalResponse<Boolean> deleteContractById(String contractId);

    GlobalResponse<ResponseContratDTO> affectContratToEtudiant (String idContrat,String nomE,String prenomE);

    GlobalResponse<List<String>> findMontantParSpecialite(LocalDate dateDebut, LocalDate dateFin);

    GlobalResponse<Long> nbContratsValides(LocalDate startDate, LocalDate endDate);

    GlobalResponse<Map<String, List<Object[]>>> findContractActiveByStudent();
}

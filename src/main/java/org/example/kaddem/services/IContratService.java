package org.example.kaddem.services;

import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestContratDTO;
import org.example.kaddem.dtos.ResponseContratDTO;
import org.example.kaddem.entities.Contrat;

import java.util.List;

public interface IContratService {

    GlobalResponse<ResponseContratDTO> addContratOfEtudiant(RequestContratDTO requestContratDTO);

    GlobalResponse<List<ResponseContratDTO>> getAllContracts();

    GlobalResponse<ResponseContratDTO> updateContract(RequestContratDTO requestContratDTO ,String contactId);

    GlobalResponse<ResponseContratDTO> retreiveContractById(String contractId);

    GlobalResponse<Boolean> deleteContractById(String contractId);

}

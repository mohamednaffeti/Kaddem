package org.example.kaddem.services;

import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestEtudiantDTO;
import org.example.kaddem.dtos.ResponseEtudiantDTO;
import org.example.kaddem.entities.Etudiant;

import java.util.List;

public interface IEtudiantService {

    GlobalResponse<ResponseEtudiantDTO> addEtudiant(RequestEtudiantDTO etudiant);
    GlobalResponse<ResponseEtudiantDTO> updateEtudiant(RequestEtudiantDTO etudiantDTO , String etudiantId);

    GlobalResponse<ResponseEtudiantDTO> retrieveEtudiantById(String etudiantId);
    GlobalResponse<Boolean> removeEtudiant(String etudiantId);

    GlobalResponse<List<ResponseEtudiantDTO>> getAll();

    GlobalResponse<Boolean> assignEtudiantToDepartement (String etudiantId, String departementId);

    GlobalResponse<ResponseEtudiantDTO> addAndAssignEtudiantToEquipeAndContract(RequestEtudiantDTO e, String idContrat, String idEquipe);
    GlobalResponse<List<ResponseEtudiantDTO>> getByDepartement(String idDepartement);
}

package org.example.kaddem.controller;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.kaddem.dtos.GlobalResponse;
import org.example.kaddem.dtos.RequestUniversiteDTO;
import org.example.kaddem.dtos.ResponseUniversiteDTO;
import org.example.kaddem.services.IUniversiteService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/universite")
@Tag(name = "Université Controller", description = "Gestion des universités")
public class UniversiteController {

    private final IUniversiteService universiteService;

    public UniversiteController(IUniversiteService universiteService) {
        this.universiteService = universiteService;
    }

    @Operation(summary = "Récupérer toutes les universités", description = "Retourne la liste de toutes les universités")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Liste des universités récupérée avec succès"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erreur serveur lors de la récupération des universités")
    })
    @GetMapping("/getAllUniversite")
    public ResponseEntity<GlobalResponse<List<ResponseUniversiteDTO>>> getAllUniversites() {

            return ResponseEntity.status(200).body(universiteService.retrieveAllUnversities());

    }

    @Operation(summary = "Ajouter une nouvelle université",
            description = "Ajoute une université dans le système")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Université ajoutée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseUniversiteDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requête invalide",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erreur serveur lors de l'ajout de l'université",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/addNewUniversite")
    public ResponseEntity<GlobalResponse<ResponseUniversiteDTO>> addNewUniversite(
            @Parameter(description = "Données pour ajouter une nouvelle université", required = true)
            @RequestBody RequestUniversiteDTO requestUniversiteDTO) {

            return ResponseEntity.status(200).body(universiteService.addUniversite(requestUniversiteDTO));

    }

    @Operation(summary = "Mettre à jour une université")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Université mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Université non trouvée avec cet ID"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur lors de la mise à jour de l'université")
    })
    @PutMapping("/updateUniversity/{universityId}")
    public ResponseEntity<GlobalResponse<ResponseUniversiteDTO>> updateUniversite (
            @RequestBody RequestUniversiteDTO requestUniversiteDTO,
            @Parameter(description = "ID de l'université à mettre à jour") @PathVariable String universityId) {
        try {
            GlobalResponse<ResponseUniversiteDTO> response = universiteService.updateUniversite(requestUniversiteDTO, universityId);
            return ResponseEntity.status(200).body(response);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(new GlobalResponse<>( null,false,"Université non trouvée"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new GlobalResponse<>( null,false,"Erreur serveur"));
        }
    }

    @Operation(summary = "Récupérer une université par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Université récupérée avec succès"),
            @ApiResponse(responseCode = "404", description = "Université non trouvée avec cet ID"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur lors de la récupération de l'université")
    })
    @GetMapping("getById/{universityId}")
    public ResponseEntity<GlobalResponse<ResponseUniversiteDTO>> getUniversityById(
            @Parameter(description = "ID de l'université à récupérer") @PathVariable String universityId) {
        try {
            return ResponseEntity.status(200).body(universiteService.retrieveUniversityById(universityId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(new GlobalResponse<>( null,false,"Université non trouvée"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new GlobalResponse<>(null,false,"Erreur serveur"));
        }
    }



}

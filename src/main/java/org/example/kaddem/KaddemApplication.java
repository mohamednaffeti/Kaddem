package org.example.kaddem;

import org.example.kaddem.entities.Departement;
import org.example.kaddem.entities.Etudiant;
import org.example.kaddem.enums.Specialite;
import org.example.kaddem.repositories.ContratRepository;
import org.example.kaddem.repositories.DepartementRepository;
import org.example.kaddem.repositories.EtudiantRepository;

import java.time.LocalDate;
import java.util.List;

import org.example.kaddem.services.IDepartementService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class KaddemApplication implements CommandLineRunner {
    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private ContratRepository contratRepository;

    @Autowired
    private DepartementRepository departementRepository;
    public static void main(String[] args) {

        SpringApplication.run(KaddemApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

    }
}

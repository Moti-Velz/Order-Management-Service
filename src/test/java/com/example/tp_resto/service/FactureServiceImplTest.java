package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.Facture;
import com.example.tp_resto.exception.FactureNotFoundException;
import com.example.tp_resto.repository.ICommandeRepo;
import com.example.tp_resto.repository.IFactureRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FactureServiceImplTest {

    @Mock
    private ICommandeRepo commandeRepo;

    @Mock
    IFactureRepo factureRepo;

    @InjectMocks
    FactureServiceImpl factureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveFacture() {
        Commande commande = new Commande();
        commande.setOrderTime(Timestamp.valueOf(LocalDateTime.now()));
        // Save the Commande object here if necessary
        when(commandeRepo.save(any(Commande.class))).thenReturn(commande);

        Facture facture = new Facture();
        facture.setCommande(commande);
        facture.setStatus(true);
        facture.setBillTime(Instant.now());

        when(factureRepo.existsByCommandeId(anyInt())).thenReturn(false);
        when(factureRepo.save(any(Facture.class))).thenReturn(facture);

        Facture savedFacture = factureService.saveFacture(facture);

        assertEquals(savedFacture, facture);
        verify(factureRepo, times(1)).save(facture);
    }

    @Test
    void testFindById() {
        Facture facture = new Facture();

        when(factureRepo.findById(any(Integer.class))).thenReturn(Optional.of(facture));

        Facture result = factureService.findById(1);
        assertEquals(facture, result);
    }

    @Test
    void testFindAll() {
        // Implementation will depend on your specific requirements
    }

    @Test
    void testFindByDate() {
        // Currently method is not implemented, you will need to add mock behaviour and assertions once it is implemented
    }

    @Test
    void testUpdateFacture() {
        Facture existingFacture = new Facture();
        existingFacture.setCommande(new Commande());

        Facture newFacture = new Facture();
        Commande newCommande = new Commande();
        newCommande.setId(5);
        newFacture.setCommande(newCommande);

        when(factureRepo.findById(any(Integer.class))).thenReturn(Optional.of(existingFacture));
        when(factureRepo.existsByCommandeId(any(Integer.class))).thenReturn(false);
        when(factureRepo.save(any(Facture.class))).thenAnswer(i -> i.getArguments()[0]);

        Facture result = factureService.updateFacture(1, newFacture);

        assertEquals(newFacture.getCommande().getId(), result.getCommande().getId());
    }


    @Test
    void testDeleteFacture() {
        Facture facture = new Facture();
        when(factureRepo.findById(any(Integer.class))).thenReturn(Optional.of(facture));

        assertDoesNotThrow(() -> factureService.deleteFacture(facture));
    }

    @Test
    void testDeleteFactureById() {
        when(factureRepo.findById(any(Integer.class))).thenReturn(Optional.of(new Facture()));

        assertDoesNotThrow(() -> factureService.deleteFactureById(1));
    }
}

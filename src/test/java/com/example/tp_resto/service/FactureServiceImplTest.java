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
import java.util.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FactureServiceImplTest {

    @Mock
    private ICommandeRepo commandeRepository;

    @Mock
    IFactureRepo factureRepository;

    @InjectMocks
    FactureServiceImpl factureService;

    private Facture facture1, facture2;
    private List<Facture> factureList;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        facture1 = new Facture();
        facture1.setId(1);
        facture2 = new Facture();
        facture2.setId(2);
        factureList = Arrays.asList(facture1, facture2);
    }

    @Test
    void testSaveFacture() {
        Commande commande = new Commande();
        commande.setOrderTime(LocalDateTime.now());
        // Save the Commande object here if necessary
        when(commandeRepository.save(any(Commande.class))).thenReturn(commande);

        Facture facture = new Facture();
        facture.setCommande(commande);
        facture.setStatus(true);
        facture.setBillTime(LocalDateTime.now());

        when(factureRepository.existsByCommandeId(anyInt())).thenReturn(false);
        when(factureRepository.save(any(Facture.class))).thenReturn(facture);

        Facture savedFacture = factureService.saveFacture(facture);

        assertEquals(savedFacture, facture);
        verify(factureRepository, times(1)).save(facture);
    }

    @Test
    void testFindById() {
        Facture facture = new Facture();

        when(factureRepository.findById(any(Integer.class))).thenReturn(Optional.of(facture));

        Facture result = factureService.findById(1);
        assertEquals(facture, result);
    }

    @Test
    void findAllSuccess() {
        when(factureRepository.findAll()).thenReturn(factureList);
        List<Facture> result = factureService.findAll();
        assertEquals(2, result.size());
        assertEquals(facture1, result.get(0));
        assertEquals(facture2, result.get(1));
        verify(factureRepository, times(1)).findAll();
    }

    @Test
    void findAllNoFactureFound() {
        when(factureRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(FactureNotFoundException.class, () -> factureService.findAll());
        verify(factureRepository, times(1)).findAll();
    }

    @Test
    void findByDate() {
        LocalDateTime testDate = LocalDateTime.of(2023, 6, 14, 12, 0);  // 2023-06-14 12:00:00
        Facture facture1 = new Facture(); // assuming an existing default constructor
        Facture facture2 = new Facture(); // assuming an existing default constructor

        when(factureRepository.findByBillTime(testDate)).thenReturn(Arrays.asList(facture1, facture2));

        List<Facture> result = factureService.findByDate(testDate);

        verify(factureRepository, times(1)).findByBillTime(testDate);
        assertEquals(2, result.size());
        assertEquals(result, Arrays.asList(facture1, facture2));
    }

    @Test
    void testUpdateFacture() {
        Facture existingFacture = new Facture();
        existingFacture.setCommande(new Commande());

        Facture newFacture = new Facture();
        Commande newCommande = new Commande();
        newCommande.setId(5);
        newFacture.setCommande(newCommande);

        when(factureRepository.findById(any(Integer.class))).thenReturn(Optional.of(existingFacture));
        when(factureRepository.existsByCommandeId(any(Integer.class))).thenReturn(false);
        when(factureRepository.save(any(Facture.class))).thenAnswer(i -> i.getArguments()[0]);

        Facture result = factureService.updateFacture(1, newFacture);

        assertEquals(newFacture.getCommande().getId(), result.getCommande().getId());
    }


    @Test
    void testDeleteFacture() {
        Facture facture = new Facture();
        when(factureRepository.findById(any(Integer.class))).thenReturn(Optional.of(facture));

        assertDoesNotThrow(() -> factureService.deleteFacture(facture));
    }

    @Test
    void testDeleteFactureById() {
        when(factureRepository.findById(any(Integer.class))).thenReturn(Optional.of(new Facture()));

        assertDoesNotThrow(() -> factureService.deleteFactureById(1));
    }
}

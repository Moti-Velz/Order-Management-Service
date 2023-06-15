package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.repository.ICommandeItemRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommandeItemServiceImplTest {

    @Mock
    ICommandeItemRepo commandeItemRepository;

    @InjectMocks
    CommandeItemServiceImpl commandeItemService;

    private CommandeItem commandeItem1;
    private CommandeItem commandeItem2;
    private List<CommandeItem> commandeItemList;
    private Commande commande;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commandeItem1 = new CommandeItem();
        commandeItem1.setId(1);
        commandeItem2 = new CommandeItem();
        commandeItem2.setId(2);
        commandeItemList = Arrays.asList(commandeItem1, commandeItem2);
        commande = new Commande();
        commande.setId(1);
    }

    @Test
    void findAll() {
        when(commandeItemRepository.findAll()).thenReturn(commandeItemList);
        List<CommandeItem> result = commandeItemService.findAll();
        assertEquals(2, result.size());
        assertEquals(commandeItem1, result.get(0));
        assertEquals(commandeItem2, result.get(1));
        verify(commandeItemRepository, times(1)).findAll();
    }

    @Test
    void findByIdSuccess() {
        when(commandeItemRepository.findById(1)).thenReturn(Optional.of(commandeItem1));
        Optional<CommandeItem> result = commandeItemService.findById(1);
        assertTrue(result.isPresent());
        assertEquals(commandeItem1, result.get());
        verify(commandeItemRepository, times(1)).findById(anyInt());
    }

    @Test
    void findByIdNotFound() {
        when(commandeItemRepository.findById(anyInt())).thenReturn(Optional.empty());
        Optional<CommandeItem> result = commandeItemService.findById(100);
        assertFalse(result.isPresent());
        verify(commandeItemRepository, times(1)).findById(anyInt());
    }

    @Test
    void save() {
        when(commandeItemRepository.save(any(CommandeItem.class))).thenReturn(commandeItem1);
        CommandeItem result = commandeItemService.save(new CommandeItem());
        assertNotNull(result);
        assertEquals(commandeItem1, result);
        verify(commandeItemRepository, times(1)).save(any(CommandeItem.class));
    }

    @Test
    void deleteById() {
        doNothing().when(commandeItemRepository).deleteById(1);
        commandeItemService.deleteById(1);
        verify(commandeItemRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteByIdWithInvalidId() {
        doThrow(new IllegalArgumentException()).when(commandeItemRepository).deleteById(anyInt());
        assertThrows(IllegalArgumentException.class, () -> commandeItemService.deleteById(100));
        verify(commandeItemRepository, times(1)).deleteById(anyInt());
    }
    @Test
    void findByCommande() {
        when(commandeItemRepository.findByCommande(commande)).thenReturn(commandeItemList);
        List<CommandeItem> result = commandeItemService.findByCommande(commande);
        assertEquals(2, result.size());
        assertEquals(commandeItem1, result.get(0));
        assertEquals(commandeItem2, result.get(1));
        verify(commandeItemRepository, times(1)).findByCommande(any(Commande.class));
    }
}
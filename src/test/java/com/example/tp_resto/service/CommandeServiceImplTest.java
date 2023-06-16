package com.example.tp_resto.service;

import com.example.tp_resto.entity.Commande;
import com.example.tp_resto.entity.CommandeItem;
import com.example.tp_resto.entity.MenuItem;
import com.example.tp_resto.repository.ICommandeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.LENIENT) //Pour eviter certains warning concernant les stubs
public class CommandeServiceImplTest {


    @Mock
    private CommandeItemServiceImpl commandeItemService;
    @Mock
    private ICommandeRepo commandeRepo;

    @InjectMocks
    private CommandeServiceImpl commandeService;

    private CommandeItem commandeItem1;
    private CommandeItem commandeItem2;
    private List<CommandeItem> commandeItemList;
    private Commande commande;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void testGetById_found() {
        Commande commande = new Commande();
        commande.setId(1);

        when(commandeRepo.findById(1)).thenReturn(Optional.of(commande));

        Optional<Commande> result = commandeService.getById(1);

        assertEquals(commande, result.get());
    }

    @Test
    void testGetById_notFound() {
        when(commandeRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> commandeService.getById(1));
    }

    @Test
    void testGetAll() {
        Commande commande1 = new Commande();
        commande1.setId(1);
        Commande commande2 = new Commande();
        commande2.setId(2);

        when(this.commandeRepo.findAll()).thenReturn(Arrays.asList(commande1, commande2));

        List<Commande> result = commandeService.getAll();
        System.out.println(result.size());
        assertEquals(2, result.size());
        assertTrue(result.containsAll(Arrays.asList(commande1, commande2)));
    }

    @Test
    void testSaveCommande() {
        Commande commande = new Commande();
        commande.setId(1);

        when(commandeRepo.save(commande)).thenReturn(commande);

        Commande result = commandeService.saveCommande(commande);

        assertEquals(commande, result);
    }

    // Test for updateCommandeById
    @Test
    void testUpdateCommandeById_ExistingId() {
        Commande existingCommande = new Commande();
        Commande newCommande = new Commande();
        newCommande.setOrderTime( LocalDateTime.now());

        when(commandeRepo.findById(any(Integer.class))).thenReturn(Optional.of(existingCommande));
        when(commandeRepo.save(any(Commande.class))).thenReturn(newCommande);

        Commande updatedCommande = commandeService.updateCommandeById(1, newCommande);

        assertEquals(newCommande.getOrderTime(), updatedCommande.getOrderTime());
    }

    @Test
    void testUpdateCommandeById_NonExistingId() {
        Commande newCommande = new Commande();
        newCommande.setOrderTime(LocalDateTime.now());
        when(commandeRepo.findById(any(Integer.class))).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            commandeService.updateCommandeById(1, newCommande);
        });

        String expectedMessage = "Commande id 1 introuvable";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Test for updateCommandeItemsByCommandeId
    @Test
    void testUpdateCommandeItemsByCommandeId_ExistingId() {
        Commande existingCommande = new Commande();
        existingCommande.setId(1);
        List<CommandeItem> newOrderItems = new ArrayList<>();
        // Fill your newOrderItems list here
        when(commandeRepo.findById(1)).thenReturn(Optional.of(existingCommande));
        when(commandeRepo.save(any(Commande.class))).thenReturn(existingCommande);

        Commande updatedCommande = commandeService.updateCommandeItemsByCommandeId(1, newOrderItems);

        assertEquals(newOrderItems, updatedCommande.getOrderItems());
    }

    @Test
    void testUpdateCommandeItemsByCommandeId_NonExistingId() {
        List<CommandeItem> newOrderItems = new ArrayList<>();
        // Fill your newOrderItems list here
        when(commandeRepo.findById(any(Integer.class))).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            commandeService.updateCommandeItemsByCommandeId(1, newOrderItems);
        });

        String expectedMessage = "Commande id 1 introuvable";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void testDeleteCommandeById_found() {
        Commande commande = new Commande();
        commande.setId(1);

        when(this.commandeRepo.findById(1)).thenReturn(Optional.of(commande));

        assertTrue(this.commandeService.deleteCommandeById(1));
        verify(this.commandeRepo, times(1)).deleteById(1);
    }

    @Test
    void testDeleteCommandeById_notFound() {
        when(commandeRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> commandeService.deleteCommandeById(1));
    }

    @Test
    public void whenCheckCommandeItemReturnZero_thenVerifyResult() {
        Commande commande = new Commande();
        CommandeItem commandeItem = new CommandeItem();
        MenuItem menuItem = new MenuItem();
        menuItem.setId(1);
        commandeItem.setMenuItem(menuItem);
        commande.setOrderItems(Collections.emptyList());

        int result = commandeService.checkCommandeItem(commandeItem, commande);
        assertEquals(0, result);
    }

    @Test
    public void whenCheckCommandeItemReturnItemId_thenVerifyResult() {
        Commande commande = new Commande();
        CommandeItem commandeItem1 = new CommandeItem();
        MenuItem menuItem1 = new MenuItem();
        menuItem1.setId(1);
        commandeItem1.setMenuItem(menuItem1);
        commandeItem1.setId(1);

        CommandeItem commandeItem2 = new CommandeItem();
        MenuItem menuItem2 = new MenuItem();
        menuItem2.setId(1);
        commandeItem2.setMenuItem(menuItem2);

        commande.setOrderItems(Arrays.asList(commandeItem1));

        int result = commandeService.checkCommandeItem(commandeItem2, commande);
        assertEquals(1, result);
    }

    @Test
    public void whenAddItemToCommandeWithExistingItem_thenVerifyResult() {
        Commande commande = new Commande();
        CommandeItem commandeItem = new CommandeItem();
        MenuItem menuItem = new MenuItem();
        menuItem.setId(1);
        commandeItem.setMenuItem(menuItem);
        commandeItem.setId(1);
        commandeItem.setQuantity(1);
        commande.addItem(commandeItem);

        CommandeItem existingCommandeItem = new CommandeItem();
        existingCommandeItem.setId(1);
        existingCommandeItem.setQuantity(2);

        when(commandeItemService.findById(anyInt())).thenReturn(Optional.of(existingCommandeItem));

        commandeService.addItemToCommande(commande, commandeItem);

        assertEquals(3, existingCommandeItem.getQuantity());
        verify(commandeItemService, times(1)).save(existingCommandeItem);
    }

    @Test
    public void whenAddItemToCommandeWithNewItem_thenVerifyResult() {
        Commande commande = new Commande();
        CommandeItem commandeItem = new CommandeItem();
        MenuItem menuItem = new MenuItem();
        menuItem.setId(1);
        commandeItem.setMenuItem(menuItem);
        commandeItem.setId(0);

        when(commandeItemService.findById(anyInt())).thenReturn(Optional.empty());

        commandeService.addItemToCommande(commande, commandeItem);

        verify(commandeItemService, times(1)).save(commandeItem);
    }
}

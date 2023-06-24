package com.example.tp_resto.service;

import com.example.tp_resto.entity.MenuItem;
import com.example.tp_resto.exception.MenuItemNotFoundException;
import com.example.tp_resto.repository.IMenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class MenuItemServiceImplTest {

    @Mock
    IMenuItem menuRepository;

    @InjectMocks
    MenuItemServiceImpl menuItemService;

    private MenuItem menuItem;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        menuItem = new MenuItem();
        menuItem.setName("testMenuItem");
    }

    @Test
    void testGetById() {
        MenuItem item = new MenuItem();
        when(menuRepository.findById(any(Integer.class))).thenReturn(Optional.of(item));

        MenuItem result = menuItemService.getById(1);
        assertEquals(item, result);
    }

    @Test
    void testGetByIdNotFound() {
        when(menuRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        assertThrows(MenuItemNotFoundException.class, () -> menuItemService.getById(1));
    }

    @Test
    void testGetByName() {
        MenuItem item = new MenuItem();
        when(menuRepository.findByNameIgnoreCase(any(String.class))).thenReturn(item);

        MenuItem result = menuItemService.getByName("name");
        assertEquals(item, result);
    }

    @Test
    void testFindAll() {
        // Prepare the mock behavior
        List<MenuItem> expectedMenuItems = Arrays.asList(new MenuItem(), new MenuItem(), new MenuItem());
        when(menuRepository.findAll()).thenReturn(expectedMenuItems);

        // Execute the service method
        List<MenuItem> actualMenuItems = menuItemService.findAll();

        // Verify the results
        assertEquals(expectedMenuItems.size(), actualMenuItems.size(), "The returned list size does not match the expected");
        assertEquals(expectedMenuItems, actualMenuItems, "The returned list does not match the expected");
    }

    @Test
    void testSave() {
        MenuItem item = new MenuItem();
        when(menuRepository.findMenuItemByName(any(String.class))).thenReturn(Optional.empty());
        when(menuRepository.save(any(MenuItem.class))).thenReturn(item);

        MenuItem result = menuItemService.save(new MenuItem());
        assertEquals(item, result);
    }

    @Test
    void testSaveExistingName() {
        MenuItem menuItem2 = new MenuItem();
        menuItem2.setName("testMenuItem");
        when(menuRepository.findMenuItemByName(any(String.class))).thenReturn(Optional.of(menuItem2));

        assertThrows(RuntimeException.class, () -> menuItemService.save(menuItem));
    }

    @Test
    void testUpdateMenuItemById() {
        MenuItem item = new MenuItem();
        when(menuRepository.findById(any(Integer.class))).thenReturn(Optional.of(item));
        when(menuRepository.save(any(MenuItem.class))).thenReturn(item);

        MenuItem result = menuItemService.updateMenuItemById(1, new MenuItem());
        assertEquals(item, result);
    }

    @Test
    void testUpdateMenuItemByIdNotFound() {
        when(menuRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        assertThrows(MenuItemNotFoundException.class, () -> menuItemService.updateMenuItemById(1, new MenuItem()));
    }

    @Test
    void testDeleteMenuItemById() {
        when(menuRepository.existsById(any(Integer.class))).thenReturn(true);
        assertThrows(MenuItemNotFoundException.class, () -> menuItemService.deleteMenuItemById(1));
    }

}

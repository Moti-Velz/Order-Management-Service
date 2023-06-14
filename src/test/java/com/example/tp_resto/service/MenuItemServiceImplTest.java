package com.example.tp_resto.service;

import com.example.tp_resto.entity.MenuItem;
import com.example.tp_resto.exception.MenuItemNotFoundException;
import com.example.tp_resto.repository.IMenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class MenuItemServiceImplTest {

    @Mock
    IMenuItem menuRepository;

    @InjectMocks
    MenuItemServiceImpl menuItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        MenuItem item = new MenuItem();
        when(menuRepository.findById(any(Integer.class))).thenReturn(Optional.of(item));

        MenuItem result = menuItemService.getById(1);
        assertEquals(item, result);
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
        // Implementation will depend on your specific requirements
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
    void testUpdateMenuItemById() {
        MenuItem item = new MenuItem();
        when(menuRepository.findById(any(Integer.class))).thenReturn(Optional.of(item));
        when(menuRepository.save(any(MenuItem.class))).thenReturn(item);

        MenuItem result = menuItemService.updateMenuItemById(1, new MenuItem());
        assertEquals(item, result);
    }

    @Test
    void testDeleteMenuItemById() {
        when(menuRepository.existsById(any(Integer.class))).thenReturn(true);

        assertTrue(menuItemService.deleteMenuItemById(1));
    }

    @Test
    void testGetAll() {
        // Implementation will depend on your specific requirements
    }
}

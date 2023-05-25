package com.pragma.powerup.plazamicroservice.domain.usecase;

import com.pragma.powerup.plazamicroservice.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        Long id = 1L;
        String name = "Test Category";
        String description = "This is a test category";
        category = new Category(id, name, description);
    }

    @Test
    void testGetters() {
        Long expectedId = 1L;
        String expectedName = "Test Category";
        String expectedDescription = "This is a test category";

        assertEquals(expectedId, category.getId());
        assertEquals(expectedName, category.getName());
        assertEquals(expectedDescription, category.getDescription());
    }

    @Test
    void testSetters() {
        Long newId = 2L;
        String newName = "New Category";
        String newDescription = "This is a new category";

        category.setId(newId);
        category.setName(newName);
        category.setDescription(newDescription);

        assertEquals(newId, category.getId());
        assertEquals(newName, category.getName());
        assertEquals(newDescription, category.getDescription());
    }
}

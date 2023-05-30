package com.pragma.powerup.plazamicroservice.domain.usecase;

import com.pragma.powerup.plazamicroservice.domain.model.Category;
import com.pragma.powerup.plazamicroservice.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.usecase.CategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class CategoryTest {

    private Category category;
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        Long id = 1L;
        String name = "Test Category";
        String description = "This is a test category";
        category = new Category(id, name, description);
        MockitoAnnotations.openMocks(this);
        categoryUseCase = new CategoryUseCase(categoryPersistencePort);
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
    @Test
    void testCreateCategory() {
        Category category = new Category(1L, "Test Category", "This is a test category");
        categoryUseCase.createCategory(category);
        verify(categoryPersistencePort).createCategory(category);
    }

}

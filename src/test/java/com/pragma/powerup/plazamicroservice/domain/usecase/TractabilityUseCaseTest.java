package com.pragma.powerup.plazamicroservice.domain.usecase;

import com.pragma.powerup.plazamicroservice.domain.model.Tractability;
import com.pragma.powerup.plazamicroservice.domain.spi.ITractabilityPersistencePort;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TractabilityUseCaseTest {

    @Mock
    private ITractabilityPersistencePort tractabilityPersistencePort;
    private TractabilityUseCase tractabilityUseCase;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tractabilityUseCase = new TractabilityUseCase(tractabilityPersistencePort);
    }

    @Test
    void createTractability() {
        Tractability tractability = new Tractability(
                ObjectId.get(),
                1L,
                1L,
                "cliente@email.com",
                LocalDateTime.now(),
                "En espera",
                "Listo",
                2L,
                "employee@email.com"
        );
        tractabilityUseCase.createTractability(tractability);
        verify(tractabilityPersistencePort).createTractability(tractability);
    }

    @Test
    void findAllByClientId() {
        List<Tractability> expectedTractabilities = Arrays.asList(
                new Tractability(
                        ObjectId.get(),
                        1L,
                        1L,
                        "cliente@email.com",
                        LocalDateTime.now(),
                        "En espera",
                        "Listo",
                        2L,
                        "employee@email.com"
                ),
                new Tractability(
                        ObjectId.get(),
                        2L,
                        2L,
                        "cliente2@email.com",
                        LocalDateTime.now(),
                        "En espera",
                        "Cancelado",
                        3L,
                        "employee2@email.com"
                )
        );
        when(tractabilityPersistencePort.findAllByClientId(1L)).thenReturn(expectedTractabilities);
        when(tractabilityPersistencePort.findAllByClientId(2L)).thenReturn(expectedTractabilities);
        List<Tractability> actualTractabilities = tractabilityUseCase.findAllByClientId(1L);
        assertEquals(expectedTractabilities, actualTractabilities);
        actualTractabilities = tractabilityUseCase.findAllByClientId(2L);
        assertEquals(expectedTractabilities, actualTractabilities);
    }

    @Test
    void getAllTractabilities() {
        List<Tractability> expectedTractabilities = Arrays.asList(
                new Tractability(
                        ObjectId.get(),
                        1L,
                        1L,
                        "cliente@email.com",
                        LocalDateTime.now(),
                        "En espera",
                        "Listo",
                        2L,
                        "employee@email.com"
                ),
                new Tractability(
                        ObjectId.get(),
                        2L,
                        2L,
                        "cliente2@email.com",
                        LocalDateTime.now(),
                        "En espera",
                        "Cancelado",
                        3L,
                        "employee2@email.com"
                )
        );
        when(tractabilityPersistencePort.getAllTractabilities(0,1)).thenReturn(expectedTractabilities);
        when(tractabilityPersistencePort.getAllTractabilities(0,2)).thenReturn(expectedTractabilities);
        List<Tractability> actualTractabilities = tractabilityUseCase.getAllTractabilities(0,2);
        assertEquals(expectedTractabilities, actualTractabilities);
    }
}
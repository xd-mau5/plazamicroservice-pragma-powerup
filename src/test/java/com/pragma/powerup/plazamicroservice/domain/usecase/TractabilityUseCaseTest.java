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
import java.util.Map;

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
    @Test
    void calculateDurationPerOrder() {
        String expectedDuration = "1 hora";
        when(tractabilityPersistencePort.calculateDurationPerOrder(1L)).thenReturn(expectedDuration);
        when(tractabilityPersistencePort.calculateDurationPerOrder(2L)).thenReturn(expectedDuration);
        String actualDuration = tractabilityUseCase.calculateDurationPerOrder(1L);
        assertEquals(expectedDuration, actualDuration);
        actualDuration = tractabilityUseCase.calculateDurationPerOrder(2L);
        assertEquals(expectedDuration, actualDuration);
    }
    @Test
    void calculateAverageDurationPerEmployee() {
        List<Tractability> tractabilityList = Arrays.asList(
                new Tractability(
                        ObjectId.get(),
                        1L,
                        1L,
                        "cliente@email.com",
                        LocalDateTime.of(2023, 6, 2, 9, 17, 0),
                        "En espera",
                        "Listo",
                        2L,
                        "employee@email.com"
                ),
                new Tractability(
                        ObjectId.get(),
                        1L,
                        1L,
                        "cliente@email.com"
                        ,LocalDateTime.of(2023, 6, 2, 10, 17, 0),
                        "Listo",
                        "Entregado",
                        2L,
                        "employee@email.com"
                )
        );
        Map<Long, String> expectedDuration = Map.of(2L, "1 hora y 0 minutos");
        when(tractabilityPersistencePort.getAllTractabilities(0,2)).thenReturn(tractabilityList);
        when(tractabilityPersistencePort.calculateAverageDurationPerEmployee()).thenReturn(expectedDuration);
        Map<Long, String> actualDuration = tractabilityUseCase.calculateAverageDurationPerEmployee();
        assertEquals(expectedDuration, actualDuration);
    }
}
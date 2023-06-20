package com.pragma.powerup.plazamicroservice.adapters.driven.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "tractability")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TractabilityEntity {
    @Id
    private ObjectId id;
    private Long orderId;
    private Long clientId;
    private String clientEmail;
    private LocalDateTime date;
    private String previousState;
    private String newState;
    private Long employeeId;
    private String employeeEmail;
}

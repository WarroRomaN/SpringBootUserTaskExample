package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "activities")
public class Activity implements Serializable {

    @Id
    @Column(name = "id")
    private Long key;
    private String activity;
    private Float accessibility;
    private ActivityType type;
    private Integer participants;
    private Float price;
    private String link;
}

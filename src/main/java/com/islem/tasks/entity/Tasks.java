package com.islem.tasks.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tasks implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String description;
    private boolean done;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private boolean favorite;
    @ManyToOne
    @JoinColumn(name="projectid")
    @JsonIgnore
    private Project project;


}

package com.islem.tasks.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.islem.tasks.dto.TasksDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projects")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description", length = 255)
    private String description;
    @ManyToOne
    @JoinColumn(name="userid")
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy="project", fetch = FetchType.EAGER)
    private List<Tasks> tasksList;


}



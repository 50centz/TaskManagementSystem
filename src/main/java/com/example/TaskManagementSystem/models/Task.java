package com.example.TaskManagementSystem.models;

import com.example.TaskManagementSystem.models.enums.Priority;
import com.example.TaskManagementSystem.models.enums.TaskStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
@NamedEntityGraph(name = "task-entity-graph", attributeNodes = {
        @NamedAttributeNode("author"),
        @NamedAttributeNode("executor")})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User author;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User executor;


}

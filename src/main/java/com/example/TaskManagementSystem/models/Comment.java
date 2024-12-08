package com.example.TaskManagementSystem.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(targetEntity = Task.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Task task;
}






//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "comments")
//@EqualsAndHashCode(of = {"id"})
//@NamedEntityGraph(name = "comment-entity-graph", attributeNodes = {
//        @NamedAttributeNode("book")})
//public class Comment {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name = "comment", nullable = false)
//    private String comment;
//
//
//    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//    @JoinColumn(name = "book_id")
//    private Book book;
//
//}

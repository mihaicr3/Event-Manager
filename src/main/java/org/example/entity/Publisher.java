package org.example.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="publishers")
public class Publisher {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false,unique = true)
    private String email;
    @Column(nullable = false)
    private String name;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String description;
    @Column
    private int role=2;
}

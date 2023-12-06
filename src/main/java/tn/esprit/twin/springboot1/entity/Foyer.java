package tn.esprit.twin.springboot1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Foyer")
public class Foyer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFoyer")
    private Long idFoyer; // Clé primaire
    private String nomFoyer;
    private Long capaciteFoyer;
    @JsonIgnore

    @OneToOne(mappedBy = "foyer")
    private Universite universite;
    @JsonIgnore

    @OneToMany(mappedBy = "foyer" , cascade = CascadeType.ALL)
    private List<Bloc> bloc;
}
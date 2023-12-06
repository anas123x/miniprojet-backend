package tn.esprit.twin.springboot1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Bloc")
public class Bloc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idBloc")
    private Long idBloc; // Clé primaire
    private String nomBloc;
    private Long capaciteBloc;
    @ManyToOne
    Foyer foyer;
    @JsonIgnore

    @OneToMany(mappedBy="bloc" , cascade = CascadeType.ALL , fetch=FetchType.EAGER)
    private List<Chambre> chambre;




}

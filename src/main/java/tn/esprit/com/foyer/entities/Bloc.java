package tn.esprit.com.foyer.entities;

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
@Table( name = "Bloc")
public class Bloc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBloc")
    private Long idBloc; // Clé primaire
    private String nomBloc;
    private Long capaciteBloc;
    @ManyToOne
    Foyer foyer;
    @JsonIgnore

    @OneToMany(mappedBy="bloc" , cascade = CascadeType.ALL , fetch=FetchType.EAGER)
    private List<Chambre> chambre;



}

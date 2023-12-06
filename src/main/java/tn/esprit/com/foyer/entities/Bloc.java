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

@Entity
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@Table( name = "Bloc")
public class Bloc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBloc")
    private Long idBloc; // Clé primaire
    private String nomBloc;
    private Long capaciteBloc;

    private Etat etat;
    @ManyToOne
    Foyer foyer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="bloc", fetch = FetchType.EAGER)
    private Set<Chambre> chambres;
    @OneToMany(mappedBy = "bm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Maintenance> maintenances;
    public Long getId() {
        return this.idBloc;
    }

    // Setter pour id
    public void setId(Long id) {
        this.idBloc = id;
    }


}

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
@Table( name = "Reservation")
public class Reservation implements Serializable {
    @Id
    @Column(name = "idReservation", length = 50)
    private String idReservation; // Clé primaire
    @JsonIgnore
    @Temporal(TemporalType.DATE)
    private Date anneeReservation;
    private String numReservation;
    private boolean estValide;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Etudiant> etudiants;
    public void setNumReservation(String numReservation) {
        this.numReservation = numReservation;
    }



}

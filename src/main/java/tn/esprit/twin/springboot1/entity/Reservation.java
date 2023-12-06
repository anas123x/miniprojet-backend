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

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Slf4j
@Table(name = "Reservation")
public class Reservation implements Serializable {
    @Id
    @Column(name="idReservation")
    private String idReservation; // Clé primaire
    private boolean estValide;
    private String numReservation;
    @JsonIgnore
    @Temporal(TemporalType.DATE)
    private Date anneeUniversitaire;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Etudiant> etudiants;

    public String getNumReservation() {
        return numReservation;
    }

    public void setNumReservation(String numReservation) {
        this.numReservation = numReservation;
    }
}

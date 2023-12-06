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
@Getter
@Setter
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Chambre")
@Entity
public class Chambre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idChambre")
    private Long idChambre; // Clé primaire
    private Long numeroChambre;
    @Enumerated(EnumType.STRING)
    private TypeChambre typeC;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    List<Reservation> reservations ;
    @JsonIgnore
    @ManyToOne
    Bloc bloc;


}

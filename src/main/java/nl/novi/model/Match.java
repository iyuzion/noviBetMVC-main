package nl.novi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "match")
@Getter
@Setter
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    //@Column(name = "teamOne")
    private Team teamOne;

    @OneToOne(cascade = CascadeType.ALL)
    //@Column(name = "teamTwo")
    private Team teamTwo;

    @OneToOne(cascade = CascadeType.ALL)
    //@Column(name = "winner")
    private Team winner;
}


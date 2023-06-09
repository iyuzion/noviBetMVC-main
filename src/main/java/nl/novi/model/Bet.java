package nl.novi.model;


import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bet")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Match match;

    @Column(name = "winner")
    private String winner;

    public boolean correctVoorspelt() {
        return Objects.equals(winner, match.getWinner());
    };
}

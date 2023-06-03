package nl.novi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "match")
@Getter
@Setter
public class Match implements Comparable<Match> {
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
    public String getWinnerName() {
        return winner != null ? winner.getName() : "geen winner";
    }
    public List<Team> getDeelnemers() {
        List<Team> list = new ArrayList<>();
        list.add(teamOne);
        list.add(teamTwo);
        return list;
    }

    @Override
    public int compareTo(Match o) {
        return Long.compare(this.id, o.getId());
    }
}


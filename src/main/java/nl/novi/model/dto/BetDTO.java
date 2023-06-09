package nl.novi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BetDTO {
    private String user;
    private Long match;
    private String winner;
}

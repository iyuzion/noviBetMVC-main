package nl.novi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchDTO {
    private Long id;
    private Long teamOne;
    private Long teamTwo;
    private Long winner;
}

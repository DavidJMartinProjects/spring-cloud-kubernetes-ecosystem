package ecosystem.gamificationservice.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum HiLo {
    HI("HIGHER"),
    LOW("LOWER");

    @Getter
    private String value;
}

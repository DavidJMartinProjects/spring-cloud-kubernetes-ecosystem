package ecosystem.gamificationservice.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum HiLo {
    HIGHER("higher"),
    LOWER("lower");

    @Getter
    private String value;
}

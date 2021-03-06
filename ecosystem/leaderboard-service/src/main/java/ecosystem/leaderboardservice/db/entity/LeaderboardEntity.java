package ecosystem.leaderboardservice.db.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "leaderboard")
public class LeaderboardEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;
    private String country;
    private int score;
    private int rank;

}

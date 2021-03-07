package ecosystem.leaderboardservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EmbeddedKafka(controlledShutdown = true, topics = "leaderboard")
public class LeaderboardServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}

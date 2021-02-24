package de.mrtimeey.comicscraper;

import de.mrtimeey.comicscraper.controller.HelloController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
class ComicScraperApplicationTests {

    @Autowired
    private HelloController helloController;

    @Test
    void contextLoads() {
        // Verifies correct startup
        assertThat(helloController).isNotNull();
    }

}

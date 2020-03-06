package test;

import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    Player p;

    @BeforeEach
    void setUp() {
        p = new Player();
    }

    @Test
    void testGetString() {
    }

}

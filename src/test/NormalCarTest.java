package test;

import model.NormalCar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NormalCarTest {

    NormalCar c;

    @BeforeEach
    void setUp() {
        c = new NormalCar(10);
    }

    @Test
    void testNegativeVelocity(){
        c = new NormalCar(10);
   }


    @Test
    void testGetCarVelocity() {
        assertEquals(10,c.getCarVelocity());
    }

}

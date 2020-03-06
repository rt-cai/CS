package test;

import exceptions.CollisionException;
import exceptions.WinException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.CarPane;

import static org.junit.jupiter.api.Assertions.fail;

public class ExceptionTest {

    CarPane c;

    @BeforeEach
    void setup(){ c = new CarPane(1,'A');}

    @Test
    void testCollisionDetectorInsideCollision(){
        c.getCars().get(0).setPosition((500-15)/2,2);
        c.getPlayer().setPosition(2);
        try{
            c.collisionDetector();
            fail("23");
        }
        catch (CollisionException e) {
            //
        }
    }
    @Test
    void testCollisionDetectorBoundCollision(){
        c.getCars().get(0).setPosition((500-15)/2+15,2);
        c.getPlayer().setPosition(2);
        try{
            c.collisionDetector();
            fail("35");
        }
        catch (CollisionException e) {
            //
        }
    }
    @Test
    void testCollisionDetectorNoCollision(){
        c.getCars().get(0).setPosition(10,2);
        c.getPlayer().setPosition(1);
        try{
            c.collisionDetector();
        }
        catch (CollisionException e) {
            fail("49");
        }
    }

    @Test
    void testWinException(){
        try{
            c.playerMove();
        }
        catch(WinException ex){
            fail("60");
        }

        try{
            c.playerMove();
            fail("65");
        }
        catch(WinException ex){

        }
    }

}

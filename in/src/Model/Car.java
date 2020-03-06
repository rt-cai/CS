package Model;

import java.awt.*;

public class Car {
    private int carVelocity;
    private Color carColor;

    public Car(int v){
        carVelocity = v;
        Color carColor = new Color(v*25,0,0);
    }

    public void move(){

    }

    public int getCarVelocity(){
        return carVelocity;
    }
    public Color getCarColor() {
        return carColor;
    }
}

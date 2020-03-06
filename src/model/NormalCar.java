package model;

import ui.CarPane;

import java.util.*;

public class NormalCar implements Car,Movable {
    private int carVelocity;
    private int[] carPosition = {0,0};

    private ArrayList<CarPane> cp;

    //MODIFIES: this
    //EFFECTS: constructs the car with velocity
    //          throws NegativeVelocityException if velocity is negative
    public NormalCar(int v) {
        carVelocity = v;
        cp = new ArrayList<>();
    }

    //reflexive
//    public void addCarPane(CarPane c){
//        if(!cp.contains(c)) {
//            cp.add(c);
//            c.addNormalCar(this);
//        }
//    }
//    public void removeCarPane(CarPane c){
//        if(!cp.contains(c)) {
//            cp.remove(c);
//            c.removeNormalCar(this);
//        }
//    }


    //EFFECTS: return the velocity
    @Override
    public int getCarVelocity(){ return carVelocity; }

    @Override
    public int getCarWidth() { return 150/carVelocity; }

    public int[] getPosition(){ return carPosition; }

    public void setPosition(int x, int y){
        carPosition[0]=x;
        carPosition[1]=y;
    }

    public void movePosition(int xChange, int yChange){
        carPosition[0]+=xChange;
        carPosition[1]+=yChange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalCar normalCar = (NormalCar) o;
        return carVelocity == normalCar.carVelocity &&
                Arrays.equals(carPosition, normalCar.carPosition) &&
                Objects.equals(cp, normalCar.cp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carVelocity,carPosition);
    }


}

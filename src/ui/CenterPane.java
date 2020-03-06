package ui;

import exceptions.CollisionException;
import exceptions.WinException;
import helper.Observer;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import model.NormalCar;
import model.Player;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class CenterPane extends GridPane implements Observer{
    final int WIDTH = 500;
    protected final int COLUMN_WIDTH = 40;
    protected final int DURATION = 1000;
    protected final int PLAYER_WIDTH = 15;
    protected final int PLAYER_COLUMN = (WIDTH-PLAYER_WIDTH)/2;

    protected HashMap<NormalCar,Shape> map;
    protected ArrayList<NormalCar> cars;
    protected Player p;
    protected int numberOfCar;

    public CenterPane(int numberOfCar) {
        cars = new ArrayList<>();
        initializePane(numberOfCar);
        this.numberOfCar = numberOfCar;
        initializeCar(numberOfCar);
    }

    private void initializePane(int numberOfCar){
        for (int i = 0; i < numberOfCar+1; i++) {
            getRowConstraints().add(new RowConstraints((WIDTH-100)/(numberOfCar+1)));
        }
        for (int i = 0; i < WIDTH; i++){
            getColumnConstraints().add(new ColumnConstraints(1));
        }
    }

    protected void addNormalCar(NormalCar n, Shape s){
        if(!map.containsKey(n)) {
            s.setStroke(Color.BLACK);
            s.setFill(Color.color(n.getCarVelocity() * 0.1, 0, 0));
            cars.add(n);
            map.put(n,s);
        }
    }

    protected abstract void addNormalCar(NormalCar n);


    protected void initializeCar(int numberOfCar){
        map = new HashMap<>();
        for (int i = 0; i < numberOfCar; i++) {
            NormalCar c = new NormalCar((int) (Math.random() * 10 + 1));
            c.setPosition(0,i+1);
            addNormalCar(c);
            add(map.get(c),0,i+1);
        }
    }

    protected void initializePlayer(int index, Shape s){
        p = new Player();
        p.addObserver(this);
        add(s,PLAYER_COLUMN,index);
    }

    public void update(int position){
        System.out.println("at position "+position);
    }

    public abstract void carMove(int numberOfCar) throws CollisionException;

    public abstract void playerMove() throws WinException;

    public abstract void collisionDetector() throws CollisionException;
}

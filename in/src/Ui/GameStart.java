package Ui;

import Model.Car;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class GameStart extends Application{

    public void start(Stage primaryStage){

        GridPane pane = new GridPane();
        pane.getColumnConstraints().add(new ColumnConstraints(100));
        pane.getRowConstraints().add(new RowConstraints(150));


        ArrayList<Car> cars = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Car c = new Car((int)(Math.random()*10));
            cars.add(c);

            Rectangle r = new Rectangle();
            r.setX(50);
            r.setY(50);
            r.setWidth(2);
            r.setHeight(1);
            r.setArcWidth(20);
            r.setArcHeight(20);

            GridPane.setConstraints(r,i,0);
            pane.getChildren().add(r);
        }

        Button btGo = new Button("Go!");
        GridPane.setConstraints(btGo,50,0);
        pane.getChildren().add(btGo);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args){
        new GameStart();
    }

}

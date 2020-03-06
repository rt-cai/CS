package model;

import helper.Subject;

import java.util.Scanner;

public class Player extends Subject{

    private String name;
    private int position;

    //EFFECTS: let the player name himself
    public void getString() {
        Scanner input = new Scanner(System.in);
        name = input.next();
    }

    //EFFECTS: return name
    public String getName(){return name;}

    public int getPosition(){return position;}

    public void setPosition(int y){position = y;}

    public void movePosition(int y){
        position+=y;
        notifyObservers(position);
    }
}

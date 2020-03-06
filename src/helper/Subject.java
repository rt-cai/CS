package helper;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers;

    public Subject(){observers = new ArrayList<>();}


    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void notifyObservers(int position){
        for(Observer o: observers)
            o.update(position);
    }
}

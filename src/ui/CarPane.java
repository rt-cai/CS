package ui;

import exceptions.CollisionException;
import exceptions.WinException;
import helper.API;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.NormalCar;
import javafx.scene.shape.Rectangle;
import model.Player;
import java.util.ArrayList;

public class CarPane extends CenterPane {

    private Circle c;
    private Label name;

    public CarPane(int numberOfCar,char c) {
        super(numberOfCar);
        name = new Label(Character.toString(c));
        initializePlayer(numberOfCar + 2);
        p.setPosition(numberOfCar + 2);
    }

    public void addNormalCar(NormalCar n){
        Rectangle r = new Rectangle(n.getCarWidth(),COLUMN_WIDTH-15);
        addNormalCar(n,r);
    }

    @Override
    public void carMove(int numberOfCar){
        for(int i = 0; i < numberOfCar; i++) {
            PathTransition pp = new PathTransition();
            pp.setDuration(Duration.millis(cars.get(i).getCarVelocity()*DURATION));
            pp.setNode(map.get(cars.get(i)));
            pp.setPath(new Line(-cars.get(i).getCarWidth()/2,i+1,
                    WIDTH+cars.get(i).getCarWidth()/2,i+1));
            pp.setCycleCount(Timeline.INDEFINITE);
            pp.play();

            int velocity = (WIDTH+2*cars.get(i).getCarWidth())/cars.get(i).getCarVelocity()*DURATION;
            cars.get(i).movePosition(velocity,0);

        }
    }

    @Override
    public void playerMove() throws WinException{
        PathTransition pm = new PathTransition();
        pm.setDuration(Duration.millis(1));
        pm.setNode(c);
        pm.setPath(new Line(c.getCenterX(),c.getCenterY()-(11-p.getPosition())*COLUMN_WIDTH, c.getCenterX(),c.getCenterY()-(12-p.getPosition())*COLUMN_WIDTH));
        pm.setCycleCount(1);
        pm.play();
        p.movePosition(-1);

        PathTransition pm1 = new PathTransition();
        pm1.setDuration(Duration.millis(1));
        pm1.setNode(name);
        pm1.setPath(new Line(c.getCenterX(),c.getCenterY()-(11-p.getPosition())*COLUMN_WIDTH, c.getCenterX(),c.getCenterY()-(12-p.getPosition())*COLUMN_WIDTH));
        pm1.setCycleCount(1);
        pm1.play();

        if(p.getPosition()==1)
            throw new WinException();

//        Line l = new Line(c.getCenterX(),c.getCenterY()-(11-p.getPosition())*COLUMN_WIDTH, c.getCenterX(),c.getCenterY()-(12-p.getPosition())*COLUMN_WIDTH);

    }

    protected void initializePlayer(int index){
        c = new Circle(PLAYER_WIDTH, Color.GREEN);
        initializePlayer(index,c);

        add(name,PLAYER_COLUMN+PLAYER_WIDTH/2,index);


//        API api = new API();
//        String imageSource = api.decodeJ(api.getResponse("http://shibe.online/api/shibes?count=[1-100]&urls=[true/false]&httpsUrls=[true/false]"));
//        Image image = new Image(imageSource);
//        Pane pane = new Pane();
//        pane.getChildren().add(new ImageView(image));
//        add(pane,PLAYER_COLUMN,index);
    }

    @Override
    public void collisionDetector() throws CollisionException {

        if(p.getPosition()<numberOfCar+2 && p.getPosition()>1)
            if (c.intersects(c.sceneToLocal(map.get(cars.get(p.getPosition()-2)).
                    localToScene(map.get(cars.get(p.getPosition()-2)).getBoundsInLocal())))) {
                        throw new CollisionException();
                    }

    }

    //methods that only for test
    public ArrayList<NormalCar> getCars(){
        return cars;
    }
    public Player getPlayer(){
        return p;
    }
}

package mechanics;

import exceptions.CollisionException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ui.CenterPane;

public class CollisionHandler implements Runnable
{

    private Thread th;
    private boolean isEnd = false;

    private CenterPane cp;
    private Stage newWindow;
    private Stage primaryStage;

    public CollisionHandler(CenterPane cp, Stage ps){
        this.cp = cp;
        primaryStage = ps;
    }

    public void start(){
        synchronized (this) {
            isEnd = false;
        }
        th = new Thread(this);
        th.start();
    }

    @Override
    public void run() {
        boolean myEnd = isEnd;
        while(!myEnd){
            try {
                cp.collisionDetector();
            }
            catch (CollisionException ex){
                synchronized (this) {
                    isEnd = true;
                }
                System.out.println("hit!");

                Platform.runLater(new Runnable() {
                    @Override public void run() {
                    newWindow = new Stage();
                    newWindow.setTitle("Try Again!");
                    newWindow.initModality(Modality.APPLICATION_MODAL);
                    newWindow.setMinWidth(200);
                    newWindow.setMinHeight(200);

                    Button btL = new Button("Restart");
                    btLHandler handler = new btLHandler();
                    btL.setOnAction(handler);
                    Label l = new Label("Loser!");
                    VBox layout = new VBox(10);
                    layout.getChildren().addAll(l, btL);
                    layout.setAlignment(Pos.CENTER);

                    Scene sceneL = new Scene(layout);
                    newWindow.setScene(sceneL);
                    newWindow.showAndWait();
                    }
                });

            }

            synchronized (this){
                myEnd = isEnd;
            }
        }
    }

    public void end(){
        synchronized (this) {
            isEnd = true;
        }
        try {
            th.join();
        } catch (InterruptedException e) {
            //
        }
    }

    class btLHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            newWindow.close();
            primaryStage.close();
            Platform.runLater( () -> new ui.Main().start( new Stage() ) );
        }
    }
}

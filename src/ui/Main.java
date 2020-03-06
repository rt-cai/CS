package ui;

import exceptions.WinException;
import helper.WriteReadFile;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.util.Duration;
import mechanics.CollisionHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Main extends Application{

    private final int NUMBER_OF_CARS = 9;
    final int WIDTH = 500;

    private char name;
    private long time;

    private BorderPane pane;
    private CarPane carPane;
    private Stage newWindow;
    private Button btGo;
    private Label recordLabel;
    private Label timeLabel;

    private CollisionHandler ch;
    private Timeline timeline;

    public static void main(String[] args) {
        new Main();
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Stage window = new Stage();
        window.setTitle("Begin!");
        window.setMinWidth(200);
        window.setMinHeight(200);
        Label before = new Label("Name it");
        TextField nameField = new TextField();
        Button btOK = new Button("Enter");
        btOK.setOnAction(__ -> {
            name = nameField.getCharacters().charAt(0);
            window.close();
        });
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.getChildren().addAll(before,nameField,btOK);
        Scene sceneB = new Scene(vbox);
        window.setScene(sceneB);

        window.setOnHidden((windowEvent) -> {
            pane = new BorderPane();
            carPane = new CarPane(NUMBER_OF_CARS, name);

            //center
            pane.setCenter(carPane);

            //bottom
            btGo = new Button("Go!");
            GoHandler handler1 = new GoHandler();
            btGo.setOnAction(handler1);

            Button btNew = new Button("New Game");
            btNew.setOnAction(__ ->
            {
                System.out.println("Restarting app!");
                primaryStage.close();
                Platform.runLater(() -> new Main().start(new Stage()));
            });

            HBox hbox = new HBox(20);
            hbox.setAlignment(Pos.BOTTOM_CENTER);
            hbox.getChildren().addAll(btGo, btNew);
            pane.setBottom(hbox);

            //top
            //WriteReadFile.initialize("data.dat");
            recordLabel = new Label("Best Time: " + Long.toString(WriteReadFile.readRecord("data.dat") / 1000) + ":" + Long.toString(WriteReadFile.readRecord("data.dat") % 1000));
            timeLabel = timeLabel();
            HBox hboxt = new HBox(20);
            hboxt.setAlignment(Pos.TOP_CENTER);
            hboxt.getChildren().addAll(recordLabel, timeLabel);
            pane.setTop(hboxt);

            //all
            Scene scene = new Scene(pane, WIDTH, WIDTH);
            primaryStage.setTitle("Game");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            //start
            carPane.carMove(NUMBER_OF_CARS);

            if (ch != null) {
                ch.end();
            }
            ch = new CollisionHandler(carPane, primaryStage);
            ch.start();

            primaryStage.show();
        });

        window.show();
    }

    //handlers
    class GoHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            try {
                carPane.playerMove();
            } catch (WinException ex) {
                ch.end();
                timeline.stop();

                newWindow = new Stage();
                newWindow.setTitle("Win!");
                newWindow.initModality(Modality.APPLICATION_MODAL);
                newWindow.setMinWidth(200);
                newWindow.setMinHeight(200);

                Button btClose = new Button("Close");
                EndHandler handler5 = new EndHandler();
                btClose.setOnAction(handler5);
                Label l = new Label("Winner!");
                Label t = new Label(Long.toString(time/1000)+":"+Long.toString(time%1000));
                VBox layout = new VBox(10);
                layout.getChildren().addAll(l, btClose,t);
                layout.setAlignment(Pos.CENTER);

                Scene scene = new Scene(layout);
                newWindow.setScene(scene);
                newWindow.showAndWait();

                if(time < WriteReadFile.readRecord("data.dat")) {
                    WriteReadFile.writeRecord(time, "data.dat");
                    recordLabel.setText("New Record: "+time/1000+":"+time%1000);
                }
            } finally {
                carPane.requestFocus();
            }
        }
    }

    class EndHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            newWindow.close();
            btGo.setDisable(true);
        }
    }

    @Override
    public void stop(){
        ch.end();
        System.exit(0);
    }

    private Label timeLabel(){
        Label label = new Label();
        DateFormat timeFormat = new SimpleDateFormat( "mm:ss:SS" );
        long startTime = System.currentTimeMillis();
        timeline = new Timeline(
                new KeyFrame(
                        Duration.millis( 10 ),
                        event -> {
                            time = System.currentTimeMillis()-startTime;
                            if ( time < 0 ) {
                                label.setText( timeFormat.format( 0 ) );
                            } else {
                                label.setText( timeFormat.format( time ) );
                            }
                        }
                )
        );
        timeline.setCycleCount( Animation.INDEFINITE );
        timeline.play();

        return label;
    }



}

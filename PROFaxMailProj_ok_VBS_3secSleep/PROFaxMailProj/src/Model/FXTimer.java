package Model;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXTimer extends Application {

    private Timeline timeline;
    private Label timerLabel = new Label(), splitTimerLabel = new Label();
    private DoubleProperty timeSeconds = new SimpleDoubleProperty(),
            splitTimeSeconds = new SimpleDoubleProperty();
    private Duration time = Duration.ZERO, splitTime = Duration.ZERO;

    @Override
    public void start(Stage primaryStage) {
        // Configure the Label
        // Bind the timerLabel text property to the timeSeconds property
        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");
        splitTimerLabel.textProperty().bind(splitTimeSeconds.asString());
        splitTimerLabel.setTextFill(Color.BLUE);
        splitTimerLabel.setStyle("-fx-font-size: 4em;");

        // Create and configure the Button
        Button button = new Button();
        button.setText("Start / Split");
        button.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (timeline != null) {
                    splitTime = Duration.ZERO;
                    splitTimeSeconds.set(splitTime.toSeconds());
                } else {
                    timeline = new Timeline(
                            new KeyFrame(Duration.millis(100),
                                    new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent t) {
                                            Duration duration = ((KeyFrame)t.getSource()).getTime();
                                            time = time.add(duration);
                                            splitTime = splitTime.add(duration);
                                            timeSeconds.set(time.toSeconds());
                                            splitTimeSeconds.set(splitTime.toSeconds());
                                        }
                                    })
                    );
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();
                }
            }
        });
        // Setup the Stage and the Scene (the scene graph)
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 300, 250);

        // Create and configure VBox
        // gap between components is 20
        VBox vb = new VBox(20);
        // center the components within VBox
        vb.setAlignment(Pos.CENTER);
        // Make it as wide as the application frame (scene)
        vb.setPrefWidth(scene.getWidth());
        // Move the VBox down a bit
        vb.setLayoutY(30);
        // Add the button and timerLabel to the VBox
        vb.getChildren().addAll(button, timerLabel, splitTimerLabel);
        // Add the VBox to the root component
        root.getChildren().add(vb);

        primaryStage.setTitle("FX Timer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
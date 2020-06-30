import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;

public class GameField extends Application{
	
	Stage window;
	
	Scene scene1, scene2;
	
	@Override
	public void start(Stage primaryStage)  {
		 window = primaryStage;
		
		Label label1 = new Label("welcome to the first scene");
		Button button1 = new Button("go to scene 2");
		button1.setOnAction(e -> window.setScene(scene2));
		
		//layout1
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(label1, button1);
		scene1 = new Scene(layout1, 450,450);
		
		//button 2
		Button button2 = new Button("go to scene 1111");
		button2.setOnAction(e -> window.setScene(scene1));
		
		//layout2
		StackPane layout2 = new StackPane();
		layout2.getChildren().add(button2);
		scene2 = new Scene(layout2, 450, 450);
		
		window.setScene(scene1);
		window.setTitle("wauuup");
		window.show();
				
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
	
	
	
	

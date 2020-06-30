/* Anthony Viramontes 
 *  This program has a 5by5 grid and uses the arrow keys
 *  to move the main player. The player is a black circle 
 *  and should be able to move without falling off the grid.
 *  
 */ 


import java.util.Arrays;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Tester extends Application {


	public static void main(String[] args) {
		Application.launch(args);
	}
	
	//5 by 5 grid
	int row;
	int column;

	//create and register the button handler
//	Button start = new Button("Start");
//	Button fbf = new Button("5 by 5");
//	Button sbs = new Button("7 by 7");
//	Button tbs = new Button("10 by 7");

	private Cell[][] cell = new Cell[5][5];

	//label at the bottom
	//private Label lbl = new Label("Words");

	//player
	Cell[][] player = Arrays.copyOf(cell, cell.length);
	int playerX;
	int playerY;

	@Override
	public void start(Stage primaryStage)  {

		GridPane pane = new GridPane();
		for(int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				pane.add(player[i][j] = new Cell(), j, i);
			}
		}


		//set up borderpane
		BorderPane borderPane = new BorderPane();
		
		borderPane.setCenter(pane);
		
		//borderPane.setBottom(buttonGridSize());

		//create a scene and place it in the stage
		Scene scene = new Scene(borderPane, 450, 450);
		primaryStage.setTitle("Anthony's game");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		//place player
		placePlayer();

		//set  the locatio of the player equal to playerX and player
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (player[i][j].getToken() == 'P') {
					playerX = i;
					playerY = j;
				}
			}
		}

		//setting player to move
		playerMove();
		//place traps
		//placeTraps();

		//player requestFocus()
		for(int i = 0; i < 5; i++) {
			for ( int j = 0; j < 5; j++) {
				if(player[i][j].getToken() == 'P') {
					player[i][j].requestFocus();
				}
			}
		}

	}

	//player & grid setup
	public class Cell extends Pane {
		private char token = ' ';

		public Cell() {
			setStyle("-fx-border-color: blue");
			this.setPrefSize(450, 450);
		}

		public char getToken() {
			return token;
		}

		//set an individual grid to the player for once cell(circle)
		public void setToken(char c) {
			token = c;

			if (token == 'P') {
				//set line for character
				Circle circle = new Circle();
				circle.setRadius(30);
				circle.setStyle("-fx-border-color: black");
				circle.centerXProperty().bind(this.maxWidthProperty().subtract(-45));
				circle.centerYProperty().bind(this.maxWidthProperty().subtract(-45));

				this.getChildren().add(circle);
			}

			if (token == ' ') {
				Circle circle1 = new Circle();
				circle1.setRadius(30);
				circle1.setStyle("-fx-fill: red");
				circle1.centerXProperty().bind(this.maxWidthProperty().subtract(-45));
				circle1.centerYProperty().bind(this.maxWidthProperty().subtract(-45));
				this.getChildren().add(circle1);
			}

			if(token == 'T') {
				Circle circle2 = new Circle();
				circle2 .setRadius(30);
				circle2.setStyle("-fx-fill: green");
				circle2.centerXProperty().bind(this.maxWidthProperty().subtract(-45));
				circle2.centerYProperty().bind(this.maxWidthProperty().subtract(-45));
				this.getChildren().add(circle2);
			}
		}
	}

	//allows player to move
	public void playerMove() {
		GridPane pane = new GridPane();


		if (player[playerX][playerY].getToken() == 'P') {
			player[playerX][playerY].setOnKeyPressed(e -> {
				switch (e.getCode()) {

				case DOWN:{

					if ( playerX != player[playerX].length - 1) {
						player[playerX][playerY].setToken(' ');
						playerX++;
						player[playerX][playerY].setToken('P');
					}

					break;
				}
				case LEFT: {

					if ( playerY != 0) {
						player[playerX][playerY].setToken(' ');
						playerY--;
						player[playerX][playerY].setToken('P');
					}
					break;
				}
				case RIGHT: {

					if ( playerY != player[playerY].length -1) {
						player[playerX][playerY].setToken(' ');
						playerY++;
						player[playerX][playerY].setToken('P');		
					}
					break;
				}
				case UP: {

					if (playerX != 0) {
						player[playerX][playerY].setToken(' ');
						playerX--;
						player[playerX][playerY].setToken('P');	
					}
					break;
				}

				default:
				}
			});

		}

	}

	//randomly places player in a cell
	public void placePlayer() {

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {

				//place one player on the field
				if (i == 1 && j == 1) {
					Random r1 = new Random();
					Random r2 = new Random();

					int row = r1.nextInt(5);
					int column = r2.nextInt(5);

					player[row][column].setToken('P');
				}
				//setting up grid to have red dots 
				if (player[i][j].getToken() == ' ') {
					player[i][j].setToken(' ');
				}

				//place one green trap on the field
				if (i == 2 && j == 2) {
					Random r3 = new Random();
					Random r4 = new Random();

					int row1 = r3.nextInt(5);
					int column1 = r4.nextInt(5);

					player[row1][column1].setToken('T');
				}
			}
		}
	}
	//setting char T to be trap
	//	public void placeTraps() {
	//
	//		for (int i = 0; i < 5; i++) {
	//			for (int j = 0; j < 5; j++) {
	//				//place one green trap on the field
	//				if (i == 1 && j == 1) {
	//					Random r1 = new Random();
	//					Random r2 = new Random();
	//
	//					int row = r1.nextInt(5);
	//					int column = r2.nextInt(5);
	//
	//					player[row][column].setToken('T');
	//				}
	//			}
	//		}
	//	}

	
	//setting buttons to chose grid size
	public HBox buttonGridSize() {
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(15,15,15,15));
		hbox.setStyle("-fx-backround-color: blue");
		hbox.getChildren().add(new Button("Start"));
		hbox.getChildren().add(this.fbf);
		hbox.getChildren().add(this.sbs);
		hbox.getChildren().add(this.tbs);
		return hbox;
	}

	public HBox displayText() {

		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(15,15,15,15));
		hbox.setStyle("-fx-backround-color: blue");
		Text text1 = new Text(20, 20 , "The sounds of flame are near");
		text1.setFill(Color.BLUE);
		hbox.getChildren().add(text1);
		return hbox;

	}


//	//implent the button click
//	class fiveByFiveButtonHandler  implements EventHandler<ActionEvent> {
//		@Override
//		public void handle(ActionEvent event) {
//			gridPane(5, 5);
//		}
//	}
//	class sevenBySevenButtonHandler  implements EventHandler<ActionEvent> {
//		@Override
//		public void handle(ActionEvent event) {
//			gridPane(7,7);
//		}
//	}
//	class tenBySevenButtonHandler  implements EventHandler<ActionEvent> {
//		@Override
//		public void handle(ActionEvent event) {
//			gridPane(10,7);
//		}
//	}
//
//
//	public GridPane gridPane(int row, int column) {
//		row = this.row;
//		column = this.column;
//		
//		GridPane pane = new GridPane();
//		for (int i = 0; i < row; i++) {
//			for (int j = 0; j < column; j++) {
//				pane.add(player[i][j] = new Cell(), j, i);
//			}
//		}
//		return pane;
//	
//	}
}

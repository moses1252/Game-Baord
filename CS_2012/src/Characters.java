
import java.util.Arrays;
import java.util.Random;

//import Tester.fiveByFiveButtonHandler;
//import Tester.sevenBySevenButtonHandler;
//import Tester.tenBySevenButtonHandler;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Characters extends Application {

	Stage window;
	Scene scene1, scene2, scene3;

	Button start = new Button("start");
	Button fbf = new Button("5 by 5");
	Button sbs = new Button("7 by 7");
	Button tbs = new Button("10 by 7");


	public static void main(String[] args) {
		Application.launch(args);
	}


	//grid
	int row;
	int column;

	private Cell[][] player;

	//player location
	int playerX;
	int playerY;


	@Override
	public void start(Stage primaryStage)  {


		window = primaryStage;

		this.start.setOnAction( e -> window.setScene(scene2));
		this.fbf.setOnAction(e -> window.setScene(scene2));
		this.sbs.setOnAction(e -> window.setScene(scene2));
		this.tbs.setOnAction(e -> window.setScene(scene2));




		//layout1
		BorderPane borderPane1 = new BorderPane();
		borderPane1.setBottom(buttonGridSize());
		scene1 = new Scene(borderPane1, 450, 450);

		//button 2
		Button back = new Button("Go back");
		back.setOnAction(e -> window.setScene(scene1));

		//layout2
		setGridPane(5,5);
		BorderPane borderPane2 = new BorderPane();
		borderPane2.setBottom(back);
		scene2 = new Scene(borderPane2, 450, 450);

		//layout3
		BorderPane borderPane3 = new BorderPane();
		//borderPane3.setBottom(back);
		scene3 = new Scene(borderPane3, 450,450);
		fbf.setOnAction(e -> window.setScene(scene3));


		//show scene
		window.setScene(scene1);
		window.setTitle("Anthony's Game!");
		window.show();

		//place player
		//placePlayer();

		//move player
		//playerMove();

		//player[playerX][playerY].requestFocus();



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
			if(token == 'E') {
				Circle circle2 = new Circle();
				circle2 .setRadius(30);
				circle2.setStyle("-fx-fill: blue");
				circle2.centerXProperty().bind(this.maxWidthProperty().subtract(-45));
				circle2.centerYProperty().bind(this.maxWidthProperty().subtract(-45));
				this.getChildren().add(circle2);
			}
			if(token == 'A') {
				Circle circle2 = new Circle();
				circle2 .setRadius(30);
				circle2.setStyle("-fx-fill: yellow");
				circle2.centerXProperty().bind(this.maxWidthProperty().subtract(-45));
				circle2.centerYProperty().bind(this.maxWidthProperty().subtract(-45));
				this.getChildren().add(circle2);
			}
		}
	}

	//allows player to move
	public void playerMove() {

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

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {

				//place one player on the field
				if (i == 1 && j == 1) {
					Random r1 = new Random();
					Random r2 = new Random();

					int row2 = r1.nextInt(row);
					int column2 = r2.nextInt(column);

					player[row2][column2].setToken('P');
				}
				//setting up grid to have red dots 
				if (player[i][j].getToken() == ' ') {
					player[i][j].setToken(' ');
				}

				//place one green trap on the field
				if (i == 2 && j == 2) {
					Random r3 = new Random();
					Random r4 = new Random();

					int row1 = r3.nextInt(row);
					int column1 = r4.nextInt(column);

					player[row1][column1].setToken('T');
				}
			}
		}
	}

	//setting buttons to chose grid size
	public HBox buttonGridSize() {
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(15,15,15,15));
		hbox.setStyle("-fx-backround-color: blue");
		hbox.getChildren().add(this.start);
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


	//implent the button click
	class fiveByFiveButtonHandler  implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			setGridPane(5, 5);
		}
	}
	class sevenBySevenButtonHandler  implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			setGridPane(7,7);
		}
	}

	class tenBySevenButtonHandler  implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			setGridPane(10,7);
		}
	}

	public GridPane setGridPane(int row, int column) {
		GridPane pane = new GridPane();

		row = this.row;
		column = this.column;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				pane.add(this.player[i][j] = new Cell(), j, i);
			}
		}

		//		//set  the location of the player equal to playerX and player
		//		for (int i = 0; i < row; i++) {
		//			for (int j = 0; j < column; j++) {
		//				if (player[i][j].getToken() == 'P') {
		//					playerX = i;
		//					playerY = j;
		//				}
		//			}
		//		}
		//
		//		//player requestFocus()
		//		for(int i = 0; i < row; i++) {
		//			for ( int j = 0; j < column; j++) {
		//				if(player[i][j].getToken() == 'P') {
		//					player[i][j].requestFocus();
		//				}
		//			}
		//		}
		//
		//		//setting player to move
		//		playerMove();
		//
		//		placePlayer();

		return pane;	
	}

	public void fivebyfive() {
		Cell[][] cell = new Cell[5][5];

		Cell[][] player = Arrays.copyOf(cell, cell.length);

		int playerX;
		int playerY;

		//set player to equal cell
		GridPane pane = new GridPane();
		for(int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				pane.add(player[i][j] = new Cell(), j, i);
			}
		}

		//set playerX playerY 
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (player[i][j].getToken() == 'P') {
					playerX = i;
					playerY = j;
				}
			}
		}

		//place player
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

		//move player
		//			if (player[playerX][playerY].getToken() == 'P') {
		//				player[playerX][playerY].setOnKeyPressed(e -> {
		//					switch (e.getCode()) {
		//
		//					case DOWN:{
		//
		//						if ( playerX != player[playerX].length - 1) {
		//							player[playerX][playerY].setToken(' ');
		//							playerX++;
		//							player[playerX][playerY].setToken('P');
		//						}
		//
		//						break;
		//					}
		//					case LEFT: {
		//
		//						if ( playerY != 0) {
		//							player[playerX][playerY].setToken(' ');
		//							playerY--;
		//							player[playerX][playerY].setToken('P');
		//						}
		//						break;
		//					}
		//					case RIGHT: {
		//
		//						if ( playerY != player[playerY].length -1) {
		//							player[playerX][playerY].setToken(' ');
		//							playerY++;
		//							player[playerX][playerY].setToken('P');		
		//						}
		//						break;
		//					}
		//					case UP: {
		//
		//						if (playerX != 0) {
		//							player[playerX][playerY].setToken(' ');
		//							playerX--;
		//							player[playerX][playerY].setToken('P');	
		//						}
		//						break;
		//					}
		//
		//					default:
		//					}
		//				});
		//
		//			}

		//player requestFocus()
		for(int i = 0; i < 5; i++) {
			for ( int j = 0; j < 5; j++) {
				if(player[i][j].getToken() == 'P') {
					player[i][j].requestFocus();
				}
			}
		}


	}








}

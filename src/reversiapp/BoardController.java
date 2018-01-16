package reversiapp;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

public class BoardController extends GridPane{
    private Cell[][] board;
    private GameLogic gl;
    private int emptyCells;
    private SingeltonData data;    
    /**
     * constructor
     * @param gl - the Game Logic
     * @param data - The current game data from the settings
     */
    public BoardController(GameLogic gl, SingeltonData data) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        int a = data.GetSize();
        int b = data.GetSize();
        this.data = data;
        this.board = new Cell[a + 2][b + 2];
        this.gl = gl;
        this.emptyCells = a * b - 4;
        
        for(int i = 0; i < a + 2; i++) {
            for(int j = 0; j < b + 2; j++) {
                board[i][j] = new Cell(i,j,' ');
            }
        }
        this.board[a/2][b/2].SetStatus('O');
        this.board[a/2 + 1][b/2 + 1].SetStatus('O');
        this.board[a/2 + 1][b/2].SetStatus('X');
        this.board[a/2][b/2 + 1].SetStatus('X');
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        try {
            fxmlLoader.load();
            this.setOnMousePressed(event -> {
                double x=event.getX();
                double y=event.getY();
                double cw=this.getPrefWidth()/(board.length-2);
                double ch=this.getPrefHeight()/(board[0].length-2);
                int i=1;
                int j=1;
                while(i*cw<x){
                    i++;
                }
                while(j*ch<y){
                    j++;
                }  
                Cell c=new Cell(i,j,' ');
                this.gl.playOneTurn(c, this);
                if (!gl.IsGameOver())
                    this.draw();
            });
            
        } catch(Exception exception){
            
        }
        
    }
    /**
     * Draw the board.
     */
    public void draw() {
        this.getChildren().clear();
        int height = (int)this.getPrefHeight();
        int width = (int)this.getPrefWidth();
        int cellHeight = height / (board.length-2);
        int cellWidth = width / (board[0].length-2);
        Rectangle rec;
        StackPane st;
        for (int i = 1; i < board.length-1; i++) {
            for (int j = 1; j < board[i].length-1; j++){
                st = new StackPane();
                st.setPrefWidth(cellWidth);
                st.setPrefHeight(cellHeight);
                rec = new Rectangle(cellWidth-2, cellHeight-2, Color.BISQUE);
                st.getChildren().add(rec);
                if(this.board[i][j].GetStatus()=='X'){
                    Circle c = new Circle(Math.min(cellWidth, cellHeight)/2-4);
         
                    c.setFill(this.data.GetFirstColor());
                    st.getChildren().add(c);
                }
                if(this.board[i][j].GetStatus()=='O'){
                    
                    Circle c = new Circle(Math.min(cellWidth, cellHeight)/2-4);
                    c.setFill(this.data.GetSecondColor());
                    st.getChildren().add(c);
                }
                this.add(st, i, j);
            }
        }
    }
    /**
     * @return the width of the board.
     */
    public int GetSizeOfRow(){
        return this.data.GetSize();
    }
    /**
     * @return the height of the board.
     */
    public int GetSizeOfCol(){
        return this.data.GetSize();
    }
    /**
     * @param i - Row in board.
     * @param j - Column in board.
     * @return the status of the cell in row i and column j.
     */
    public char GetValue(int i, int j){
        return board[i][j].GetStatus();
    }
    /**
     * Change the status from free to black or white.
     * @param x - Row in board.
     * @param y - Column in board.
     * @param identity - the new status to set.
     */
    public void UpdateOneCell(int x, int y, char identity){
        this.board[x][y].SetStatus(identity);
    }
    /**
     * @return emptyCells member - the number of empty cells.
     */
    public int GetEmptyCells(){
        return this.emptyCells;
    }
    /**
     * Change emptyCells member
     */
    public void SetEmptyCells(){
        this.emptyCells--;
    }
    /**
     * Change emptyCells member
     */
    public void DecEmptyCells(){
        this.emptyCells--;
    }
}

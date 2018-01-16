package reversiapp;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class InfoFrame extends VBox{

    private int Xscore = 2;
    private int Oscore = 2;
    private boolean isXTurn;
    private SettingFrame sttingWindow;
    private SingeltonData data;
    /**
     * constructor
     * @param sttingWindow - the setting window which setting can be set on.
     * @param data - The current game data from the settings
     */
    public InfoFrame(SettingFrame sttingWindow, SingeltonData data){
        this.sttingWindow = sttingWindow;
        this.data = data;
        this.isXTurn = data.GetIsXStart();
    }
    /**
     * Calculate the score of each player.
     * @param b - BoardController.
     */
    public void calcScore(BoardController b){
        this.Xscore = 0;
        this.Oscore = 0;
        for(int i=1;i<=b.GetSizeOfCol();i++){
            for(int j=1;j<=b.GetSizeOfRow();j++){
                if(b.GetValue(i,j)=='X')
                    this.Xscore++;
                if(b.GetValue(i,j)=='O')
                    this.Oscore++;
            }
        }
        this.switchTurn();
        this.draw();
    }
    /**
     * @return Xscore member - the score of x player
     */
    public int GetXScore(){
        return this.Xscore;
    }
    /**
     * @return Oscore member - the score of o player
     */
    public int GetOScore(){
        return this.Oscore;
    }
    /**
     * @return isXTurn member - is x player is playing now
     */
    public boolean Getis(){
        return this.isXTurn;
    }
    /**
     * Draw the information frame.
     */
    public void draw(){
        this.getChildren().clear();
        String st="";
        if(this.isXTurn)
            st=this.data.GetFirstColorAsString();
        else
            st=this.data.GetSecondColorAsString();
        Label currPlayer = new Label("Current player:  " + st);
        Label firstInfo = new Label(this.data.GetFirstColorAsString()+" player score:  " + this.Xscore);
        Label secondInfo = new Label(this.data.GetSecondColorAsString()+" player score:  " + this.Oscore);
        this.getChildren().addAll(this.sttingWindow, currPlayer, firstInfo, secondInfo);
    }
    /**
     * Set isXturn member.
     */
    public void switchTurn(){
        this.isXTurn=!this.isXTurn;
    }
    /**
     * @return the winner color or "even".
     */
    public String GetWinner() {
        if (this.Xscore > this.Oscore)
            return this.data.GetFirstColorAsString();
        if (this.Xscore < this.Oscore)
            return this.data.GetSecondColorAsString();
        return "even";

    }
    /**
     * @return data member - the setting information.
     */
    public SingeltonData GetData(){
        return this.data;
    }
}

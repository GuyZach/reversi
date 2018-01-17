package logic;

import java.util.Vector;

import javax.swing.JOptionPane;

import reversiapp.BoardController;
import reversiapp.InfoFrame;

public class GameLogic {
    private HumanPlayer firstPlayer;
    private HumanPlayer secondPlayer;
    private GameRules gameRules;
    private boolean isXTurn;
    private int passBackTurnsCounter;
    private InfoFrame obs;
    private boolean isGameOver = false;
    /**
     * constructor
     */
    public GameLogic(HumanPlayer first, HumanPlayer second, GameRules gl, InfoFrame obs){
        this.firstPlayer=first;
        this.secondPlayer=second;
        this.gameRules=gl;
        this.passBackTurnsCounter=0;
        this.isXTurn=obs.GetData().GetIsXStart();
        this.obs = obs;
    }
    /**
     * Operate one turn play process.
     * @param c - cell on the board.
     * @param b - BoardController.
     */
    public void playOneTurn(Cell c, BoardController b){
        boolean isLegallChoice;
        Vector<Cell> xvec;
        //black player turn
        if(this.isXTurn){
            c.SetStatus('X');
            xvec = this.gameRules.FindOptions(b, this.firstPlayer);
            //if there are no options for black player
            if(xvec.size()==0){
                this.passBackTurnsCounter++;
            }
            //there are options
            else{
                isLegallChoice = this.firstPlayer.chooseOption(xvec,c);
                if(isLegallChoice){
                    this.gameRules.ChangeBoard(c,this.firstPlayer,b);
                    b.SetEmptyCells();
                    this.obs.calcScore(b);
                    this.switchTurn();
                }
            }    
        }   

        //white player turn
        else{
            c.SetStatus('O');
            xvec = this.gameRules.FindOptions(b, this.secondPlayer);
          //if there are no options for white player
            if(xvec.size()==0){
                this.passBackTurnsCounter++;
            }
            //there are options
            else{
                isLegallChoice = this.secondPlayer.chooseOption(xvec,c);
                if(isLegallChoice){
                    this.gameRules.ChangeBoard(c,this.secondPlayer,b);
                    b.SetEmptyCells();
                    this.obs.calcScore(b);
                    this.switchTurn();
                }
            }    
        }
        //System.out.println(b.GetEmptyCells()+" "+this.passBackTurnsCounter);
        if(b.GetEmptyCells()==0 || this.passBackTurnsCounter > 1){
            this.isGameOver = true;
            b.draw();
            this.FinishAlert();
        }
    }
    /**
     * showing the ending alert message. 
     */
    public void FinishAlert() {
        String s = obs.GetWinner();
        if(s=="even")
            JOptionPane.showMessageDialog(null, "you got the same score");
        else
            JOptionPane.showMessageDialog(null, s+", you are the winner!!");
    }
    /**
     * Switch turn to the other 
     */
    public void switchTurn(){
        this.isXTurn=!this.isXTurn;
    }
    /**
     * @return obs member - the information frame witch show the scores and current player. 
     */
    public InfoFrame GetScroeCounter(){
        return this.obs;
    }
    /**
     * @return isGameOver member - true if the game is over. 
     */
    public boolean IsGameOver() {
        return this.isGameOver;
    }
}

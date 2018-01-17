package logic;

import java.util.Vector;

public class HumanPlayer {
    private char friend;
    private char enemy;
    private int score = 2;
    /**
     * constructor
     */
    public HumanPlayer(){
    }
    /**
     * constructor
     * @param x - the x coordinate
     * @param y - the y coordinate
     */
    public HumanPlayer(char c){
        this.friend = c;
        if(c == 'X'){
            this.enemy = 'O';
        }
        if(c == 'O'){
            this.enemy = 'X';
        }
    }
    /**
     * Check if legal option.
     * @param v - list of legal cells.
     * @param c - board cell.
     * @return boolean value.
     */
    public boolean chooseOption(Vector<Cell> v, Cell c){
        for(int i = 0; i<v.size(); i++){
            if(v.get(i).GetX() == c.GetX() && v.get(i).GetY() == c.GetY()){
                return true;
            }            
        }
        return false;
    }
    /**
     * @return player name(id).
     */
    public char GetIdentity(){
        return this.friend;
    }
    /**
     * @return enemy player name(id).
     */
    public char GetEnemy(){
        return this.enemy;
    }
    /**
     * @return player score.
     */
    public int GetScore(){
        return this.score;
    }
}

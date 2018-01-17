package logic;

import java.util.Vector;

import reversiapp.BoardController;

public class GameRules {
    /**
     * constructor
     */
    public GameRules(){
    }
    /**
     * @param b - BoardController.
     * @param player - the current player.
     * @return vec - the legal options for the player to play.
     */
    public Vector<Cell> FindOptions(BoardController b, HumanPlayer player){
        Vector<Cell> vec = new Vector<Cell>(2);
        int x = b.GetSizeOfCol();
        int y = b.GetSizeOfRow();
        boolean bo = false;
        for(int i = 1; i <= x; i++){
            for(int j = 1; j <= y; j++){
                if(b.GetValue(i, j) == ' '){
                    bo = bo || ChackLine(i,j,-1,-1,b,player);
                    bo = bo || ChackLine(i,j,0,-1,b,player);
                    bo = bo || ChackLine(i,j,1,-1,b,player);
                    bo = bo || ChackLine(i,j,1,0,b,player);
                    bo = bo || ChackLine(i,j,1,1,b,player);
                    bo = bo || ChackLine(i,j,0,1,b,player);
                    bo = bo || ChackLine(i,j,-1,1,b,player);
                    bo = bo || ChackLine(i,j,-1,0,b,player);
                    
                    if(bo == true){
                        Cell p = new Cell(i, j,' ');
                        vec.addElement(p);
                    }
                    bo = false;
                }
            }
        }
        return vec;
    }
    /**
     * Check if the line will be changed if the player will play the given cell coordinate.
     * @param i - the cell row.
     * @param j - the cell column.
     * @param dx - the horizontal direction progress.
     * @param dy - the vertical direction progress.
     * @param b - BoardController.
     * @param player - the current player.
     * @return boolean value
     */
    public boolean ChackLine(int i, int j, int dx, int dy, BoardController b, HumanPlayer player){
       i = i + dx;
       j = j + dy;
       if(b.GetValue(i, j)==player.GetIdentity())
           return false;
       while(i+dx<=b.GetSizeOfRow() && i+dx>=1 && j+dy<=b.GetSizeOfCol() && j+dy>=1){
           if(b.GetValue(i, j)==player.GetEnemy() && b.GetValue(i+dx, j+dy)==player.GetIdentity()){
               return true;
           }
           if(b.GetValue(i, j)==' '){
               return false;
           }
           i = i + dx;
           j = j + dy;
       }
       return false;
    }
    /**
     * Change the board according to the chosen cell.
     * @param p - the cell.
     * @param player - the current player.
     * @param b - BoardController.
     */
    public void ChangeBoard(Cell p, HumanPlayer player, BoardController b){
        boolean bo = false;
        int x = p.GetX();
        int y = p.GetY();
        b.UpdateOneCell(x, y, player.GetIdentity());
        bo=ChackLine(x,y,-1,-1,b,player);
        if(bo==true){
            this.ChangeLine(p,-1,-1,player,b);
        }
        bo=ChackLine(x,y,0,-1,b,player);
        if(bo==true){
            this.ChangeLine(p,0,-1,player,b);
        }
        bo=ChackLine(x,y,1,-1,b,player);
        if(bo==true){
            this.ChangeLine(p,1,-1,player,b);
        }
        bo=ChackLine(x,y,1,0,b,player);
        if(bo==true){
            this.ChangeLine(p,1,0,player,b);
        }
        bo=ChackLine(x,y,1,1,b,player);
        if(bo==true){
            this.ChangeLine(p,1,1,player,b);
        }
        bo=ChackLine(x,y,0,1,b,player);
        if(bo==true){
            this.ChangeLine(p,0,1,player,b);
        }
        bo=ChackLine(x,y,-1,1,b,player);
        if(bo==true){
            this.ChangeLine(p,-1,1,player,b);
        }
        bo=ChackLine(x,y,-1,0,b,player);
        if(bo==true){
            this.ChangeLine(p,-1,0,player,b);
        }
    }
    /**
     * Change one line according to the chosen cell.
     * @param p - the cell.
     * @param dx - the horizontal direction progress.
     * @param dy - the vertical direction progress.
     * @param b - BoardController.
     * @param player - the current player.
     */
    public void ChangeLine(Cell p, int dx, int dy, HumanPlayer player, BoardController b){
        int i=p.GetX()+dx;
        int j=p.GetY()+dy;
        while(b.GetValue(i,j)==player.GetEnemy()){
            b.UpdateOneCell(i,j,player.GetIdentity());
            i=i+dx;
            j=j+dy;
        }
    }
}

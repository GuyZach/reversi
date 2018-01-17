package logic;

public class Cell {
    private int x;
    private int y;
    private char st;
    /**
     * constructor
     * @param x - the x coordinate
     * @param y - the y coordinate
     */
    public Cell(int x, int y, char st){
        this.x = x;
        this.y = y;
        this.st = st;
    }
    /**
     * @return the x coordinate.
     */
    public int GetX(){
        return this.x;
    }
    /**
     * @return the y coordinate.
     */
    public int GetY(){
        return this.y;
    }
    /**
     * @return the status.
     */
    public char GetStatus(){
        return this.st;
    }
    /**
     * set newSt member.
     * @param newSt the new status value.
     */
    public void SetStatus(char newSt){
        this.st = newSt;
    }
    /**
     * set x and y members.
     * @param x - the x value.
     * @param y - the y value.
     */
    public void Setxy(int x, int y){
        this.x=x;
        this.y=y;
    }
}

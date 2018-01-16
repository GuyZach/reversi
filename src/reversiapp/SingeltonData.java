package reversiapp;

import javafx.scene.paint.Color;

public class SingeltonData {
    private int size;
    private String XColor;
    private String OColor;
    private boolean isXStart;
    /**
     * constructor
     */
    public SingeltonData() {
        this.size = 8;
        this.XColor = "black";
        this.OColor = "white";
        this.isXStart = true;
    }
    /**
     * set board size.
     * @param size board.
     */
    public void SetSize(int size) {
        this.size = size;
    }
    /**
     * set X player color.
     * @param color - string color name
     */
    public void SetFirstColor(String color) {
        this.XColor = color;
    }
    /**
     * set O player color.
     * @param color -string color name
     */
    public void SetSecondColor(String color) {
        this.OColor = color;
    }
    /**
     * set starter player.
     * @param starter - string starter player name
     */
    public void SetStarter(String starter) {
        if(starter.equals("Black player")){
            this.isXStart = true;
        }
        if(starter.equals("White player")){
            this.isXStart = false;
        }
    }
    /**
     * @return isXStart
     */
    public boolean GetIsXStart() {
        return this.isXStart;
    }
    /**
     * @return size
     */
    public int GetSize() {
        return this.size;
    }
    /**
     * @return Xcolor as Color type
     */
    public Color GetFirstColor() {
        return this.convertToColor(this.XColor);
    }
    /**
     * @return Ocolor as Color type
     */
    public Color GetSecondColor() {
        return this.convertToColor(this.OColor);
    }
    /**
     * @return Xcolor as String type
     */
    public String GetFirstColorAsString() {
        return this.XColor;
    }
    /**
     * @return Ocolor as String type
     */
    public String GetSecondColorAsString() {
        return this.OColor;
    }
    /**
     * convert from String type to Color type.
     * @param string - string color name
     */
    public Color convertToColor(String string) {
        Color c = Color.BLACK;
        switch(string){
        case "blue":
            c = Color.BLUE;
            break;
        case "red":
            c = Color.RED;
            break;
        case "green":
            c = Color.GREEN;
            break;
        case "yellow":
            c = Color.YELLOW;
            break;
        case "black":
            c = Color.BLACK;
            break;
        case "white":
            c = Color.WHITE;
            break;
        }
        return c;
    }
}

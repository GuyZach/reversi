package reversiapp;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import logic.GameLogic;
import logic.GameRules;
import logic.HumanPlayer;
import logic.SingeltonData;

public class GamaController implements Initializable {
    @FXML
    private HBox root;
    
    @Override
    /**
     * Construct all the objects for the game.
     * @param location
     * @param resources
     */
    public void initialize(URL location, ResourceBundle resources) {        
        SingeltonData data = new SingeltonData();
        String line = null;
        try {
            FileReader fileReader = new FileReader("data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            line = bufferedReader.readLine();
            data.SetSize(Integer.parseInt(line));
            line = bufferedReader.readLine();
            data.SetFirstColor(line);
            line = bufferedReader.readLine();
            data.SetSecondColor(line);
            line = bufferedReader.readLine();
            data.SetStarter(line);
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + "data.txt" + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + "data.txt" + "'");                  
        }
        
        HumanPlayer first = new HumanPlayer('X');
        HumanPlayer second = new HumanPlayer('O');
        GameRules gr = new GameRules();
        SettingFrame sttingWindow = new SettingFrame();
        InfoFrame so = new InfoFrame(sttingWindow, data);
        GameLogic gl = new GameLogic(first,second,gr,so);
        BoardController reversiBoard = new BoardController(gl,data);
             
        reversiBoard.setPrefWidth(400);
        reversiBoard.setPrefHeight(400);
        
        so.setPrefWidth(150);
        so.setPrefHeight(400);
        root.getChildren().addAll(so, reversiBoard);
        so.draw();

        reversiBoard.draw();
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            reversiBoard.setPrefWidth(boardNewWidth);
            reversiBoard.draw();
        });

        root.heightProperty().addListener((observable, oldValue, newValue) -> {
                reversiBoard.setPrefHeight(newValue.doubleValue());
                reversiBoard.draw();
        });        
    }
}

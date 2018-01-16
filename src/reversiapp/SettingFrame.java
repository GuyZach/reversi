package reversiapp;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingFrame extends HBox {
    private ComboBox<String> boardSizes;
    private ComboBox<String> blackColors;
    private ComboBox<String> whiteColores;
    private ComboBox<String> starter;
    private Stage settingsStage;
    /**
     * constructor
     */
    public SettingFrame(){
        Label settings = new Label("settings");
        this.getChildren().addAll(settings);  
        settings.setOnMousePressed(event -> {
            this.setSetting();
        });
    }
    /**
     * Construct the setting window.
     */
    public void setSetting() {
        StackPane v = new StackPane();
        this.settingsStage = new Stage();
        settingsStage.setTitle("Settings");
        Scene scene = new Scene(v,250,250);
        HBox h1 = new HBox();
        HBox h2 = new HBox();
        HBox h3 = new HBox();
        HBox h4 = new HBox();
        Label l1 = new Label(" Board size:                 ");
        Label l2 = new Label(" Black player color:     ");
        Label l3 = new Label(" White player color:    ");
        Label l4 = new Label(" Starting player:          ");

        this.boardSizes = new ComboBox<String>(FXCollections.observableArrayList(
                "4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"));
        this.blackColors = new ComboBox<String>(FXCollections.observableArrayList(
                "blue", "red", "green", "yellow", "black", "white"));
        this.whiteColores = new ComboBox<String>(FXCollections.observableArrayList(
                "blue", "red", "green", "yellow", "black", "white"));
        this.starter = new ComboBox<String>(FXCollections.observableArrayList("Black player", "White player"));
        h1.getChildren().addAll(l1,this.boardSizes);
        h2.getChildren().addAll(l2,this.blackColors);
        h3.getChildren().addAll(l3,this.whiteColores);
        h4.getChildren().addAll(l4,this.starter);

        Button bt = new Button("OK");
        VBox allinfo = new VBox();
        allinfo.getChildren().addAll(h1,h2,h3,h4);
        v.getChildren().addAll(allinfo,bt);
        settingsStage.setScene(scene);
        settingsStage.show();     
        bt.setPrefWidth(80);
        
        bt.setOnMousePressed(event -> {
            this.ApplySettings();
            settingsStage.close();
        });     
    }
    /**
     * white the setting to a file.
     */
    public void ApplySettings() {
        if(this.boardSizes.getValue()==null || this.blackColors.getValue()==null || this.whiteColores.getValue()==null) {
            JOptionPane.showMessageDialog(null, "changes won't be saved if not filling all fields");
            return;
        }
        int choosenSize = Integer.parseInt((String)this.boardSizes.getSelectionModel().getSelectedItem());
        String choosenFirstColor = (String)this.blackColors.getSelectionModel().getSelectedItem();
        String choosenSecondColor = (String)this.whiteColores.getSelectionModel().getSelectedItem();
        String choosenStarter = (String)this.starter.getSelectionModel().getSelectedItem();
        
        try {
            FileWriter fileWriter = new FileWriter("data.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Integer.toString(choosenSize));
            bufferedWriter.newLine();
            bufferedWriter.write(choosenFirstColor);
            bufferedWriter.newLine();
            bufferedWriter.write(choosenSecondColor);
            bufferedWriter.newLine();
            bufferedWriter.write(choosenStarter);
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + "data.txt" + "'");
        }
    }
}

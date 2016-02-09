/*
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package makedesktop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import makedesktop.design.design;

/**
 * A sample that demonstrates how to draw and paint shapes, apply visual
 * effects, blend colors in overlapping objects, and animate objects.
 *
 * @see javafx.scene.effect.BlendMode
 * @see javafx.scene.effect.BoxBlur
 * @see javafx.scene.shape.Circle
 * @see javafx.scene.Group
 * @see javafx.scene.paint.LinearGradient
 * @see javafx.animation.Timeline
 */
public class MakeDesktop extends Application {

    private static final double WIDTH = 530, HEIGHT = 600;
    private final int MASTER_WIDTH=530, MASTER_HEIGHT=600;
    private final String TITLE="Make-Desktop Version 1.3";
    
    public String name,type,exec,icon,comment,catagories;
    public boolean terminal,no_display;
    
    public MakeDesktop(){
        
    }
    
    //create a console for logging mouse events
    final ListView<String> console = new ListView<String>();
    //create a observableArrayList of logged events that will be listed in console
    final ObservableList<String> consoleObservableList = FXCollections.observableArrayList();
    {
        //set up the console
        console.setItems(consoleObservableList);
        console.setLayoutY(575);
        console.setPrefSize(600, 27);
    }
    
    private void showOnConsole(String text) {
        //if there is 8 items in list, delete first log message, shift other logs and  add a new one to end position
        if (consoleObservableList.size()==1) {
           consoleObservableList.remove(0);
        }
        consoleObservableList.add(text);
    }
    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setResizable(false);
        primaryStage.setTitle(TITLE);

        GridPane grid = new GridPane();

        //Set Grid Gap/Padding
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(15, 15, 15, 15));
        
        
        //Displaying text
        Text title = new Text(TITLE);
        Text author = new Text("author - zafran");
        title.setStyle("-fx-font: 15px TAHOMA; -fx-fill: white;");
        author.setStyle("-fx-font: 10px TAHOMA; -fx-fill: white;");
        
        
        
        Label l_name = new Label("Name");
        Label l_type = new Label("Type");
        Label l_exec = new Label("Execute path");
        Label l_icon = new Label("Icon path");
        Label l_comment = new Label("Comment");
        Label l_catagories = new Label("Catagories");
        Label l_terminal = new Label("Terminal");
        Label l_no_display = new Label("No Display");
        
        l_name.setTextFill(Color.WHITE);
        l_type.setTextFill(Color.WHITE);
        l_exec.setTextFill(Color.WHITE);
        l_icon.setTextFill(Color.WHITE);
        l_comment.setTextFill(Color.WHITE);
        l_catagories.setTextFill(Color.WHITE);
        l_terminal.setTextFill(Color.WHITE);
        l_no_display.setTextFill(Color.WHITE);

        //Displaying TextField
        final TextField t_name = new TextField();
        final TextField t_type = new TextField();
        t_type.setText("Application");
        final TextField t_exec = new TextField();
        t_exec.setOnMouseEntered((MouseEvent me) -> {
            showOnConsole("Enter the path for the executable, can add value such as 'java -jar' at front.");
        });
        t_exec.setOnMouseExited((MouseEvent me) -> {
           consoleObservableList.remove(0);
        });
        
        final TextField t_icon = new TextField();
        t_icon.setOnMouseExited((MouseEvent me) -> {
           consoleObservableList.remove(0);
        });

        t_icon.setOnMouseEntered((MouseEvent me) -> {
            showOnConsole("Enter the path for the icon. Can be (.png), (.jpg)");
        });
        t_exec.setOnMouseExited((MouseEvent me) -> {
           consoleObservableList.remove(0);
        });
        
        
        final TextField t_comment = new TextField();
        t_comment.setText("Some comment");
        final TextField t_catagories = new TextField();        
        t_catagories.setText("Application; Programming; ");
        //Radio Button
        final RadioButton r_term_true = new RadioButton("True");
        final RadioButton r_term_false = new RadioButton("False");
        
        final RadioButton r_nd_true = new RadioButton("True");
        final RadioButton r_nd_false = new RadioButton("False");
        
        final ToggleGroup term = new ToggleGroup();
        final ToggleGroup n_disp = new ToggleGroup();
        
        r_term_true.setToggleGroup(term);
        r_term_false.setToggleGroup(term);
        r_term_true.setSelected(true);
        
        r_nd_true.setToggleGroup(n_disp);
        r_nd_false.setToggleGroup(n_disp);
        r_nd_false.setSelected(true);
        
	//Set position of text
        GridPane.setConstraints(title, 0, 0);
	GridPane.setConstraints(author, 0, 1);
        
        GridPane.setConstraints(l_name, 0, 2);
	GridPane.setConstraints(l_type, 0, 3);
        GridPane.setConstraints(l_exec, 0, 4);
	
        GridPane.setConstraints(l_icon, 0, 5);
	GridPane.setConstraints(l_comment, 0, 6);
        GridPane.setConstraints(l_catagories, 0, 7);
	
        GridPane.setConstraints(l_terminal, 0, 9);
        GridPane.setConstraints(l_no_display, 0, 11);

        
        //Set position of TextField
        GridPane.setConstraints(t_name, 1, 2);
	GridPane.setConstraints(t_type, 1, 3);
        GridPane.setConstraints(t_exec, 1, 4);
        
        GridPane.setConstraints(t_icon, 1, 5);
	GridPane.setConstraints(t_comment, 1, 6);
        GridPane.setConstraints(t_catagories, 1, 7);
        
        GridPane.setConstraints(r_term_true, 1, 9);
        GridPane.setConstraints(r_term_false, 1, 10);
        
        GridPane.setConstraints(r_nd_true, 1, 11);
        GridPane.setConstraints(r_nd_false, 1, 12);

        Button btn = new Button();
        Button browse_1 = new Button("...");
        Button browse_2 = new Button("...");

        
        btn.setText("Make It");
        btn.setOnAction((ActionEvent event) -> {

            if(t_name.getText().isEmpty() || t_exec.getText().isEmpty()){
                showOnConsole("You must not leave name and execute empty!");
            }
            else{
                name = t_name.getText();
                type = t_type.getText();
                exec = t_exec.getText();
                icon = t_icon.getText();
                comment = t_comment.getText();
                catagories = t_catagories.getText();

                if(term.getSelectedToggle() == r_term_true)
                    terminal = true;
                else if(term.getSelectedToggle() == r_term_false)
                    terminal = true;

                if(n_disp.getSelectedToggle() == r_nd_true)
                    no_display = true;
                else if(n_disp.getSelectedToggle() == r_nd_false)
                    no_display = true;

                try {
                    saveResult();
                } catch (IOException ex) {
                    Logger.getLogger(MakeDesktop.class.getName()).log(Level.SEVERE, null, ex);
                }

                MakeDesktop obj = new MakeDesktop();

                String command = "chmod +x " + name + ".desktop";

                String output = obj.executeCommand(command);
                showOnConsole("Successfuly created "+name+" file!");
            }
        });
        
        
        browse_1.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            
            //Extention filter
            FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
            fileChooser.getExtensionFilters().add(extentionFilter);
            
            //Set to user directory or go to default if cannot access
            String userDirectoryString = System.getProperty("user.home");
            File userDirectory = new File(userDirectoryString);
            if(!userDirectory.canRead()) {
                userDirectory = new File("c:/");
            }
            fileChooser.setInitialDirectory(userDirectory);
            
            //Choose the file
            File chosenFile = fileChooser.showOpenDialog(null);
            //Make sure a file was selected, if not return default
            String path;
            if(chosenFile != null) {
                path = chosenFile.getPath();
                t_exec.setText(path);
            } else {
                //default return value
                path = null;
            }
        });
        
        ImageView v1 = new ImageView();

        browse_2.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            
            //Extention filter
            FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
            fileChooser.getExtensionFilters().add(extentionFilter);
            
            //Set to user directory or go to default if cannot access
            String userDirectoryString = System.getProperty("user.home");
            File userDirectory = new File(userDirectoryString);
            if(!userDirectory.canRead()) {
                userDirectory = new File("c:/");
            }
            fileChooser.setInitialDirectory(userDirectory);
            
            //Choose the file
            File chosenFile = fileChooser.showOpenDialog(null);
            //Make sure a file was selected, if not return default
            String path;
            if(chosenFile != null) {
                path = chosenFile.getPath();
                t_icon.setText(path);
                
                Image img = new Image(new File(path).toURI().toString());
                
                v1.setImage(img);
                v1.setFitWidth(50);
                v1.setPreserveRatio(true);
                v1.setSmooth(true);
                v1.setCache(true);
                
                
                
            } else {
                //default return value
                path = null;
            }
        });


        
        //Set position button
        GridPane.setConstraints(btn, 2, 14); 
        GridPane.setConstraints(browse_1, 2, 4); 
        GridPane.setConstraints(browse_2, 2, 5); 
        GridPane.setConstraints(v1, 0, 15);

        grid.getChildren().addAll( 
                title, 
                author, 
                l_name, 
                l_type, 
                l_exec, 
                l_icon, 
                l_comment, 
                l_catagories,
                l_terminal,
                l_no_display,
                t_name, 
                t_type, 
                t_exec, 
                t_icon, 
                t_comment, 
                t_catagories,
                r_term_true,
                r_term_false,
                r_nd_true,
                r_nd_false,
                btn,
                browse_1,
                browse_2,
                v1);
                
        design obj = new design();
        
        root.getChildren().addAll(obj.init_design(WIDTH, HEIGHT),grid, console);
        obj.play();

        primaryStage.setScene(new Scene(root, MASTER_WIDTH, MASTER_HEIGHT));

    }



    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX 
     * application. main() serves only as fallback in case the 
     * application can not be launched through deployment artifacts,
     * e.g., in IDEs with limited FX support. NetBeans ignores main().
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void saveResult() throws IOException{
        
        //Saving the output to Desktop file
        BufferedWriter saveFile = null;
        try
        {       	
    		String save_dir = name+".desktop";

           
        	 File filename = new File(save_dir);
            
            saveFile = new BufferedWriter(new FileWriter(filename));
            saveFile.write("[Desktop Entry]\n");
            saveFile.write("Name="+name+"\n");
            saveFile.write("Type="+type+"\n");
            saveFile.write("Exec="+exec+"\n");
            saveFile.write("Terminal="+terminal+"\n");
            saveFile.write("Icon="+icon+"\n");
            saveFile.write("Comment="+comment+"\n");
            saveFile.write("NoDisplay="+no_display+"\n");
            saveFile.write("Catagories="+catagories+"\n");
            saveFile.write("Name[en]="+name+"\n");

           

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(saveFile != null) saveFile.close();
        }
    }
    
    private String executeCommand(String command) {

            StringBuffer output = new StringBuffer();

            Process p;
            try {
                    p = Runtime.getRuntime().exec(command);
                    p.waitFor();
                    BufferedReader reader = 
                        new BufferedReader(new InputStreamReader(p.getInputStream()));

                    String line = "";			
                    while ((line = reader.readLine())!= null) {
                            output.append(line + "\n");
                    }

            } catch (Exception e) {
                    e.printStackTrace();
            }

            return output.toString();

    }


}

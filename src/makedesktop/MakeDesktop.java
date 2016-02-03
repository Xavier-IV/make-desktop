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
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private static final double WIDTH = 495, HEIGHT = 480;
    private Timeline animation;
    public String name,type,exec,icon,comment,catagories;
    public boolean terminal,no_display;
    
    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Make Desktop 1.0");
        primaryStage.setScene(new Scene(root, 495,480));
        Group layer1 = new Group();
        for(int i=0; i<15;i++) {
            Circle circle = new Circle(200,Color.web("white",0.05f));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white",0.2f));
            circle.setStrokeWidth(4f);
            layer1.getChildren().add(circle);
        }
        // create second list of circles
        Group layer2 = new Group();
        for(int i=0; i<20;i++) {
            Circle circle = new Circle(70,Color.web("white",0.05f));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white",0.1f));
            circle.setStrokeWidth(2f);
            layer2.getChildren().add(circle);
        }
        // create third list of circles
        Group layer3 = new Group();
        for(int i=0; i<10;i++) {
            Circle circle = new Circle(150,Color.web("white",0.05f));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white",0.16f));
            circle.setStrokeWidth(4f);
            layer3.getChildren().add(circle);
        }
        // Set a blur effect on each layer
        layer1.setEffect(new BoxBlur(30,30,3));
        layer2.setEffect(new BoxBlur(2,2,2));
        layer3.setEffect(new BoxBlur(10,10,3));
        // create a rectangle size of window with colored gradient
        Rectangle colors = new Rectangle(WIDTH, HEIGHT,
                new LinearGradient(0f,1f,1f,0f,true, CycleMethod.NO_CYCLE, new Stop(0,Color.web("#f8bd55")),
                        new Stop(0.14f,Color.web("#c0fe56")),
                        new Stop(0.28f,Color.web("#5dfbc1")),
                        new Stop(0.43f,Color.web("#64c2f8")),
                        new Stop(0.57f,Color.web("#be4af7")),
                        new Stop(0.71f,Color.web("#ed5fc2")),
                        new Stop(0.85f,Color.web("#ef504c")),
                        new Stop(1,Color.web("#f2660f")))
        );
        colors.setBlendMode(BlendMode.OVERLAY);
        // create main content
        Group group = new Group(
                new Rectangle(WIDTH, HEIGHT, Color.BLACK),
                layer1, 
                layer2,
                layer3,
                colors
        );
        Rectangle clip = new Rectangle(WIDTH, HEIGHT);
        clip.setSmooth(false);
        group.setClip(clip);
        
        
        GridPane grid = new GridPane();

        //Set Grid Gap/Padding
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        
        
        //Displaying text
        Text title = new Text("Make-Desktop Version 1.0");
        Text author = new Text("author - zafran");
        title.setStyle("-fx-font: 15px TAHOMA; -fx-fill: white;");
        author.setStyle("-fx-font: 10px TAHOMA; -fx-fill: white;");
        
        Label l_name = new Label("Name");
        Label l_type = new Label("Type");
        Label l_exec = new Label("Execute");
        Label l_icon = new Label("Icon path");
        Label l_comment = new Label("Comment");
        Label l_catagories = new Label("Catagories");
        Label l_terminal = new Label("Terminal");
        Label l_no_display = new Label("No Display");
        
        //Displaying TextField
        final TextField t_name = new TextField();
        final TextField t_type = new TextField();
        final TextField t_exec = new TextField();
        final TextField t_icon = new TextField();
        final TextField t_comment = new TextField();
        final TextField t_catagories = new TextField();        

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
        btn.setText("Make It");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
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

                            
            }
        });
        
        //Set position button
        GridPane.setConstraints(btn, 2, 14); 


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
                btn);
                
        
        root.getChildren().addAll(group, grid);
        // create list of all circles
        List<Node> allCircles = new ArrayList<Node>();
        allCircles.addAll(layer1.getChildren());
        allCircles.addAll(layer2.getChildren());
        allCircles.addAll(layer3.getChildren());
        // Create a animation to randomly move every circle in allCircles
        animation = new Timeline();
        for(Node circle: allCircles) {
            animation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, // set start position at 0s
                    new KeyValue(circle.translateXProperty(),random()*WIDTH),
                    new KeyValue(circle.translateYProperty(),random()*HEIGHT)
                ),
                new KeyFrame(new Duration(40000), // set end position at 40s
                    new KeyValue(circle.translateXProperty(),random()*WIDTH),
                    new KeyValue(circle.translateYProperty(),random()*HEIGHT)
                )
            );
        }
        animation.setAutoReverse(true);
        animation.setCycleCount(Animation.INDEFINITE);
    }

    @Override public void stop() {
        animation.stop();
    }

    public void play() {
        animation.play();
    }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        play();
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

            
//            [Desktop Entry]
//            Name=Eclipse 
//            Type=Application
//            Exec=env UBUNTU_MENUPROXY=0 eclipse44
//            Terminal=false
//            Icon=eclipse
//            Comment=Integrated Development Environment
//            NoDisplay=false
//            Categories=Development;IDE;
//            Name[en]=Eclipse

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

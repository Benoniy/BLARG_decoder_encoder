import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class TXT_DECODER {
    static Group g = new Group();
    static ArrayList<String> lines, currentObj;
    static int line_count;
    static boolean firstLine = true, firstObj = true;

    public static void decode(String filename){
        firstLine = true;
        firstObj = true;
        Main.bkPane.getChildren().clear();
        Main.objPane.getChildren().clear();

        line_count = 0;
        try {
            lines = new ArrayList<>(Files.readAllLines(Paths.get(filename)));
            lines.removeAll(Collections.singleton(""));
            ArrayList<String> currentObj = new ArrayList<>();

            for (String l : lines){
                String line = l.toLowerCase().replace(" ", "").replace("\n", "");


                if (firstLine){
                    currentObj.add(line);
                    firstLine = false;
                }
                else {
                    if (!line.contains("[")){
                        currentObj.add(line);
                    }
                    else {
                        processObj(currentObj);
                        currentObj.clear();
                        currentObj.add(line);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void processObj(ArrayList<String> currentObj){
        System.out.println(currentObj);
        if (firstObj){
            genMain(currentObj);
            firstObj = false;
        }
        else {
            genShape(currentObj);
        }
    }


    static void genShape(ArrayList<String> currentObj){
        String title = "";
        String type = "";
        String sprite = "";
        double ingameX=1, ingameY=1, ingameW=1, ingameH=1;
        Color color = Color.HOTPINK;

        for (String line : currentObj){
            if (line.contains("sprite=")){
                sprite = line.replace("sprite=", "");
            }
        }

        for (String s : currentObj){
            if (s.contains("[")){
                title = s;
            }
            else if (s.contains("type=")){
                type = s.replace("type=", "");
            }
            else if (s.contains("size=")){
                s = s.replace("size=", "").replace("w", "")
                        .replace("h", "").replace("%", "");
                String[] values = s.split(",");
                try{
                    if (!sprite.contains("icon")){
                        ingameW = Integer.parseInt(values[0]);
                        ingameH = Integer.parseInt(values[1]);
                    }
                    else{
                        ingameW = Integer.parseInt(values[0]);
                        ingameH = ingameW;
                    }
                }
                catch (Exception ignored){}
            }

            else if (s.contains("position=")){
                s = s.replace("position=", "").replace("w", "")
                        .replace("h", "");
                String[] values = s.split(",");
                try{
                    ingameX = Integer.parseInt(values[0]);
                    ingameY = Integer.parseInt(values[1]);
                }
                catch (Exception ignored){
                }
            }
            else if (s.contains("color=")){
                color = workColour(s.replace("color=", ""));
            }
        }

        if (type.equals("sprite")){
            if (sprite.equals("square")){
                square s = new square(Main.bkPane, title, ingameX, ingameY, ingameW, ingameH, color);
                Main.sprites.add(s);
            }
            else if (sprite.equals("circle")){
                circle s = new circle(Main.bkPane, title, ingameX, ingameY, ingameW, ingameH, color);
                Main.sprites.add(s);
            }
            else if (sprite.equals("iconhydrogen")){
                Icon s = new IconHydrogen(Main.bkPane, title, ingameX, ingameY, ingameW, ingameH);
                Main.sprites.add(s);
            }
            else if (sprite.equals("iconenergy")){
                Icon s = new IconEnergy(Main.bkPane, title, ingameX, ingameY, ingameW, ingameH);
                Main.sprites.add(s);
            }
        }
    }

    static void genMain(ArrayList<String> currentObj){
        String title = "";
        int screenVal = 1;
        Color bkColor = Color.BLACK;

        for (String line : currentObj){
            if (line.contains("[")){
                title = line;
            }
            else if (line.contains("screen")){
                String screen = line.replace("screen=", "");
                screenVal = Integer.parseInt(screen);
            }
            else if (line.contains("color")){
                String color = line.replace("color=","");
                bkColor = workColour(color);
            }
        }


        Main.bk = new Instance(Main.bkPane, title);
        Main.bk.setScreen(screenVal);
        Main.bk.setColor(bkColor);
    }

    static Color workColour(String sus){
        if (sus.contains("#")){
            return Color.valueOf(sus);
        }
        else{
            return Color.valueOf("rgb("+sus+")");
        }
    }
}

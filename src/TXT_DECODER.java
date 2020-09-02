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
    static boolean first = true;

    static String temp_title = "";
    static String sprite = "";

    public static void decode(String filename){
        first = true;
        Main.bkPane.getChildren().clear();
        Main.objPane.getChildren().clear();

        line_count = 0;
        try {

            lines = new ArrayList<>(Files.readAllLines(Paths.get(filename)));
            lines.removeAll(Collections.singleton(""));

            currentObj = new ArrayList<>();

            for (String l : lines){
                String line = l.toLowerCase().replace("\n", "").replace(" ", "");
                if (line.contains("[")){
                    temp_title = line;
                }
                if (line.contains("sprite")){
                    sprite = line.replace("sprite=", "");
                }

                if (!line.contains("[") | line_count < 1){
                    currentObj.add(line);
                }
                else {
                    if(first){
                        genMain(currentObj);
                        first = false;
                        currentObj.clear();
                    }
                    else if (line.contains("[")) {
                        if (sprite.equals("square")) {
                            genShape(currentObj, "square");
                            currentObj.clear();
                        }
                        else if (sprite.equals("circle")) {
                            genShape(currentObj, "circle");
                            currentObj.clear();
                        }
                    }
                }
                line_count++;
            }

            if (sprite.equals("square")){
                genShape(currentObj, "square");
                currentObj.clear();
            }
            else if (sprite.equals("circle")) {
                genShape(currentObj, "circle");
                currentObj.clear();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void genShape(ArrayList<String> list, String type){
        String title = temp_title;
        Color color = Color.HOTPINK;
        int x=0, y=1, w=0, h=0;

        for (String s : list){
            if (s.contains("size=")){
                s = s.replace("size=", "").replace("w", "")
                        .replace("h", "");
                String[] values = s.split(",");
                try{
                    w = Integer.parseInt(values[0]);
                    h = Integer.parseInt(values[1]);
                }
                catch (Exception ignored){}
            }
            else if (s.contains("position=")){
                s = s.replace("position=", "").replace("w", "")
                        .replace("h", "");
                String[] values = s.split(",");
                System.out.println(values[1]);
                try{
                    x = Integer.parseInt(values[0]);
                    y = Integer.parseInt(values[1]);
                }
                catch (Exception ignored){
                }
            }
            else if (s.contains("color=")){
                color = workColour(s.replace("color=", ""));
            }
        }
        if (type.contains("square")){
            square s = new square(Main.bkPane, title, x, y, w, h, color);
            Main.sprites.add(s);
        }
        else if (type.contains("circle")){
            circle s = new circle(Main.bkPane, title, x, y, w, h, color);
            Main.sprites.add(s);
        }

    }

    static void genMain(ArrayList<String> list){
        String title = "";
        int screenVal = 1;
        Color bkColor = Color.BLACK;

        for (String line : list){
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

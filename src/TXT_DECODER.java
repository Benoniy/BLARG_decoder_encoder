import Sprites.*;
import javafx.scene.paint.Color;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;


public class TXT_DECODER {
    private static boolean firstLine = true, firstObj = true;
    private static String title = "", type = "", data = "";
    private static double ingameX=1, ingameY=1, ingameW=1, ingameH=1;
    private static Color color = null;

    public static void decode(String filename){
        firstLine = true;
        firstObj = true;
        Main.bkPane.getChildren().clear();
        Main.objPane.getChildren().clear();

        try {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(filename)));
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

    private static void processObj(ArrayList<String> currentObj){
        if (firstObj){
            genMain(currentObj);
            firstObj = false;
        }
        else {
            pickType(currentObj);
        }
        title = ""; type = ""; data = "";
        ingameX=1; ingameY=1; ingameW=1; ingameH=1;
        color = null;
    }

    private static void pickType(ArrayList<String> currentObj){
        for (String line : currentObj){
            if (line.contains("type=")){
                type = line.replace("type=", "");
            }
        }
        if (type.equals("sprite")){
            genShape(currentObj);
        }
        else if (type.equals("text")){
            genText(currentObj);
        }
    }

    private static void genText(ArrayList<String> currentObj){
        String anchor = "";

        for (String line : currentObj){
            checkStandard(line);

            if (line.contains("anchor=")){
                anchor = line.replace("anchor=", "");
            }
        }
    }

    private static void genShape(ArrayList<String> currentObj){

        for (String line : currentObj){
            checkStandard(line);
        }

        if (data.equals("square")){
            Square s = new Square(Main.bkPane, title, ingameX, ingameY, ingameW, ingameH, color);
            Main.sprites.add(s);
        }
        else if (data.equals("circle")){
            Circle s = new Circle(Main.bkPane, title, ingameX, ingameY, ingameW, ingameH, color);
            Main.sprites.add(s);
        }
        else if (data.equals("iconhydrogen")){
            Icon s = new IconHydrogen(Main.bkPane, title, ingameX, ingameY, ingameW, ingameH);
            Main.sprites.add(s);
        }
        else if (data.equals("iconenergy")){
            Icon s = new IconEnergy(Main.bkPane, title, ingameX, ingameY, ingameW, ingameH);
            Main.sprites.add(s);
        }
    }

    private static void checkStandard(String s){
        if (s.contains("[")){
            title = s;
            System.out.println(title);
        }
        else if (s.contains("sprite=") | s.contains("data=")){
            data = s.replace("sprite=", "").replace("data=","");
        }

        else if (s.contains("size=")){
            s = s.replace("size=", "").replace("w", "")
                    .replace("h", "").replace("%", "");
            String[] values = s.split(",");
            try{
                if (!data.contains("icon")){
                    ingameW = Double.parseDouble(values[0]);
                    ingameH = Double.parseDouble(values[1]);
                }
                else{
                    ingameW = Double.parseDouble(values[0]);
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
                System.out.println(values[0]);
                ingameX = Double.parseDouble(values[0]);
                ingameY = Double.parseDouble(values[1]);
            }
            catch (Exception ignored){
            }
        }
        else if (s.contains("color=")){
            color = workColour(s.replace("color=", ""));
        }
    }

    private static void genMain(ArrayList<String> currentObj){
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

    private static Color workColour(String sus){
        if (sus.contains("#")){
            return Color.valueOf(sus);
        }
        else{
            return Color.valueOf("rgb("+sus+")");
        }
    }
}

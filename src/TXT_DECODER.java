import Sprites.*;
import Text.Text;
import javafx.scene.paint.Color;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;


public class TXT_DECODER {
    private static boolean firstLine = true, firstObj = true;
    private static String title = "", type = "", data = ""; //Data could be (sprite, data, text)
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
                String line = l.replace("\n", "");
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
            processObj(currentObj);
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
        String anchor = "center";
        for (String line : currentObj){
            if (line.contains("anchor=")){
                anchor = line.replace("anchor=", "").replace(" ", "");
            }
            else if (line.contains("text=")){
                data = line.replace("text=", "").trim();
            }
            checkStandard(line);
        }

        Text s = new Text(Main.objPane, title, data, anchor, ingameX, ingameY, ingameW, color);
        Main.GuiElements.add(s);
    }

    private static void genShape(ArrayList<String> currentObj){

        for (String line : currentObj){
            checkStandard(line);
        }
        data = data.toLowerCase();

        if (data.equals("square")){
            Square s = new Square(Main.objPane, title, ingameX, ingameY, ingameW, ingameH, color);
            Main.GuiElements.add(s);
        }
        else if (data.equals("circle")){
            Circle s = new Circle(Main.objPane, title, ingameX, ingameY, ingameW, ingameH, color);
            Main.GuiElements.add(s);
        }
        else if (data.equals("iconhydrogen")){
            Icon s = new IconHydrogen(Main.objPane, title, ingameX, ingameY, ingameW, ingameH);
            Main.GuiElements.add(s);
        }
        else if (data.equals("iconenergy")){
            Icon s = new IconEnergy(Main.objPane, title, ingameX, ingameY, ingameW, ingameH);
            Main.GuiElements.add(s);
        }
    }

    private static void checkStandard(String s){
        s = s.replace(" ", "");
        if (s.contains("[")){
            title = s;
        }
        else if (s.contains("sprite=")){
            data = s.replace("sprite=", "");
        }

        else if (s.contains("size=")){
            s = s.replace("size=", "").replace("w", "")
                    .replace("h", "").replace("%", "")
                    .replace(" ", "").toLowerCase();
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
                    .replace("h", "").replace(" ", "").toLowerCase();
            String[] values = s.split(",");
            try{
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
            line = line.replace(" ", "").toLowerCase();
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

package sample;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;
//By:Keeran Srikugan
//Lab07
//Date: March 16, 2021

public class Controller {

    @FXML  public Map<String, Integer> collectData = new TreeMap<>();
    @FXML  public GraphicsContext gc;
    @FXML  private Canvas canvas;

    //Similar to lab 6, here there is an array of colors made to match the pie chart when it is  drawn
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON
    };


    //Here we just initialize anything that need to be initialized such as calling the functions so they can run and etc.
    @FXML
    public void initialize(){
        gc=canvas.getGraphicsContext2D();
        counter();
        drawPie(1000,pieColours);
    }

    //Counter function
    //This function is built off of using the FindAverage zip file done in the lectures
    public void counter() {
        //input file path
        File inputFile = new File("C:/Users/keera/Coding/Java-Second-Year/csci2020u/lab07/weatherwarnings-2015.csv");

        //file manipultion
        try{
            FileReader fileInput = new FileReader(inputFile);
            BufferedReader input = new BufferedReader(fileInput);

            String line;
            while((line = input.readLine()) != null){
                String[] cols = line.split(",");

                //the cols[5] makes it so that is only checks the 6th column and we ignore everything else
                if(collectData.containsKey(cols[5])){
                    int previous = collectData.get(cols[5]);
                    collectData.put(cols[5], previous + 1);
                }else{
                    collectData.put(cols[5],1);
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawPie(int x,Color[]colors){

        //Here we save the values from the tree map all into one array
        int[] valueArray = new int[collectData.size()];
        int count = 0;

        //For statement to loop through all keys in the tree map
        //Website used to help with this: https://stackoverflow.com/questions/1318980/how-to-iterate-over-a-treemap
        for(Map.Entry<String, Integer> entry : collectData.entrySet()){
           valueArray[count]=entry.getValue();
           System.out.println(entry.getKey());
           count++;
        }

        //Below I used the exact same pie function from lab 6 with just the array names changed
        double total = 0;
        int length = valueArray.length;
        //This measures the total amount of slices(arcs) that need to be drawn
        for (int i = 0; i < length; i++) {
            total = total + valueArray[i];
        }

        //We declare the starting variable and keep adding the new angle to it to find the new starting angle
        double start = 0.0;
        for (int i = 0; i < length; i++) {
            //Here we fil the pie and we calculate the new Angle
            gc.setFill(colors[i]);
            double newAngle=360*(valueArray[i]/total);
            gc.fillArc(x/2,0,300,300,start,newAngle,ArcType.ROUND);
            start=start+newAngle;
        }

        //-----------------------------------
        //This part is for the legend of the pie chart
        //-----------------------------------

        //I used k as a counter for the arrays
        int k = 0;

        //The inital variables are the starting position for the rectangles and text
        int initalRect = 50;
        int initalText = 65;

        //Loops through all the key sets int he tree map like from earlier.
        for(Map.Entry<String, Integer> entry : collectData.entrySet()){

            //Here we just draw the rectangle and the text
            gc.setFill(colors[k]);
            gc.setStroke(colors[k]);
            gc.fillText(entry.getKey(), 310,initalText);
            gc.strokeRect(250, initalRect, 30, 20);
            gc.fillRect(250, initalRect, 30, 20);

            //Here all the values get updated so that the next rectangle values can be loaded and calculated
            initalRect = initalRect + 40;
            initalText = initalText + 40;
            k++;
        }
    }
}

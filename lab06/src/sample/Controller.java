package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Controller {
    @FXML  public GraphicsContext gc;
    @FXML  private Canvas canvas;


    //Coding Listing 1(Bar Graph)
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    //Coding listing 2(Pie graph)
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    @FXML
    public void initialize(){
        gc=canvas.getGraphicsContext2D();
        drawPie(1000,purchasesByAgeGroup,pieColours);
        drawBar(avgHousingPricesByYear,avgCommercialPricesByYear,150, 150,350);
    }


    public void drawBar(double [] avgHousing, double [] avgComm,int base,int x, int y){
        //We are going to start by getting the max and min values
        double max = 0.0;
        double min = 0.0;

        //This is where the lengths are saved in a variable to be called later
        int length = avgHousing.length;
        int length2 = avgComm.length;


        for(int i = 0; i < length; i++) {
            if (i == 0) {
                max = avgHousing[i];
                min = avgHousing[i];
            }
            if (avgHousing[i] > max) {
                max = avgHousing[i];
            }

            if (avgHousing[i] < min) {
                min = avgHousing[i];
            }
        }

        for(int i = 0; i < length2; i++) {
            if (avgComm[i] > max) {
                max = avgComm[i];
            }

            if (avgComm[i] < min) {
                min = avgComm[i];
            }
        }

        //Here we draw the bar graph

        //This is the bars for the average housing price
        //First I calculate the spacing for my graph:
        double barSpacing = x/ length;
        double startingPoint = 150;
        double barLength;
        gc.setFill(Color.RED);
        for(int i= 0; i < length; i++){
            //The line below calculates the length of the bar before it is drawn
            barLength = (avgHousing[i]/max) * y;

            //The code below fills the bar with the color chosen up above
            gc.fillRect(startingPoint - 100,(y-barLength) + 20, barSpacing,barLength + 20);
            startingPoint = startingPoint +(barSpacing*2.5);
        }

        //This is the bars for the average housing price
        startingPoint = 150 + barSpacing;
        gc.setFill(Color.BLUE);
        for(int i= 0; i < length2; i++){
            barLength = (avgComm[i]/max) * y;
            gc.fillRect(startingPoint - 100,(y-barLength) + 20, barSpacing,barLength + 20);
            startingPoint = startingPoint +(barSpacing*2.5);
        }

    }

    //This is the function to draw the pie
    //Site used as reference to do this : https://www.tutorialspoint.com/javaexamples/gui_piechart.htm
    public void drawPie(int x, int[]purchases,Color[]colors){


        double total = 0;
        int length = purchases.length;
        //This measures the total amount of slices(arcs) that need to be drawn
        for (int i = 0; i < length; i++) {
            total = total + purchases[i];
        }

        //We declare the starting variable and keep adding the new angle to it to find the new starting angle
        double start = 0.0;
        for (int i = 0; i < length; i++) {
            //Here we fil the pie and we calculate the new Angle
            gc.setFill(colors[i]);
            double newAngle=360*(purchases[i]/total);
            gc.fillArc(x/2,0,300,300,start,newAngle,ArcType.ROUND);
            start=start+newAngle;
        }
    }
}


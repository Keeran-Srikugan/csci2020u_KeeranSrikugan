package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

//Name: Keeran Srikugan
//Date: March 30, 2021
//Lab 09

public class Controller {
    @FXML Canvas canvas;
    private GraphicsContext gc;
    private final ArrayList<Float> googleList = new ArrayList<>();
    private final ArrayList<Float> appleList = new ArrayList<>();


    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        drawLinePlot(googleList,appleList);
    }

    private ArrayList<Float> downloadStockPrices(String ticker) {
        ArrayList<Float> list = new ArrayList<>();
        try{
            String sURL = "https://query1.finance.yahoo.com/v7/finance/download/"+ticker+"?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true";
            URL netURL = new URL(sURL);

            URLConnection conn = netURL.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);

            InputStream inStream = conn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inStream));

            String line;
            int count = 0;
            while ((line = in.readLine()) != null) {
                if(count > 1){
                    float number = Float.parseFloat(line.split(",")[4]);
                    list.add(number);
                }
                count++;
            }
            inStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public void drawLinePlot(ArrayList<Float> googleList, ArrayList<Float> appleList) {
        //x and y axis
        gc.setStroke(Color.BLACK);
        gc.strokeLine(100, 50, 100, 600);

        gc.setStroke(Color.BLACK);
        gc.strokeLine(100, 600,750, 600);

        googleList = downloadStockPrices("GOOG");
        appleList = downloadStockPrices("AAPL");
        double xStart = 100;
        double yStart = 700;

        double xStart2 = 100;
        double yStart2 = 575;

        float xSpace = 10;
        float ySpace = 25;

        for (int i = 0; i < googleList.size(); i++) {
            try{
                gc.setStroke(Color.RED);
                plotLine(xStart,yStart - googleList.get(i) + ySpace,xStart + xSpace,yStart - googleList.get(i+1) + ySpace);

                gc.setStroke(Color.BLUE);
                plotLine(xStart2,yStart2 - appleList.get(i) + ySpace,xStart2 + xSpace,yStart2 - appleList.get(i+1) + ySpace);
            }
            catch(IndexOutOfBoundsException e){
                //e.printStackTrace();
            }
            xStart += xSpace;
            xStart2 += xSpace;
        }
    }

    //draws a line
    public void plotLine(double x1, double y1, double x2, double y2 ){
        gc.strokeLine(x1,y1,x2,y2);
    }
}
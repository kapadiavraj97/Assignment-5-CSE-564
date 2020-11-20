import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Controller implements ActionListener {
    Canvas canvas;
    JFrame frame;
    List<Integer> x = new ArrayList<>();
    List<Integer> y = new ArrayList<>();
    List<Integer> x_normalised = new ArrayList<>();
    List<Integer> y_normalised = new ArrayList<>();
    public Controller(){
        canvas = new Canvas();
        canvas.aboutMenu.addActionListener(this);
        canvas.open.addActionListener(this);
        canvas.save.addActionListener(this);
        canvas.runProject.addActionListener(this);
        frame = new JFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==canvas.aboutMenu){
           canvas.dialog.setVisible(true);
        }
        else if(e.getSource()==canvas.open){
           String filename = chooseFile();
            readFile(filename);
            x_scale(x);
            y_scale(y);
            Repository.createInstance().addCoordinates(x_normalised,y_normalised);
            canvas.repaint();
        }
        else if(e.getSource()==canvas.save){
            createFile();
            saveFile(Repository.createInstance().getX_coordinates(), Repository.createInstance().getY_coordinates());
        }
        else if(e.getSource()==canvas.runProject){
        	LinkedList<City> cities = new LinkedList<City>();
        	LinkedList<City> cities2 = new LinkedList<City>();
        	LinkedList<City> cities3 = new LinkedList<City>();
    		for(int i=0;i<x_normalised.size();i++) {
                int xCoordinate = x_normalised.get(i);
                int yCoordinate = y_normalised.get(i);
                cities.add(new City(xCoordinate,yCoordinate));
            }
    		cities2 = cities;
    		cities3 = cities;
    		NearestNeighbour nn = new NearestNeighbour();
        	Route route1 = new Route(cities);
//        	Route route2 = new Route(cities);
//        	Route route3 = new Route(cities);
        	route1 = nn.findShortestRoute(cities,5);
        	System.out.println(cities2);
//        	route2 = nn.findShortestRoute(cities,0);
//        	route3 = nn.findShortestRoute(cities,0);
        	Graphics g = canvas.getGraphics();
        	try {
				canvas.drawlines(g, route1);
//				canvas.drawlines2(g, route2);
//				canvas.drawlines3(g, route3);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
        }
    }

    private String chooseFile() {
        String fileName = "";
        try {
            JFileChooser chosenFile = new JFileChooser();
            int showOpenDialog = chosenFile.showOpenDialog(null);
            if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
                fileName = chosenFile.getSelectedFile().getAbsolutePath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public void readFile(String filename){
        int i=1,j=0;
        try{
            File f1 = new File(filename);
            Scanner scanner = new Scanner(f1);
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                if(i>7 && !data.equals("EOF")) {
                    String str[]=data.split(" ");
                    x.add((int)Double.parseDouble(str[1]));
                    y.add((int)Double.parseDouble(str[2]));
                    i++;
                    j++;
                }
                else
                    i++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void x_scale(List<Integer> list){
        int padding = 20;
        int labelPadding = 12;
        double xScale = (canvas.getWidth() - (3 * padding) - labelPadding) / (list.size() - 1);
        for(int i = 0; i< list.size(); i++) {
            double x_coord = ((list.get(i) / getMaxScore(list)) + i * xScale + padding + labelPadding);
            this.x_normalised.add((int)x_coord);
        }
    }

    public void y_scale(List<Integer> list){
        int padding = 20;
        int labelPadding = 12;
        double yScale = (canvas.getHeight() - (2 * padding) - labelPadding) / (getMaxScore(list) - getMinScore(list));
        for(int i = 0; i<list.size(); i++) {
            double y_coord = ((getMaxScore(list) - list.get(i)) * yScale + padding);
            this.y_normalised.add((int)y_coord);
        }
    }

    private double getMinScore(List<Integer> data) {
        int minScore = Integer.MAX_VALUE;
        for (Integer score : data) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }

    private double getMaxScore(List<Integer> data) {
        int maxScore = Integer.MIN_VALUE;
        for (Integer score : data) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

    public void createFile(){
        try {
            File myObj = new File("filename.txt");
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void saveFile(List<Integer> x, List<Integer> y){
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("filename.txt"));
            for(int i=0;i<x.size();i++){
                myWriter.write(i+"  " + x.get(i) + "  " + y.get(i) );
                myWriter.write(System.getProperty( "line.separator" ));
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

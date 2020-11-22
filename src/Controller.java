import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller implements ActionListener, Runnable{
    private Canvas canvas;
    List<Integer> x = new ArrayList<>();
    List<Integer> y = new ArrayList<>();
    List<Integer> x_normalised = new ArrayList<>();
    List<Integer> y_normalised = new ArrayList<>();
    TSPCalculation tspCalculation;
    public Controller(){
        canvas = new Canvas();
        tspCalculation = new TSPCalculation();
        canvas.getAboutMenu().addActionListener(this);
        canvas.getOpen().addActionListener(this);
        canvas.getSave().addActionListener(this);
        canvas.getRunProject().addActionListener(this);
        canvas.getNewProject().addActionListener(this);
        canvas.getStopProject().addActionListener(this);
        canvas.t = new Thread(this);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==canvas.getAboutMenu()){
            canvas.getDialog().setVisible(true);
        }
        else if(e.getSource()==canvas.getOpen()){
            String filename = chooseFile();
            readFile(filename);
            x_scale(x);
            y_scale(y);
            Repository.createInstance().addCoordinates(x_normalised,y_normalised);
            canvas.repaint();
        }
        else if(e.getSource()==canvas.getSave()){
            //createFile();
            String filename = chooseFile();
            saveFile(Repository.createInstance().getX_coordinates(), Repository.createInstance().getY_coordinates(),filename);
        }
        else if(e.getSource()==canvas.getRunProject()){
            tspCalculation.tsp(tspCalculation.distanceCalculation(Repository.createInstance().getX_coordinates(),Repository.createInstance().getY_coordinates()));
            Repository.createInstance().setRoute1(tspCalculation.getRouteNodes().get(tspCalculation.getCost().get(0).get(1)));
            Repository.createInstance().setRoute2(tspCalculation.getRouteNodes().get(tspCalculation.getCost().get(1).get(1)));
            Repository.createInstance().setRoute3(tspCalculation.getRouteNodes().get(tspCalculation.getCost().get(2).get(1)));
            if(canvas.t.isAlive()){
                canvas.t.resume();
            }
            else{
                canvas.t.start();
            }
        }
        else if(e.getSource()==canvas.getNewProject()){
            Repository.createInstance().getX_coordinates().clear();
            Repository.createInstance().getY_coordinates().clear();
            canvas.t.stop();
            canvas.repaint();
        }
        else if(e.getSource() == canvas.getStopProject()){
            canvas.t.suspend();
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

    public void saveFile(List<Integer> x, List<Integer> y,String filename){
        try {
            FileWriter myWriter = new FileWriter(filename);
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

    @Override
    public void run() {
        try{
            Graphics g1 = canvas.getGraphics();
            Graphics g2 = canvas.getGraphics();
            Graphics g3 = canvas.getGraphics();
            g1.setColor(Color.red);
            g2.setColor(Color.blue);
            g3.setColor(Color.green);
            for (int i = 0; i < Repository.getRoute1().size() - 1; i++) {
                int x1 = Repository.createInstance().getX_coordinates().get(Repository.getRoute1().get(i));
                int y1 = Repository.createInstance().getY_coordinates().get(Repository.getRoute1().get(i));
                int x2 = Repository.createInstance().getX_coordinates().get(Repository.getRoute1().get(i + 1));
                int y2 = Repository.createInstance().getY_coordinates().get(Repository.getRoute1().get(i + 1));
                int x3 = Repository.createInstance().getX_coordinates().get(Repository.getRoute2().get(i));
                int y3 = Repository.createInstance().getY_coordinates().get(Repository.getRoute2().get(i));
                int x4 = Repository.createInstance().getX_coordinates().get(Repository.getRoute2().get(i + 1));
                int y4 = Repository.createInstance().getY_coordinates().get(Repository.getRoute2().get(i + 1));
                int x5 = Repository.createInstance().getX_coordinates().get(Repository.getRoute3().get(i));
                int y5 = Repository.createInstance().getY_coordinates().get(Repository.getRoute3().get(i));
                int x6 = Repository.createInstance().getX_coordinates().get(Repository.getRoute3().get(i + 1));
                int y6 = Repository.createInstance().getY_coordinates().get(Repository.getRoute3().get(i + 1));
                g1.drawLine(x1, y1, x2, y2);
                g2.drawLine(x3, y3, x4, y4);
                g3.drawLine(x5,y5,x6,y6);
                canvas.t.sleep(1000);
            }
            canvas.flag = true;
            canvas.repaint();
        }catch (Exception e){}

    }
}

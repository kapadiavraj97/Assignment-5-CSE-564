import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

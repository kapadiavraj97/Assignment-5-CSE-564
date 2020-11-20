import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Canvas extends JPanel implements MouseListener {
    Vector v = new Vector();
    JMenu file;
    JMenu project;
    JDialog dialog;
    public JMenu about;
    Route route;
    JMenuItem open, save, newProject, runProject, stopProject, aboutMenu;
    JLabel l = new JLabel("About Us: This project is developed by Vraj, Aihaab and Deepti.");

    public JMenu getFile() {
        return file;
    }

    public JMenu getProject() {
        return project;
    }

    public JMenu getAbout() {
        return about;
    }

    public Canvas(){
        file= new JMenu("File");
        project= new JMenu("Project");
        about= new JMenu("About");
        open= new JMenuItem("Open");
        save= new JMenuItem("Save");
        newProject = new JMenuItem("New");
        runProject = new JMenuItem("Run");
        stopProject = new JMenuItem("Stop");
        aboutMenu = new JMenuItem("About");
        dialog= new JDialog();
        dialog.setBounds(132, 132, 350, 300);
        dialog.add(l);
        file.add(open);
        file.add(save);
        project.add(newProject);
        project.add(runProject);
        project.add(stopProject);
        about.add(aboutMenu);
        add(file);
        addMouseListener(this);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        g.setColor(Color.BLUE);
        List<Integer> x = Repository.createInstance().getX_coordinates();
        List<Integer> y = Repository.createInstance().getY_coordinates();
        for(int i=0;i<x.size();i++) {
            int xCoordinate = x.get(i);
            int yCoordinate = y.get(i);
            g.drawOval(xCoordinate, yCoordinate, 5, 5);
        }
    }
    
    public void drawlines(Graphics g, Route route) throws InterruptedException {
    	Graphics2D g2 = (Graphics2D) g;
    	g.setColor(Color.RED);
        for (int i = 0; i+1 <= route.cities.size()-1; i++) {
            int x1 = route.cities.get(i).getX();
            int y1 = route.cities.get(i).getY();
            int x2 = route.cities.get(i+1).getX();
            int y2 = route.cities.get(i+1).getY();
            TimeUnit.SECONDS.sleep(1);
            g2.draw(new Line2D.Double(x1,y1, x2, y2));
        }
        
    }
    public void drawlines2(Graphics g, Route route) throws InterruptedException {
    	Graphics2D g2 = (Graphics2D) g;
    	g.setColor(Color.PINK);
        for (int i = 0; i+1 <= route.cities.size()-1; i++) {
            int x1 = route.cities.get(i).getX();
            int y1 = route.cities.get(i).getY();
            int x2 = route.cities.get(i+1).getX();
            int y2 = route.cities.get(i+1).getY();
            TimeUnit.SECONDS.sleep(1);
            g2.draw(new Line2D.Double(x1,y1, x2, y2));
        }
        
    }
    public void drawlines3(Graphics g, Route route) throws InterruptedException {
    	Graphics2D g2 = (Graphics2D) g;
    	g.setColor(Color.GRAY);
        for (int i = 0; i+1 <= route.cities.size()-1; i++) {
            int x1 = route.cities.get(i).getX();
            int y1 = route.cities.get(i).getY();
            int x2 = route.cities.get(i+1).getX();
            int y2 = route.cities.get(i+1).getY();
            TimeUnit.SECONDS.sleep(1);
            g2.draw(new Line2D.Double(x1,y1, x2, y2));
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Repository.createInstance().getX_coordinates().add(x);
        Repository.createInstance().getY_coordinates().add(y);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

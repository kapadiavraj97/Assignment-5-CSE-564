import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

public class Canvas extends JPanel implements MouseListener {
    Vector v = new Vector();
    JMenu file;
    JMenu project;
    JDialog dialog;
    public JMenu about;
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
        super.paint(g);
        int width = getWidth();
        g.setColor(Color.BLUE);
        List<Integer> x = Repository.createInstance().getX_coordinates();
        List<Integer> y = Repository.createInstance().getY_coordinates();
        for(int i=0;i<x.size();i++) {
            int xCoordinate = x.get(i);
            int yCoordinate = y.get(i);
            g.drawOval(xCoordinate, yCoordinate, 10, 10);
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

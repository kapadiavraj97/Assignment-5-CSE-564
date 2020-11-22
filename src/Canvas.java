import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;


public class Canvas extends JPanel implements MouseListener, Observer{
    private JMenu file;
    private JMenu project;
    private JDialog dialog;
    private JMenu about;
    private JMenuItem open, save, newProject, runProject, stopProject, aboutMenu;
    private JLabel l = new JLabel("This project is developed by Vraj, Aihaab and Deepti.");
    boolean flag = false;
    Thread t;

    public JDialog getDialog() {
        return dialog;
    }

    public JMenuItem getOpen() {
        return open;
    }

    public JMenuItem getSave() {
        return save;
    }

    public JMenuItem getNewProject() {
        return newProject;
    }

    public JMenuItem getRunProject() {
        return runProject;
    }

    public JMenuItem getStopProject() {
        return stopProject;
    }

    public JMenuItem getAboutMenu() {
        return aboutMenu;
    }

    public JLabel getL() {
        return l;
    }

    public boolean isFlag() {
        return flag;
    }

    public Thread getT() {
        return t;
    }

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
        l.setSize(20,20);
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
        try {
            super.paintComponent(g);
            int width = getWidth();
            g.setColor(Color.BLUE);
            List<Integer> x = Repository.createInstance().getX_coordinates();
            List<Integer> y = Repository.createInstance().getY_coordinates();
            for (int i = 0; i < x.size(); i++) {
                int xCoordinate = x.get(i);
                int yCoordinate = y.get(i);
                g.drawOval(xCoordinate, yCoordinate, 5, 5);
            }
            if (flag == true) {
                g.setColor(Color.red);
                for (int i = 0; i < Repository.getRoute1().size(); i++) {
                    int x1 = Repository.createInstance().getX_coordinates().get(Repository.getRoute1().get(i));
                    int y1 = Repository.createInstance().getY_coordinates().get(Repository.getRoute1().get(i));
                    int x2 = Repository.createInstance().getX_coordinates().get(Repository.getRoute1().get(i + 1));
                    int y2 = Repository.createInstance().getY_coordinates().get(Repository.getRoute1().get(i + 1));
                    g.drawLine(x1, y1, x2, y2);
                }
            }
        }catch(Exception e){}
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

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

}

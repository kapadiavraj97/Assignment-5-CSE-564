import javax.swing.*;

public class Canvas extends JPanel {
    JMenu file;
    JMenu project;
    JDialog dialog;
    public JMenu about;
    JMenuItem open, save, newProject, runProject, stopProject, aboutMenu;
    JLabel l = new JLabel("This project is developed by Vraj, Aihaab and Deepti.");

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
    }
}

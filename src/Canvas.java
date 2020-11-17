import javax.swing.*;

public class Canvas extends JPanel {
    public JMenu file;
    public JMenu project;

    public JMenu getFile() {
        return file;
    }

    public JMenu getProject() {
        return project;
    }

    public JMenu getAbout() {
        return about;
    }

    public JMenu about;
    JMenuItem open, save, newProject, runProject, stopProject, aboutMenu;
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
        file.add(open);
        file.add(save);
        project.add(newProject);
        project.add(runProject);
        project.add(stopProject);
        about.add(aboutMenu);
        add(file);
    }
}

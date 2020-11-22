import javax.swing.*;

public class Main extends JFrame {
    public Main(){
        Controller controller = new Controller();
        JMenuBar jMenuBar = new JMenuBar();
        JDialog aboutDialog = new JDialog(this,"dialog box");
        JLabel l = new JLabel("About team");
        jMenuBar.add(controller.getCanvas().getFile());
        jMenuBar.add(controller.getCanvas().getProject());
        jMenuBar.add(controller.getCanvas().getAbout());
        setJMenuBar(jMenuBar);
        add(controller.getCanvas());
        setSize(1200,1000);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String args[]){
        Main main = new Main();
    }
}

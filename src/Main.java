import javax.swing.*;

public class Main extends JFrame {
    public Main(){
        Controller controller = new Controller();
        JMenuBar jMenuBar = new JMenuBar();
        JDialog aboutDialog = new JDialog(this,"dialog box");
        JLabel l = new JLabel("About team");
        jMenuBar.add(controller.canvas.getFile());
        jMenuBar.add(controller.canvas.getProject());
        jMenuBar.add(controller.canvas.getAbout());
        setJMenuBar(jMenuBar);
        add(controller.canvas);
        setSize(1200,1000);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String args[]){
        Main main = new Main();
    }
}

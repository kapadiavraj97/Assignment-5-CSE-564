import javax.swing.*;

public class Main extends JFrame {
    public Main(){
        Canvas canvas = new Canvas();
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(canvas.getFile());
        jMenuBar.add(canvas.getProject());
        jMenuBar.add(canvas.getAbout());
        setJMenuBar(jMenuBar);
        add(canvas);
        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String args[]){
        Main main = new Main();
    }
}

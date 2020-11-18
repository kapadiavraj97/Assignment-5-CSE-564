import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    Canvas canvas;
    public Controller(){
        canvas = new Canvas();
        canvas.aboutMenu.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==canvas.aboutMenu){
            canvas.dialog.setVisible(true);
        }

    }
}

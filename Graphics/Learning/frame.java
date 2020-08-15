import javax.swing.*;

public class frame extends JFrame{

    graphics graph = new graphics();

    public frame() {
        this.setSize(420, 420);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(graph);
        this.setVisible(true);
    }

}
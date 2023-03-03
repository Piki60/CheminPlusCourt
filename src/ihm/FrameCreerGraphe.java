package ihm;

import javax.swing.JFrame;

public class FrameCreerGraphe extends JFrame
{

    public FrameCreerGraphe()
    {
        this.setTitle("Créer un graphe");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
}

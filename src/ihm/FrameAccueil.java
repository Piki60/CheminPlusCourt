package ihm;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameAccueil extends JFrame
{
    private JPanel panelAccueil;
    private JButton btnCreerGraphe;
    private JButton btnChargerGraphe;

    public FrameAccueil()
    {
        this.setTitle("Accueil");
        this.setSize(500, 500);

        this.panelAccueil = new JPanel();
        this.panelAccueil.setBackground(new Color(216,216,216));
        this.panelAccueil.setLayout(null);

        this.btnCreerGraphe = new JButton("Créer un graphe");
        this.btnCreerGraphe.setBackground(new Color(0, 151, 178));
        this.btnCreerGraphe.setBounds(100, 180, 300, 50);

        this.btnChargerGraphe = new JButton("Charger un graphe");
        this.btnChargerGraphe.setBackground(new Color(0, 151, 178));
        this.btnChargerGraphe.setBounds(100, 240, 300, 50);


        this.panelAccueil.add(this.btnCreerGraphe);
        this.panelAccueil.add(this.btnChargerGraphe);

        this.add(this.panelAccueil);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        FrameAccueil frameAccueil = new FrameAccueil();
    }
}

package ihm;

import controleur.Controleur;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameAccueil extends JFrame implements ActionListener
{
    private Controleur ctrl;
    private JPanel panelAccueil;
    private JButton btnCreerGraphe;
    private JButton btnChargerGraphe;

    public FrameAccueil(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle("Accueil");
        this.setSize(500, 500);
        setLocationRelativeTo(null);

        // Panel Accueil
        this.panelAccueil = new JPanel();
        this.panelAccueil.setBackground(new Color(216,216,216));
        this.panelAccueil.setLayout(null);

        // Bouton Créer un graphe
        this.btnCreerGraphe = new JButton("Créer un graphe");
        this.btnCreerGraphe.setBackground(new Color(0, 151, 178));
        this.btnCreerGraphe.setBounds(100, 180, 300, 50);

        // Bouton Charger un graphe
        this.btnChargerGraphe = new JButton("Charger un graphe");
        this.btnChargerGraphe.setBackground(new Color(0, 151, 178));
        this.btnChargerGraphe.setBounds(100, 240, 300, 50);

        // Ajout des composants au panel
        this.panelAccueil.add(this.btnCreerGraphe);
        this.panelAccueil.add(this.btnChargerGraphe);

        // Ajout du panel à la frame
        this.add(this.panelAccueil);

        // Ajout des listeners
        this.btnCreerGraphe.addActionListener(this);
        this.btnChargerGraphe.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        new FrameAccueil(new Controleur());
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == this.btnCreerGraphe)
        {
            new FrameCreerGraphe( ctrl);
            this.dispose();
        }
        else if(e.getSource() == this.btnChargerGraphe)
        {
            new FrameChargerGraphe();
            this.dispose();
        }
    }
}

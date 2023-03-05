package ihm;

import controleur.Controleur;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class FrameCreerGraphe extends JFrame
{
    private Controleur ctrl;
    private PanelFormulaire panelFormulaire;
    private PanelGraphe panelGraphe;

    public FrameCreerGraphe(Controleur ctrl)
    {
        this.ctrl = ctrl;
        
        this.setTitle("graphe");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout());

        // Panel Formulaire
        this.panelFormulaire = new PanelFormulaire(ctrl);
        this.panelGraphe = new PanelGraphe(ctrl);

        // Ajout du panel à la frame
        this.add(this.panelFormulaire, BorderLayout.WEST);
        this.add(this.panelGraphe, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
}

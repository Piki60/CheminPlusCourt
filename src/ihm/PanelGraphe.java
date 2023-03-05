package ihm;

import controleur.Controleur;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel; 

public class PanelGraphe extends JPanel
{
    public PanelGraphe(Controleur ctrl)
    {
        this.setBackground(new Color(216,216,216));
        this.setLayout(new BorderLayout());
    }
}


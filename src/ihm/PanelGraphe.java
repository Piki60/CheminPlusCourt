package ihm;

import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;

import controleur.Controleur;
import metier.Arete;
import metier.Noeud;
public class PanelGraphe extends JPanel
{
    private Controleur ctrl;
    private List<Noeud> lstNoeuds;
    private List<Arete> lstAretes;

    private Noeud selectionNoeud;

    public PanelGraphe(Controleur ctrl)
    {
        this.setBackground(new Color(216,216,216));
        



    }
}


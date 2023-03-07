package controleur;

import java.io.File;
import java.util.List;

import ihm.FrameAccueil;

import metier.Metier;
import metier.Noeud;
import metier.Arete;

public class Controleur 
{
    private Metier metier;
    private FrameAccueil ihm;
    
    public Controleur()
    {
        this.metier = new Metier();
        this.ihm = new FrameAccueil(this);
    }

    public void sauvegarder(File file) { metier.ecrireXML(file);}
    public void lireXML  (File file) { metier.lireXML  (file);}

    public void ajouterNoeud(int x, int y)
    {
        metier.ajouterNoeud(new Noeud(x, y));
    }

    public void ajouterArete(Noeud noeud1, Noeud noeud2, int cout)
    {
        metier.ajouterArete(new Arete(noeud1, noeud2, cout));
    }

    public void supprimerNoeud(Noeud noeud) { metier.supprimerNoeud(noeud); }
    public void supprimerArete(Arete arete) { metier.supprimerArete(arete); }

    public List<Noeud> getAlNoeuds() { return metier.getAlNoeuds(); }
    public List<Arete> getAlAretes() { return metier.getAlAretes(); }

    public static void main(String[] args)
    {
        new Controleur();
    }

    public void modifierNoeud(char id, int x, int y) 
    {
        metier.modifierNoeud(id, x, y);
    }

    public void modifierArete(Noeud n1, Noeud n2, int cout) 
    {
        metier.modifierArete(n1, n2, cout);
    }


    public void setPosNoeud(Noeud selectedNode, int startX, int startY) 
    {
        metier.setPosNoeud(selectedNode, startX, startY);
    }
   
}

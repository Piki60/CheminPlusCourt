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

    //XML
    public void sauvegarder (File file) { metier.ecrireXML(file);}
    public void lireXML     (File file) { metier.lireXML  (file);}

    //Noeud
    public void ajouterNoeud    (char nom,int x, int y )         { metier.ajouterNoeud(new Noeud(nom, x, y)); }
    public void modifierNoeud   (int id, char nom, int x, int y) { metier.modifierNoeud(id, nom, x, y); }
    public void supprimerNoeud  (Noeud noeud)           { metier.supprimerNoeud(noeud); }

    //Arete
    public void ajouterArete    (Noeud noeud1, Noeud noeud2, int cout) { metier.ajouterArete(new Arete(noeud1, noeud2, cout));}    
    public void modifierArete   (int id, int nouveauCout)              { metier.modifierArete(id, nouveauCout);  }
    public void supprimerArete  (Arete arete)                          { metier.supprimerArete(arete); }

    //Getters
    public List<Noeud> getAlNoeuds() { return metier.getAlNoeuds(); }
    public List<Arete> getAlAretes() { return metier.getAlAretes(); }


    public void setPosNoeud(Noeud selectedNode, int startX, int startY) 
    {
        metier.setPosNoeud(selectedNode, startX, startY);
    }

    public static void main(String[] args)
    {
        new Controleur();
    }

    

   


    
   
}

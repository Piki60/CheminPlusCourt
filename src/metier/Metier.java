package metier;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;

/*import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
*/
import java.io.File;

public class Metier 
{
    private List<Noeud> alNoeuds; 
    private List<Arete> alAretes;
    
    public Metier()
    {
        alNoeuds = new ArrayList<Noeud>();
        alAretes = new ArrayList<Arete>();
    }

    public boolean ajouterNoeud(Noeud n)
    {
        if (n == null || alNoeuds.contains(n))
            return false;

        alNoeuds.add(n);
        return true;
    }

    public boolean ajouterArete(Arete a)
    {
        // Si l'arête est nulle ou si les noeuds de l'arête ne sont pas dans la liste des noeuds
        if (a == null || !alNoeuds.contains(a.getNoeud1()) && !alNoeuds.contains(a.getNoeud2()))
            return false;

        // Si il existe déjà une arête entre les deux noeuds
        if (alAretes.size() != 0)
        {
            for (Arete arete : alAretes)
                if (arete.getNoeud1().getId() == a.getNoeud1().getId() && arete.getNoeud2().getId() == a.getNoeud2().getId())
                    return false;
        }
        
        alAretes.add(a);
        return true;
    }

    // Getters
    public List<Noeud> getAlNoeuds() {return alNoeuds;}
    public List<Arete> getAlAretes() {return alAretes;}

    // méthode pour lire un fichier texte qui importe directement les noeuds et les arêtes
    /*public  void lireFichier(File file)
    {
        Document doc = null;
        Element racine;

        SAXBuilder sxb = new SAXBuilder();

        try
        {
            doc = sxb.build(file);
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de la lecture du fichier");
        }

        racine = doc.getRootElement();

        
    }*/

    public void ecrireXML(File file)
    {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        //String 
    }








}

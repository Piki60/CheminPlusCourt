package metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FileWriter;

public class Metier 
{
    private List<Noeud> alNoeuds; 
    private List<Arete> alAretes;

    private int[] tabDistances;
    
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
        System.out.println(n.toString());
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

    public boolean supprimerNoeud(Noeud n)
    {
        if (n == null || !alNoeuds.contains(n))
            return false;

        for (int i = 0; i < alAretes.size(); i++) 
        {
            Arete a = alAretes.get(i);
            if (a.getNoeud1() == n || a.getNoeud2() == n) 
            {
                alAretes.remove(i);
                i--;
            }
        }

        alNoeuds.remove(n);

        return true;

    }

    public boolean supprimerArete(Arete a)
    {
        if (a == null || !alAretes.contains(a))
            return false;
        
        alAretes.remove(a);
        return true;
    }

    // Getters
    public List<Noeud> getAlNoeuds() {return alNoeuds;}
    public List<Arete> getAlAretes() {return alAretes;}

    public int[] getTabDistances() {return tabDistances;}

    public void ecrireXML(File file)
    {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        String tab = "\t";

        xml += "<graphe>\n";
        xml += tab + "<noeuds>\n";
        tab += "\t";

        for(Noeud n : alNoeuds)
        {
            xml += tab + "<noeud id=\"" + n.getId() + "\"> \n";

            tab += "\t";
            xml += tab + "<nom>" + n.getNom() + "</nom>\n";
            xml += tab + "<x>" + n.getX() + "</x>\n";
            xml += tab + "<y>" + n.getY() + "</y>\n";

            tab = tab.substring(0, tab.length() - 1);

            xml += tab + "</noeud>\n";
        }

        tab = tab.substring(0, tab.length() - 1);
        xml += tab + "</noeuds>\n\n";

        xml += tab + "<aretes>\n";

        tab += "\t";

        for(Arete a : alAretes)
        {
            xml += tab + "<arete id=\"" + a.getId() + "\"> \n";
            tab += "\t";

            xml += tab + "<noeud1>" + a.getNoeud1().getId() + "</noeud1>\n";
            xml += tab + "<noeud2>" + a.getNoeud2().getId() + "</noeud2>\n";
            xml += tab + "<cout>" + a.getCout() + "</cout>\n";

            tab = tab.substring(0, tab.length() - 1);

            xml += tab + "</arete>\n";
        }

        tab = tab.substring(0, tab.length() - 1);
        xml += tab + "</aretes>\n";

        xml += "</graphe>\n";

        try {

            FileWriter fw = new FileWriter(file);
            fw.write(xml);
            fw.close();

        } catch (Exception e) 
            {System.out.println("Erreur lors de l'écriture du fichier");}


    }

    public void lireXML(File file)
    {
        Map<Integer, Noeud> mapNoeuds = new HashMap<Integer, Noeud>();
        
        Document document = null;
        Element racine;

        SAXBuilder sxb = new SAXBuilder();
        try
        {
            document = sxb.build(file);
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de la lecture du fichier");
        }

        racine = document.getRootElement();

        Element noeuds = racine.getChild("noeuds");

        for (Element noeud : noeuds.getChildren())
        {
            int id = Integer.parseInt(noeud.getAttributeValue("id"));

            char nom = noeud.getChildText("nom").charAt(0);
            int x = Integer.parseInt(noeud.getChildText("x"));
            int y = Integer.parseInt(noeud.getChildText("y"));

            ajouterNoeud(new Noeud(nom, x, y));

            mapNoeuds.put(id, getNoeud(id));
            
        }

        Element aretes = racine.getChild("aretes");

        for (Element arete : aretes.getChildren())
        {
            int idNoeud1 = Integer.parseInt(arete.getChildText("noeud1"));
            int idNoeud2 = Integer.parseInt(arete.getChildText("noeud2"));

            Noeud noeud1 = this.getNoeud(idNoeud1);
            Noeud noeud2 = this.getNoeud(idNoeud2);

            int cout = Integer.parseInt(arete.getChildText("cout"));

            ajouterArete(new Arete(noeud1, noeud2, cout));


        }
    }

    public Noeud getNoeud(int id)
    {
        for (Noeud n : alNoeuds)
            if (n.getId() == id)
                return n;

        return null;
    }

    public void modifierNoeud(int id, char nom, int x, int y) 
    {
        for (Noeud n : alNoeuds)
            if (n.getId() == id)
            {
                n.setNom(nom);
                n.setX(x);
                n.setY(y);
            }
    }

    public void modifierArete(int id, int cout) 
    {
        for (Arete a : alAretes)
        {
            if (a.getId() == id)
            {
                a.setCout(cout);
                return;
            }
        }
    }

    public void setPosNoeud(Noeud selectedNode, int startX, int startY) 
    {
        for (Noeud n : alNoeuds)
            if (n.getId() == selectedNode.getId())
            {
                n.setX(startX);
                n.setY(startY);
            }
    }

}
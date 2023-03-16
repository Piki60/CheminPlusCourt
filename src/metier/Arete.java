package metier;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

public class Arete
{
    private static int idAretes=0;

    private int id = 0;

    private Noeud noeud1;
    private Noeud noeud2;
    private int cout;

    public Arete(Noeud noeud1, Noeud noeud2, int cout)
    {
        this.noeud1 = noeud1;
        this.noeud2 = noeud2;
        this.cout = cout;

        this.id = idAretes++;
    }

    // Getters
    public int      getId       () { return id;     }
    public Noeud    getNoeud1   () { return noeud1; }
    public Noeud    getNoeud2   () { return noeud2; }
    public int      getCout     () { return cout;   }   

    // Setters
    public void setnoeud1(Noeud noeud1) { this.noeud1 = noeud1; }
    public void setnoeud2(Noeud noeud2) { this.noeud2 = noeud2; }
    public void setCout  (int cout)     { this.cout   = cout;   }

    @Override
    public String toString()
    {
        return "Arête " + id + " : " + noeud1.getNom() + " =>" + noeud2.getNom() + " = " + cout;
    }

    

}
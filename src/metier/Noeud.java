package metier;

public class Noeud 
{
    private static int idNoeuds = 0;

    private int id;
    
    private char nom; 

    private int x;
    private int y;

    public Noeud(char nom, int x, int y) 
    {
        this.nom = nom;

        this.x = x;
        this.y = y;

        this.id = idNoeuds++;
    }

    // Getters

    public int getId   () { return id;}
    public char getNom () { return nom; }
    public int  getX    () { return x; }
    public int  getY    () { return y; }

    // Setters

    public void setNom(char nom) { this.nom = nom; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setXY(int x, int y) { this.x = x; this.y = y; }


    public String toString() 
    {
        return "Noeud " + nom + " (" + x + "; " + y + ")";
    }

}
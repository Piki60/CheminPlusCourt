package metier;

public class Noeud 
{
    private static char idNoeuds = 'A';

    private char id; 

    private int x;
    private int y;

    public Noeud(int x, int y) 
    {
        this.x = x;
        this.y = y;

        this.id = idNoeuds++;
    }

    // Getters

    public char getId   () { return id;}
    public int  getX    () { return x; }
    public int  getY    () { return y; }

    // Setters

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }


    public String toString() 
    {
        return "Noeud " + id + " (" + x + "; " + y + ")";
    }

}
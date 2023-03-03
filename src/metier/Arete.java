package metier;

public class Arete
{
    private static int id=0;
    private Noeud noeud1;
    private Noeud noeud2;
    private int cout;

    public Arete(Noeud noeud1, Noeud noeud2, int cout)
    {
        this.noeud1 = noeud1;
        this.noeud2 = noeud2;
        this.cout = cout;
        Arete.id++;
    }

    public int getId()
    {
        return id;
    }

    public Noeud getNoeud1()
    {
        return noeud1;
    }

    public Noeud getNoeud2()
    {
        return noeud2;
    }

    public int getCout()
    {
        return cout;
    }

    public void setnoeud1(Noeud noeud1)
    {
        this.noeud1 = noeud1;
    }

    public void setnoeud2(Noeud noeud2)
    {
        this.noeud2 = noeud2;
    }

    public void setcout(int cout)
    {
        this.cout = cout;
    }

    @Override
    public String toString()
    {
        return "Arete{" + "id=" + id + ", noeud1=" + noeud1 + ", noeud2=" + noeud2 + ", cout=" + cout + '}';
    }
}
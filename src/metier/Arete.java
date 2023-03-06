package metier;

public class Arete
{
    private static int id=0;
    private int index = 0;
    private Noeud noeud1;
    private Noeud noeud2;
    private int cout;

    public Arete(Noeud noeud1, Noeud noeud2, int cout)
    {
        this.noeud1 = noeud1;
        this.noeud2 = noeud2;
        this.cout = cout;
        this.index++;
        Arete.id = this.index;
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

    public void setCout(int cout)
    {
        this.cout = cout;
    }

    @Override
    public String toString()
    {
        return id + ":" + noeud1.getId() + " =>" + noeud2.getId() + " = " + cout;
    }
}
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
        return "Arête " + id + " : " + noeud1.getNom() + " =>" + noeud2.getNom() + " = " + cout;
    }

    public void draw(Graphics g)
    {
        int x1 = noeud1.getX() + 10;
        int y1 = noeud1.getY() + 10;
        int x2 = noeud2.getX() + 10;
        int y2 = noeud2.getY() + 10;
        
        int arrowSize = 10;
        double dx = x2 - x1;
        double dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int lineX = (int) (x1 + (10 + arrowSize) * Math.cos(angle));
        int lineY = (int) (y1 + (10 + arrowSize) * Math.sin(angle));
        int arrowX = (int) (x2 - (10 + arrowSize) * Math.cos(angle));
        int arrowY = (int) (y2 - (10 + arrowSize) * Math.sin(angle));

        g.drawLine(lineX, lineY, arrowX, arrowY);
        
        int[] arrowHeadX = new int[3];
        int[] arrowHeadY = new int[3];
        arrowHeadX[0] = arrowX;
        arrowHeadY[0] = arrowY;
        arrowHeadX[1] = (int) (arrowX - arrowSize * Math.cos(angle - Math.PI / 6));
        arrowHeadY[1] = (int) (arrowY - arrowSize * Math.sin(angle - Math.PI / 6));
        arrowHeadX[2] = (int) (arrowX - arrowSize * Math.cos(angle + Math.PI / 6));
        arrowHeadY[2] = (int) (arrowY - arrowSize * Math.sin(angle + Math.PI / 6));
        
        g.fillPolygon(arrowHeadX, arrowHeadY, 3);

        int distance = 10;
        int costX = (int) (lineX + ((arrowX - lineX) / 2) + distance * Math.sin(angle));
        int costY = (int) (lineY + ((arrowY - lineY) / 2) - distance * Math.cos(angle));
    
        g.drawString(String.valueOf(cout), costX, costY);
    }

}
package ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;
import metier.Arete;
import metier.Noeud;

public class PanelGraphe extends JPanel 
{
    private Controleur ctrl;
    private FrameCreerGraphe frame;

    private List<Noeud> lstNoeuds;
    private List<Arete> lstAretes;

    public PanelGraphe(FrameCreerGraphe frame, Controleur ctrl) 
    {
        this.ctrl = ctrl;
        this.frame = frame;

        this.initComponent();

        this.add(new JLabel("Graphe"));
        this.setBackground(Color.WHITE);

    }

    private void initComponent() 
    {
        this.lstNoeuds = ctrl.getAlNoeuds();
        this.lstAretes = ctrl.getAlAretes();
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        this.dessinerAretes(g);
        this.dessinerNoeuds(g);
    }

    private void dessinerAretes(Graphics g) 
    {
        super.paintComponent(g);

        for (Arete arete : lstAretes) 
        {
            g.setColor(Color.BLACK);

            Noeud noeud1 = arete.getNoeud1();
            Noeud noeud2 = arete.getNoeud2();
            
            //coordonnées du debut de la ligne et de la fin de la ligne, on ajoute 10 pour centrer le noeud
            int x1 = noeud1.getX() + 10;
            int y1 = noeud1.getY() + 10;
            int x2 = noeud2.getX() + 10;
            int y2 = noeud2.getY() + 10;
            
            
            int arrowSize = 10; //taille de la flèche

            double dx = x2 - x1; //distance x entre les deux noeuds
            double dy = y2 - y1; //distance y entre les deux noeuds
            double angle = Math.atan2(dy, dx); //avec atan2 on peut avoir l'angle entre les deux noeuds

            //on multiplie par Math.cos(angle) et Math.sin(angle) pour avoir la position de la flèche
            int lineX = (int) (x1 + (10 + arrowSize) * Math.cos(angle)); 
            int lineY = (int) (y1 + (10 + arrowSize) * Math.sin(angle));
            int arrowX = (int) (x2 - (10 + arrowSize) * Math.cos(angle));
            int arrowY = (int) (y2 - (10 + arrowSize) * Math.sin(angle));

            g.drawLine(lineX, lineY, arrowX, arrowY);
            
            //on créé deux tableaux pour les coordonnées des deux points de la flèche
            int[] arrowHeadX = new int[3]; 
            int[] arrowHeadY = new int[3];
            arrowHeadX[0] = arrowX; 
            arrowHeadY[0] = arrowY;
            //on utilise Math.PI/6 pour avoir un angle de 30°
            arrowHeadX[1] = (int) (arrowX - arrowSize * Math.cos(angle - Math.PI / 6)); 
            arrowHeadY[1] = (int) (arrowY - arrowSize * Math.sin(angle - Math.PI / 6));
            arrowHeadX[2] = (int) (arrowX - arrowSize * Math.cos(angle + Math.PI / 6));
            arrowHeadY[2] = (int) (arrowY - arrowSize * Math.sin(angle + Math.PI / 6));
            
            g.fillPolygon(arrowHeadX, arrowHeadY, 3);

            //on dessine le cout de l'arete
            int distance = 10;
            int costX = (int) (lineX + ((arrowX - lineX) / 2) + distance * Math.sin(angle));
            int costY = (int) (lineY + ((arrowY - lineY) / 2) - distance * Math.cos(angle));
        
            g.drawString(String.valueOf(arete.getCout()), costX, costY);
      
        }
    }
    
    private void dessinerNoeuds(Graphics g) 
    {
        for (Noeud noeudE : lstNoeuds) 
        {
            g.setColor(new Color(0, 151, 178));
            g.fillOval(noeudE.getX(), noeudE.getY(), 20, 20);
            g.setColor(Color.BLACK);
            g.drawString(noeudE.getNom() + "", noeudE.getX() + 7, noeudE.getY() + 15);
        }
    }   
 
    public void majIHM() 
    {
        this.repaint();
        this.revalidate();
        this.repaint();
    }
}
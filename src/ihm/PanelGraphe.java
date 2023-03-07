package ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import controleur.Controleur;
import metier.Arete;
import metier.Noeud;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class PanelGraphe extends JPanel implements MouseListener
{
    private Controleur ctrl;
    private FrameCreerGraphe frame;
    private List<Noeud> lstNoeuds;
    private List<Arete> lstAretes;

    private int idNoeudDrag;

    private int startX;
    private int startY;

    public PanelGraphe(FrameCreerGraphe frame, Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.frame = frame;

        this.setBackground(new Color(216,216,216));
        
        this.initComponent();
        this.add(new JLabel("Coucou"));

        this.addMouseListener(this);

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
        for (Arete a : lstAretes)
        {
            g.setColor(Color.BLACK);
            g.drawLine(a.getNoeud1().getX()+10, a.getNoeud1().getY()+10, a.getNoeud2().getX()+10, a.getNoeud2().getY()+10);
            g.drawString(a.getCout()+"", (a.getNoeud1().getX()+a.getNoeud2().getX())/2, (a.getNoeud1().getY()+a.getNoeud2().getY())/2);
        }
    }

    private void dessinerNoeuds(Graphics g) 
    {
        for (Noeud n : lstNoeuds)
        {
            g.setColor(new Color(0, 151, 178));
            g.fillOval(n.getX(), n.getY(), 20, 20);
            g.setColor(Color.BLACK);
            g.drawString(n.getId()+"", n.getX()+7, n.getY()+15);
        }
    }   

    @Override
    public void mousePressed(MouseEvent e) {

        
    }

    public void mouseClicked(MouseEvent e) 
    {
        // Si le clic est sur un nœud, enregistrez l'index et les coordonnées
        int mouseX = e.getX();
        int mouseY = e.getY();
        for (int i = 0; i < this.lstNoeuds.size(); i++) {
            Noeud node = this.lstNoeuds.get(i);
            if (mouseX >= node.getX() && mouseX <= node.getX()
                    && mouseY >= node.getY() && mouseY <= node.getY()) {
                idNoeudDrag = i;
                startX = mouseX;
                startY = mouseY;
                break;
            }
        }
           
    }

    public void mouseDragged(MouseEvent e) {
        // Si un nœud est sélectionné, mettez à jour ses coordonnées en fonction du déplacement de la souris
        if (idNoeudDrag >= 0) {
            int deltaX = e.getX() - startX;
            int deltaY = e.getY() - startY;
            Noeud selectedNode = this.lstNoeuds.get(idNoeudDrag);
            selectedNode.setX(selectedNode.getX() + deltaX);
            selectedNode.setY(selectedNode.getY() + deltaY);
            startX = e.getX();
            startY = e.getY();
             // Redessiner le panel avec les nouvelles positions des nœuds
            this.ctrl.setPosNoeud(selectedNode, startX, startY);
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    public void majIHM() 
    {
        this.repaint();
        this.revalidate();
        this.repaint();
    }
}


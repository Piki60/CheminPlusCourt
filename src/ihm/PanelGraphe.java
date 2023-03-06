package ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
    private Ellipse2D[]   noeuds;

    private Noeud selectionNoeud;
    private int idNoeudDrag;

    public PanelGraphe(FrameCreerGraphe frame, Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.frame = frame;

        this.setBackground(new Color(216,216,216));
        
        this.initComponent();

        this.noeuds.addMouseListener(this);

    }

    private void initComponent() 
    {
        this.lstNoeuds = ctrl.getAlNoeuds();
        this.lstAretes = ctrl.getAlAretes();

        this.noeuds = new Ellipse2D[this.lstNoeuds.size()];

    }

    public void paint(Graphics g)
    {
        super.paint(g);
        this.dessinerNoeuds(g);
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
            if (n == selectionNoeud)
            {
                g.setColor(new Color(0, 151, 178));
            }
            else
            {
                g.setColor(new Color(0, 151, 178));
            }
            g.fillOval(n.getX(), n.getY(), 20, 20);
            g.setColor(Color.BLACK);
            g.drawString(n.getId()+"", n.getX()+7, n.getY()+15);
            this.noeuds[this.lstNoeuds.indexOf(n)] = new Ellipse2D.Double(n.getX(), n.getY(), 20, 20);
        }
    }   

    public void setSelectionNoeud(Noeud selectionNoeud) 
    {
        this.selectionNoeud = selectionNoeud;
    }

    public Noeud getSelectionNoeud() 
    {
        return selectionNoeud;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e))
		{
			for (int i = 0 ; i < this.noeuds.length ; i++)
				if (this.noeuds[i].contains(e.getPoint()))
				{
					this.idNoeudDrag = i;
					this.ctrl.selectNoeud(i);
				}

			for (int i = 0 ; i < this.tabNomNoeud.length ; i++)
				if (this.tabNomNoeud[i].contains(e.getPoint()))
				{
					this.idNomNoeudDrag = i;
					this.ctrl.selectNoeud(i);
				}
		}

		if (SwingUtilities.isRightMouseButton(e))
		{
			released = false;
			startPoint = MouseInfo.getPointerInfo().getLocation();
		}
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e))
		{
			this.idNoeudDrag = null;
			this.idNomNoeudDrag = null;
		}
			

		if (SwingUtilities.isRightMouseButton(e))
		{
			released = true;
			repaint();
		}
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}


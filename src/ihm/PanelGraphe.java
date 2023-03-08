package ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controleur.Controleur;
import metier.Arete;
import metier.Noeud;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.Point;


public class PanelGraphe extends JPanel
{
    private Controleur ctrl;
    private FrameCreerGraphe frame;


    private List<Noeud> lstNoeuds;
    private List<Arete> lstAretes;

    private List<Ellipse2D.Double> noeuds;
    private Ellipse2D.Double selectedNode;

    private Integer idNoeudDrag;
    private Noeud noeudSelec;
    private Point pointDebut;
    private int startX;
    private int startY;

    public PanelGraphe(FrameCreerGraphe frame, Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.frame = frame;

        this.setBackground(new Color(216,216,216));
        
        this.initComponent();
        this.add(new JLabel("Graphe"));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (Ellipse2D.Double node : noeuds) {
                    if (node.contains(e.getPoint())) {
                        selectedNode = node;
                        break;
                    }
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                selectedNode = null;
            }
        });
        
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedNode != null) {
                    double dx = e.getX() - selectedNode.getCenterX();
                    double dy = e.getY() - selectedNode.getCenterY();
                    selectedNode.setFrame(selectedNode.getX() + dx, selectedNode.getY() + dy, 20, 20);
                    repaint();
                }
            }
        });

    }

    private void initComponent() 
    {
        this.lstNoeuds = ctrl.getAlNoeuds();
        this.lstAretes = ctrl.getAlAretes();
        noeuds= new ArrayList<Ellipse2D.Double>();
        for (Noeud n : lstNoeuds) {
            noeuds.add(new Ellipse2D.Double(n.getX(), n.getY(), 20, 20));
        }
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
            g.drawString(n.getNom()+"", n.getX()+7, n.getY()+15);
        }   
    }   

 
    public void majIHM() 
    {
        this.repaint();
        this.revalidate();
        this.repaint();
    }

    

    public Noeud getNoeudSelec() {
        return noeudSelec;
    }

}
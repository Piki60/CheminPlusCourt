package ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

    private int xSelectionne, ySelectionne;
    private Noeud noeudSelectionne;
    public PanelGraphe(FrameCreerGraphe frame, Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.frame = frame;

        this.setBackground(new Color(216,216,216));
        
        this.initComponent();
        this.add(new JLabel("Graphe"));

        addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                
                for (Noeud noeud : lstNoeuds) {
                    if (e.getX() >= noeud.getX() - 10 && e.getX() <= noeud.getX() + 10
                            && e.getY() >= noeud.getY() - 10 && e.getY() <= noeud.getY() + 10) {
                        // Le clic de souris est sur un noeud
                        noeudSelectionne = noeud;
                        xSelectionne = e.getX() - noeud.getX();
                        ySelectionne = e.getY() - noeud.getY();
                        break;
                    }
                }
            }
        
            @Override
            public void mouseReleased(MouseEvent e) {
                
                noeudSelectionne = null;
            }
        
            @Override
            public void mouseDragged(MouseEvent e) {
                if (noeudSelectionne != null) {
                    noeudSelectionne.setX(e.getX() - xSelectionne);
                    noeudSelectionne.setY(e.getY() - ySelectionne);
                    repaint();
                }
            }

        });

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
            arete.draw(g);
           
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
        return noeudSelectionne;
    } 

}
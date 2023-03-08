package ihm;

import java.util.List;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controleur.Controleur;
import metier.Arete;
import metier.Noeud;

public class DialogTabDistance extends JDialog
{
    private Controleur ctrl;
    private Noeud noeudDepart;

    private Object[] ligne;

    private List<Noeud> lstNoeuds;
    private List<Arete> lstAretes;

    private int[] tabDistance;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    public DialogTabDistance(Controleur ctrl, Noeud noeudDepart)
    {
        
        this.setTitle("Tableau des distances");
        this.setLocationRelativeTo(null);
        this.setSize(300, 300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        this.ctrl = ctrl;
        this.noeudDepart = noeudDepart;
        this.lstNoeuds = ctrl.getAlNoeuds();
        this.lstAretes = ctrl.getAlAretes();

        this.table = new JTable();
        this.model = new DefaultTableModel();
        this.scrollPane = new JScrollPane(table);

        
        this.initialisation();
        this.bellmanFord();

    
        table.setModel(model);
        this.add(scrollPane);

    }

    public void initialisation()
    {
        //phase d'initialisation du tableau
        String[] entetes = new String[lstNoeuds.size()];
        entetes[0] = "";
        for (int i = 1; i < entetes.length; i++)
        {
            entetes[i] = "N" + i;
        }

        model.setColumnIdentifiers(entetes);


        //on initialise le tableau des distances
        this.tabDistance = new int[lstNoeuds.size()];

        tabDistance[noeudDepart.getId()] = 0;
        for (int i = 0; i < lstNoeuds.size(); i++)
        {
            if (i != noeudDepart.getId())
                tabDistance[i] = Integer.MAX_VALUE;
        }

        //on écrit dans la première ligne du tableau les distances initiales

        ligne = new Object[lstNoeuds.size() + 1];
        ligne[0] = "Initialisation";
        for (int i = 0; i < tabDistance.length; i++)
        {
            if (tabDistance[i] == Integer.MAX_VALUE)
                ligne[i + 1] = "+∞";
            else
                ligne[i + 1] = tabDistance[i];
        }

        model.addRow(ligne);
    }


    public void bellmanFord()
    {
        for (int i = 0; i < lstNoeuds.size() - 1; i++)
        {
            for (Arete a : lstAretes)
            {
                int indexDepart = this.lstNoeuds.indexOf(a.getNoeud1());
                int indexArrivee = this.lstNoeuds.indexOf(a.getNoeud2());
                int cout = a.getCout();

                if (tabDistance[indexDepart] != Integer.MAX_VALUE && 
                    tabDistance[indexDepart] + cout < tabDistance[indexArrivee])
                {
                    tabDistance[indexArrivee] = tabDistance[indexDepart] + cout;
                }
            }

            //on écrit dans la ligne suivante du tableau les nouvelles distances
            ligne = new Object[lstNoeuds.size() + 1];
            ligne[0] = "Iteration " + (i + 1);
            for (int j = 0; j < tabDistance.length; j++)
            {
                if (tabDistance[j] == Integer.MAX_VALUE)
                    ligne[j + 1] = "+∞";
                else
                    ligne[j + 1] = tabDistance[j];

            }

            //si la ligne est identique à la précédente, on arrête l'algorithme
            if (model.getRowCount() > 0)
            {
                //on récupère chaque élément de la ligne précédente
                Vector<Object> lignePrecedente = model.getDataVector().elementAt(model.getRowCount() - 1);

                //on vérifie si la ligne actuelle est identique à la précédente
                int nbIdentiques = 0;
                for (int j = 1; j < lignePrecedente.size(); j++)
                {
                    if (lignePrecedente.get(j).equals(ligne[j]))
                        nbIdentiques++;
                }

                if (nbIdentiques == lignePrecedente.size() - 1)
                    break;
                else 
                    model.addRow(ligne);

            }

        }
    }
}

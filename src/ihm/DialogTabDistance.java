package ihm;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DialogTabDistance extends JDialog
{
    private int[] tab;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    public DialogTabDistance(int[] tab)
    {
        
        this.setTitle("Tableau des distances");
        this.setLocationRelativeTo(null);
        this.setSize(300, 300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        this.tab = tab;
        this.table = new JTable();
        this.model = new DefaultTableModel();
        this.scrollPane = new JScrollPane(table);

        this.model.addColumn("Noeud");
        this.model.addColumn("Distance");

        for (int i = 0; i < tab.length; i++)
        {
            if(tab[i] == Integer.MAX_VALUE)
                this.model.addRow(new Object[]{i, "+∞"});
            else
                this.model.addRow(new Object[]{i, tab[i]});
        }

        this.table.setModel(model);
        this.add(scrollPane);

        
    }
}

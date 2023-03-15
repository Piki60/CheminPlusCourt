package ihm;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controleur.Controleur;
import metier.Noeud;

public class DialogChoixChemin extends JDialog implements ActionListener
{
        private Controleur ctrl;
        private PanelFormulaire pnlF;

        private JComboBox<Noeud> combo;

        private JButton btnValider;
        private JButton btnAnnuler;

        public DialogChoixChemin(PanelFormulaire pnlF, Controleur ctrl)
        {
                this.ctrl = ctrl;
                this.pnlF = pnlF;

                Dimension dim = new Dimension (350, 150);

                this.setTitle("Chemin le plus court");
                this.setSize(dim);
                this.setLocationRelativeTo(null);
                this.setResizable(false);
                this.setLayout(null);
                this.setVisible(true);

                //creation des composants
                JLabel labelNoeud = new JLabel("Noeud de départ : ");

                this.combo = new JComboBox<Noeud>();

                for (int i = 0; i < ctrl.getAlNoeuds().size(); i++)
                {
                        this.combo.addItem(ctrl.getAlNoeuds().get(i));
                }

                this.btnValider = new JButton("Valider");
                this.btnAnnuler = new JButton("Annuler");

                //positionnement des composants

                labelNoeud.setBounds(10, 20, 150, 20);
                this.combo.setBounds(160, 20, 150, 20);

                this.btnValider.setBounds(10, 60, 150, 20);
                this.btnAnnuler.setBounds(160, 60, 150, 20);
                
                //ajout des composants

                this.add(labelNoeud);
                this.add(this.combo);

                this.add(this.btnValider);
                this.add(this.btnAnnuler);

                //ajout des listeners
                this.btnValider.addActionListener(this);
                this.btnAnnuler.addActionListener(this);                                   
                
        }

        public void actionPerformed(ActionEvent e) 
        {
                if(e.getSource() == this.btnValider)
                {
                        if(this.combo.getSelectedIndex() == -1)
                        {
                                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un noeud", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                                Noeud n = (Noeud) this.combo.getSelectedItem();
                                System.out.println(n.toString());
                                new DialogTabDistance(ctrl, n);
                                this.dispose();
                        }
                }
                else if(e.getSource() == this.btnAnnuler)
                {
                        this.dispose();
                }
        }

        
}
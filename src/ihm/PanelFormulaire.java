package ihm;

import metier.Arete;
import metier.Noeud;
import controleur.Controleur;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.attribute.standard.DialogTypeSelection;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File; 
import java.io.IOException;

public class PanelFormulaire extends JPanel implements ActionListener
{
        private Controleur ctrl;
        private JPanel panelNoeuds;
        private JPanel panelAretes;
        private JPanel panelBtn;
        private List<Noeud> lstNoeuds;
        private List<Arete> lstAretes;

        private JLabel      lblX;
        private JLabel      lblY;
        private JTextField  txtX;
        private JTextField  txtY;
        private JButton     btnAjouterNoeud;
        private JButton     btnSupprimerNoeud;
        private JList<Noeud>       listNoeuds;
        private DefaultListModel<Noeud> listModelNoeuds;
        private JScrollPane scrollPaneNoeuds;

        private JLabel      lblNoeud1;
        private JLabel      lblNoeud2;
        private JLabel      lblCout;
        private JComboBox<Noeud>   comboNoeud1;
        private JComboBox<Noeud>   comboNoeud2;
        private JTextField  txtCout;
        private JButton     btnAjouterArete;
        private JButton     btnSupprimerArete;
        private JList<Arete>       listAretes;
        private DefaultListModel<Arete> listModelAretes;
        private JScrollPane scrollPaneAretes;

        private JButton btnSauvegarder;
        private JButton btnCheminCourt;


    public PanelFormulaire(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setBackground(new Color(216,216,216));
        this.setLayout(new GridLayout(5,1));

        this.lstNoeuds = this.ctrl.getMetier().getAlNoeuds();      
        this.lstAretes = this.ctrl.getMetier().getAlAretes();  

        // Panel Noeuds
        this.panelNoeuds = new JPanel();
        this.panelNoeuds.setBackground(new Color(216,216,216));
        GroupLayout layout = new GroupLayout(this.panelNoeuds);
        this.panelNoeuds.setLayout(layout);


        //Composants du panel Noeuds
        this.lblX = new JLabel("Position X :", JLabel.RIGHT);
        this.lblY = new JLabel("Position Y :", JLabel.RIGHT);

        this.txtX = new JTextField(10);
        this.txtX.addKeyListener(new EntierTextField());

        this.txtY = new JTextField(10);
        this.txtY.addKeyListener(new EntierTextField());

        this.btnAjouterNoeud = new JButton("Ajouter un noeud");
        this.btnAjouterNoeud.setBackground(new Color(0, 151, 178));
        
        this.btnSupprimerNoeud = new JButton("Supprimer un noeud");
        this.btnSupprimerNoeud.setBackground(new Color(0, 151, 178));

        // Liste des noeuds
        this.listNoeuds = new JList<Noeud>();

        this.listModelNoeuds = new DefaultListModel<Noeud>();
        for (int i = 0; i < this.lstNoeuds.size(); i++)
        {
                this.listModelNoeuds.addElement(this.lstNoeuds.get(i));
        }
        this.listNoeuds.setModel(this.listModelNoeuds);
        
        this.scrollPaneNoeuds = new JScrollPane(this.listNoeuds);
        this.scrollPaneNoeuds.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Ajout des composants au panel Noeuds
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup().
                addComponent(this.lblX).
                addComponent(this.lblY).
                addComponent(this.btnAjouterNoeud));

        hGroup.addGroup(layout.createParallelGroup().
                addComponent(this.txtX).
                addComponent(this.txtY).
                addComponent(this.btnSupprimerNoeud));

        hGroup.addGroup(layout.createParallelGroup().
                addComponent(this.scrollPaneNoeuds));
        
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(this.lblX).
                addComponent(this.txtX)
                .addComponent(this.scrollPaneNoeuds));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(this.lblY).
                addComponent(this.txtY)
                .addComponent(this.scrollPaneNoeuds));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(this.btnAjouterNoeud).
                addComponent(this.btnSupprimerNoeud));
        layout.setVerticalGroup(vGroup);


        // Panel Aretes
        this.panelAretes = new JPanel();
        this.panelAretes.setBackground(new Color(216,216,216));
        
        GroupLayout layout2 = new GroupLayout(this.panelAretes);
        this.panelAretes.setLayout(layout2);

        //Composants du panel Aretes
        this.lblNoeud1 = new JLabel("Noeud 1 :");
        this.lblNoeud2 = new JLabel("Noeud 2 :");
        this.lblCout = new JLabel("Cout :");

        this.comboNoeud1 = new JComboBox<>();
        
        
        this.comboNoeud2 = new JComboBox<>();
        this.txtCout = new JTextField(10);
        this.txtCout.addKeyListener(new EntierTextField());

        this.btnAjouterArete = new JButton("Ajouter une arete");
        this.btnAjouterArete.setBackground(new Color(0, 151, 178));

        this.btnSupprimerArete = new JButton("Supprimer une arete");
        this.btnSupprimerArete.setBackground(new Color(0, 151, 178));

        // Liste des aretes
        this.listAretes = new JList<Arete>();

        this.listModelAretes = new DefaultListModel<Arete>();
        for (int i = 0; i < this.lstAretes.size(); i++)
        {
                this.listModelAretes.addElement(this.lstAretes.get(i));
        }

        this.listAretes.setModel(this.listModelAretes);
        
        this.scrollPaneAretes = new JScrollPane(this.listAretes);
        this.scrollPaneAretes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        

        // Ajout des composants au panel Aretes
        layout2.setAutoCreateGaps(true);
        layout2.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup2 = layout2.createSequentialGroup();
        hGroup2.addGroup(layout2.createParallelGroup().
                addComponent(this.lblNoeud1).
                addComponent(this.lblNoeud2).
                addComponent(this.lblCout).
                addComponent(this.btnAjouterArete));
        
        hGroup2.addGroup(layout2.createParallelGroup().
                addComponent(this.comboNoeud1).
                addComponent(this.comboNoeud2).
                addComponent(this.txtCout).
                addComponent(this.btnSupprimerArete));
        
        hGroup2.addGroup(layout2.createParallelGroup().
                addComponent(this.scrollPaneAretes));
        
        layout2.setHorizontalGroup(hGroup2);

        GroupLayout.SequentialGroup vGroup2 = layout2.createSequentialGroup();
        vGroup2.addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(this.lblNoeud1).
                addComponent(this.comboNoeud1)
                .addComponent(this.scrollPaneAretes));

        vGroup2.addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(this.lblNoeud2).
                addComponent(this.comboNoeud2)
                .addComponent(this.scrollPaneAretes));
        
        vGroup2.addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(this.lblCout).
                addComponent(this.txtCout)
                .addComponent(this.scrollPaneAretes));

        vGroup2.addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(this.btnAjouterArete).
                addComponent(this.btnSupprimerArete));

        layout2.setVerticalGroup(vGroup2);

        // Panel BTN
        this.panelBtn = new JPanel();
        this.panelBtn.setBackground(new Color(216,216,216));
        this.panelBtn.setLayout(null);

        this.btnSauvegarder = new JButton("Sauvegarder");
        this.btnSauvegarder.setBackground(new Color(0, 151, 178));
        this.btnSauvegarder.setBounds(60, 80, 150, 30);
        this.btnCheminCourt = new JButton("Chemin le plus court");
        this.btnCheminCourt.setBackground(new Color(0, 151, 178));
        this.btnCheminCourt.setBounds(300, 80, 200, 30);

        this.panelBtn.add(this.btnSauvegarder);
        this.panelBtn.add(this.btnCheminCourt);

        // Panel Principal
        this.add(new JLabel("NOEUDS"));
        this.add(this.panelNoeuds);
        this.add(new JLabel("ARETES"));
        this.add(this.panelAretes);
        this.add(this.panelBtn);

        // Ajout des listeners
        this.btnAjouterNoeud.addActionListener(this);
        this.btnAjouterArete.addActionListener(this);
        this.btnSauvegarder.addActionListener(this);
        this.btnCheminCourt.addActionListener(this);
    }


        @Override
        public void actionPerformed(ActionEvent e) 
        {
                if(e.getSource() == this.btnAjouterNoeud)
                {
                        if(this.txtX.getText().equals("") || this.txtY.getText().equals(""))
                        {
                                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                                int x = Integer.parseInt(this.txtX.getText());
                                int y = Integer.parseInt(this.txtY.getText());
                                this.ctrl.ajouterNoeud(x, y);
                                this.txtX.setText("");
                                this.txtY.setText("");
                                this.majIHM();
                        }
                }
                else if(e.getSource() == this.btnAjouterArete)
                {
                        if(this.comboNoeud1.getSelectedItem() == null || this.comboNoeud2.getSelectedItem() == null || this.txtCout.getText().equals(""))
                        {
                                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                                Noeud n1 = (Noeud) this.comboNoeud1.getSelectedItem();
                                Noeud n2 = (Noeud) this.comboNoeud2.getSelectedItem();
                                int cout = Integer.parseInt(this.txtCout.getText());
                                this.ctrl.ajouterArete(n1, n2, cout);
                                this.comboNoeud1.setSelectedItem(1);
                                this.comboNoeud2.setSelectedItem(1);
                                this.txtCout.setText("");
                                this.majIHM();
                        }
                }
                else if(e.getSource() == this.btnSupprimerNoeud)
                {
                        if(this.listNoeuds.getSelectedValue() == null)
                        {
                                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un noeud", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                                Noeud n = (Noeud) this.listNoeuds.getSelectedValue();
                                this.ctrl.supprimerNoeud(n);
                                this.majIHM();
                        }
                }
                else if(e.getSource() == this.btnSupprimerArete)
                {
                        if(this.listAretes.getSelectedValue() == null)
                        {
                                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une arête", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                                Arete a = (Arete) this.listAretes.getSelectedValue();
                                this.ctrl.supprimerArete(a);
                                this.majIHM();
                        }
                }
                else if(e.getSource() == this.btnSauvegarder)
                {
                        //sauvegarder dans un fichier
                       JDialog dialog = new JDialog();

                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Sauvegarder");
                        
                        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
                        fileChooser.setFileFilter(filter);
                        fileChooser.setApproveButtonText("Sauvegarder");
                        fileChooser.setApproveButtonToolTipText("Sauvegarder");

                        int userSelection = fileChooser.showSaveDialog(dialog);
                        
                        if (userSelection == JFileChooser.APPROVE_OPTION) 
                        {
                                File fileToSave = fileChooser.getSelectedFile();
                                System.out.println("Enregistrer sous: " + fileToSave.getAbsolutePath());
                                this.ctrl.ecrireXML(fileToSave);
                        }

                }
                else if(e.getSource() == this.btnCheminCourt)
                {
                        
                }
        }


        private void majIHM() 
        {
                // Mise à jour de la liste des noeuds
                this.listModelNoeuds.removeAllElements();
                for (int i = 0; i < this.lstNoeuds.size(); i++)
                {
                        this.listModelNoeuds.addElement(this.lstNoeuds.get(i));
                }
                this.listNoeuds.setModel(this.listModelNoeuds);

                // Mise à jour de la liste des arêtes
                this.listModelAretes.removeAllElements();
                for (int i = 0; i < this.lstAretes.size(); i++)
                {
                        this.listModelAretes.addElement(this.lstAretes.get(i));
                }
                this.listAretes.setModel(this.listModelAretes);

                // Mise à jour des combobox
                this.comboNoeud1.removeAllItems();
                this.comboNoeud2.removeAllItems();

                for (int i = 0; i < this.lstNoeuds.size(); i++)
                {
                        this.comboNoeud1.addItem(this.lstNoeuds.get(i));
                        this.comboNoeud2.addItem(this.lstNoeuds.get(i));
                }

                this.repaint();
        }
               

        private class EntierTextField extends KeyAdapter
        {
                @Override
                public void keyTyped(KeyEvent e) 
                {
                        // on récupère le caractère qui vient d'être saisi et si ce n'est pas un chiffre (méthode isDigit()), on l'ignore
                        char c = e.getKeyChar();
                        if(!Character.isDigit(c))
                        {
                                e.consume();
                        }
                }
        }
}

package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import controleur.Controleur;
import metier.Arete;
import metier.Noeud;

public class PanelFormulaire extends JPanel implements ActionListener, ListSelectionListener
{
        private Controleur ctrl;
        private FrameCreerGraphe frame;
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
        private JButton     btnModifierNoeud;
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


    public PanelFormulaire(FrameCreerGraphe frame, Controleur ctrl)
    {
        this.frame = frame;
        this.ctrl = ctrl;
        this.setBackground(new Color(216,216,216));
        this.setLayout(new GridLayout(5,1));

        this.lstNoeuds = this.ctrl.getAlNoeuds();      
        this.lstAretes = this.ctrl.getAlAretes();  

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

        this.btnModifierNoeud = new JButton("Modifier un noeud");
        this.btnModifierNoeud.setEnabled(false);
        this.btnModifierNoeud.setBackground(new Color(0, 151, 178));
        
        this.btnSupprimerNoeud = new JButton("Supprimer un noeud");
        this.btnSupprimerNoeud.setBackground(new Color(0, 151, 178));
        this.btnSupprimerNoeud.setEnabled(false);

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
        this.scrollPaneNoeuds.setPreferredSize(new java.awt.Dimension(200, 500));

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
                addComponent(this.btnModifierNoeud));

        hGroup.addGroup(layout.createParallelGroup().
                addComponent(this.scrollPaneNoeuds)
                .addComponent(this.btnSupprimerNoeud));
        
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
                addComponent(this.btnModifierNoeud).
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
        this.scrollPaneAretes.setPreferredSize(new java.awt.Dimension(200, 500));
        

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
                addComponent(this.scrollPaneAretes)
                );
        
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
        this.btnSupprimerNoeud.addActionListener(this);
        this.btnSupprimerArete.addActionListener(this);
        this.btnModifierNoeud.addActionListener(this);
        this.btnSauvegarder.addActionListener(this);
        this.btnCheminCourt.addActionListener(this);

        this.listNoeuds.addListSelectionListener(this);
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
                                

                                for ( Arete a : this.lstAretes )
                                {
                                        if ( a.getNoeud1() == n1 && a.getNoeud2() == n2 )
                                        {
                                                JOptionPane.showMessageDialog(null, "L'arete existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
                                                return;
                                        }
                                }

                                this.ctrl.ajouterArete(n1, n2, cout);                               

                                this.txtCout.setText("");
                                this.comboNoeud1.setSelectedIndex(-1);
                                this.comboNoeud2.setSelectedIndex(-1);

                                this.majIHM();
                        }
                }
                else if(e.getSource() == this.btnSupprimerNoeud)
                {
                        
                        Noeud n = (Noeud) this.listNoeuds.getSelectedValue();

                        for ( Arete a : this.lstAretes )
                        {
                                if ( a.getNoeud1() == n || a.getNoeud2() == n )
                                {
                                        this.ctrl.supprimerArete(a);
                                }
                        }

                        this.ctrl.supprimerNoeud(n);
                        this.majIHM();
                        
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
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Sauvegarder le graphe");
                        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        fileChooser.setAcceptAllFileFilterUsed(false);
                        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichier XML", "xml"));

                        int userSelection = fileChooser.showSaveDialog(this);

                        if (userSelection == JFileChooser.APPROVE_OPTION) 
                        {
                                File fileToSave = fileChooser.getSelectedFile();

                                if ( !fileToSave.getName().endsWith(".xml"))
                                        fileToSave = new File(fileToSave.getAbsolutePath() + ".xml");   

                                if ( fileToSave.exists() )
                                {
                                        int reponse = JOptionPane.showConfirmDialog(null, "Le fichier existe déjà, voulez-vous le remplacer ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                                        if ( reponse == JOptionPane.NO_OPTION )
                                                return;
                                }

                                this.ctrl.sauvegarder(fileToSave);
                        }
                        
                }
                else if(e.getSource() == this.btnCheminCourt)
                {
                        if ( this.lstNoeuds.size() == 0 )
                        {
                                JOptionPane.showMessageDialog(null, "Veuillez ajouter des noeuds", "Erreur", JOptionPane.ERROR_MESSAGE);
                                return;
                        }
                        else if ( this.lstAretes.size() == 0 )
                        {
                                JOptionPane.showMessageDialog(null, "Veuillez ajouter des arêtes", "Erreur", JOptionPane.ERROR_MESSAGE);
                                return;
                        }
                        
                        new DialogCheminCourt();                        
                }
                else if (e.getSource() == this.btnModifierNoeud)
                {
                        Noeud n = (Noeud) this.listNoeuds.getSelectedValue();

                        int newX = Integer.parseInt(this.txtX.getText());
                        int newY = Integer.parseInt(this.txtY.getText());

                        this.ctrl.modifierNoeud(n.getId(), newX, newY);

                        this.txtX.setText("");
                        this.txtY.setText("");
                        this.listNoeuds.clearSelection();

                        this.btnModifierNoeud.setEnabled(false);

                        this.majIHM();
                }
        }


        @Override
        public void valueChanged(ListSelectionEvent e) 
        {
                if(e.getSource() == this.listNoeuds)
                {
                        if(this.listNoeuds.getSelectedValue() != null)
                        {
                                Noeud n = (Noeud) this.listNoeuds.getSelectedValue();
                                this.txtX.setText(String.valueOf(n.getX()));
                                this.txtY.setText(String.valueOf(n.getY()));

                                this.btnAjouterNoeud.setEnabled(false);
                                this.btnSupprimerNoeud.setEnabled(true);
                                this.btnModifierNoeud.setEnabled(true);
                        }
                }
        }


        private void majIHM() 
        {
                this.listModelNoeuds.removeAllElements();
                for (int i = 0; i < this.lstNoeuds.size(); i++)
                {
                        this.listModelNoeuds.addElement(this.lstNoeuds.get(i));
                }
                this.listNoeuds.setModel(this.listModelNoeuds);

                this.listModelAretes.removeAllElements();
                for (int i = 0; i < this.lstAretes.size(); i++)
                {
                        this.listModelAretes.addElement(this.lstAretes.get(i));
                }
                this.listAretes.setModel(this.listModelAretes);

                this.comboNoeud1.removeAllItems();
                this.comboNoeud2.removeAllItems();

                for (int i = 0; i < this.lstNoeuds.size(); i++)
                {
                        this.comboNoeud1.addItem(this.lstNoeuds.get(i));
                        this.comboNoeud2.addItem(this.lstNoeuds.get(i));
                }

                this.repaint();
                //this.frame.majIHM();
                
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

        private class DialogCheminCourt extends JDialog implements ActionListener
        {
                private JComboBox<Noeud> combo;

                private JButton btnValider;
                private JButton btnAnnuler;

                public DialogCheminCourt()
                {
                        Dimension dim = new Dimension (350, 150);

                        this.setTitle("Chemin le plus court");
                        this.setSize(dim);
                        this.setLocationRelativeTo(null);
                        this.setResizable(false);
                        this.setLayout(null);
                        this.setVisible(true);

                        //creation des composants
                        JLabel labelNoeud = new JLabel("Noeud de départ : ");

                        for (int i = 0; i < lstNoeuds.size(); i++)
                        {
                                this.combo.addItem(lstNoeuds.get(i));
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
                                        //ctrl.cheminLePlusCourt(n);
                                }
                        }
                        else if(e.getSource() == this.btnAnnuler)
                        {
                                this.dispose();
                        }
                }

                
        }

       
}

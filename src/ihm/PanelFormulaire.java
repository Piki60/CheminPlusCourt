package ihm;

import metier.Noeud;
import metier.Arete;
import controleur.Controleur;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PanelFormulaire extends JPanel implements ActionListener
{
        private Controleur ctrl;
        private JPanel panelNoeuds;
        private JPanel panelAretes;
        private JPanel panelBtn;

        private JLabel      lblX;
        private JLabel      lblY;
        private JTextField  txtX;
        private JTextField  txtY;
        private JButton     btnAjouterNoeud;
        private JList       listNoeuds;
        private JScrollPane scrollPaneNoeuds;

        private JLabel      lblNoeud1;
        private JLabel      lblNoeud2;
        private JLabel      lblCout;
        private JComboBox   comboNoeud1;
        private JComboBox   comboNoeud2;
        private JTextField  txtCout;
        private JButton     btnAjouterArete;
        private JList       listAretes;
        private JScrollPane scrollPaneAretes;

        private JButton btnSauvegarder;
        private JButton btnCheminCourt;


    public PanelFormulaire(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setBackground(new Color(216,216,216));
        this.setLayout(new GridLayout(5,1));
        

        // Panel Noeuds
        this.panelNoeuds = new JPanel();
        this.panelNoeuds.setBackground(new Color(216,216,216));
        GroupLayout layout = new GroupLayout(this.panelNoeuds);
        this.panelNoeuds.setLayout(layout);

        //Composants du panel Noeuds
        this.lblX = new JLabel("Position X :", JLabel.RIGHT);
        this.lblY = new JLabel("Position Y :", JLabel.RIGHT);

        this.txtX = new JTextField(10);
        this.txtX.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke) {
                   String value = txtX.getText();
                   int l = value.length();
                   if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE || ke.getKeyCode() == KeyEvent.VK_DELETE) {
                      txtX.setEditable(true);
                   } else {
                      txtX.setEditable(false);
                   }
                }
             });

        this.txtY = new JTextField(10);
        this.txtY.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke) {
                   String value = txtY.getText();
                   int l = value.length();
                   if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE || ke.getKeyCode() == KeyEvent.VK_DELETE) {
                      txtY.setEditable(true);
                   } else {
                      txtY.setEditable(false);
                   }
                }
             });

        this.btnAjouterNoeud = new JButton("Ajouter un noeud");
        this.btnAjouterNoeud.setBackground(new Color(0, 151, 178));

        JPanel panelLstNoeud = new JPanel();
        panelLstNoeud.setBackground(new Color(250,250,250));
        this.listNoeuds = new JList();
        this.scrollPaneNoeuds = new JScrollPane(this.listNoeuds);
        panelLstNoeud.add(this.scrollPaneNoeuds);

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
                addComponent(this.txtY));

        hGroup.addGroup(layout.createParallelGroup().
                addComponent(panelLstNoeud));
        
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(this.lblX).
                addComponent(this.txtX)
                .addComponent(panelLstNoeud));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(this.lblY).
                addComponent(this.txtY)
                .addComponent(panelLstNoeud));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(this.btnAjouterNoeud));
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
        this.txtCout.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent ke) {
                   String value = txtCout.getText();
                   int l = value.length();
                   if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE || ke.getKeyCode() == KeyEvent.VK_DELETE) {
                      txtCout.setEditable(true);
                   } else {
                      txtCout.setEditable(false);
                   }
                }
             });

        this.btnAjouterArete = new JButton("Ajouter une arete");
        this.btnAjouterArete.setBackground(new Color(0, 151, 178));

        JPanel panelLstArete = new JPanel();
        panelLstArete.setBackground(new Color(250,250,250));
        this.listAretes = new JList();
        this.scrollPaneAretes = new JScrollPane(this.listAretes);
        panelLstArete.add(this.scrollPaneAretes);

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
                addComponent(this.txtCout));
        
        hGroup2.addGroup(layout2.createParallelGroup().
                addComponent(panelLstArete));
        
        layout2.setHorizontalGroup(hGroup2);

        GroupLayout.SequentialGroup vGroup2 = layout2.createSequentialGroup();
        vGroup2.addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(this.lblNoeud1).
                addComponent(this.comboNoeud1)
                .addComponent(panelLstArete));

        vGroup2.addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(this.lblNoeud2).
                addComponent(this.comboNoeud2)
                .addComponent(panelLstArete));
        
        vGroup2.addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(this.lblCout).
                addComponent(this.txtCout)
                .addComponent(panelLstArete));

        vGroup2.addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(this.btnAjouterArete));

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
                                this.txtCout.setText("");
                        }
                }
                else if(e.getSource() == this.btnSauvegarder)
                {
                        
                }
                else if(e.getSource() == this.btnCheminCourt)
                {
        }
        }
}

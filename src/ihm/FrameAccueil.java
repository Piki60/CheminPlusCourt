package ihm;

import controleur.Controleur;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FrameAccueil extends JFrame implements ActionListener
{
    private Controleur ctrl;
    private JPanel panelAccueil;
    private JButton btnCreerGraphe;
    private JButton btnChargerGraphe;

    public FrameAccueil(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle("Accueil");
        this.setSize(500, 500);
        setLocationRelativeTo(null);

        // Panel Accueil
        this.panelAccueil = new JPanel();
        this.panelAccueil.setBackground(new Color(216,216,216));
        this.panelAccueil.setLayout(null);

        // Bouton Créer un graphe
        this.btnCreerGraphe = new JButton("Créer un graphe");
        this.btnCreerGraphe.setBackground(new Color(0, 151, 178));
        this.btnCreerGraphe.setBounds(100, 180, 300, 50);

        // Bouton Charger un graphe
        this.btnChargerGraphe = new JButton("Charger un graphe");
        this.btnChargerGraphe.setBackground(new Color(0, 151, 178));
        this.btnChargerGraphe.setBounds(100, 240, 300, 50);

        // Ajout des composants au panel
        this.panelAccueil.add(this.btnCreerGraphe);
        this.panelAccueil.add(this.btnChargerGraphe);

        // Ajout du panel à la frame
        this.add(this.panelAccueil);

        // Ajout des listeners
        this.btnCreerGraphe.addActionListener(this);
        this.btnChargerGraphe.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == this.btnCreerGraphe)
        {
            new FrameCreerGraphe( ctrl);
            this.dispose();
        }
        else if(e.getSource() == this.btnChargerGraphe)
        {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers XML", "xml");

            fileChooser.setCurrentDirectory(new java.io.File("."));

            fileChooser.setFileFilter(filter);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setCurrentDirectory(new java.io.File("./src/"));


            int returnVal = fileChooser.showOpenDialog(this);

            if(returnVal == JFileChooser.APPROVE_OPTION)
            {
                ctrl.lireXML(fileChooser.getSelectedFile());
                new FrameCreerGraphe(ctrl);
                
                this.dispose();
            }
            this.dispose();
        }
    }
}

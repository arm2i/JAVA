package puissance4.view;

import puissance4.controlor.Puissance4Listener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import puissance4.model.Config;
import puissance4.model.Token;
import puissance4.model.Game;
import java.awt.Component;
import java.util.ArrayList;

/**
 *
 * @author G37269
 */
public class Puissance4Vue extends JFrame implements Observer {
    private static final long serialVersionUID = 1L;

    private Token[][] plateau;
    private Game puissance4;
    private ArrayList<JButton> playListButton;
    private JToggleButton jToggleButton1;
    private JPanel playGroundPan;
    private JPanel jPanel1;

    private JPanel playZone;
    private JPanel jPanel2;
    private JButton newBut;
    private JScrollPane jScrollPane1;

    /**
     *
     */
    private JLabel outPut;
    private JButton close;
    private JLabel title;

    /**
     *
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public Puissance4Vue() {
        initComponents();

        this.puissance4 = new Game(); //ceartion puissance 4
        this.plateau = new Token[Config.LIGN_NUMBER][Config.COLUMN_NUMBER];
        this.plateau = puissance4.getPuissance4().getGrille().getPlateauToken();

        puissance4.addObserver(this);

        for (int i = 0; i < Config.allCase; i++) { //Config.allCase=42 cases creation des cases
            JButton tpPanel = new JButton(); // co
            tpPanel.setEnabled(false); // deactive le clique du bouton
            playZone.add(tpPanel); // playZone=Jpanel
        }
        outPut.setText("is Go !!!"); //outPut c'est affichage c'est un Jtextfield boutin is go
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {//GEN-BEGIN:initComponents
        playListButton = new ArrayList<>();
        jToggleButton1 = new JToggleButton();
        playGroundPan = new JPanel();
        jPanel1 = new JPanel();
        String play;
        for(int k=0;k<Config.COLUMN_NUMBER;k++){ // initialisation des 6 boutons colonne
            //JButton play +"k"= new JButton(Integer.toString(k));
             playListButton.add(new JButton(Integer.toString(k))); //cree et nome bouton
            Component add = jPanel1.add(playListButton.get(k)); //afficher les boutons component avec ecouteur
             playListButton.get(k).addActionListener(new Puissance4Listener(this)); //Puissance4Listener class ecouteur
        }        

        playZone = new JPanel();
        jPanel2 = new JPanel();
        newBut = new JButton();
        jScrollPane1 = new JScrollPane();
        outPut = new JLabel();
        close = new JButton();
        title = new JLabel();

        jToggleButton1.setText("jToggleButton1");

        playGroundPan.setLayout(new BorderLayout());
        jPanel1.setLayout(new GridLayout(1, 7));


        playGroundPan.add(jPanel1, BorderLayout.SOUTH);

        playZone.setLayout(new GridLayout(6, 7));

        playZone.setBackground(new java.awt.Color(51, 51, 255));
        playGroundPan.add(playZone, BorderLayout.CENTER);

        getContentPane().add(playGroundPan, BorderLayout.CENTER);

        jPanel2.setLayout(new GridLayout(1, 3));

        newBut.setText("Nouveau");

        jPanel2.add(newBut);
        newBut.addActionListener(new Puissance4Listener(this));

        outPut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jScrollPane1.setViewportView(outPut);

        jPanel2.add(jScrollPane1);

        close.setText("Fermer");
        jPanel2.add(close);
        close.addActionListener(new Puissance4Listener(this));

        getContentPane().add(jPanel2, BorderLayout.SOUTH);

        title.setBackground(new java.awt.Color(0, 102, 255));
        title.setFont(new java.awt.Font("Century Gothic", 1, 14));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("*** Puissance 4 : G37269 ****");
        getContentPane().add(title, BorderLayout.NORTH);

        pack();
    }//GEN-END:initComponents

    public Game getPuissance4() {
        return this.puissance4;
    }

    public void setOutput(String msg) {
        this.outPut.setText(msg);
    }

    @Override
    public void update(Observable o, Object arg) {

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                int position = i * Config.var + j;
                if (plateau[i][j] == null) {
                    playZone.getComponent(position).setBackground(new java.awt.Color(200, 200, 200));
                } else {
                    
                    switch (plateau[i][j].getColor()) {
                        //case null: System.out.print(" ");break;
                        case RED:
                            playZone.getComponent(position).setBackground(new java.awt.Color(255, 0, 0));
                            break;
                        case YELLOW:
                            playZone.getComponent(position).setBackground(new java.awt.Color(255, 255, 0));
                            break;
                    }
                }
                if (puissance4.gameIsOver() == false) {
                    String name = (puissance4.getPuissance4().getCurrentPlayer().getNameColor()).toString();
                    this.outPut.setText("Is player : " + name);
                } else {
                    if (puissance4.getPuissance4().getGrille().isFullGrille() == true) {
                        this.outPut.setText("GAME IS OVER");
                    } else {
                        String name2 = (puissance4.getPuissance4().getWinner().getNameColor()).toString();
                        this.outPut.setText("winner is : " + name2);
                    }
                }
            }
        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projettest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Float.parseFloat;
import static java.util.logging.Level.parse;
import javafx.scene.layout.Pane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Formation
 */
public class Calculatrice extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form Calculatrice
     */
    JButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7
            ,btn8,btn9,btnPlus,btnMoins,btnMult,btnDiv
            ,btnC,btnPoint,egal;
    JTextField resultat;
    
       String chaine="";
       int point=1;
       float nb1=0,nb2=0,toto=0;
      String tt, operateur;
            
    
    public Calculatrice() {
        initComponents();
        btn0 = new JButton("0");btn1 = new JButton("1");
        btn2= new JButton("2");btn3= new JButton("3");
        btn4= new JButton("4"); btn5= new JButton("5");
        btn6= new JButton("6"); btn7= new JButton("7");
        btn8= new JButton("8"); btn9= new JButton("9");
        btnPlus= new JButton("+"); btnMoins= new JButton("-");
        btnMult= new JButton("*"); btnDiv= new JButton("/");
        btnC= new JButton("C");  btnPoint= new JButton(".");
        
        
        JPanel panelBtn = new JPanel(new GridLayout(4,4));
        panelBtn.add(btn1);panelBtn.add(btn2);panelBtn.add(btn3);panelBtn.add(btnPlus);
        panelBtn.add(btn4);panelBtn.add(btn5);panelBtn.add(btn6);panelBtn.add(btnMoins);
        panelBtn.add(btn7);panelBtn.add(btn8);panelBtn.add(btn9);panelBtn.add(btnMult);
        panelBtn.add(btn0);panelBtn.add(btnC);panelBtn.add(btnPoint);panelBtn.add(btnDiv);
        
        
        
        btn0.addActionListener(this);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);
        btn7.addActionListener(this);
        btn8.addActionListener(this);
        btn9.addActionListener(this);
        btnPoint.addActionListener(this);
        
        btnC.addActionListener(this);
        
        btnPlus.addActionListener(this);
        btnMoins.addActionListener(this);
        btnMult.addActionListener(this);
        btnDiv.addActionListener(this);
        
        
        
        
        
        JPanel panelResultat = new JPanel();
        this.resultat = new JTextField("0");
        resultat.setPreferredSize(new Dimension(270,50));
        panelResultat.setLayout(new FlowLayout());
        panelResultat.add(resultat);
        
       
        
        
        JPanel panelEgal = new JPanel();
        panelEgal.setLayout(new FlowLayout());
        egal = new JButton(" = ");
        egal.setPreferredSize(new Dimension(270,50));
        panelEgal.add(egal);  
        egal.addActionListener(this);
        
        JPanel pane  =  new JPanel(new BorderLayout());
        pane.add(panelResultat,BorderLayout.NORTH);
        pane.add(panelBtn,BorderLayout.CENTER);
        pane.add(panelEgal,BorderLayout.SOUTH);
      
        this.setContentPane(pane);
        this.setSize(300, 300);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Calculatrice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Calculatrice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Calculatrice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calculatrice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calculatrice().setVisible(true);
            }
        });
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void actionPerformed(ActionEvent ae) {
     
      
      
       //while (ae.getSource()!= btnPlus || ae.getSource()!= btnPlus)
        if(ae.getSource()==btn0){chaine=chaine+"0";this.resultat.setText(chaine);}
        if(ae.getSource()==btn1){chaine=chaine+"1";this.resultat.setText(chaine);}
        if(ae.getSource()==btn2){chaine=chaine+"2";this.resultat.setText(chaine);}
        if(ae.getSource()==btn3){chaine=chaine+"3";this.resultat.setText(chaine);}
        if(ae.getSource()==btn4){chaine=chaine+"4";this.resultat.setText(chaine);}
        if(ae.getSource()==btn5){chaine=chaine+"5";this.resultat.setText(chaine);}
        if(ae.getSource()==btn6){chaine=chaine+"6";this.resultat.setText(chaine);}
        if(ae.getSource()==btn7){chaine=chaine+"7";this.resultat.setText(chaine);}
        if(ae.getSource()==btn8){chaine=chaine+"8";this.resultat.setText(chaine);}
        if(ae.getSource()==btn9){chaine=chaine+"9";this.resultat.setText(chaine);}
        
       
        if(ae.getSource()==btnPoint && point==1){
            point=0;
            {chaine=chaine+".";this.resultat.setText(chaine);}}
        
         if(ae.getSource()==btnC){ nb1=0;
                                    chaine="";
                                    operateur = "";
                                    //this.resultat.setText("0");
                                    point=1;
                                     }
        if(ae.getSource()==btnPlus){ operateur = "+";nb1=Float.parseFloat(chaine);
                                    chaine="";
                                    point=1;
                                   // this.resultat.setText("0");
                                    
                                    } 
        if(ae.getSource()==btnMoins){ operateur = "-";nb1=Float.parseFloat(chaine);
                                    chaine="";
                                    point=1;
                                    //this.resultat.setText("0");
                                   }
        if(ae.getSource()==btnMult){ operateur = "*";nb1=Float.parseFloat(chaine);
                                    chaine="";
                                    point=1;
                                    //this.resultat.setText("0");
                                    }
        if(ae.getSource()==btnDiv){ operateur = "/";nb1=Float.parseFloat(chaine);
                                    chaine="";
                                    point=1;
                                    //this.resultat.setText("0");
                                    }
        
     
        
       if(ae.getSource()== egal){
            nb2=Float.parseFloat(chaine);
           
           if(operateur == "+"){
                                 toto=nb1+nb2;
                              float j = (int) toto;
                              float i=j-toto;
                              if(i==0){toto=(int)toto;
                                     System.out.println(""+toto); 
                                    this.resultat.setText(""+toto);
                              }};
                                }
           if(operateur == "-"){toto=nb1-nb2;
                                System.out.println(""+toto); 
                                this.resultat.setText(""+toto);}
            if(operateur == "*"){toto=nb1*nb2;
                                System.out.println(""+toto); 
                                this.resultat.setText(""+toto);}                     
           if(operateur == "/"){
                        if(nb2!=0){
                                toto=nb1/nb2;
                                System.out.println(""+toto); 
                                this.resultat.setText(""+toto);} 
                        else { this.resultat.setText("ERROR!!!!!!!");}}
           
                                
          //tt=String.valueOf(toto) ;
                                                               
                                   
       
                              
        
        
}           
/// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

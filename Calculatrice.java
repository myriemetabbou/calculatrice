import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * Calculatrice
 */
public class Calculatrice extends JFrame{

        private JPanel container = new JPanel();//un conteneur principal
        String [] tab_string  = {"1","2","3","4","5","6","7","8","9","0",".","=","C","+","-","*","/"};
        JButton[] tab_button  = new JButton[tab_string.length];
        private JLabel ecran  = new JLabel();//affiche contenu ecran claculatrice
        private Dimension dim = new Dimension(50, 40);
        private Double chiffre1;
        private String operateur = "";
        private boolean update = false, clicOperateur = false;
        private Dimension dim2 = new Dimension(50, 31);
        
    


            public Calculatrice(){

                this.setSize(250, 280);//taille fenetre
                this.setTitle("calculette");//titre fenetre 
                
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//fermer fenetre
                this.setLocationRelativeTo(null);//fentre au centre ecran
                this.setResizable(false);//utilisateur peut pas redimenssionner
                


                initComposant();

                this.setContentPane(container);//container sera conteneur principal de fentre
                this.setVisible(true);//fenetre visible 

            }

                    private void initComposant(){

                        Font police = new Font("Arial ", Font.BOLD, 20 );
                        ecran = new JLabel("0");// creer label affiche 0
                        ecran.setFont(police);//police de l ecran
                        ecran.setHorizontalTextPosition(JLabel.RIGHT);
                        ecran.setPreferredSize(new Dimension(220, 20));//taille jlabel ecran

                        JPanel operateur = new JPanel();//conteneur operateur 
                        operateur.setPreferredSize(new Dimension(55, 225));

                        JPanel chiffre = new JPanel();// conteneur chiffres
                        chiffre.setPreferredSize(new Dimension(165, 225));

                        JPanel panEcran = new JPanel();//contient jlabel ecran
                        panEcran.setPreferredSize(new Dimension(220, 30));

                        for(int i = 0; i < tab_string.length; i++){

                            tab_button[i] = new JButton(tab_string[i]);
                            tab_button[i].setPreferredSize(dim);

                            switch(i){
                                
                                case 11 : 
                                tab_button[i].addActionListener(new EgalListener());
                                chiffre.add(tab_button[i]);
                                break;

                                case 12 : 
                                tab_button[i].setForeground(Color.red);
                                tab_button[i].addActionListener(new ResetListener());
                                tab_button[i].setPreferredSize(dim2);
                                operateur.add(tab_button[i]);
                                break; 
                                
                                case 13 :
                               tab_button[i].addActionListener(new PlusListener());
                               tab_button[i].setPreferredSize(dim2); 
                               operateur.add(tab_button[i]);
                               break;

                               case 14 :
                               tab_button[i].addActionListener(new MoinsListener());
                               tab_button[i].setPreferredSize(dim2);
                               operateur.add(tab_button[i]);
                               break;

                               case 15 :
                               tab_button[i].addActionListener(new MultiListener());
                               tab_button[i].setPreferredSize(dim2);
                               operateur.add(tab_button[i]);
                               break;

                               case 16 :
                               tab_button[i].addActionListener(new DivListener());
                               tab_button[i].setPreferredSize(dim2);
                               operateur.add(tab_button[i]);
                               break;

                               default :
                               chiffre.add(tab_button[i]);// bouton ajoutes au conteneurs chiffres 
                               tab_button[i].addActionListener(new ChiffreListener());//ajoute ecouteur evenementa chq btn
                               //nouvel obj chiffrelitsnr cree et associe au btn
                               break;
                               
                            }
                        }
                    

                                panEcran.add(ecran);
                                panEcran.setBorder(BorderFactory.createLineBorder(Color.black));

                                container.add(panEcran, BorderLayout.NORTH);
                                container.add(chiffre, BorderLayout.CENTER);
                                container.add(operateur, BorderLayout.EAST);
                    }

                                public void calcul(){
                                    if(operateur.equals("+")){
                                        chiffre1 = chiffre1 + Double.valueOf(ecran.getText()).doubleValue();
                                        //recupere val avant cliquer sur plus et ajouter la 2 eme val saisi
                                        ecran.setText(String.valueOf(chiffre1));//maj chiffre1
                                    }

                                    if(operateur.equals("-")){
                                        chiffre1 = chiffre1 - Double.valueOf(ecran.getText()).doubleValue();
                                        ecran.setText(String.valueOf(chiffre1));
                                    }

                                    if(operateur.equals("*")){
                                        chiffre1 = chiffre1 * Double.valueOf(ecran.getText()).doubleValue();
                                        ecran.setText(String.valueOf(chiffre1));
                                    }

                                    if(operateur.equals("/")){
                                        try{
                                        chiffre1 = chiffre1 / Double.valueOf(ecran.getText()).doubleValue();
                                        ecran.setText(String.valueOf(chiffre1));
                                        }catch(ArithmeticException e){
                                            ecran.setText("0");
                                        }
                                    }

                                }


        class ChiffreListener implements ActionListener  {
            @Override 
            public void actionPerformed(ActionEvent e){
                String str = ((JButton)e.getSource()).getText();//renvoie btn clique 
                if (update){//si il a pas ete mis a jour 
                    update = false;// eccran ete mis a jour et on affiche direct la vel saisi
                }
                    else if(!ecran.getText().equals("0")){// si diff 0 alors 
                        str = ecran.getText() + str ;    // concatene ce qu il y avait sur ecrzn   btn clique 
                    }

                    ecran.setText(str);// afficher sur ecran chiffre ou sequences de chiffres 
                
            }


                            }


        class EgalListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                calcul();// on fait le calcul
                update = true;//il faut mettre a jour 
                clicOperateur = false;

                }
            
        }   
        
        class PlusListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                if(clicOperateur){// si bouton operateur  cliqué on fait direct calcul
                    calcul();
                    ecran.setText(String.valueOf(chiffre1));// affiche la nouvelle val de chiffre1
                }
                else {
                    chiffre1 = Double.valueOf(ecran.getText()).doubleValue();// si plus clique mais pas saisi val
                    // on affiche juste la premiere val saisi
                    clicOperateur = true;//comme quoi operteur clique 

                }
                operateur = "+";
                update = true;
                
            }
            
        }
    

        class MoinsListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                if(clicOperateur){//deja clique sur operateur alors val 1 est stocke dans chiffre1 apres calcul
                    calcul();
                    ecran.setText(String.valueOf(chiffre1));
                }
                else {
                    chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                    clicOperateur = true;

                }
                operateur = "-";
                update = true;
                
            }
            
        }

        class MultiListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                if(clicOperateur){
                    calcul();
                    ecran.setText(String.valueOf(chiffre1));
                }
                else {
                    chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                    clicOperateur = true;

                }
                operateur = "*";
                update = true;//ecran mis a jour 
                
            }
            
        }

        class DivListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                if(clicOperateur){
                    calcul();
                    ecran.setText(String.valueOf(chiffre1));
                }
                else {
                    chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                    clicOperateur = true;

                }
                operateur = "/";
                update = true;
                
            }
            
        }

        class ResetListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
               clicOperateur = false ;
               update = true ;
               chiffre1 = 0.0 ;
               operateur = "";
               ecran.setText("");

            }
        }
        
        




                                

        





                





                    
            
        
    }
    

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author obetMubarok
 */
class fungsiPertama {

    String x = "21";
    String fungsi1 = "", fungsi2 = "";
    String angka[] = {"1.0", "2.0", "3.0", "4.0", "5.0", "6.0", "7.0", "8.0", "9.0"};
    String op[] = {"+", "-", "/", "*", "^"};
    int random = 0;
    
    public String[] isiArray1() {
        String[] tree1 = new String[7];
        tree1[0] = op[(0 + (int) (Math.random() * 5))];
        tree1[1] = op[(0 + (int) (Math.random() * 5))];
        tree1[2] = x;
        tree1[3] = x;
        tree1[4] = "+";
        tree1[5] = angka[(0 + (int) (Math.random() * 8))];
        tree1[6] = x;

        return tree1;
    }

    public String[] isiArray2() {
        String[] tree2 = new String[7];
        tree2[0] = op[(0 + (int) (Math.random() * 5))];
        tree2[1] = op[(0 + (int) (Math.random() * 5))];
        tree2[2] = x;
        tree2[3] = x;
        tree2[4] = "+";
        tree2[5] = angka[(0 + (int) (Math.random() * 8))];
        tree2[6] = x;

        return tree2;
    }

    public double selisi(double[] a, double[] b) {
        double hasil = 0;
        for (int i = 0; i < a.length; i++) {
            hasil += a[i] - b[i];
        }
        return hasil;
    }

    public String[] isiKearray(String x) {
        String[] hasil = new String[7];
        String teks = "";
        int indek = 0;
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) != ' ') {
                teks += x.charAt(i);
                if (i == x.length() - 1) {
                    hasil[indek] = teks;
                    indek++;
                    teks = "";
                }
            } else {
                hasil[indek] = teks;
                teks = "";
                indek++;
            }
        }
        return hasil;
    }

    public String isiKestring(String[] x) {
        String hasil = "";
        for (int i = 0; i < 7; i++) {
            hasil += " " + x[i];
        }
        return hasil;
    }
}

public class FormUtama extends javax.swing.JFrame {

    /**
     * Creates new form FormUtama
     */
    double[] deret = new double[7];
    DefaultTableModel model;
    Object[] o = new Object[9];
    String fungsiTerbesar = "";
    int urutanLoaping;
    int statusTabelIsi = 0;
    public FormUtama() {
        //setUndecorated(true);

        initComponents();
        setTitle("Genetic Programming-Ubaidillah");
        setLocationRelativeTo(this);

        deret = isiDeret();
        jTextField1.setText(stringDeret(deret));

        model = new DefaultTableModel();
        tabelHasil.setModel(model);
        //tabelKasus.setPreferredSize(tabelKasus.getPreferredSize().width);
        model.addColumn("Loaping ke-");
        model.addColumn("1");
        model.addColumn("2");
        model.addColumn("3");
        model.addColumn("4");
        model.addColumn("5");
        model.addColumn("6");
        model.addColumn("7");
        model.addColumn("Fitnes");
    }

    public JLabel getFungsiKetemu() {
        return fungsiKetemu;
    }

    public JLabel getLoapKetemu() {
        return loapKetemu;
    }

    

    public void setDeret(double[] deret) {
        this.deret = deret;
    }

    
    int fungsiDeret(int x) {
        int hasil = x + x * x;
        return hasil;
    }

    private double[] isiDeret() {
        double[] x = new double[7];
        for (int i = 0; i < x.length; i++) {
            x[i] = fungsiDeret(i + 1);
        }
        return x;
    }

    public void setStatusTabelIsi(int statusTabelIsi) {
        this.statusTabelIsi = statusTabelIsi;
    }

    
    String stringDeret(double[] x) {
        String hasil = "";
        for (int i = 0; i < x.length; i++) {
            hasil += x[i] + "      ";
        }
        return hasil;
    }

    public void loadData() {
        if(statusTabelIsi==1){
            for(int i=0;i<100;i++){
                model.removeRow(0);
            }
            
        }
        urutanLoaping = 0;
        double fitnes1, fitnes2, fitnesTerpilih = 0, fitnesTerbesar = 0.0000;
        double[] deretP1 = new double[7];
        double[] deretP2 = new double[7];
        double[] terpilih = new double[7];
        DecimalFormat des = new DecimalFormat("0.000");
        DecimalFormat des2 = new DecimalFormat("0.0000");
        fungsiPertama fp = new fungsiPertama();
        String fungsi1 = fp.isiKestring(fp.isiArray1());
        String fungsi2 = fp.isiKestring(fp.isiArray2());
        ExpressionTree caclTerpilih = new ExpressionTree(new Scanner(fungsi1));
        ExpressionTree calc = new ExpressionTree(new Scanner(fungsi1));
        ExpressionTree calc2 = new ExpressionTree(new Scanner(fungsi2));
        String[] baru = new String[7];

        //System.out.println(calc.showInFix(calc.));
        for (int k = 0; k < 100; k++) {
            fitnes1 = 0;
            fitnes2 = 0;
            //deret tree 1
            for (int i = 0; i < 7; i++) {
                calc.x = (i + 1);
                try {
                    deretP1[i] = Double.parseDouble(des.format(calc.evaluate()));
                    fitnes1 += Math.abs(deret[i] - calc.evaluate());
                } catch (NumberFormatException ex) { // handle your exception
                    System.out.println("eror");
                    fungsiKetemu.setText("");
                    loapKetemu.setText("");
                    k = 0;
                    break;
                }

            }
            //deret tree 2
            for (int i = 0; i < deret.length; i++) {
                calc2.x = (i + 1);
                try {
                    deretP2[i] = Double.parseDouble(des.format(calc2.evaluate()));
                    fitnes2 += Math.abs(deret[i] - calc2.evaluate());
                } catch (NumberFormatException ex) { // handle your exception
                    System.out.println("eror");
                    for (int l = 0; l < (urutanLoaping - 1); l++) {
                        model.removeRow(0);
                    }
                    fungsiKetemu.setText("");
                    loapKetemu.setText("");
                    k = 0;
                    break;
                }
            }

            /*if (fitnesTerpilih > fitnesTerbesar) {
                fitnesTerbesar = fitnesTerpilih;
                fungsiKetemu.setText("Rumus yang paling mendekati : " + caclTerpilih.tampilString2(caclTerpilih.root));
                loapKetemu.setText("Berada pada loaping ke-" + urutanLoaping + " dengan nilai fitnes : " + des2.format(fitnesTerbesar));

            }*/
            urutanLoaping++;
            //mencari fitness
            if ((1 / (1 + fitnes1)) > (1 / (1 + fitnes2))) {
                terpilih = deretP1;
                fitnesTerpilih = (1 / (1 + fitnes1));
                
                if (fitnesTerpilih > fitnesTerbesar) {
                    fitnesTerbesar = fitnesTerpilih;
                    fungsiKetemu.setText("Rumus yang paling mendekati : " + caclTerpilih.tampilString2(caclTerpilih.root));
                    loapKetemu.setText("Berada pada loaping ke-" + urutanLoaping + " dengan nilai fitnes : " + des2.format(fitnesTerbesar));
                }
                caclTerpilih = calc;
                
                calc = calc.crossover(calc, calc2);
                calc2 = caclTerpilih;
            } else {
                terpilih = deretP2;
                fitnesTerpilih = (1 / (1 + fitnes2));
                
                if (fitnesTerpilih > fitnesTerbesar) {
                    fitnesTerbesar = fitnesTerpilih;
                    fungsiKetemu.setText("Rumus yang paling mendekati : " + caclTerpilih.tampilString2(caclTerpilih.root));
                    loapKetemu.setText("Berada pada loaping ke-" + urutanLoaping + " dengan nilai fitnes : " + des2.format(fitnesTerbesar));
                    
                }
                caclTerpilih = calc2;
                calc2 = calc.crossover(calc2, calc);
                calc = caclTerpilih;
            }

            calc = calc.mutasi(calc);
            calc2 = calc2.mutasi(calc2);

            o[0] = "Ke-" + (k + 1);
            o[1] = "" + terpilih[0];
            o[2] = "" + terpilih[1];
            o[3] = "" + terpilih[2];
            o[4] = "" + terpilih[3];
            o[5] = "" + terpilih[4];
            o[6] = "" + terpilih[5];
            o[7] = "" + terpilih[6];
            o[8] = "" + des2.format(fitnesTerpilih);
            model.addRow(o);
            statusTabelIsi=1;
        }
        
        System.out.println(model.getColumnCount());
        //
        //String h =""+calc.showInFix(caclTerpilih.root);

        //System.out.println(h);
        //System.out.println(calc.showPreFix(caclTerpilih.root));
    }

    public JTextField getjTextField1() {
        return jTextField1;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelHasil = new javax.swing.JTable();
        fungsiKetemu = new javax.swing.JLabel();
        loapKetemu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Book Antiqua", 1, 11)); // NOI18N
        jLabel3.setText("Deret :");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jTextField1.setText("1      2        3");

        jButton2.setFont(new java.awt.Font("Book Antiqua", 1, 11)); // NOI18N
        jButton2.setText("Proses");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Book Antiqua", 1, 11)); // NOI18N
        jButton4.setText("Ganti deret");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("APLIKASI GENETIC PROGRAMMING");

        jLabel2.setFont(new java.awt.Font("Showcard Gothic", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DALAM PENCARIAN RUMUS DERET BILANGAN");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cs2_premium.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        tabelHasil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelHasil);

        fungsiKetemu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        loapKetemu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(10, 10, 10)
                                .addComponent(jTextField1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jButton4)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fungsiKetemu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(loapKetemu, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 79, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fungsiKetemu, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loapKetemu, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jPanel1.setBackground(Color.lightGray);
        System.out.println(statusTabelIsi);
        new GantiDeret(this, tabelHasil, deret, statusTabelIsi).setVisible(true);


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        loadData();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fungsiKetemu;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel loapKetemu;
    private javax.swing.JTable tabelHasil;
    // End of variables declaration//GEN-END:variables
}

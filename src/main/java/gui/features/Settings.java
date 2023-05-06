package gui.features;

import gui.lib.Environment;
import javax.swing.*;

public class Settings extends JFrame {

  public Settings(Environment env) {
    initComponents();
  }

  private void initComponents() {

    buttonGroup1 = new ButtonGroup();
    buttonGroup2 = new ButtonGroup();
    buttonGroup3 = new ButtonGroup();
    jLabel1 = new JLabel();
    jButton1 = new JButton();
    jPanel1 = new JPanel();
    jScrollPane1 = new JScrollPane();
    jTextArea1 = new JTextArea();
    jPanel4 = new JPanel();
    jPanel3 = new JPanel();
    jRadioButton5 = new JRadioButton();
    jRadioButton6 = new JRadioButton();
    jRadioButton7 = new JRadioButton();
    jRadioButton8 = new JRadioButton();
    jPanel6 = new JPanel();
    jRadioButton13 = new JRadioButton();
    jRadioButton14 = new JRadioButton();
    jRadioButton15 = new JRadioButton();
    jRadioButton16 = new JRadioButton();
    jPanel8 = new JPanel();
    jRadioButton21 = new JRadioButton();
    jRadioButton22 = new JRadioButton();
    jRadioButton23 = new JRadioButton();
    jRadioButton24 = new JRadioButton();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 36));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText("Adjust your settings");

    jButton1.setText("Save");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    jPanel1.setBorder(BorderFactory.createTitledBorder("Your Name"));

    jTextArea1.setColumns(20);
    jTextArea1.setRows(1);
    jTextArea1.setToolTipText("Enter your name");
    jScrollPane1.setViewportView(jTextArea1);

    GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap()));
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap()));

    jPanel3.setBorder(BorderFactory.createTitledBorder("Language to learn"));
    jPanel3.setToolTipText("Select the language you want to learn");

    buttonGroup1.add(jRadioButton5);
    jRadioButton5.setText("English");
    jRadioButton5.setToolTipText("");
    jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jRadioButton5ActionPerformed(evt);
      }
    });

    buttonGroup1.add(jRadioButton6);
    jRadioButton6.setText("German");
    jRadioButton6.setToolTipText("");
    jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jRadioButton6ActionPerformed(evt);
      }
    });

    buttonGroup1.add(jRadioButton7);
    jRadioButton7.setText("Spanish");
    jRadioButton7.setToolTipText("");
    jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jRadioButton7ActionPerformed(evt);
      }
    });

    buttonGroup1.add(jRadioButton8);
    jRadioButton8.setText("French");
    jRadioButton8.setToolTipText("");
    jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jRadioButton8ActionPerformed(evt);
      }
    });

    GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton5)
                    .addComponent(jRadioButton6)
                    .addComponent(jRadioButton7)
                    .addComponent(jRadioButton8))
                .addContainerGap(62, Short.MAX_VALUE)));
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton5)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton6)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton7)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton8)));

    GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)));

    jPanel6.setBorder(BorderFactory.createTitledBorder("Translation language"));
    jPanel6.setToolTipText("Select the language you want to translate from");

    buttonGroup2.add(jRadioButton13);
    jRadioButton13.setText("English");
    jRadioButton13.setToolTipText("");
    jRadioButton13.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jRadioButton13ActionPerformed(evt);
      }
    });

    buttonGroup2.add(jRadioButton14);
    jRadioButton14.setText("German");
    jRadioButton14.setToolTipText("");
    jRadioButton14.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jRadioButton14ActionPerformed(evt);
      }
    });

    buttonGroup2.add(jRadioButton15);
    jRadioButton15.setText("Spanish");
    jRadioButton15.setToolTipText("");
    jRadioButton15.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jRadioButton15ActionPerformed(evt);
      }
    });

    buttonGroup2.add(jRadioButton16);
    jRadioButton16.setText("French");
    jRadioButton16.setToolTipText("");
    jRadioButton16.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jRadioButton16ActionPerformed(evt);
      }
    });

    GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
        jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton13)
                    .addComponent(jRadioButton14)
                    .addComponent(jRadioButton15)
                    .addComponent(jRadioButton16))
                .addContainerGap(97, Short.MAX_VALUE)));
    jPanel6Layout.setVerticalGroup(
        jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton13)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton14)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton15)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton16)));

    jPanel8.setBorder(BorderFactory.createTitledBorder("Interface language"));
    jPanel8.setToolTipText("Select the language for your interface");

    buttonGroup3.add(jRadioButton21);
    jRadioButton21.setText("English");
    jRadioButton21.setToolTipText("");
    jRadioButton21.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jRadioButton21ActionPerformed(evt);
      }
    });

    buttonGroup3.add(jRadioButton22);
    jRadioButton22.setText("German");
    jRadioButton22.setToolTipText("");
    jRadioButton22.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jRadioButton22ActionPerformed(evt);
      }
    });

    buttonGroup3.add(jRadioButton23);
    jRadioButton23.setText("Spanish");
    jRadioButton23.setToolTipText("");
    jRadioButton23.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jRadioButton23ActionPerformed(evt);
      }
    });

    buttonGroup3.add(jRadioButton24);
    jRadioButton24.setText("French");
    jRadioButton24.setToolTipText("");
    jRadioButton24.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jRadioButton24ActionPerformed(evt);
      }
    });

    GroupLayout jPanel8Layout = new GroupLayout(jPanel8);
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(
        jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton21)
                    .addComponent(jRadioButton22)
                    .addComponent(jRadioButton23)
                    .addComponent(jRadioButton24))
                .addContainerGap(68, Short.MAX_VALUE)));
    jPanel8Layout.setVerticalGroup(
        jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton21)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton22)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton23)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton24)));

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(jButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                            GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap()));

    pack();
  }

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void jRadioButton13ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void jRadioButton14ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void jRadioButton15ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void jRadioButton16ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void jRadioButton21ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void jRadioButton22ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void jRadioButton23ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void jRadioButton24ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private ButtonGroup buttonGroup1;
  private ButtonGroup buttonGroup2;
  private ButtonGroup buttonGroup3;
  private JButton jButton1;
  private JLabel jLabel1;
  private JPanel jPanel1;
  private JPanel jPanel3;
  private JPanel jPanel4;
  private JPanel jPanel6;
  private JPanel jPanel8;
  private JRadioButton jRadioButton13;
  private JRadioButton jRadioButton14;
  private JRadioButton jRadioButton15;
  private JRadioButton jRadioButton16;
  private JRadioButton jRadioButton21;
  private JRadioButton jRadioButton22;
  private JRadioButton jRadioButton23;
  private JRadioButton jRadioButton24;
  private JRadioButton jRadioButton5;
  private JRadioButton jRadioButton6;
  private JRadioButton jRadioButton7;
  private JRadioButton jRadioButton8;
  private JScrollPane jScrollPane1;
  private JTextArea jTextArea1;

}

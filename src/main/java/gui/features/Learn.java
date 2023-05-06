package gui.features;

import gui.lib.Environment;
import javax.swing.*;

public class Learn extends JFrame {

  private JButton jButton1;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JPanel jPanel1;
  private JScrollPane jScrollPane1;
  private JTextArea jTextArea1;

  public Learn(Environment env) {
    initComponents();
  }

  private void initComponents() {

    jLabel1 = new JLabel();
    jButton1 = new JButton();
    jPanel1 = new JPanel();
    jScrollPane1 = new JScrollPane();
    jTextArea1 = new JTextArea();
    jLabel2 = new JLabel();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 36));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText("What's {word} in {language}?");

    jButton1.setText("Submit / Continue");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    jPanel1.setBorder(BorderFactory.createTitledBorder("Your Answer"));
    jPanel1.setToolTipText("");

    jTextArea1.setColumns(20);
    jTextArea1.setRows(1);
    jTextArea1.setToolTipText("Enter your answer");
    jScrollPane1.setViewportView(jTextArea1);

    GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1,
                    GroupLayout.DEFAULT_SIZE,
                    461, Short.MAX_VALUE)
                .addContainerGap()));
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1,
                    GroupLayout.PREFERRED_SIZE,
                    47,
                    GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)));

    jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
    jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel2.setText("Result");

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(
                    GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(jLabel1,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(jLabel2,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(jButton1,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE))
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout
                .createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1,
                    GroupLayout.DEFAULT_SIZE,
                    GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE)
                .addPreferredGap(
                    LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1,
                    GroupLayout.PREFERRED_SIZE,
                    GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(
                    LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2,
                    GroupLayout.PREFERRED_SIZE,
                    38,
                    GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(
                    LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap()));

    pack();
  }

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }
}

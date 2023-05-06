package gui.features;

import javax.swing.*;
import java.awt.event.*;
import gui.lib.Environment;
import gui.lib.NavigateTo;

public class Learn extends JPanel {

  private final Environment env;
  private final NavigateTo navigateTo;

  private JLabel title;
  private JPanel answerPanel;
  private JTextArea answerField;
  private JLabel result;
  private JButton submitContinue;
  private JButton backToMenu;

  public Learn(Environment env, NavigateTo navigateTo) {
    this.env = env;
    this.navigateTo = navigateTo;

    setupTitle();
    setupAnswer();
    setupResult();
    setupSubmitContinue();
    setupBackToMenu();

    setupLayout();
  }

  private void setupTitle() {
    title = new JLabel();
    title.setFont(new java.awt.Font("Helvetica Neue", 0, 36));
    title.setHorizontalAlignment(SwingConstants.CENTER);
    title.setText("What's {word} in {language}?");
  }

  private void setupAnswer() {
    answerField = new JTextArea();
    answerField.setColumns(30);
    answerField.setRows(1);
    answerField.getDocument().putProperty("filterNewlines", Boolean.TRUE);

    answerPanel = new JPanel();
    answerPanel.setBorder(BorderFactory.createTitledBorder("Your Answer"));
    answerPanel.setToolTipText("Please type your answer");
    answerPanel.add(answerField);
  }

  private void setupResult() {
    result = new JLabel();
    result.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
    result.setHorizontalAlignment(SwingConstants.CENTER);
    result.setText("Result");
  }

  private void setupSubmitContinue() {
    submitContinue = new JButton();
    submitContinue.setText("Submit / Continue");
    submitContinue.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        // TODO: submit / continue
      }
    });
  }

  private void setupBackToMenu() {
    backToMenu = new JButton();
    backToMenu.setText("Return");
    backToMenu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        navigateTo.execute("menu");
      }
    });
  }

  private void setupLayout() {
    GroupLayout layout = new GroupLayout(this);
    setLayout(layout);

    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(
                    GroupLayout.Alignment.LEADING)
                    .addComponent(answerPanel,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(title,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(result,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(submitContinue,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backToMenu,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, 50)))
                .addContainerGap()));

    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout
                .createSequentialGroup()
                .addContainerGap()
                .addComponent(title,
                    GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(answerPanel,
                    GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(result,
                    GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(submitContinue,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backToMenu,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap()));
  }
}

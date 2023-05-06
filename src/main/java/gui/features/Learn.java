package gui.features;

import javax.swing.*;
import java.awt.event.*;
import gui.lib.Environment;
import gui.lib.NavigateTo;

public class Learn extends JPanel {

  private final NavigateTo navigateTo;

  private JLabel titleLabel = new JLabel();
  private JPanel answerPanel = new JPanel();
  private JTextArea answerField = new JTextArea();
  private JLabel resultLabel = new JLabel();
  private JButton submitContinueButton = new JButton();
  private JButton returnButton = new JButton();

  public Learn(Environment env, NavigateTo navigateTo) {
    this.navigateTo = navigateTo;

    setupTitleLabel();
    setupAnswer();
    setupResultLabel();
    setupSubmitContinueButton();
    setupReturnButton();

    setupLayout();
  }

  private void setupTitleLabel() {
    titleLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 36));
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    titleLabel.setText("What's {word} in {language}?");
  }

  private void setupResultLabel() {
    resultLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
    resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
    resultLabel.setText("Result");
  }

  private void setupAnswer() {
    answerField.setColumns(30);
    answerField.setRows(1);
    answerPanel.setBorder(BorderFactory.createTitledBorder("Your Answer"));
    answerPanel.setToolTipText("Please type your answer");
    answerPanel.add(answerField);
  }

  private void setupSubmitContinueButton() {
    submitContinueButton.setText("Submit / Continue");
    submitContinueButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        // TODO: submit / continue
      }
    });
  }

  private void setupReturnButton() {
    returnButton.setText("{Return}");
    returnButton.addActionListener(new ActionListener() {
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
                    .addComponent(titleLabel,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resultLabel,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(submitContinueButton,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(returnButton,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, 50)))
                .addContainerGap()));

    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout
                .createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel,
                    GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(answerPanel,
                    GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultLabel,
                    GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(submitContinueButton,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(returnButton,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap()));
  }
}

package gui.features;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import gui.lib.Environment;
import gui.lib.NavigateTo;

public class Menu extends JPanel {

  private final NavigateTo navigateTo;

  private final JLabel titleLabel = new JLabel();
  private final JButton learnButton = new JButton();
  private final JButton settingsButton = new JButton();

  public Menu(Environment env, NavigateTo navigateTo) {
    this.navigateTo = navigateTo;

    setupTitleLabel();
    setupLearnButton();
    setupSettingsButton();
    setupLayout();
  }

  private void setupTitleLabel() {
    titleLabel.setFont(new Font("Helvetica Neue", 0, 36));
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    titleLabel.setText("Hello there!");
  }

  private void setupLearnButton() {
    learnButton.setText("Learn language");
    learnButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        navigateTo.execute("learn");
      }
    });
  }

  private void setupSettingsButton() {
    settingsButton.setText("Settings");
    settingsButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        navigateTo.execute("settings");
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
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(learnButton, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(settingsButton, GroupLayout.PREFERRED_SIZE, 108,
                            GroupLayout.PREFERRED_SIZE)))
                .addContainerGap()));

    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(learnButton, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(settingsButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE))
                .addContainerGap()));
  }

}

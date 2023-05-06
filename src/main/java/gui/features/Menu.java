package gui.features;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gui.lib.Environment;
import gui.lib.NavigateTo;

public class Menu extends JPanel {

  private final Environment env;
  private final NavigateTo navigateTo;

  private JLabel title;
  private JButton learn;
  private JButton settings;

  public Menu(Environment env, NavigateTo navigateTo) {
    this.env = env;
    this.navigateTo = navigateTo;

    setupTitleLabel();
    setupLearnButton();
    setupSettingsButton();
    setupLayout();
  }

  private void setupTitleLabel() {
    title = new JLabel();
    title.setFont(new Font("Helvetica Neue", 0, 36));
    title.setHorizontalAlignment(SwingConstants.CENTER);
    title.setText("Hello there, " + env.getLearner().getName() + "!");
  }

  private void setupLearnButton() {
    learn = new JButton();
    learn.setText("Learn language");
    learn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        navigateTo.execute("learn");
      }
    });
  }

  private void setupSettingsButton() {
    settings = new JButton();
    settings.setText("Settings");
    settings.addActionListener(new ActionListener() {
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
                    .addComponent(title,
                        GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(learn,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(settings,
                            GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap()));

    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title,
                    GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(learn,
                        GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(settings,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap()));
  }

}

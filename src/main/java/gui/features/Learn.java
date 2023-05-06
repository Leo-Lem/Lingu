package gui.features;

import javax.swing.*;

import backend.model.Language;
import backend.services.interfaces.Localizer;
import gui.comps.*;

public class Learn extends JPanel {

  private TitleLabel questionLabel = new TitleLabel("");
  private TextField answerField;
  private JLabel resultLabel;
  private ActionButton submitContinueButton;
  private ActionButton returnButton;

  public Learn(Localizer localizer, ActionButton.Action submitContinue, ActionButton.Action backToMenu) {
    answerField = new TextField("", "");
    resultLabel = new JLabel("");
    resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
    submitContinueButton = new ActionButton("", submitContinue);
    returnButton = new ActionButton("", backToMenu);
    update(localizer, "", Language.ENGLISH, "", true);
    layOut();
  }

  public void update(Localizer localizer, String word, Language target, String localizedResult, Boolean isSubmit) {
    questionLabel.setText(localizer.localize("QUESTION", word, localizer.localize(target)));
    answerField.setLabel(localizer.localize("YOUR_ANSWER"));
    resultLabel.setText(localizedResult);
    submitContinueButton.setLabel(localizer.localize(isSubmit ? "SUBMIT" : "CONTINUE"));
    returnButton.setLabel(localizer.localize("BACK_TO_MENU"));
  }

  private void layOut() {
    GroupLayout layout = new GroupLayout(this);

    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(
                    GroupLayout.Alignment.LEADING)
                    .addComponent(answerField,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(questionLabel,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(resultLabel,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(submitContinueButton,
                            GroupLayout.DEFAULT_SIZE,
                            GroupLayout.DEFAULT_SIZE,
                            Short.MAX_VALUE)
                        .addPreferredGap(
                            LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(returnButton,
                            GroupLayout.DEFAULT_SIZE,
                            GroupLayout.DEFAULT_SIZE,
                            50)))
                .addContainerGap()));

    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout
                .createSequentialGroup()
                .addContainerGap()
                .addComponent(questionLabel,
                    GroupLayout.DEFAULT_SIZE,
                    GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(answerField,
                    GroupLayout.PREFERRED_SIZE,
                    GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultLabel,
                    GroupLayout.PREFERRED_SIZE, 38,
                    GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(
                    GroupLayout.Alignment.LEADING, false)
                    .addComponent(submitContinueButton,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(returnButton,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE))
                .addContainerGap()));

    setLayout(layout);
  }
}

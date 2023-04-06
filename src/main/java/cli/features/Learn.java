package cli.features;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.concurrent.CancellationException;

import backend.model.*;
import cli.lib.*;

public class Learn {
  private final Environment env;

  public Learn(Environment env) {
    this.env = env;
  }

  public void run() throws CancellationException {
    Learner learner = env.getLearner();

    env.getPrinter().println(env.getLocalizedMessage()
        .set("LETS_START_LEARNING", learner.getTarget()).asLingu().build());

    while (true) {
      Vocabulary relevantVocabulary = learner.getVocabulary().get(learner.getSource(), learner.getTarget());

      if (relevantVocabulary.count() < 8) {
        Vocabulary newVocabulary = generateVocabulary(8);
        relevantVocabulary.add(newVocabulary.get());
        learner.getVocabulary().add(newVocabulary.get());
        env.setLearner(learner);
      }

      for (Vocab vocab : relevantVocabulary.prefix(8)) {
        Optional<String> translation = env.getTranslator().translate(vocab);

        if (translation.isPresent()) {
          String answer = askQuestion(vocab);

          env.getPrinter().println(env.getLocalizedMessage()
              .set("ACKNOWLEDGE_ANSWER", answer).asLingu().build());

          handleAnswer(answer, translation.get(), vocab);

          env.setLearner(learner);
        }
      }

      askIfShouldContinue(relevantVocabulary);
    }
  }

  private void askIfShouldContinue(Vocabulary relevantVocabulary) {
    env.getPrinter().println(env.getLocalizedMessage()
        .set("END_OF_LEARNING", relevantVocabulary.get().length).asLingu().build());

    env.getPrinter().println(env.getLocalizedMessage()
        .set("CONTINUE_LEARNING_YES_OR_NO", "YES_SYMBOL", "NO_SYMBOL").asLingu().build());

    if (!env.getPrompter().readYesOrNo(env.getLearner().getName()))
      throw new CancellationException();
  }

  private String askQuestion(Vocab vocab) {
    env.getPrinter().println(env.getLocalizedMessage()
        .set("HOW_TO_TRANSLATE", vocab.getWord(), vocab.getTarget()).asLingu().build());

    return env.getPrompter().readNextString(env.getLearner().getName());
  }

  private void handleAnswer(String answer, String translation, Vocab vocab) {
    if (answer.equalsIgnoreCase(translation)) {
      env.getPrinter().println(env.getLocalizedMessage()
          .set(
              "ANSWER_IS_CORRECT", vocab.getNextUp().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a")))
          .asLingu().build());
      vocab.advanceStage();
    } else {
      env.getPrinter().println(env.getLocalizedMessage()
          .set("ANSWER_IS_WRONG", translation).asLingu().build());
      vocab.resetStage();
    }
  }

  private Vocabulary generateVocabulary(Integer count) {
    Vocabulary vocabulary = new Vocabulary();

    String[] ids = env.getWordGenerator().generateWords(Language.BASE, count);

    for (String id : ids) {
      Optional<String> word = env.getTranslator().translate(id, Language.BASE, env.getLearner().getSource());

      if (word.isPresent()) {
        Vocab vocab = new Vocab(word.get(), env.getLearner().getSource(), env.getLearner().getTarget());

        if (!vocabulary.contains(vocab))
          vocabulary.add(vocab);
      }
    }

    return vocabulary;
  }

}

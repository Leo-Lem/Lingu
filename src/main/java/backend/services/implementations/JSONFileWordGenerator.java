package backend.services.implementations;

import java.util.*;

import backend.model.*;
import backend.services.interfaces.*;

public class JSONFileWordGenerator implements WordGenerator {

  private final String[] words;

  public JSONFileWordGenerator() throws IllegalStateException {
    this(new JSONFilePersistor<>(String[].class, "src/main/resources/words.json"));
  }

  public JSONFileWordGenerator(Persistor<String[]> persistor) throws IllegalStateException {
    try {
      this.words = persistor.load().get();
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("The translations file cannot be located…");
    } catch (ClassCastException e) {
      throw new IllegalStateException("The translations file is corrupted and needs to be replaced…");
    }
  }

  @Override
  public Language[] getSupportedLanguages() {
    return new Language[] { Language.BASE };
  }

  @Override
  public String[] generateWords(Language language, Integer count) {
    if (!Arrays.asList(getSupportedLanguages()).contains(language))
      throw new IllegalArgumentException("Language is not available: " + language);
    else if (count < 1)
      throw new IllegalArgumentException("Count needs to be larger than 0");
    else if (count > words.length)
      throw new IllegalArgumentException("Count is greater than available words");

    Random rand = new Random();
    String[] subset = Arrays.copyOf(words, count);
    for (int i = 0; i < count; i++) {
      subset[i] = words[rand.nextInt(words.length)];
    }
    return subset;
  }

}

package backend.services.json;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import backend.model.Language;
import backend.services.interfaces.Localizer;
import backend.services.interfaces.Persistor;

public class JSONFileLocalizer implements Localizer {

  private Language language;
  private final Map<String, Map<String, String>> localizations;

  public JSONFileLocalizer() {
    this(Language.defaultLanguage());
  }

  public JSONFileLocalizer(Language language) {
    this(language, new JSONFilePersistor("src/main/resources/localizations.json"));
  }

  @SuppressWarnings("unchecked")
  public JSONFileLocalizer(Language language, Persistor persistor)
      throws IllegalArgumentException, IllegalStateException {
    if (!Arrays.asList(getSupportedLanguages()).contains(language))
      throw new IllegalArgumentException("Language is not available: " + language);

    this.language = language;

    try {
      this.localizations = persistor.load(Map.class).get();
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("The localizations file cannot be located…");
    } catch (ClassCastException e) {
      throw new IllegalStateException("The localizations file for " + language + " is corrupted…");
    }
  }

  @Override
  public Language[] getSupportedLanguages() {
    return new Language[] { Language.BASE, Language.ENGLISH, Language.GERMAN, Language.SPANISH };
  }

  @Override
  public Language getLanguage() {
    return language;
  }

  @Override
  public Language setLanguage(Language language) {
    return this.language = language;
  }

  @Override
  public String localize(Object key, Object... arguments) {
    String formatSpecifier = IntStream.range(0, arguments.length)
        .mapToObj(i -> " %s")
        .collect(Collectors.joining(""));
    Object[] localizedArguments = Arrays.stream(arguments)
        .map(this::localize)
        .toArray();

    return String.format(localize(key + formatSpecifier), localizedArguments);
  }

  public String localize(Object key) {
    return Optional.ofNullable(localizations.get(key.toString()))
        .flatMap(localization -> Optional.ofNullable(localization.get(language.getCode())))
        .orElse(key.toString());
  }

}

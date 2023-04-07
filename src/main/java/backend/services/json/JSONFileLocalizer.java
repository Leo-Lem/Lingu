package backend.services.json;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.fasterxml.jackson.annotation.*;

import backend.model.*;
import backend.services.interfaces.*;

public class JSONFileLocalizer implements Localizer {

  @JsonFormat(shape = JsonFormat.Shape.OBJECT)
  public static class Localizations {
    private Map<String, Map<String, String>> localizations;

    @JsonCreator
    public Localizations(Map<String, Map<String, String>> localizations) {
      this.localizations = localizations;
    }

    @JsonValue
    public Map<String, Map<String, String>> getLocalizations() {
      return localizations;
    }

    public Optional<String> get(Object key, Language language) {
      return Optional.ofNullable(localizations.get(key.toString()))
          .flatMap(localization -> Optional.ofNullable(localization.get(language.getCode())));
    }
  }

  private Language language;
  private final Localizations localizations;

  public JSONFileLocalizer() {
    this(Language.defaultLanguage());
  }

  public JSONFileLocalizer(Language language) {
    this(language, new JSONFilePersistor<>(Localizations.class, "src/main/resources/localizations.json"));
  }

  public JSONFileLocalizer(Language language, Persistor<Localizations> persistor)
      throws IllegalArgumentException, IllegalStateException {
    if (!Arrays.asList(getSupportedLanguages()).contains(language))
      throw new IllegalArgumentException("Language is not available: " + language);

    this.language = language;

    try {
      this.localizations = persistor.load().get();
    } catch (Exception e) {
      throw new IllegalStateException("The localizations file cannot be loaded: " + e.getMessage());
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
    return localizations.get(key, language).orElse(key.toString());
  }

}

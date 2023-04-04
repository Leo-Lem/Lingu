package lingu.services;

import java.util.Optional;

import lingu.model.Language;

public interface Localizer {

  public Language getLanguage();

  public Language setLanguage(Language language);

  public Optional<String> localize(String key);

}

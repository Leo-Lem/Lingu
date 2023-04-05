package lingu.services.interfaces;

import lingu.model.Language;

public interface Localizer {

  public Language[] getAvailableLanguages();

  public Language getLanguage();

  public Language setLanguage(Language language);

  public String localize(String key);

  public String localizeWithStringArgument(String key, String argument);

}

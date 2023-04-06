package backend.services.interfaces;

import backend.model.Language;

public interface Localizer extends SupportsLanguages {

  public Language getLanguage();

  public Language setLanguage(Language language);

  public String localize(Object key, Object... argument);

}

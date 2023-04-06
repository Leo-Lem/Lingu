package backend.services.interfaces;

import backend.model.Language;

public interface WordGenerator extends SupportsLanguages {

  public String[] generateWords(Language language, Integer count);

}

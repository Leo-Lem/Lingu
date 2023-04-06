package backend.services.interfaces;

import java.util.Optional;

import backend.model.*;

public interface Translator extends SupportsLanguages {

  public Optional<String> translate(Vocab vocab);

  public Optional<String> translate(String word, Language source, Language target);

}

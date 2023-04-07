package backend;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

import backend.model.Language;
import backend.services.interfaces.Translator;
import backend.services.json.JSONFileTranslator;

public class JSONFileTranslatorTests {

  @Test
  public void whenTranslatingHouseToSpanish_thenReturnsCasa() {
    Translator translator = new JSONFileTranslator();

    Optional<String> translation = translator.translate("house", Language.ENGLISH, Language.SPANISH);

    assertEquals(translation, Optional.of("casa"));
  }

  @Test
  public void whenTranslatingNoWord_thenReturnsNothing() {
    Translator translator = new JSONFileTranslator();

    Optional<String> translation = translator.translate("NGX678", Language.ENGLISH, Language.SPANISH);

    assertFalse(translation.isPresent());
  }

}

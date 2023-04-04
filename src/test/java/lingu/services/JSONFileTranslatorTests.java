package lingu.services;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

import lingu.model.Language;
import lingu.services.interfaces.Translator;

public class JSONFileTranslatorTests {

  @Test
  public void whenTranslatingHouseToSpanish_thenReturnsCasa() {
    Translator translator = new JSONFileTranslator(Language.ENGLISH, Language.SPANISH);

    Optional<String> translation = translator.translate("house");

    assertEquals(translation, Optional.of("casa"));
  }

  @Test
  public void whenTranslatingNoWord_thenReturnsNothing() {
    Translator translator = new JSONFileTranslator(Language.ENGLISH, Language.SPANISH);

    Optional<String> translation = translator.translate("NGX678");

    assertFalse(translation.isPresent());
  }

}

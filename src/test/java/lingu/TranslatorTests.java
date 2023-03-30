package lingu;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.*;

import lingu.services.Translator;

public abstract class TranslatorTests {

  protected Translator translator;

  @Before
  public abstract void setup();

  @Test
  public void whenTranslatingHouse_thenReturnsSomething() {
    Optional<String> translation = translator.translate("house");

    assertTrue(translation.isPresent());
  }

  @Test
  public void whenTranslatingNoWord_thenReturnsNothing() {
    Optional<String> translation = translator.translate("NGX678");

    assertFalse(translation.isPresent());
  }

}

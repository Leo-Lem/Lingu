package lingu;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

import lingu.model.Language;
import lingu.services.JSONFileLocalizer;
import lingu.services.Localizer;

public class JSONFileLocalizerTests {

  @Test
  public void whenLocalizingWelcomeMessageInEnglish_thenReturnsSomeString() {
    Localizer localizer = new JSONFileLocalizer(Language.ENGLISH);

    Optional<String> welcomeMessage = localizer.localize("WELCOME_MESSAGE");

    assertTrue(welcomeMessage.isPresent());
  }

  @Test
  public void whenLocalizingUnknownKey_thenReturnsEmpty() {
    Localizer localizer = new JSONFileLocalizer(Language.ENGLISH);

    Optional<String> welcomeMessage = localizer.localize("XYZ123");

    assertFalse(welcomeMessage.isPresent());
  }

}

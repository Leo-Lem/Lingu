package lingu.services;

import static org.junit.Assert.*;

import org.junit.Test;

import lingu.model.Language;
import lingu.services.interfaces.Localizer;

public class JSONFileLocalizerTests {

  @Test
  public void whenLocalizingSubtitle_thenReturnSomeLocalization() {
    Localizer localizer = new JSONFileLocalizer(Language.ENGLISH);

    String key = "SUBTITLE";
    String localized = localizer.localize(key);

    assertNotEquals(key, localized);
  }

  @Test
  public void whenLocalizingUnknownKey_thenReturnsKey() {
    Localizer localizer = new JSONFileLocalizer(Language.ENGLISH);

    String key = "XYZ123";
    String localized = localizer.localize(key);

    assertEquals(key, localized);
  }

}

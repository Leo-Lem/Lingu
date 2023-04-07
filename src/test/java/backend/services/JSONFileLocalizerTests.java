package backend.services;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.model.Language;
import backend.services.interfaces.Localizer;
import backend.services.json.JSONFileLocalizer;

public class JSONFileLocalizerTests {

  private Localizer localizer = new JSONFileLocalizer(Language.ENGLISH);

  @Test
  public void whenLocalizingSubtitle_thenReturnSomeLocalization() {
    String key = "SUBTITLE";

    String localized = localizer.localize(key);

    assertEquals(localized, "Your language companion");
  }

  @Test
  public void whenLocalizingUnknownKey_thenReturnsKey() {
    String key = "XYZ123";

    String localized = localizer.localize(key);

    assertEquals(key, localized);
  }

  @Test
  public void whenLocalizingWithArgument_thenReturnsSomeLocalization() {
    String key = "NEW_LOCALE";
    Language argument = Language.GERMAN;

    String localized = localizer.localize(key, argument);

    assertEquals(String.format("Wonderful! We'll speak German…"), localized);
  }

  @Test
  public void whenLocalizingUnknownWithArgument_thenReturnsKeyWithArgument() {
    String key = "XYZ123", argument = "James";

    String localized = localizer.localize(key, argument);

    assertEquals(String.format("XYZ123 James"), localized);
  }

}

package lingu;

import lingu.model.Language;
import lingu.services.OfflineTranslator;

public class OfflineTranslatorTests extends TranslatorTests {

  @Override
  public void setup() {
    super.translator = new OfflineTranslator(Language.ENGLISH, Language.GERMAN);
  }

}

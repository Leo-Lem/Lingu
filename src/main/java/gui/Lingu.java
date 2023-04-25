package gui;

import backend.model.Language;
import backend.model.Learner;
import backend.services.implementations.DerbyDBPersistor;
import backend.services.interfaces.Persistor;

public class Lingu {
  public static void main(String[] args) {
    Persistor<Learner> persistor = new DerbyDBPersistor();

    persistor.save(
        new Learner()
            .setName("Leo")
            .setLocale(Language.ENGLISH)
            .setSource(Language.ENGLISH)
            .setTarget(Language.SPANISH));
  }
}

package lib;

import java.util.Optional;

import backend.services.interfaces.Persistor;

public class InMemoryPersistor<T> implements Persistor<T> {

  private T persisted = null;

  @Override
  public Optional<T> load() {
    return Optional.ofNullable(persisted);
  }

  @Override
  public void save(T object) {
    persisted = object;
  }

}

package lingu.services;

import java.util.Optional;

public interface Persistor<T> {

  public void save(T object);

  public Optional<T> load();

}

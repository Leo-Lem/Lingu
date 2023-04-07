package backend.services.interfaces;

import java.util.Optional;

public interface Persistor<T> {

  Optional<T> load();

  void save(T object);

}
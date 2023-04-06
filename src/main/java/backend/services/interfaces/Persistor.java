package backend.services.interfaces;

import java.util.Optional;

public interface Persistor {

  String getPathname();

  <T> Optional<T> load(Class<T> type);

  <T> void save(T object);

}
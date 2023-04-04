package lingu.services.interfaces;

import java.io.File;
import java.util.Optional;

public interface FileHandler {

  <T> Optional<T> load(String pathname, Class<T> type);

  <T> void save(T object, String pathname);

  <T> Optional<T> load(File file, Class<T> type);

  <T> void save(T object, File file);

}
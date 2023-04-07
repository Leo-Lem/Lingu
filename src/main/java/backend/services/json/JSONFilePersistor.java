package backend.services.json;

import java.io.*;
import java.util.Optional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import backend.services.interfaces.Persistor;

public class JSONFilePersistor<T> implements Persistor<T> {

  private final ObjectMapper mapper;
  private final Class<T> type;
  private final String pathname;

  public JSONFilePersistor(Class<T> type, String pathname) {
    this(type, pathname, new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT));
  }

  public JSONFilePersistor(Class<T> type, String pathname, ObjectMapper mapper) {
    this.type = type;
    this.pathname = pathname;
    this.mapper = mapper;
  }

  @Override
  public Optional<T> load() {
    File file = new File(pathname);

    try (Reader reader = new FileReader(file)) {
      return Optional.of(mapper.readValue(reader, type));
    } catch (IOException e) {
    } catch (Exception e) {
      System.err.println("Error loading file at " + file.getAbsolutePath() + ": " + e.getMessage());
    }

    return Optional.empty();
  }

  @Override
  public void save(T object) {
    File file = new File(pathname);

    try (Writer writer = new FileWriter(file)) {
      mapper.writeValue(writer, object);
    } catch (IOException e) {
    } catch (Exception e) {
      System.err.println("Error loading file at " + file.getAbsolutePath() + ": " + e.getMessage());
    }
  }

}

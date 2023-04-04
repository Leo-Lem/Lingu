package lingu.services;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lingu.services.interfaces.FileHandler;

public class JSONFileHandler implements FileHandler {

  private final ObjectMapper MAPPER;

  public JSONFileHandler() {
    this(new ObjectMapper().registerModule(new JavaTimeModule()));
  }

  public JSONFileHandler(ObjectMapper mapper) {
    this.MAPPER = mapper;
  }

  @Override
  public <T> Optional<T> load(String pathname, Class<T> type) {
    return load(new File(pathname), type);
  }

  @Override
  public <T> void save(T object, String pathname) {
    save(object, new File(pathname));
  }

  @Override
  public <T> Optional<T> load(File file, Class<T> type) {
    try (Reader reader = new FileReader(file)) {
      return Optional.of(MAPPER.readValue(reader, type));
    } catch (IOException e) {
      // System.err.println("Error loading file at " + file.getAbsolutePath() + ": " +
      // e.getMessage());
      return Optional.empty();
    }
  }

  @Override
  public <T> void save(T object, File file) {
    try (Writer writer = new FileWriter(file)) {
      MAPPER.writeValue(writer, object);
    } catch (IOException e) {
      // System.err.println("Error saving file to : " + file.getAbsolutePath() + ": "
      // + e.getMessage());
    }
  }

}

package backend.services.json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import backend.services.interfaces.Persistor;

public class JSONFilePersistor implements Persistor {

  private final ObjectMapper mapper;
  private String pathname;

  public JSONFilePersistor(String pathname) {
    this(pathname, new ObjectMapper().registerModule(new JavaTimeModule()));
  }

  public JSONFilePersistor(String pathname, ObjectMapper mapper) {
    this.pathname = pathname;
    this.mapper = mapper;
  }

  @Override
  public String getPathname() {
    return pathname;
  }

  @Override
  public <T> Optional<T> load(Class<T> type) {
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
  public <T> void save(T object) {
    File file = new File(pathname);

    try (Writer writer = new FileWriter(file)) {
      mapper.writeValue(writer, object);
    } catch (IOException e) {
    } catch (Exception e) {
      System.err.println("Error loading file at " + file.getAbsolutePath() + ": " + e.getMessage());
    }
  }

}

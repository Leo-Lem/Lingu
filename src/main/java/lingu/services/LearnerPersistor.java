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

import lingu.model.Learner;

public class LearnerPersistor implements Persistor<Learner> {

  private final File FILE;
  private final ObjectMapper MAPPER;

  public LearnerPersistor() {
    this.FILE = new File("target/learner.json");
    this.MAPPER = new ObjectMapper();
    MAPPER.registerModule(new JavaTimeModule());
  }

  @Override
  public void save(Learner learner) {
    try (Writer writer = new FileWriter(FILE)) {
      MAPPER.writeValue(writer, learner);
    } catch (IOException e) {
      System.err.println("Error saving learner: " + e.getMessage());
    }
  }

  @Override
  public Optional<Learner> load() {
    try (Reader reader = new FileReader(FILE)) {
      Learner learner = MAPPER.readValue(reader, Learner.class);
      return Optional.of(learner);
    } catch (IOException e) {
      System.err.println("Error loading learner: " + e.getMessage());
      return Optional.empty();
    }
  }

}

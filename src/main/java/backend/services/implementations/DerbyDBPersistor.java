package backend.services.implementations;

import java.sql.*;
import java.util.Optional;

import backend.model.Learner;
import backend.services.interfaces.Persistor;

public class DerbyDBPersistor implements Persistor<Learner> {

  private final Connection connection;

  public DerbyDBPersistor() {
    try {
      connection = DriverManager.getConnection("jdbc:derby:LinguDB;create=true");
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to connect to database.");
    }
  }

  @Override
  public Optional<Learner> load() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'load'");
  }

  @Override
  public void save(Learner object) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
    // Statement statement = connection.createStatement();
  }

}

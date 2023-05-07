package backend.services.implementations;

import backend.model.*;
import backend.services.interfaces.Persistor;
import java.sql.*;
import java.util.Optional;
import org.apache.derby.jdbc.EmbeddedDriver;

public class DerbyDBPersistor implements Persistor<Learner> {

  private static final String CONNECTION_URL = "jdbc:derby:target/database;create=true";
  private final EmbeddedDriver driver = new EmbeddedDriver();

  public DerbyDBPersistor() {
    try (Connection connection = driver.connect(CONNECTION_URL, null)) {
      String CREATE_LEARNER_SQL = "CREATE TABLE learners ("
              + "id INT PRIMARY KEY NOT NULL,"
              + "name VARCHAR(255),"
              + "locale CHAR(2),"
              + "source CHAR(2),"
              + "target CHAR(2)"
              + ")";

      String CREATE_VOCAB_SQL = "CREATE TABLE vocabs ("
              + "word VARCHAR(255),"
              + "source CHAR(2),"
              + "target CHAR(2),"
              + "stage INT,"
              + "nextUp TIMESTAMP,"
              + "learner_id INT,"
              + "PRIMARY KEY (word, source, target, learner_id),"
              + "FOREIGN KEY (learner_id) references learners"
              + ")";

      try {
        connection.createStatement().execute(CREATE_LEARNER_SQL);
      } catch (SQLException e) {
        if (e.getSQLState().equals("X0Y32")) {
          return;
        } else {
          throw e;
        }
      }

      try {
        connection.createStatement().execute(CREATE_VOCAB_SQL);
      } catch (SQLException e) {
        if (e.getSQLState().equals("42X05")) {
          return;
        } else {
          throw e;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to connect to database.");
    }
  }

  @Override
  public Optional<Learner> load() {
    try (Connection connection = driver.connect(CONNECTION_URL, null)) {
      String selectLearnerSQL = "SELECT name, locale, source, target FROM learners WHERE id=1";
      ResultSet result = connection.createStatement().executeQuery(selectLearnerSQL);

      if (result.next()) {
        Learner learner = deserializeLearner(result);

        String selectVocabsSQL = "SELECT word, source, target, stage, nextUp FROM vocabs WHERE learner_id=1";
        PreparedStatement selectVocabsStatement = connection.prepareStatement(selectVocabsSQL);
        learner.setVocabulary(deserializeVocabulary(selectVocabsStatement.executeQuery()));

        return Optional.of(learner);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return Optional.empty();
  }

  @Override
  public void save(Learner learner) {
    try (Connection connection = driver.connect(CONNECTION_URL, null)) {
      serializeLearner(connection, learner).executeUpdate();

      for (Vocab vocab : learner.getVocabulary()) {
        serializeVocab(connection, vocab).executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private Learner deserializeLearner(ResultSet result) throws SQLException {
    Learner learner = new Learner();
    learner.setName(result.getString("name"));
    learner.setLocale(Language.valueOfCode(result.getString("locale")));
    learner.setSource(Language.valueOfCode(result.getString("source")));
    learner.setTarget(Language.valueOfCode(result.getString("target")));
    return learner;
  }

  private Vocabulary deserializeVocabulary(ResultSet results) throws SQLException {
    Vocabulary vocabulary = new Vocabulary();

    while (results.next()) {
      Vocab vocab = new Vocab(
              results.getString("word"),
              Language.valueOfCode(results.getString("source")),
              Language.valueOfCode(results.getString("target")),
              Integer.parseInt(results.getString("stage")),
              results.getTimestamp("nextUp").toLocalDateTime());

      vocabulary.add(vocab);
    }

    return vocabulary;
  }

  private PreparedStatement serializeLearner(Connection connection, Learner learner) throws SQLException {
    boolean learnerExists = learnerExists(connection, learner);

    String upsertLearnerSQL = learnerExists
            ? "UPDATE learners SET name=?, locale=?, source=?, target=? WHERE id=1"
            : "INSERT INTO learners (id, name, locale, source, target) VALUES (1, ?, ?, ?, ?)";

    PreparedStatement statement = connection.prepareStatement(upsertLearnerSQL);

    if (learnerExists) {
      statement.setString(1, learner.getName());
      statement.setString(2, learner.getLocale().getCode());
      statement.setString(3, learner.getSource().getCode());
      statement.setString(4, learner.getTarget().getCode());
    } else {
      if (learner.getName() != null) {
        statement.setString(1, learner.getName());
      } else {
        statement.setNull(1, Types.VARCHAR);
      }

      statement.setString(2, learner.getLocale().getCode());

      if (learner.getSource() != null) {
        statement.setString(3, learner.getSource().getCode());
      } else {
        statement.setNull(3, Types.VARCHAR);
      }

      if (learner.getTarget() != null) {
        statement.setString(4, learner.getTarget().getCode());
      } else {
        statement.setNull(4, Types.VARCHAR);
      }
    }

    return statement;
  }

  private PreparedStatement serializeVocab(Connection connection, Vocab vocab) throws SQLException {
    boolean vocabExists = vocabExists(connection, vocab);

    String upsertVocabSQL = vocabExists
            ? "UPDATE vocabs SET stage=?, nextUp=?"
            : "INSERT INTO vocabs (word, source, target, stage, nextUp, learner_id) VALUES (?, ?, ?, ?, ?, 1)";

    PreparedStatement statement = connection.prepareStatement(upsertVocabSQL);

    if (vocabExists) {
      statement.setInt(1, vocab.getStage());
      statement.setObject(2, Timestamp.valueOf(vocab.getNextUp()));
    } else {
      statement.setString(1, vocab.getWord());
      statement.setString(2, vocab.getSource().getCode());
      statement.setString(3, vocab.getTarget().getCode());
      statement.setInt(4, vocab.getStage());
      statement.setObject(5, Timestamp.valueOf(vocab.getNextUp()));
    }

    return statement;
  }

  private boolean learnerExists(Connection connection, Learner learner) throws SQLException {
    String query = "SELECT 1 FROM learners WHERE id=1";
    PreparedStatement statement = connection.prepareStatement(query);
    return statement.executeQuery().next();
  }

  private boolean vocabExists(Connection connection, Vocab vocab) throws SQLException {
    String query = "SELECT 1 FROM vocabs WHERE word=? AND source=? AND target=? AND learner_id=1";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, vocab.getWord());
    statement.setString(2, vocab.getSource().getCode());
    statement.setString(3, vocab.getTarget().getCode());
    return statement.executeQuery().next();
  }

}

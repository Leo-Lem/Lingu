package backend.services.implementations;

import java.sql.*;
import java.util.Optional;

import backend.model.*;
import backend.services.interfaces.Persistor;

public class DerbyDBPersistor implements Persistor<Learner> {

  private static final String CONNECTION_URL = "jdbc:derby:target/database;create=true";

  public DerbyDBPersistor() {
    try (Connection connection = DriverManager.getConnection(CONNECTION_URL)) {
      String CREATE_LEARNER_SQL = "CREATE TABLE learners ("
          + "name VARCHAR(255) PRIMARY KEY NOT NULL,"
          + "locale CHAR(2) NOT NULL,"
          + "source CHAR(2) NOT NULL,"
          + "target CHAR(2) NOT NULL"
          + ")";

      String CREATE_VOCAB_SQL = "CREATE TABLE vocabs ("
          + "word VARCHAR(255) NOT NULL,"
          + "source CHAR(2) NOT NULL,"
          + "target CHAR(2) NOT NULL,"
          + "stage INT NOT NULL,"
          + "nextUp TIMESTAMP NOT NULL,"
          + "learner_name VARCHAR(255) NOT NULL,"
          + "PRIMARY KEY (word, source, target, learner_name),"
          + "FOREIGN KEY (learner_name) references learners"
          + ")";

      Statement statement = connection.createStatement();
      statement.execute(CREATE_LEARNER_SQL);
      statement.execute(CREATE_VOCAB_SQL);
    } catch (SQLException e) {
      if (e.getErrorCode() != 30000) {
        e.printStackTrace();
        throw new RuntimeException("Failed to connect to database.");
      }
    }
  }

  @Override
  public Optional<Learner> load() {
    try (Connection connection = DriverManager.getConnection(CONNECTION_URL)) {
      String selectLearnerSQL = "SELECT name, locale, source, target FROM learners";
      ResultSet result = connection.createStatement().executeQuery(selectLearnerSQL);

      if (result.next()) {
        Learner learner = createLearner(result);

        String selectVocabsSQL = "SELECT word, source, target, stage, nextUp FROM vocabs WHERE learner_name=?";
        PreparedStatement selectVocabsStatement = connection.prepareStatement(selectVocabsSQL);
        selectVocabsStatement.setString(1, learner.getName());
        learner.setVocabulary(createVocabulary(selectVocabsStatement.executeQuery()));

        return Optional.of(learner);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return Optional.empty();
  }

  @Override
  public void save(Learner object) {
    try (Connection connection = DriverManager.getConnection(CONNECTION_URL)) {
      String upsertLearnerSQL = learnerExists(connection, object)
          ? "UPDATE learners SET locale=?, source=?, target=? WHERE name=?"
          : "INSERT INTO learners (name, locale, source, target) VALUES (?, ?, ?, ?)";
      setLearnerParameters(connection.prepareStatement(upsertLearnerSQL), object)
          .executeUpdate();

      for (Vocab vocab : object.getVocabulary()) {
        String upsertVocabSQL = vocabExists(connection, vocab, object.getName())
            ? "UPDATE vocabs SET stage=?, nextUp=?"
            : "INSERT INTO vocabs (word, source, target, stage, nextUp, learner_name) VALUES (?, ?, ?, ?, ?, ?)";

        setVocabParameters(connection.prepareStatement(upsertVocabSQL), vocab, object.getName())
            .executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private Learner createLearner(ResultSet result) throws SQLException {
    Learner learner = new Learner();
    learner.setName(result.getString("name"));
    learner.setLocale(Language.valueOfCode(result.getString("locale")));
    learner.setSource(Language.valueOfCode(result.getString("source")));
    learner.setTarget(Language.valueOfCode(result.getString("target")));
    return learner;
  }

  private Vocabulary createVocabulary(ResultSet results) throws SQLException {
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

  private PreparedStatement setLearnerParameters(PreparedStatement statement, Learner learner) throws SQLException {
    statement.setString(1, learner.getName());
    statement.setString(2, learner.getLocale().getCode());
    statement.setString(3, learner.getSource().getCode());
    statement.setString(4, learner.getTarget().getCode());
    return statement;
  }

  private PreparedStatement setVocabParameters(PreparedStatement statement, Vocab vocab, String name)
      throws SQLException {
    statement.setString(1, vocab.getWord());
    statement.setString(2, vocab.getSource().getCode());
    statement.setString(3, vocab.getTarget().getCode());
    statement.setInt(4, vocab.getStage());
    statement.setObject(5, Timestamp.valueOf(vocab.getNextUp()));
    statement.setString(6, name);
    return statement;
  }

  private boolean learnerExists(Connection connection, Learner learner) throws SQLException {
    String query = "SELECT 1 FROM learners WHERE name=?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, learner.getName());
    return statement.executeQuery().next();
  }

  private boolean vocabExists(Connection connection, Vocab vocab, String name) throws SQLException {
    String query = "SELECT 1 FROM vocabs WHERE word=? AND source=? AND target=? AND learner_name=?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, vocab.getWord());
    statement.setString(2, vocab.getSource().getCode());
    statement.setString(3, vocab.getTarget().getCode());
    statement.setString(4, name);
    return statement.executeQuery().next();
  }
}

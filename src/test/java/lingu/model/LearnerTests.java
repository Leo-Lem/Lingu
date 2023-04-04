package lingu.model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class LearnerTests {

  @Test
  public void givenStreakStartIsSet_whenGettingStreak_thenValueShouldMatch() {
    Learner learner = new Learner("Leo");
    Integer difference = 10;
    LocalDate streakStart = LocalDate.ofEpochDay(0);
    LocalDate current = LocalDate.ofEpochDay(difference);

    learner.setStreakStart(streakStart);
    assertEquals(learner.getStreak(current), difference);
  }

}

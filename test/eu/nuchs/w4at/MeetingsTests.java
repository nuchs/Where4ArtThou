package eu.nuchs.w4at;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class MeetingsTests {

  @Test
  public void ANewSetOfMeetingsShouldBeEmpty() {
    String expected = "{\"locations\":{}}";
    assertThat(gson.toJson(sut), is(equalTo(expected)));
  }

  @Test
  public void ACharacterAddedAtALocationShouldBeRecordedAgainstThatLocation() {
    sut.add("Pub", "Ford");
    String expected = "{\"locations\":{\"Pub\":[\"Ford\"]}}";
    assertThat(gson.toJson(sut), is(equalTo(expected)));
  }

  @Test
  public void TwoCharactersAddedAtTheSameLocationShouldBothBeRecordedAgainstThatLocation() {
    sut.add("Pub", "Ford");
    sut.add("Pub", "Arthur");
    String expected = "{\"locations\":{\"Pub\":[\"Ford\",\"Arthur\"]}}";
    assertThat(gson.toJson(sut), is(equalTo(expected)));
  }

  @Test
  public void TwoCharactersAddedAtDifferentLocationsShouldBeRecordedAgainstTheirLocation() {
    sut.add("Pub", "Ford");
    sut.add("Magrathea", "Slartibartfast");
    String expected = "{\"locations\":{\"Pub\":[\"Ford\"],\"Magrathea\":[\"Slartibartfast\"]}}";
    assertThat(gson.toJson(sut), is(equalTo(expected)));
  }

  @Test
  public void ACharacterShouldBeRecordedAgainstEachLocationAtMostOnce() {
    sut.add("Pub", "Ford");
    sut.add("Pub", "Ford");
    String expected = "{\"locations\":{\"Pub\":[\"Ford\"]}}";
    assertThat(gson.toJson(sut), is(equalTo(expected)));
  }

  @Before
  public void SetUp () {
    sut = new Meetings();
  }

  private Meetings sut;
  private static Gson gson = new Gson();
}

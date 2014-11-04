package eu.nuchs.w4at;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class NameMatcherTests {

    @Test(expected = IllegalArgumentException.class)
    public void AddingANullCharacterNameShouldFail() {
        sut.addName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void AddingAWhiteSpaceCharacterNameShouldFail() {
        sut.addName("   ");
    }

    @Test
    public void NoCharactersShouldMatchTheNullString() {
        sut.addName("The Cat");
        assertThat(sut.match(null), is(equalTo("")));
    }

    @Test
    public void NoCharactersShouldMatchTheEmptyString() {
        sut.addName("Polymorph");
        assertThat(sut.match(""), is(equalTo("")));
    }

    @Test
    public void NoCharactersShouldMatchAWhiteSpaceString() {
        sut.addName("Ace Rimmer");
        assertThat(sut.match("   "), is(equalTo("")));
    }

    @Test
    public void TheNameOfAMatchedCharacterShouldBeReturned() {
        sut.addName("Lister");
        assertThat(sut.match("Lister"), is(equalTo("lister")));
    }

    @Test
    public void IfTheCharacterNameDoesNotContainTheLetterInASingleLetterPatternNoMatchShouldBeReturned() {
        sut.addName("Rimmer");
        assertThat(sut.match("x"), is(equalTo("")));
    }

    @Test
    public void IfTheCharacterNameContainsTheLetterInASingleLetterPatternItShouldBeReturned() {
        sut.addName("Kryton");
        assertThat(sut.match("y"), is(equalTo("kryton")));
    }

    @Test
    public void MatchingShouldBeCaseInsensitive() {
        sut.addName("Chen");
        assertThat(sut.match("c"), is(equalTo("chen")));
    }

    @Test
    public void IfTwoCharactersHaveBeenAddedButOnlyOneMatchesTheMatchShouldBeReturned() {
        sut.addName("Queeg 500");
        sut.addName("Warden Knot");
        assertThat(sut.match("Q"), is(equalTo("queeg 500")));
    }
    
    @Test
    public void IfTwoCharacterNamesMatchTheShorterOneShouldBeReturned() {
        sut.addName("Duane Dibbley");
        sut.addName("Kochanski");
        assertThat(sut.match("a"), is(equalTo("kochanski")));
    }
    
    @Test
    public void IfACharacterNameContainsAllTheLettersInThePatternItShouldMatch() {
        sut.addName("Mr Flibble");
        assertThat(sut.match("M b"), is(equalTo("mr flibble")));
    }

    @Test
    public void IfANameContainsAllTheLettersInThePatternButInTheWrongOrderItShouldNotMatch() {
        sut.addName("Legion");
        assertThat(sut.match("noigel"), is(equalTo("")));
    }

    @Before
    public void SetUp () {
        sut = new NameMatcher();
    }

    private NameMatcher sut;
}

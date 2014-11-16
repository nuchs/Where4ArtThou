package eu.nuchs.w4at;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class LineAnalyserTests {
    
    @Test
    public void ALineStartingWithSCENEIsASceneLine() {
        assertThat(sut.analyse("SCENE 1"), is(equalTo(LineType.SCENE)));
    }

    @Test
    public void ALineStartingWithACTIsASceneLine() {
        assertThat(sut.analyse("ACT 1"), is(equalTo(LineType.SCENE)));
    }

    @Test
    public void ALineWithSCENEAfterTheStartIsNotASceneLine() {
        assertThat(sut.analyse(" BOB: This is my SCENE!"), is(not(equalTo(LineType.SCENE))));
    }

    @Test
    public void ALineStartingWithAWordWhosePrefixIsSCENEIsNotASceneLine() {
        assertThat(sut.analyse("SCENERY"), is(not(equalTo(LineType.SCENE))));
    }

    @Test
    public void ALineStartingWithAWordWhosePrefixIsACTIsNotASceneLine() {
        assertThat(sut.analyse("ACTING"), is(not(equalTo(LineType.SCENE))));
    }

    @Test
    public void ALineInWhichACharacterStartsToSpeakIsASpeakerLine() {
        assertThat(sut.analyse("  BOB. Hello World!"), is(equalTo(LineType.SPEAKER)));
    }

    @Test
    public void IfALineDoesNotStartWithWhiteSpaceItIsNotASpeakerLine() {
        assertThat(sut.analyse("BOB. Hello World!"), is(not(equalTo(LineType.SPEAKER))));
    }

    @Test
    public void ALineInWhichACharacterIsDescribedIsACharacterLine() {
        assertThat(sut.analyse("  BOB, The hero of the story!"), is(equalTo(LineType.CHARACTER)));
    }

    @Test
    public void IfALineDoesNotStartWithWhiteSpaceItIsNotACharacterLine() {
        assertThat(sut.analyse("BOB, The hero of the story!"), is(equalTo(LineType.UNCLASSIFIED)));
    }

    @Test
    public void ABlankLineShouldBeUnclassified() {
        assertThat(sut.analyse(""), is(equalTo(LineType.UNCLASSIFIED)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void RequestingTheSpeakerShouldFailForSceneLines() {
        sut.getSpeaker("SCENE II");
    }

    @Test
    public void RequestingTheSpeakerShouldGetTheNameOfTheSpeakerForASpeakerLine() {
        assertThat(sut.getSpeaker("  BOB. Hello World!"), is(equalTo("BOB")));
    }

    @Test
    public void RequestingTheCharacterShouldGetTheNameOfTheCharacterForACharacterLine() {
        assertThat(sut.getCharacter("  BOB, The hero of the story!"), is(equalTo("BOB")));
    }

    @Test
    public void TheStartOfTheCharacterListIsADramaisPersonaeLine() {
        assertThat(sut.analyse("DRAMATIS PERSONAE"), is(equalTo(LineType.DRAMATIS_PERSONAE)));
    }

    @Before
    public void SetUp () {
        sut = new LineAnalyser();
    }

    private LineAnalyser sut;
}

package eu.nuchs.w4at;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayAnalyserTests {

    @Test(expected = NullPointerException.class)
    public void YouMustProvideALineAnalyserOnCreation() {
        PlayAnalyser sut = new PlayAnalyser(null);
    }

    @Test
    public void ASceneShouldNotHaveAnyAssociationsToBeginWith() {
        List<Association> associations = sut.GetNewAssociations();
        assertThat(associations.isEmpty(), is(true));
    }

    @Test
    public void ACharacterDoesNotAssociateWithThemself() {
        sut.addLine("KIM JONG IL. I'm so ronery");
        List<Association> associations = sut.GetNewAssociations();
        assertThat(associations.isEmpty(), is(true));
    }

    @Test
    public void WhenTwoCharactersHaveSpokenTheyShouldBeAssociated() {
        Association expectedAssociation1 = new Association("DAVE", "HAL", "nowhere");
        Association expectedAssociation2 = new Association("HAL", "DAVE", "nowhere");

        sut.addLine("  DAVE. Open the pod bay doors, HAL");
        sut.addLine("  HAL. I'm sorry Dave. I'm afraid I can't do that");
        List<Association> associations = sut.GetNewAssociations();

        assertThat(associations, hasItem(expectedAssociation1));
        assertThat(associations, hasItem(expectedAssociation2));
    }

    @Test
    public void CharactersInOneSceneShouldNotBeAssociatedWithOnesFromAnotherScene() {
        sut.addLine("  DAVE. Open the pod bay doors, HAL");
        sut.addLine("  HAL. I'm sorry Dave. I'm afraid I can't do that");
        sut.addLine("SCENE IV");
        sut.addLine("  BUNGLE. How did I end up on Jupiter?");
        List<Association> associations = sut.GetNewAssociations();

        assertThat(associations.isEmpty(), is(true));
    }

    @Test
    public void WhenACharacterSpeaksAgainNoNewAssociationsShouldBeCreated() {
        sut.addLine("  DAVE. Open the pod bay doors, HAL");
        sut.addLine("  HAL. I'm sorry Dave. I'm afraid I can't do that");
        sut.addLine("  DAVE. Can I have my yoghurt back then?");
        List<Association> associations = sut.GetNewAssociations();

        assertThat(associations.isEmpty(), is(true));
    }

    @Test
    public void CharactersShouldBeAssociatedAtTheScenesDescription() {
        Association expectedAssociation1 = new Association("DAVE", "HAL", "Discovery 1");
        Association expectedAssociation2 = new Association("HAL", "DAVE", "Discovery 1");

        sut.addLine("SCENE IV");
        sut.addLine("Discovery 1");
        sut.addLine("  DAVE. Open the pod bay doors, HAL");
        sut.addLine("  HAL. I'm sorry Dave. I'm afraid I can't do that");
        List<Association> associations = sut.GetNewAssociations();

        assertThat(associations, hasItem(expectedAssociation1));
        assertThat(associations, hasItem(expectedAssociation2));
    }

    @Test
    public void ChangingSceneShouldChangeTheSceneDescription() {
        Association expectedAssociation1 = new Association("DAVE", "HAL", "Peru");
        Association expectedAssociation2 = new Association("HAL", "DAVE", "Peru");

        sut.addLine("SCENE IV");
        sut.addLine("Discovery 1");
        sut.addLine("  DAVE. Open the pod bay doors, HAL");
        sut.addLine("  HAL. I'm sorry Dave. I'm afraid I can't do that");
        sut.addLine("SCENE V");
        sut.addLine("Peru");
        sut.addLine("  DAVE. How did I get here?");
        sut.addLine("  HAL. I'm not sure");
        List<Association> associations = sut.GetNewAssociations();

        assertThat(associations, hasItem(expectedAssociation1));
        assertThat(associations, hasItem(expectedAssociation2));
    }

    @Before
    public void SetUp () {
        sut = new PlayAnalyser(new LineAnalyser());
    }

    private PlayAnalyser sut;
}

package eu.nuchs.w4at;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SceneAnalyserTests {

    @Test(expected = NullPointerException.class)
    public void YouMustProvideALineAnalyserOnCreation() {
        SceneAnalyser sut = new SceneAnalyser(null);
    }

    @Test
    public void ASceneShouldNotHaveAnyAssociationsToBeginWith() {
        SceneAnalyser sut = new SceneAnalyser(new LineAnalyser());
        List<Association> associations = sut.GetNewAssociations();
        assertThat(associations.isEmpty(), is(true));
    }

    @Test
    public void ACharacterDoesNotAssociateWithThemself() {
        SceneAnalyser sut = new SceneAnalyser(new LineAnalyser());
        sut.addLine("KIM JONG IL. I'm so ronery");
        List<Association> associations = sut.GetNewAssociations();
        assertThat(associations.isEmpty(), is(true));
    }

    @Test
    public void WhenTwoCharactersHaveSpokenTheyShouldBeAssociated() {
        SceneAnalyser sut = new SceneAnalyser(new LineAnalyser());
        Association expectedAssociation1 = new Association("DAVE", "HAL");
        Association expectedAssociation2 = new Association("HAL", "DAVE");

        sut.addLine("  DAVE. Open the pod bay doors, HAL");
        sut.addLine("  HAL. I'm sorry Dave. I'm afraid I can't do that");
        List<Association> associations = sut.GetNewAssociations();

        assertThat(associations, hasItem(expectedAssociation1));
        assertThat(associations, hasItem(expectedAssociation2));
    }

    @Test
    public void CharactersInOneSceneShouldNotBeAssociatedWithOnesFromAnotherScene() {
        SceneAnalyser sut = new SceneAnalyser(new LineAnalyser());

        sut.addLine("  DAVE. Open the pod bay doors, HAL");
        sut.addLine("  HAL. I'm sorry Dave. I'm afraid I can't do that");
        sut.addLine("SCENE IV");
        sut.addLine("  BUNGLE. How did I end up on Jupiter?");
        List<Association> associations = sut.GetNewAssociations();

        assertThat(associations.isEmpty(), is(true));
    }

    @Test
    public void WhenACharacterSpeaksAgainNoNewAssociationsShouldBeCreated() {
        SceneAnalyser sut = new SceneAnalyser(new LineAnalyser());

        sut.addLine("  DAVE. Open the pod bay doors, HAL");
        sut.addLine("  HAL. I'm sorry Dave. I'm afraid I can't do that");
        sut.addLine("  DAVE. Can I have my yoghurt back then?");
        List<Association> associations = sut.GetNewAssociations();

        assertThat(associations.isEmpty(), is(true));
    }
}

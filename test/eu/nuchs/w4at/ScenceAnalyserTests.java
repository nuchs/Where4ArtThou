package eu.nuchs.w4at;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScenceAnalyserTests {

    @Test
    public void ASceneShouldNotHaveAnyAssociationsToBeginWith() {
        SceneAnalyser sut = new SceneAnalyser();
        List<Association> associations = sut.GetNewAssociations();
        assertThat(associations.isEmpty(), is(true));
    }

}

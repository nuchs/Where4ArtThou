package eu.nuchs.w4at;

import com.google.gson.Gson;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CharactersTests {

    @Test(expected = IllegalArgumentException.class)
    public void YouMustProvideANonNullName() {
        Character sut = new Character(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void YouMustProvideANonWhitesspaceName() {
        Character sut = new Character("");
    }
    
    @Test
    public void ACharactersJSONRepresentationShouldHaveTheirName() {
        Character sut = new Character("Iago");
    
        assertThat(sut.toJson(), containsString("\"name\":\"Iago\""));
    }

    @Test
    public void AnAddedAssociateWillAppearInTheJSONRepresentation() {
        Character sut = new Character("Antipholus");
        sut.addAssociate("Dromio");
        assertThat(sut.toJson(), containsString("\"associates\":[\"Dromio\"]"));
    }

    @Test
    public void AllAssociatesShouldAppearInTheJSONRepresentation() {
        Character sut = new Character("Cordeilla");
        sut.addAssociate("Goneril");
        sut.addAssociate("Regan");
        assertThat(sut.toJson(), containsString("Goneril"));
        assertThat(sut.toJson(), containsString("Regan"));
    }

    @Test
    public void ACharactersJSONRepresentationShouldBeValid() {
        Character sut = new Character("Timon");
        gson.fromJson(sut.toJson(), Character.class);
    }

    Gson gson = new Gson();
}

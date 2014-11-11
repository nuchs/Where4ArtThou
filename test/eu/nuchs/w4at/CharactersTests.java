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
        sut.addAssociate("Dromio", "nowhere");
        assertThat(sut.toJson(), containsString("\"associates\":[\"Dromio\"]"));
    }

    @Test
    public void AllAssociatesShouldAppearInTheJSONRepresentation() {
        Character sut = new Character("Cordeilla");
        sut.addAssociate("Goneril", "nowhere");
        sut.addAssociate("Regan", "nowhere");
        assertThat(sut.toJson(), containsString("Goneril"));
        assertThat(sut.toJson(), containsString("Regan"));
    }

    @Test
    public void AddingAnAssociateMultipleTimesShouldAddItOnceToTheJSONRepresentation() {
        Character sut = new Character("Romeo");
        String associate = "Juliet";
        sut.addAssociate(associate, "nowhere");
        sut.addAssociate(associate, "nowhere");
        sut.addAssociate(associate, "nowhere");

        assertThat(countSubStringOccurences(associate, sut.toJson()), is(equalTo(1)));
    }

    @Test
    public void ACharacterShouldNotAppearInItsAssociatesListInTheJSONRepresentation() {
        String name = "Prospero";
        Character sut = new Character(name);
        sut.addAssociate(name, "nowhere");

        assertThat(countSubStringOccurences(name, sut.toJson()), is(equalTo(1)));
    }

    @Test
    public void ACharactersJSONRepresentationShouldBeValid() {
        Character sut = new Character("Timon");
        gson.fromJson(sut.toJson(), Character.class);
    }

    private int countSubStringOccurences(String associate, String json) {
        int count = 0;
        for (int i = json.indexOf(associate); i > -1; i = json.indexOf(associate, i+1)) {
            count++;
        }
        return count;
    }

    private Gson gson = new Gson();
}

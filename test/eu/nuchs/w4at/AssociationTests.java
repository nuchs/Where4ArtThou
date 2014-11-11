package eu.nuchs.w4at;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class AssociationTests {

    @Test(expected = IllegalArgumentException.class)
    public void YouMustProvideANonNullCharacterName() {
        Association sut = new Association(null, "Mr NoBody", "nowhere");
    }

    @Test(expected = IllegalArgumentException.class)
    public void YouMustProvideANonWhiteSpaceCharacterName() {
        Association sut = new Association("  ", "The Invisible Man", "nowhere");
    }

    @Test(expected = IllegalArgumentException.class)
    public void YouMustProvideANonNullAssociateName() {
        Association sut = new Association("Mr NoBody", null, "nowhere");
    }

    @Test(expected = IllegalArgumentException.class)
    public void YouMustProvideANonWhiteSpaceAssociateName() {
        Association sut = new Association("The Invisible Man", "  ", "nowhere");
    }

    @Test(expected = IllegalArgumentException.class)
    public void YouMustProvideANonNullSceneName() {
        Association sut = new Association("Mr NoBody", "The Invisible Man", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void YouMustProvideANonWhiteSpaceSceneName() {
        Association sut = new Association("The Invisible Man", "Mr Nobody", "  ");
    }

    @Test
    public void TheCharacterNameShouldMatchTheOneSpecifiedOnCreation() {
        Association sut = new Association("Adam", "Eve", "nowhere");
        assertThat(sut.getCharacter(), is(equalTo("Adam")));
    }

    @Test
    public void TheAssociateNameShouldMatchTheOneSpecifiedOnCreation() {
        Association sut = new Association("Adam", "Eve", "nowhere");
        assertThat(sut.getAssociate(), is(equalTo("Eve")));
    }

    @Test
    public void TheSceneNameShouldMatchTheOneSpecifiedOnCreation() {
        Association sut = new Association("Adam", "Eve", "nowhere");
        assertThat(sut.getLocation(), is(equalTo("nowhere")));
    }
    @Test
    public void AssociationsShouldBeEqualIfTheyHaveMatchingCharactersAssociationsAndLocations() {
        Association sut1 = new Association("Laurel", "Hardy", "nowhere");
        Association sut2 = new Association("Laurel", "Hardy", "nowhere");

        assertThat(sut1, is(equalTo(sut2)));
    }

    @Test
    public void AnAssociationShouldNotBeEqualToNull() {
        Association sut = new Association("Batman", "Robin", "nowhere");
        assertThat(sut, is(not(equalTo(null))));
    }

    @Test
    public void AnAssociationShouldNotBeEqualToAnotherType() {
        Association sut = new Association("Bob", "Vic", "nowhere");
        assertThat(sut.equals("Bob Vic"), is(false));
    }

    @Test
    public void EqualityShouldBeReflexive() {
        Association sut = new Association("Dick", "Dom", "nowhere");
        assertThat(sut, is(equalTo(sut)));
    }

    @Test
    public void EqualityShouldBeSymmetric() {
        Association sut1 = new Association("Morecambe", "Wise", "nowhere");
        Association sut2 = new Association("Morecambe", "Wise", "nowhere");
        assertThat(sut1, is(equalTo(sut2)));
        assertThat(sut2, is(equalTo(sut1)));
    }

    @Test
    public void EqualityShouldBeTransitive() {
        Association sut1 = new Association("Rama", "Sita", "nowhere");
        Association sut2 = new Association("Rama", "Sita", "nowhere");
        Association sut3 = new Association("Rama", "Sita", "nowhere");
        assertThat(sut1, is(equalTo(sut2)));
        assertThat(sut2, is(equalTo(sut3)));
        assertThat(sut1, is(equalTo(sut3)));
    }

    @Test
    public void EqualAssociationsShouldHaveEqualHashcodes() {
        Association sut1 = new Association("Sonny", "Cher", "nowhere");
        Association sut2 = new Association("Sonny", "Cher", "nowhere");
        assertThat(sut1.hashCode(), is(equalTo(sut2.hashCode())));
    }
}

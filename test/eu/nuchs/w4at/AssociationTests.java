package eu.nuchs.w4at;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class AssociationTests {

    @Test(expected = IllegalArgumentException.class)
    public void YouMustProvideANonNullCharacterName() {
        Association sut = new Association(null, "Mr NoBody");
    }

    @Test(expected = IllegalArgumentException.class)
    public void YouMustProvideANonWhiteSpaceCharacterName() {
        Association sut = new Association("  ", "The Invisible Man");
    }

    @Test(expected = IllegalArgumentException.class)
    public void YouMustProvideANonNullAssociateName() {
        Association sut = new Association("Mr NoBody", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void YouMustProvideANonWhiteSpaceAssociateName() {
        Association sut = new Association("The Invisible Man", "  ");
    }

    @Test
    public void TheCharacterNameShouldMatchTheOneSpecifiedOnCreation() {
        Association sut = new Association("Adam", "Eve");
        assertThat(sut.getCharacter(), is(equalTo("Adam")));
    }

    @Test
    public void TheAssociateNameShouldMatchTheOneSpecifiedOnCreation() {
        Association sut = new Association("Adam", "Eve");
        assertThat(sut.getAssociate(), is(equalTo("Eve")));
    }

    @Test
    public void TwoAssociationsShouldBeEqualIfTheyHaveMatchingCharactersAndMatchingAssociations() {
        Association sut1 = new Association("Laurel", "Hardy");
        Association sut2 = new Association("Laurel", "Hardy");

        assertThat(sut1, is(equalTo(sut2)));
    }

    @Test
    public void AnAssociationShouldNotBeEqualToNull() {
        Association sut = new Association("Batman", "Robin");
        assertThat(sut, is(not(equalTo(null))));
    }

    @Test
    public void AnAssociationShouldNotBeEqualToAnotherType() {
        Association sut = new Association("Bob", "Vic");
        assertThat(sut.equals("Bob Vic"), is(false));
    }

    @Test
    public void EqualityShouldBeReflexive() {
        Association sut = new Association("Dick", "Dom");
        assertThat(sut, is(equalTo(sut)));
    }

    @Test
    public void EqualityShouldBeSymmetric() {
        Association sut1 = new Association("Morecambe", "Wise");
        Association sut2 = new Association("Morecambe", "Wise");
        assertThat(sut1, is(equalTo(sut2)));
        assertThat(sut2, is(equalTo(sut1)));
    }

    @Test
    public void EqualityShouldBeTransitive() {
        Association sut1 = new Association("Rama", "Sita");
        Association sut2 = new Association("Rama", "Sita");
        Association sut3 = new Association("Rama", "Sita");
        assertThat(sut1, is(equalTo(sut2)));
        assertThat(sut2, is(equalTo(sut3)));
        assertThat(sut1, is(equalTo(sut3)));
    }

    @Test
    public void EqualAssociationsShouldHaveEqualHashcodes() {
        Association sut1 = new Association("Sonny", "Cher");
        Association sut2 = new Association("Sonny", "Cher");
        assertThat(sut1.hashCode(), is(equalTo(sut2.hashCode())));
    }
}

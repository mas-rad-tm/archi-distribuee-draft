package ch.globaz.avs4.personnes.domain.model;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;



public class NSSTest {

    static Validator validator;


    @Test
    public void assertThatInstanciationWork(){

        NSS nss = NSSBuilder.aleatoire();

        assertThat(nss.nss()).isNotNull();

    }

    @Test(expected = NullPointerException.class)
    public void assertThatNullValueThrowException(){

        NSS nss = new NSS(null);

        Set<ConstraintViolation<NSS>> violations = validator.validate(nss);

        assertTrue(violations.size() != 0);

    }

    @Test
    public void assertThatNssValueCorrectlyGenerated(){

        NSS nss = NSSBuilder.aleatoire();
        System.out.println(nss.nss());

        assertThat(nss.nss()).hasSize(16);

        assertThat(nss.nss().charAt(3)).isEqualTo('.');

        assertThat(nss.nss().charAt(8)).isEqualTo('.');

    }



}
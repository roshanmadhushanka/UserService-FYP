package validation;

import exception.RequiredAttributeMissingException;
import exception.ValidationFailedException;
import model.User;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by roshanalwis on 8/26/17.
 */
public class ValidationTestCase {

    @Test
    public void failTestValidateFirstName(){
        User user = new User();
        user.setFirstName("Roshan@");

        try {
            Validation.userValidation(user);
        } catch (ValidationFailedException e) {
            assertThat(e.getMessage(), is("Validation Failed: firstName"));
        } catch (RequiredAttributeMissingException e) {

        }
    }

    @Test
    public void failTestValidateLastName(){
        User user = new User();
        user.setLastName("Alwis1");

        try {
            Validation.userValidation(user);
        } catch (ValidationFailedException e) {
            assertThat(e.getMessage(), is("Validation Failed: lastName"));
        } catch (RequiredAttributeMissingException e) {

        }
    }

    @Test
    public void failTestValidateEmail(){
        User user = new User();
        user.setEmail("roshanalwis.@gmail.com");

        try {
            Validation.userValidation(user);
        } catch (ValidationFailedException e) {
            assertThat(e.getMessage(), is("Validation Failed: email"));
        } catch (RequiredAttributeMissingException e) {

        }
    }

    @Test
    public void failTestValidatePassword(){
        User user = new User();
        user.setPassword("12345678");

        try {
            Validation.userValidation(user);
        } catch (ValidationFailedException e) {
            assertThat(e.getMessage(), is("Validation Failed: password"));
        } catch (RequiredAttributeMissingException e) {

        }
    }

    @Test
    public void failTestRequiredFirstName(){
        User user = new User();
        user.setLastName("Alwis");
        user.setEmail("alwisroshan@gmail.com");
        user.setPassword("12@aD3456");

        try {
            Validation.userValidation(user);
        } catch (ValidationFailedException e) {

        } catch (RequiredAttributeMissingException e) {
            assertThat(e.getMessage(), is("Missing Attribute: firstName"));
        }
    }

    @Test
    public void failTestRequiredLastName(){
        User user = new User();
        user.setFirstName("Roshan");
        user.setEmail("alwisroshan@gmail.com");
        user.setPassword("12@aD3456");

        try {
            Validation.userValidation(user);
        } catch (ValidationFailedException e) {

        } catch (RequiredAttributeMissingException e) {
            assertThat(e.getMessage(), is("Missing Attribute: lastName"));
        }
    }

    @Test
    public void failTestRequiredEmail(){
        User user = new User();
        user.setFirstName("Roshan");
        user.setLastName("Alwis");
        user.setPassword("12@aD3456");

        try {
            Validation.userValidation(user);
        } catch (ValidationFailedException e) {

        } catch (RequiredAttributeMissingException e) {
            assertThat(e.getMessage(), is("Missing Attribute: email"));
        }
    }

    @Test
    public void failTestRequiredPassword(){
        User user = new User();
        user.setFirstName("Roshan");
        user.setLastName("Alwis");
        user.setEmail("alwisroshan@gmail.com");

        try {
            Validation.userValidation(user);
        } catch (ValidationFailedException e) {

        } catch (RequiredAttributeMissingException e) {
            assertThat(e.getMessage(), is("Missing Attribute: password"));
        }
    }


}

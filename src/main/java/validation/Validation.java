package validation;

import exception.RequiredAttributeMissingException;
import exception.ValidationFailedException;
import model.User;

/**
 * Created by roshanalwis on 8/24/17.
 */
public abstract class Validation {

    // Attribute validation
    public static boolean validateFirstName(String firstName){
        return firstName.matches( "[A-Z][a-zA-Z]*" );
    }

    public static boolean validateLastName(String lastName){
        return lastName.matches( "[a-zA-z]+([ '-][a-zA-Z]+)*" );
    }

    public static boolean validateEmail(String email){
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean validatePassword(String password){
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }

    // Unit validation
    public static boolean userValidation(User user) throws RequiredAttributeMissingException, ValidationFailedException {
        // Validate first name
        if(user.getFirstName() != null){
            if(!validateFirstName(user.getFirstName())){
                throw new ValidationFailedException("Validation Failed: firstName");
            }
        } else {
            throw new RequiredAttributeMissingException("Missing Attribute: firstName");
        }

        // Validate last name
        if(user.getLastName() != null){
            if(!validateLastName(user.getLastName())){
                throw new ValidationFailedException("Validation Failed: lastName");
            }
        } else {
            throw new RequiredAttributeMissingException("Missing Attribute: lastName");
        }

        // Validate email
        if(user.getEmail() != null){
            if(!validateEmail(user.getEmail())){
                throw new ValidationFailedException("Validation Failed: email");
            }
        } else {
            throw new RequiredAttributeMissingException("Missing Attribute: email");
        }

        // Validate password
        if(user.getPassword() != null){
            if(!validatePassword(user.getPassword())){
                throw new ValidationFailedException("Validation Failed: password");
            }
        } else {
            throw new RequiredAttributeMissingException("Missing Attribute: password");
        }
        return true;
    }

    // Login validation
    public static boolean loginValidation(User user) throws ValidationFailedException,
            RequiredAttributeMissingException {
        // Validate email
        if(user.getEmail() != null){
            if(!validateEmail(user.getEmail())){
                throw new ValidationFailedException("Validation Failed: email");
            }
        } else {
            throw new RequiredAttributeMissingException("Missing Attribute: email");
        }

        // Validate password;
        if(user.getPassword() != null){
            if(!validatePassword(user.getPassword())){
                throw new ValidationFailedException("Validation Failed: password");
            }
        } else {
            throw new RequiredAttributeMissingException("Missing Attribute: password");
        }
        return true;
    }
}

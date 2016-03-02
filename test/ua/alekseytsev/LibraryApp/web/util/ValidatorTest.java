package ua.alekseytsev.LibraryApp.web.util;

import org.junit.Test;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;

public class ValidatorTest {

    @Test(expected = LibraryException.class)
    public void testValidateEmailExceptionFail() throws LibraryException {
        Validator.validateEmail("as");

    }

    @Test(expected = LibraryException.class)
    public void testValidateFieldsFail() throws LibraryException {
        Validator.validateFields("");
    }

    @Test(expected = LibraryException.class)
    public void testValidatePasswordsFail() throws LibraryException {
        Validator.validatePasswords("1", "2");
    }


    @Test
    public void testValidateEmailException() throws LibraryException {
        Validator.validateEmail("good@email.ua");

    }

    @Test
    public void testValidateFields() throws LibraryException {
        Validator.validateFields("not empty");
    }

    @Test
    public void testValidatePasswords() throws LibraryException {
        Validator.validatePasswords("same", "same");
    }

}
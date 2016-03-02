package ua.alekseytsev.LibraryApp.web.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains method for validation data from input fields
 */
public final class Validator {
    private static final String REGEX_EMAIL = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
    private static final Logger LOG = LogManager.getLogger(Validator.class);

    private Validator() {
    }


    /**
     * Validates the email.
     *
     * @param email the email
     * @throws ua.alekseytsev.LibraryApp.exceptions.LibraryException if incorrect email
     */
    public static void validateEmail(String email) throws LibraryException {
        LOG.debug("Check email format");
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new LibraryException(LibraryException.ERR_BAD_EMAIL);
        }
        LOG.debug("Success validation email");
    }

    /**
     * Check fields
     *
     * @param fields
     * @throws LibraryException if empty field
     */
    public static void validateFields(String... fields) throws LibraryException {
        LOG.debug("Check empty fields");
        for (String field : fields) {
            if ("".equals(field)) {
                throw new LibraryException(LibraryException.ERR_EMPTY_FIELDS);
            }
        }
        LOG.debug("Success validation field");
    }

    /**
     * Check  a password
     *
     * @param password
     * @param confirmPassword
     * @throws LibraryException if passwords not equals
     */
    public static void validatePasswords(String password, String confirmPassword) throws LibraryException {
        LOG.debug("Check matches of passwords");
        if (!password.equals(confirmPassword)) {
            //Passwords does not match
            throw new LibraryException(LibraryException.ERR_PASSWORD_MATCHING);
        }
        LOG.debug("Success validation password");
    }

    public static void validateAmountBooks(Integer amount, Integer inStock) throws LibraryException {
        LOG.debug("Check amount books");
        if (amount < inStock) {
            throw new LibraryException(LibraryException.ERR_BAD_QUANTITY_BOOKS);
        }
        LOG.debug("Success");
    }
}



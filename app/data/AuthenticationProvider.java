package data;

/**
 * Enumerable for the different supported oauth providers.
 * This is so we can determine which method to authenticate a given user.
 */
public enum AuthenticationProvider {
    NATIVE,
    FACEBOOK,
};

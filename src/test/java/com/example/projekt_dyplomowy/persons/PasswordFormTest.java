package com.example.projekt_dyplomowy.persons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordFormTest {

    @Test
    void no_password_gives_error() {
        PasswordForm passwordForm = new PasswordForm();
        assertFalse(passwordForm.isValid());
    }

    @Test
    void the_same_passwords_is_ok() {
        PasswordForm passwordForm = new PasswordForm();
        passwordForm.setPassword("wsb123");
        passwordForm.setRepeatedPassword("wsb123");
        assertTrue(passwordForm.isValid());
    }

    @Test
    void other_passwords_give_error() {
        PasswordForm passwordForm = new PasswordForm();
        passwordForm.setPassword("wsb123");
        passwordForm.setRepeatedPassword("wsbwsb");
        assertFalse(passwordForm.isValid());
    }

}
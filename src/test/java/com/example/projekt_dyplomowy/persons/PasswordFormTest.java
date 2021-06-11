package com.example.projekt_dyplomowy.persons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordFormTest {

    @Test
    void no_password_gives_error() {
        PasswordForm passwordForm = new PasswordForm();
        assertFalse(passwordForm.isValid());
    }
}
package com.example.projekt_dyplomowy.persons;

import com.example.projekt_dyplomowy.validators.ValidPasswordsForm;
import lombok.*;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ValidPasswordsForm
public class PasswordForm {

    Long id;

    @NotBlank
    String password;

    @NotBlank
    String repeatedPassword;

    boolean isValid;

    @AssertTrue(message = "passVerify field should be equal than pass field")
    public boolean isValid() {
        if (password == null)
            return false;
        if (repeatedPassword == null)
            return false;
        else
            return this.password.equals(this.repeatedPassword);
    }
}

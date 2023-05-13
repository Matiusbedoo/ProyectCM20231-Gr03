package co.edu.udea.proyecto20231_gr03.domain

import android.util.Patterns
import io.konform.validation.Validation
import io.konform.validation.ValidationResult
import io.konform.validation.jsonschema.minLength
import io.konform.validation.jsonschema.pattern

data class User(val email: String, val type: UserType) {
    fun validate(): ValidationResult<User> {
        return Validation {
            User::email required {
                pattern(Patterns.EMAIL_ADDRESS.toString()) hint ("Correo inválido")
            }
        }.validate(this)
    }
}

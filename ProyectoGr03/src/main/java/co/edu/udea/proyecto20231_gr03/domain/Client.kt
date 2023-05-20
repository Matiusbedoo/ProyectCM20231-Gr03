package co.edu.udea.proyecto20231_gr03.domain

import android.util.Patterns
import io.konform.validation.Validation
import io.konform.validation.ValidationResult
import io.konform.validation.jsonschema.minLength
import io.konform.validation.jsonschema.pattern

data class Client(
    val email: String,
    val name: String,
    val phone: String,
) {

    constructor() : this("", "cliente", "3xxxxxxxx")
    fun validate(): ValidationResult<Client> {
        return Validation {
            Client::email required {
                pattern(Patterns.EMAIL_ADDRESS.toString()) hint ("Correo inválido")
            }
            Client::name required {
                minLength(1) hint ("Ingrese un nombre")
            }
            Client::phone required {
                pattern("^\\d{10}") hint ("Teléfono inválido")
            }
        }.validate(this)
    }
}
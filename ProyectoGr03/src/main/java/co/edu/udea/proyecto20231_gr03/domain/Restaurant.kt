package co.edu.udea.proyecto20231_gr03.domain

import android.util.Patterns
import io.konform.validation.Validation
import io.konform.validation.ValidationResult
import io.konform.validation.jsonschema.minLength
import io.konform.validation.jsonschema.pattern

data class Restaurant(
    val email: String,
    val name: String,
    val address: String,
    val phone: String,
    val restaurantOwner: RestaurantOwner
) {

    constructor() : this("", "Restaurante", "", "12312312", RestaurantOwner("", ""))

    fun validate(): ValidationResult<Restaurant> {
        return Validation {
            Restaurant::email required {
                pattern(Patterns.EMAIL_ADDRESS.toString()) hint ("Correo inválido")
            }
            Restaurant::name required {
                minLength(1) hint ("Ingrese un nombre")
            }
            Restaurant::address required {
                minLength(1) hint ("Ingrese un nombre")
            }
            Restaurant::phone required {
                pattern("^\\d{10}") hint ("Teléfono inválido")
            }
            Restaurant::restaurantOwner required { }
        }.validate(this)
    }
}
package co.edu.udea.proyecto20231_gr03.domain

import io.konform.validation.Validation
import io.konform.validation.ValidationResult
import io.konform.validation.jsonschema.minLength

data class RestaurantOwner(val name: String, val phone: String) {
    fun validate(): ValidationResult<RestaurantOwner> {

        return Validation {
            RestaurantOwner::name required {

                minLength(1) hint ("Ingrese un nombre de representante")
            }
        }.validate(this)
    }
}
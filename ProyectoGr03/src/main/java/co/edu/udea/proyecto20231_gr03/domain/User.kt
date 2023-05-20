package co.edu.udea.proyecto20231_gr03.domain

data class User(val email: String, val type: UserType) {

    constructor() : this("", UserType.Client)

}

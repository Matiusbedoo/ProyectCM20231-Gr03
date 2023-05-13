package co.edu.udea.proyecto20231_gr03.helpers

import android.os.Bundle
import com.google.gson.Gson

object SerializeHelper {
    fun <T> serializeInBundle(data: T): Bundle {
        val gson = Gson()
        return Bundle().apply {
            putString(data!!::class.simpleName, gson.toJson(data))
        }
    }

    inline fun <reified T> deserializeFromBundle(bundle: Bundle?): T? {
        val gson = Gson()

        if (bundle != null) {
            return gson.fromJson(bundle.getString(T::class.simpleName), T::class.java)
        }
        return null;
    }
}

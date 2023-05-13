package co.edu.udea.proyecto20231_gr03

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseAuthModule {
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object FirestoreModule {
    @Provides
    fun provideFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }
}

@Module
@InstallIn(SingletonComponent::class)
object GsonModule {
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}
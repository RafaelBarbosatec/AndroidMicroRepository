package com.rafaelbarbosatec.sdk.core.db

import android.content.Context
import androidx.room.Room

class DatabaseCreate(private val context: Context) {
    fun create(): SuperDigitalDatabase {
        return Room.databaseBuilder(
            context,
            SuperDigitalDatabase::class.java,
            "super-digital"
        )
        .build()
    }
}
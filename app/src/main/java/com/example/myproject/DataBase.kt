package com.example.myproject

import android.annotation.SuppressLint
import android.arch.persistence.room.Room
import android.content.Context
import android.util.Log
import java.io.FileOutputStream
import java.io.IOException


class DatabaseCopier private constructor() {

    val roomDatabase: DataStructArticles

    private object Holder {
        @SuppressLint("StaticFieldLeak")
        val INSTANCE = DatabaseCopier()
    }

    init {
        //call method that check if database not exists and copy prepopulated file from assets
        copyAttachedDatabase(appContext!!, DATABASE_NAME)
        roomDatabase = Room.databaseBuilder(
            appContext!!,
            DataStructArticles::class.java!!, DATABASE_NAME
        )
            .addMigrations(DataStructArticles.MIGRATION_1_2)
            .build()
    }


    private fun copyAttachedDatabase(context: Context, databaseName: String) {
        val dbPath = context.getDatabasePath(databaseName)

        // If the database already exists, return
        if (dbPath.exists()) {
            return
        }

        // Make sure we have a path to the file
        dbPath.getParentFile().mkdirs()

        // Try to copy database file
        try {
            val inputStream = context.getAssets().open("databases/$databaseName")
            val output = FileOutputStream(dbPath)

            val buffer = ByteArray(8192)
            var length: Int
            length = inputStream.read(buffer, 0, 8192)
            while (length > 0) {
                output.write(buffer, 0, length)
                length = inputStream.read(buffer, 0, 8192)
            }


            output.flush()
            output.close()
            inputStream.close()
        } catch (e: IOException) {
            Log.d(TAG, "Failed to open file", e)
            e.printStackTrace()
        }

    }

    companion object {
        private val TAG = DatabaseCopier::class.java.simpleName
        private const val DATABASE_NAME = "autoarticles.db"
        private var appContext: Context? = null

        fun getInstance(context: Context): DatabaseCopier {
            appContext = context
            return Holder.INSTANCE
        }
    }

}
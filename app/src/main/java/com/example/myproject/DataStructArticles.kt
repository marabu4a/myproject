package com.example.myproject
import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.*
import android.arch.persistence.room.migration.Migration


class AppActivity: Application() {
    companion object {
        var db: DataStructArticles? = null
        fun getDatabase(): DataStructArticles? {
            return db
        }

    }

    override fun onCreate() {
        super.onCreate()
         db = DatabaseCopier.getInstance(applicationContext).roomDatabase

    }
    @Entity(tableName = "articles")
    data class StructArticles(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "title") var title: String?,
        @ColumnInfo(name = "text") var text: String?,
        @ColumnInfo(name = "image") var image: String?
    )
    @Dao
    interface ReadoutModelDao {
        @get:Query("SELECT * FROM articles")
        val allStructArticles: List<StructArticles>

        @Query("SELECT * FROM articles WHERE id = :id")
        fun getReadoutById(id: Long): StructArticles

        @Query("SELECT * FROM articles WHERE title = :title")
        fun getReadoutByAddr(title: String): StructArticles

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun addReadout(readoutModel: StructArticles)

        @Update
        fun updateReadout(readoutModel: StructArticles)

        @Delete
        fun deleteReadout(readoutModel: StructArticles)
    }
    @Database(
        entities = [StructArticles::class],
        version = 2
    )
    abstract class DataStructArticles : RoomDatabase() {

        abstract fun readoutDAO(): ReadoutModelDao


        companion object {

            @JvmField
            val MIGRATION_1_2: Migration = object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                }
            }
        }
    }
}





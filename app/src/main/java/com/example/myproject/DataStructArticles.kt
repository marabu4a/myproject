package com.example.myproject
import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.*
import android.arch.persistence.room.migration.Migration
import android.content.Context
import android.util.Log
import java.io.FileOutputStream
import java.io.IOException
private const val DB_NAME = "autoarticles.db"
class AppActivity: Application() {
    companion object {
        var db: DataStructArticles? = null

    }
    private fun copyDatabase(context: Context, databaseName: String) {
        val dbPath = context.getDatabasePath(databaseName)

        // If the database already exists, return
        if (dbPath.exists()) {
            Log.d("Activity", "db Path Exists")
            return
        }

        // Make sure we have a path to the file
        dbPath.parentFile.mkdirs()
        // Try to copy database file
        try {
            val inputStream = context.assets.open(databaseName);
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
            Log.d("Activity", "Failed to open file", e)
            e.printStackTrace()
        }

    }
    /*private fun buildDatabase(context: Context): DataStructArticles {
        val dbFile = context.getDatabasePath(DB_NAME)

        if (!dbFile.exists()) {
            copyDatabase(applicationContext,DB_NAME,dbFile.absolutePath)
        }

        return Room.databaseBuilder(
            context.applicationContext,
            DataStructArticles::class.java, DB_NAME
        )
            .build()
    }*/
    override fun onCreate()  {
        super.onCreate()
        //val dbFile = applicationContext.getDatabasePath(DB_NAME)

        //copyDatabase(applicationContext,DB_NAME)
        //db = Room.databaseBuilder(
        //    applicationContext,
        //    DataStructArticles::class.java, DB_NAME
        //).addMigrations(DataStructArticles.MIGRATION_1_2).build()

        db = DatabaseCopier.getInstance(applicationContext).roomDatabase

    }

}
@Entity(tableName = "articles")
data class StructArticles(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "text") var text: String?,
    @ColumnInfo(name = "image") var image: String?)



@Dao // Указываем, что при доступе к некоторым полям будет задействован наш конвертер DateConverter
interface ReadoutModelDao {

    @get:Query("select * from articles")            // "get:" означает применение аннотации "Query" к геттеру (функцию геттера для переменной allReadoutItems вручную не пишем)
    val allStructArticles: List<StructArticles>             // Обёртываем возвращаемое значение LiveData<...> чтобы отслеживать изменения в базе. При изменении данных будут рассылаться уведомления

    @Query("select * from articles where id = :id")
    fun getReadoutById(id: Long): StructArticles

    @Query("select * from articles where title = :title")
    fun getReadoutByAddr(title: String): List<StructArticles>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReadout(readoutModel: StructArticles)

    @Update
    fun updateReadout(readoutModel: StructArticles)

    @Delete
    fun deleteReadout(readoutModel: StructArticles)
}

@Database(entities = [StructArticles::class], version = 2)  // Перечисляем в entities, какие классы будут использоваться для создания таблиц.
abstract class DataStructArticles : RoomDatabase() {

    abstract fun readoutDAO(): ReadoutModelDao           // Описываем абстрактные методы для получения объектов интерфейса BorrowModelDao, которые вам понадобятся

    // Сопутствующий объект для получения базы данных (фактически синглтон). Можно не использовать.
    companion object {

        /*private var INSTANCE: DataStructArticles? = null

        fun getDatabase(context: Context): DataStructArticles? {
            if (INSTANCE == null) {

                INSTANCE = Room.databaseBuilder(context, DataStructArticles::class.java, "autoarticles.db")
                    .build()
            }
            return INSTANCE as DataStructArticles
        }

        fun destroyDbInstance() {
            INSTANCE = null
        }*/
        @JvmField
        val MIGRATION_1_2 : Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }
    }
}





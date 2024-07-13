package com.ftq.pricetag

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ftq.pricetag.dao.BranchDao
import com.ftq.pricetag.dao.ProductDao
import com.ftq.pricetag.dao.RetailerDao
import com.ftq.pricetag.entity.BranchEntity
import com.ftq.pricetag.entity.ProductEntity
import com.ftq.pricetag.entity.RetailerEntity
import com.ftq.pricetag.entity.TransactionEntity

@Database(entities = [RetailerEntity::class, BranchEntity::class, ProductEntity::class, TransactionEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun retailerDao(): RetailerDao
    abstract fun branchDao(): BranchDao
    abstract fun productDao(): ProductDao
//    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

package com.ftq.pricetag.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ftq.pppb.price_tag.dao.RetailerDao
import com.ftq.pricetag.data.entity.Branch
import com.ftq.pricetag.data.entity.Product
import com.ftq.pricetag.data.entity.Retailer
import com.ftq.pricetag.data.entity.Transaction

@Database(entities = [Retailer::class, Branch::class, Product::class, Transaction::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun retailerDao(): RetailerDao
//    abstract fun branchDao(): BranchDao
//    abstract fun productDao(): ProductDao
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

package com.example.transectas.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FindingDao {
    /**
     * Inserts a item into the table.
     *
     * @param finding A new item.
     * @return The row ID of the newly inserted item.
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(finding: Hallazgos?): Long

    /**
     * Update the item. The item is identified by the row ID.
     *
     * @param finding The item to update.
     * @return A number of items updated. This should always be `1`.
     */
    @Update
    fun update(finding: Hallazgos?): Int?

    /**
     * Select all items in order.
     *
     * @return A [List] of all the items in the table.
     */
    @Query("SELECT * from " + Hallazgos.TABLE_NAME + " ORDER BY " + Hallazgos.COLUMN_ID + " DESC")
    fun getAllFindings(): LiveData<List<Hallazgos?>?>?

    /**
     * Select a item by the ID.
     *
     * @param id The row ID.
     * @return A [Cursor] of the selected item.
     */
    @Query("SELECT * FROM " + Hallazgos.TABLE_NAME + " WHERE " + Hallazgos.COLUMN_ID + " = :id")
    fun getFindingbyId(id: Long): Hallazgos?
}
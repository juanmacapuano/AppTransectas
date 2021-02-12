package com.example.transectas.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface MaterialArqueologicoDao {

    /**
     * Inserts a item into the table.
     *
     * @param materialArqueologico A new item.
     * @return The row ID of the newly inserted item.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(MaterialArqueologico materialArqueologico);
}

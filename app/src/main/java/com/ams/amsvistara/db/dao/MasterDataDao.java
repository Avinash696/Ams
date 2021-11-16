package com.ams.amsvistara.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ams.amsvistara.db.table.master.MasterData;


@Dao
public interface MasterDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertSingleInfo(MasterData masterData);

    @Update
    void updateMasterData(MasterData masterData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(MasterData masterData);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAllMasterData(MasterData masterData);

    @Query("SELECT * FROM master_data")
    MasterData getMasterData();

/*
  @Query("SELECT saveFlag FROM pv_item WHERE AssetCode == :assetCode")
    boolean getSaveFlagPVItemTable(String assetCode);

    @Query("SELECT * FROM pv_item WHERE AssetCode == :assetCode")
    PVItemTable getSavedPVItemTable(String assetCode);
*/

/*
    @Query("DELETE FROM pv_item")
    void deleteSingleWithId(int localId);*/

    @Delete
    void deleteSingle(MasterData masterData);

    @Query("DELETE FROM master_data")
    void deleteAll();

    @Query("SELECT COUNT(*) FROM master_data")
    public Integer getItemCountLive();


}

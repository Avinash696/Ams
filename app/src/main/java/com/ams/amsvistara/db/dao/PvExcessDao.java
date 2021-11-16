package com.ams.amsvistara.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ams.amsvistara.db.table.excess.PvExcessReq;

import java.util.List;


@Dao
public interface PvExcessDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertSingleInfo(PvExcessReq pvExcessReq);

    @Update
    void updatePvExcess(PvExcessReq pvExcessReq);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(PvExcessReq pvExcessReq);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAllPvExcessReq(PvExcessReq pvExcessReq);

    @Query("SELECT * FROM pv_excess")
    List<PvExcessReq> getPvExcessReq();

    @Delete
    void deleteSingle(PvExcessReq pvExcessReq);

    @Query("DELETE FROM pv_excess")
    void deleteAll();

    @Query("DELETE FROM pv_excess Where assetnumber == :assetnumberStr")
    void deleteWhere(String assetnumberStr);

    @Query("SELECT COUNT(*) FROM pv_excess")
     Integer getItemCountLive();


}

package com.ams.amsvistara.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ams.amsvistara.application.AMSApplication;
import com.ams.amsvistara.db.dao.MasterDataDao;
import com.ams.amsvistara.db.dao.PvExcessDao;
import com.ams.amsvistara.db.table.excess.PvExcessReq;
import com.ams.amsvistara.db.table.master.MasterData;


@Database(entities = {MasterData.class, PvExcessReq.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    private static final String DB_NAME = "vistara_ams_app_db";

    //public abstract PVItemTableDao pVItemTableDao();
    public abstract MasterDataDao masterDataDao();
    public  abstract PvExcessDao pvExcessDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(AMSApplication.instance, AppDatabase.class, DB_NAME)
                    // .addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

   /* static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `attendance_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `serverStatus` INTEGER NOT NULL, `Employee_ID` TEXT, `Attendance_Date` TEXT, `AttendanceDate` TEXT, `Attendance_Day` TEXT, `Attendance_Month` TEXT, `Check_In_Time` TEXT, `Check_In_Latitude` TEXT, `Check_In_Longitude` TEXT, `Check_In_Battery_Percentage` TEXT, `Check_In_Address_Label` TEXT, `Check_In_Activity_Location` TEXT, `Check_in_Attachment_URL` TEXT, `Check_in_Address1` TEXT, `Check_in_Address2` TEXT, `Check_in_Locality` TEXT, `Check_in_City` TEXT, `Check_in_State` TEXT, `Check_in_PIN` TEXT, `Check_Out_Time` TEXT, `CheckOutLatitude` TEXT, `Check_Out_Longitude` TEXT, `Check_Out_Battery_Percentage` TEXT, `Check_Out_Address_Label` TEXT, `Check_Out_Activity_Location` TEXT, `Check_out_Attachment_URL` TEXT, `Check_out_Address1` TEXT, `Check_out_Address2` TEXT, `Check_out_Locality` TEXT, `Check_out_City` TEXT, `Check_out_State` TEXT, `Check_out_PIN` TEXT, `No_Of_Minutes` INTEGER NOT NULL, `Regularization_Applicable` TEXT, `Regularization_Tran_ID` TEXT, `Transaction_ID` TEXT, `Work_Place` TEXT, `Attendance_Type` TEXT, `Regularization_Type` TEXT, `Regularization_In_Time` TEXT, `Regularization_Out_Time` TEXT, `Reason_For_Regularization` TEXT, `Others` TEXT, `Holiday_Name` TEXT, `Check_in_Time_Stamp` TEXT, `Check_out_Time_Stamp` TEXT, `Check_In_IsSync` TEXT, `Check_Out_IsSync` TEXT, `Reg_Type` TEXT, `Reg_Type_Code` INTEGER NOT NULL, `Color_Code_To_Show` TEXT, `isOnLeave` TEXT, `Applied_For` INTEGER NOT NULL, `Overall_Status_Code` INTEGER NOT NULL, `IsWorking` INTEGER NOT NULL, `Approver_Flag` TEXT, `Details_To_Show` INTEGER NOT NULL, `Reg_Leave_Type` TEXT, `Reg_Short_Work_Hours_Reason` TEXT, `Reg_Current_Approver_ID` TEXT, `Reg_Current_Approver_Name` TEXT, `Reg_Overall_Status` TEXT, `Reg_Created_By_ID` TEXT, `Reg_Created_By_Name` TEXT, `Reg_Created_Date` TEXT, `Reg_Modified_By_ID` TEXT, `Reg_Modified_By_Name` TEXT, `Reg_Modified_Date` TEXT, `Min_Work_Mintes` INTEGER NOT NULL, `Min_Work_Minutes` INTEGER NOT NULL, `Leave_Tran_ID` TEXT, `Leave_From_Date` TEXT, `Leave_To_Date` TEXT, `Leave_From_Half_Day_Flag` INTEGER NOT NULL, `Leave_To_Half_Day_Flag` INTEGER NOT NULL, `Leave_Total_Leaves` REAL NOT NULL, `Leave_Reason_For_Leave` TEXT, `Leave_Regularization_Type` TEXT, `Leave_Leave_ID` TEXT, `Leave_Leave_Type` TEXT, `Leave_Current_Approver_ID` TEXT, `Leave_Current_Approver_Name` TEXT, `Leave_Overall_Status` INTEGER NOT NULL, `Leave_Leave_Status` TEXT, `Leave_Modified_By_ID` TEXT, `Leave_Modified_By_Name` TEXT, `Leave_Created_Date` TEXT, `Leave_Modified_Date` TEXT)");
            database.execSQL("CREATE TABLE IF NOT EXISTS `check_in_out_workflow_detail_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `Approver_ID` TEXT, `Approver_Level` INTEGER NOT NULL, `Approver_Name` TEXT, `Approver_Role` TEXT, `Color_Code_To_Show` TEXT, `Last_Modified_By_Emp_Code` TEXT, `Last_Modified_By_Emp_Name` TEXT, `Last_Modified_Date` TEXT, `Remarks` TEXT, `Requestor_Code` TEXT, `Status` TEXT, `Status_Code` INTEGER NOT NULL, `Transaction_ID` TEXT)");
        }
    };*/


    public static void destroyInstance() {
        INSTANCE = null;
    }
}

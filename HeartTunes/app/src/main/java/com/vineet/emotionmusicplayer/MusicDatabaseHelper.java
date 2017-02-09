package com.vineet.emotionmusicplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Student on 30-01-2017.
 */
public class MusicDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "tunes";
    private static final int DB_VERSION = 1;

    MusicDatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE MUSIC(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "PITCH DOUBLE, "
        + "RMS DOUBLE, "
        + "EMOTION INTEGER);");

        db.execSQL("CREATE TABLE ONETIMEFLAG(FLAG INTEGER);");

        insertFlagValue(db);
        insertMusicValues(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }

    private static void insertMusicValues(SQLiteDatabase db){

        //Training Data Set for Calm Tag
        insertMusic(db,96.95968,8.327366322,0);
        insertMusic(db,196.65582,10.21855245,0);
        insertMusic(db,103.83206,7.22911155,0);
        insertMusic(db,217.1854,10.57287364,0);
        insertMusic(db,164.63118,10.00296758,0);
        insertMusic(db,233.04884,3.893300755,0);
        insertMusic(db,131.20801,8.58053068,0);
        insertMusic(db,36.78106,17.2516065,0);
        insertMusic(db,95.9903,9.192153692,0);
        insertMusic(db,154.54591,16.94403414,0);
        insertMusic(db,98.35367,8.465014273,0);
        insertMusic(db,25.43642,15.73792171,0);
        insertMusic(db,261.24973,15.06975076,0);
        insertMusic(db,117.0497,4.717221576,0);
        insertMusic(db,131.6028,13.79006165,0);
        insertMusic(db,174.25084,20.97421992,0);
        insertMusic(db,36.810448,12.48070518,0);
        insertMusic(db,98.149574,15.91533687,0);
        insertMusic(db,108.18362,9.982305422,0);
        insertMusic(db,139.6457,8.394734307,0);
        insertMusic(db,36.92755,13.83042098,0);
        insertMusic(db,191.69302,11.54995906,0);
        insertMusic(db,117.497856,10.02465832,0);
        insertMusic(db,30.91103,15.74448931,0);
        insertMusic(db,98.12756,7.240473015,0);
        insertMusic(db,21.811377,24.72928521,0);
        insertMusic(db,39.380997,7.241441963,0);
        insertMusic(db,88.135864,7.952503953,0);
        insertMusic(db,98.021904,8.259892047,0);
        insertMusic(db,70.690765,13.29763043,0);
        insertMusic(db,35.546436,24.73161625,0);
        insertMusic(db,48.897972,17.01376312,0);
        insertMusic(db,111.99758,5.596914035,0);
        insertMusic(db,38.669743,11.80130364,0);
        insertMusic(db,21.87365,13.91970301,0);
        insertMusic(db,81.98965,30.30194328,0);
        insertMusic(db,41.25205,5.253087157,0);
        insertMusic(db,249.60487,13.58069903,0);
        insertMusic(db,37.341244,6.743701554,0);

        //Training Data Set for Sad Tag
        insertMusic(db,295.2035,5.673742875,1);
        insertMusic(db,204,2.37307998,1);
        insertMusic(db,77.831406,3.5039297,1);
        insertMusic(db,74.47772,5.962409422,1);
        insertMusic(db,104.47229,5.802345141,1);
        insertMusic(db,41.24273,1.174303021,1);
        insertMusic(db,61.000767,0.033834435,1);
        insertMusic(db,66.239845,3.1124212,1);
        insertMusic(db,55.103317,3.014965061,1);
        insertMusic(db,24.37203,6.76340855,1);
        insertMusic(db,43.887505,3.382837304,1);
        insertMusic(db,62.196465,4.087925477,1);
        insertMusic(db,25.04781,0.011404834,1);
        insertMusic(db,82.199715,5.702705167,1);
        insertMusic(db,87.90991,1.357505334,1);
        insertMusic(db,49.468796,0.081851926,1);
        insertMusic(db,26.004883,6.460813642,1);
        insertMusic(db,30.943317,7.648473997,1);
        insertMusic(db,46.022377,0.066412074,1);
        insertMusic(db,49.253605,6.338267395,1);
        insertMusic(db,293.53418,4.475785772,1);
        insertMusic(db,54.93991,6.18234169,1);
        insertMusic(db,98.096016,3.996039603,1);
        insertMusic(db,98.1013,0.175414042,1);
        insertMusic(db,49.268272,4.58061873,1);
        insertMusic(db,103.301636,0.775060787,1);
        insertMusic(db,43.864746,2.396792204,1);
        insertMusic(db,119.98377,4.764622492,1);
        insertMusic(db,86.96144,1.804628146,1);
        insertMusic(db,30.873024,0.375703477,1);
        insertMusic(db,58.84501,0.258223748,1);
        insertMusic(db,49.863388,0.5983868,1);
        insertMusic(db,103.72711,1.358929943,1);
        insertMusic(db,130.42435,6.189620579,1);
        insertMusic(db,60.674557,0.022155908,1);
        insertMusic(db,39.035965,3.406314484,1);
        insertMusic(db,49.4345,7.622904011,1);
        insertMusic(db,110.45982,2.397355604,1);
        insertMusic(db,25.051882,0.012313674,1);
        insertMusic(db,149.80925,0.550359934,1);
        insertMusic(db,81.63781,0.042077618,1);
        insertMusic(db,97.29161,5.001285885,1);
        insertMusic(db,46.370457,1.965485932,1);

        //Training Data Set for Happy Tag
        insertMusic(db,166.98436,21.7394995728252,2);
        insertMusic(db,87.94474,29.4185088454726,2);
        insertMusic(db,49.21818,30.967296237465,2);
        insertMusic(db,47.054993,50.2272870179778,2);
        insertMusic(db,155.95558,28.4819586225167,2);
        insertMusic(db,81.68702,41.0218452096636,2);
        insertMusic(db,58.30805,24.1280969445046,2);
        insertMusic(db,53.550106,37.8003443144129,2);
        insertMusic(db,187.15111,29.7087479752479,2);
        insertMusic(db,57.536438,29.4891076642335,2);
        insertMusic(db,48.76359,20.8854384442051,2);
        insertMusic(db,65.92674,26.4537616334825,2);
        insertMusic(db,69.76841,21.0048940141503,2);
        insertMusic(db,445.18362,16.4738027154066,2);
        insertMusic(db,81.98965,30.3019432803168,2);
        insertMusic(db,88.31709,20.8505048843133,2);
        insertMusic(db,47.2747,23.4027423952243,2);
        insertMusic(db,91.74801,18.6800400584978,2);
        insertMusic(db,395.98532,40.5638015243984,2);
        insertMusic(db,320.90475,21.8168353072973,2);
        insertMusic(db,97.19177,18.5541377708897,2);
        insertMusic(db,65.71113,17.475603403811,2);
        insertMusic(db,393.28064,24.0510825496769,2);
        insertMusic(db,276.64435,15.9825629020205,2);
        insertMusic(db,63.051003,26.7326950613744,2);
        insertMusic(db,49.01042,32.95776544793,2);
        insertMusic(db,162.14581,19.5669509460391,2);
        insertMusic(db,43.693733,25.3010297574538,2);
        insertMusic(db,159.01405,16.0050732176392,2);
        insertMusic(db,388.0995,21.701220389811,2);
        insertMusic(db,56.099792,16.0518707075729,2);
        insertMusic(db,112.73136,15.9473899782991,2);
        insertMusic(db,60.36975,17.5894531240026,2);
        insertMusic(db,46.97265,36.3793107639116,2);
        insertMusic(db,54.919144,19.1043872172347,2);
        insertMusic(db,61.19904,22.8406502669719,2);
        insertMusic(db,217.79488,24.1815272174648,2);
        insertMusic(db,624.55756,21.5670026989499,2);
        insertMusic(db,248.80496,27.4836671307785,2);
        insertMusic(db,194.0883,31.7210734534907,2);
        insertMusic(db,439.48764,20.1088099310526,2);
        insertMusic(db,522.899,22.8207892015186,2);
        insertMusic(db,175.42981,16.2213487296071,2);
        insertMusic(db,183.97977,16.6755907210946,2);
        insertMusic(db,345.7074,15.4686551998704,2);
        insertMusic(db,248.08998,21.210573930061,2);
        insertMusic(db,249.79538,17.474778276102,2);


        //Training Data Set for Angry Tag
        insertMusic(db,700.95856,5.96106334618322,3);
        insertMusic(db,359.69767,2.98029659761684,3);
        insertMusic(db,186.57027,8.38232502340851,3);
        insertMusic(db,389.39746,0.377387927442925,3);
        insertMusic(db,1045.7511,8.81042590836197,3);
        insertMusic(db,248.86159,10.6847283228866,3);
        insertMusic(db,165.23476,1.16936487312627,3);
        insertMusic(db,2133.3792,4.37835361084504,3);
        insertMusic(db,156.89061,4.01268286381343,3);
        insertMusic(db,347.81973,3.10987665357983,3);
        insertMusic(db,310.3346,0.425745481055604,3);
        insertMusic(db,638.6183,5.69862937459917,3);
        insertMusic(db,311.1704,1.08592597616321,3);
        insertMusic(db,258.04892,2.34923584027359,3);
        insertMusic(db,2072.5715,0.0218017728183047,3);
        insertMusic(db,219.01794,7.35751818236172,3);
        insertMusic(db,3712.8276,2.11421441219359,3);
        insertMusic(db,224.58649,2.49092503807628,3);
        insertMusic(db,235.02043,2.64545076625205,3);
        insertMusic(db,262.55353,1.10390683361356,3);
        insertMusic(db,148.52562,4.76494948756541,3);
        insertMusic(db,212.47449,0.1140289046583,3);
        insertMusic(db,1489.5623,11.8884463629398,3);
        insertMusic(db,1053.2028,2.8273895060225,3);
        insertMusic(db,162.9652,5.34941358524388,3);
        insertMusic(db,407.15738,5.54119394386022,3);
        insertMusic(db,209.51736,4.84147480703365,3);
        insertMusic(db,662.5933,3.63127339237203,3);
        insertMusic(db,261.1032,1.29257741941394,3);
        insertMusic(db,232.11893,1.00379454616047,3);
        insertMusic(db,422.32507,1.87514488274823,3);
        insertMusic(db,238.93912,0.849218662953882,3);
        insertMusic(db,239.62799,9.88635135658557,3);
        insertMusic(db,246.01256,9.29112984713175,3);
        insertMusic(db,177.52382,3.36114089982129,3);
        insertMusic(db,279.38992,10.8256529596784,3);
        insertMusic(db,1847.4578,0.663160782210268,3);
        insertMusic(db,277.3596,2.82859123323905,3);
        insertMusic(db,466.1288,18.7321209351684,3);

        //Training Data Set for Fear Tag
        insertMusic(db,32.680786,23.6478417088792,4);
        insertMusic(db,262.13104,2.62905303964691,4);
        insertMusic(db,259.15094,2.74192387428647,4);
        insertMusic(db,110.36454,7.6268126583762,4);
        insertMusic(db,130.47453,0.962755157284866,4);
        insertMusic(db,334.2235,2.67925502586179,4);
        insertMusic(db,38.4897,14.0508930768594,4);
        insertMusic(db,866.9949,5.74478414547154,4);
        insertMusic(db,698.1545,0.506154539681291,4);
        insertMusic(db,59.26146,15.7216329163444,4);
        insertMusic(db,279.87613,5.02618401606252,4);
        insertMusic(db,49.950306,14.3559428316785,4);
        insertMusic(db,1144.9459,6.65810797886742,4);
        insertMusic(db,179.07303,3.00809971713156,4);
        insertMusic(db,273.10025,4.27299089509046,4);
        insertMusic(db,249.79922,0.0117856675995343,4);
        insertMusic(db,105.43536,4.26445773699904,4);
        insertMusic(db,36.74563,8.84991696616593,4);
        insertMusic(db,82.42732,1.10859586082303,4);
        insertMusic(db,1017.8545,1.8528826811645,4);
        insertMusic(db,35.98796,3.81302446026474,4);
        insertMusic(db,374.8272,5.60285822149351,4);
        insertMusic(db,85.16587,1.33415271383961,4);
        insertMusic(db,544.09344,1.03495351018426,4);
        insertMusic(db,97.99136,3.78466350530976,4);
        insertMusic(db,41.516365,13.4835948119666,4);
        insertMusic(db,40.307926,2.84400126610246,4);
        insertMusic(db,66.07733,2.74818051729662,4);
        insertMusic(db,35.641445,19.5307085727742,4);
        insertMusic(db,90.42376,11.077785971926,4);
        insertMusic(db,461.92926,5.07549379239012,4);
        insertMusic(db,63.42575,0.0150917351162263,4);
        insertMusic(db,367.59995,5.52432722784059,4);
        insertMusic(db,82.18277,9.12855939341735,4);
        insertMusic(db,46.780098,14.5545578119712,4);
        insertMusic(db,63.168728,2.97435161892254,4);
        insertMusic(db,123.100075,3.76202495148234,4);
        insertMusic(db,1405.643,6.30386761995085,4);
        insertMusic(db,69.76841,21.0048940141503,4);
        insertMusic(db,325.96484,3.68501208323189,4);
        insertMusic(db,65.819855,5.78915050844143,4);
        insertMusic(db,350.52252,16.697411437666,4);
        insertMusic(db,375.25452,0.146559595658265,4);
        insertMusic(db,87.01074,8.98515852518914,4);
        insertMusic(db,251.16896,1.923984182537,4);
        insertMusic(db,1548.5348,1.62394503463539,4);
        insertMusic(db,52.246773,3.80226705960058,4);
        insertMusic(db,163.45126,8.80439032536459,4);
    }

    private static void insertMusic(SQLiteDatabase db, double pitch, double rms, int emotion){
        ContentValues musicValues = new ContentValues();
        musicValues.put("PITCH",pitch);
        musicValues.put("RMS",rms);
        musicValues.put("EMOTION",emotion);
        db.insert("MUSIC",null,musicValues);
    }

    public static double[][] getClusterAverage(Context context){
        double[][] musicClusterArray = new double[5][3];

        for (int i = 0; i <=4; i++){
            musicClusterArray[i][0] = 0;
            musicClusterArray[i][1] = 0;
            musicClusterArray[i][2] = 0;
        }

        SQLiteOpenHelper musicDatabaseHelper = new MusicDatabaseHelper(context);
        SQLiteDatabase db = musicDatabaseHelper.getReadableDatabase();

        Cursor cur = db.rawQuery("SELECT * FROM MUSIC",null);

        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    double pitchValue = cur.getDouble(cur.getColumnIndex("PITCH"));
                    double rmsValue = cur.getDouble(cur.getColumnIndex("RMS"));
                    int emotionValue = cur.getInt(cur.getColumnIndex("EMOTION"));

                    musicClusterArray[emotionValue][0] = musicClusterArray[emotionValue][0] + pitchValue;
                    musicClusterArray[emotionValue][1] = musicClusterArray[emotionValue][1] + rmsValue;
                    musicClusterArray[emotionValue][2]++;

                } while (cur.moveToNext());
            }
        }

        for (int i = 0; i <=4; i++){
            musicClusterArray[i][0] = musicClusterArray[i][0]/musicClusterArray[i][2];
            musicClusterArray[i][1] = musicClusterArray[i][1]/musicClusterArray[i][2];
        }

        cur.close();
        db.close();

        return musicClusterArray;
    }

    public static int getEmotion(double pitch, double rms, Context context){
        int emotionIndex = 0;
        double d,minDistance=9999999;
        double[][] musicClusterArray = new double[5][3];

        musicClusterArray = getClusterAverage(context);

        for (int i = 0; i <=4; i++){
            d =java.lang.Math.sqrt(((pitch -musicClusterArray[i][0])*(pitch -musicClusterArray[i][0])) +((rms-musicClusterArray[i][1])*(rms-musicClusterArray[i][1])));
            if(d<minDistance){
                minDistance = d;
                emotionIndex = i;
            }
        }
        return emotionIndex;
    }

    public static void insertFlagValue(SQLiteDatabase db){
        ContentValues flagValue = new ContentValues();
        flagValue.put("FLAG",0);
        db.insert("ONETIMEFLAG",null,flagValue);
    }

    public static int getFlag(Context context){
        int flag = -1;
        SQLiteOpenHelper musicDatabaseHelper = new MusicDatabaseHelper(context);
        SQLiteDatabase db = musicDatabaseHelper.getReadableDatabase();

        Cursor cur = db.rawQuery("SELECT * FROM ONETIMEFLAG",null);

        if (cur != null) {
            if (cur.moveToFirst()) {
                flag = cur.getInt(cur.getColumnIndex("FLAG"));
            }
        }
        cur.close();
        db.close();
        return flag;
    }

    public static void setFlag(Context context){
        SQLiteOpenHelper musicDatabaseHelper = new MusicDatabaseHelper(context);
        SQLiteDatabase db = musicDatabaseHelper.getWritableDatabase();
        ContentValues flagValue = new ContentValues();
        flagValue.put("FLAG",1);
        db.update("ONETIMEFLAG",flagValue,"FLAG = 0",null);
        db.close();
    }

}

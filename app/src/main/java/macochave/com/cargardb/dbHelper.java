package macochave.com.cargardb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by u on 27/05/2016.
 */
public class dbHelper extends SQLiteAssetHelper{

    public static final String DBNAME = "dbDesicion.sqlite";
    public static final int DBSCHEME = 1;

    public dbHelper(Context context) {
        super(context, DBNAME, null, DBSCHEME);
    }
}

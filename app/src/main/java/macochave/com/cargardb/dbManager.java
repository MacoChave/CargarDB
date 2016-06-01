package macochave.com.cargardb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by u on 27/05/2016.
 */
public class dbManager {

    private static final String OPCION = "opcion";
    private static final String CATEGORIA = "categoria";
    private static final String[] OP_VALUES = new String[]{"_id", "nombre", "foto", "id_categoria"};
    private static final String[] CT_VALUES = new String[]{"_id", "nombre"};

    private  dbHelper helper;
    private SQLiteDatabase database;

    public dbManager(Context context) {
        helper = new dbHelper(context);
        database = helper.getWritableDatabase();
    }

    private ContentValues valuesOpcion(String nombre, String foto, int id_categoria) {
        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("foto", foto);
        valores.put("id_categoria", id_categoria);
        return valores;
    }

    private ContentValues valuesCategoria(String categoria) {
        ContentValues valores = new ContentValues();
        valores.put("nombre", categoria);
        return valores;
    }

    public ArrayList<Opcion> selectOpcion() {
        ArrayList<Opcion> lista = new ArrayList<>();

        Cursor cursor = database.query(OPCION, OP_VALUES, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Opcion opcion = new Opcion();
                opcion.setId(cursor.getInt(0));
                opcion.setNombre(cursor.getString(1));
                opcion.setFoto(cursor.getString(2));
                opcion.setId_categoria(cursor.getInt(3));
                lista.add(opcion);
            } while (cursor.moveToNext());
        }

        return lista;
    }

    public ArrayList<Categoria> selectCategoria() {
        ArrayList<Categoria> lista = new ArrayList<>();

        Cursor cursor = database.query(CATEGORIA, CT_VALUES, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Categoria categoria = new Categoria();
                categoria.setId(cursor.getInt(0));
                categoria.setCategoria(cursor.getString(1));
                lista.add(categoria);
            } while (cursor.moveToNext());
        }

        return lista;
    }

    public ArrayList<Opcion> selectOpcionCategoria (String id_categoria) {

        ArrayList<Opcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + OPCION + " WHERE id_categoria = " + id_categoria;
        Cursor cursor = database.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Opcion opcion = new Opcion();
                opcion.setId(cursor.getInt(0));
                opcion.setNombre(cursor.getString(1));
                opcion.setFoto(cursor.getString(2));
                opcion.setId_categoria(cursor.getInt(3));
                lista.add(opcion);
            } while (cursor.moveToNext());
        }

        return lista;
    }

    public int selectIdCategoria (String nombre) {
        String sql = "SELECT _id FROM " + CATEGORIA + " WHERE nombre = '" + nombre + "'";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        } else {
            return 0;
        }
    }

    public long insertOpcion (String nombre, String foto, int id_categoria) {
        return database.insert(OPCION, "foto", valuesOpcion(nombre, foto, id_categoria));
    }

    public long insertCategoria (String categoria) {
        return  database.insert(CATEGORIA, null, valuesCategoria(categoria));
    }
}

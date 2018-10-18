package es.hol.melqui.lectornoticias;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by melqui on 2/14/18.
 */

public final class FeedDatabase extends SQLiteOpenHelper {

    private static final int COLUMN_ID = 0;
    private static final int COLUMN_TITULO = 1;
    private static final int COLUMN_DESC = 2;
    private static final int COLUMN_URL = 3;

    private static FeedDatabase singleton;

    /* etiqueta de deupracion */

    private static final String TAG = FeedDatabase.class.getSimpleName();

    /* Nombre de la base de datos */
    public static final String DATABASE_NAME = "Feed.db";

    /*Versión actual de la base de datos*/
    public static final int DATABASE_VERSION = 1;

    private FeedDatabase(Context context) {
        super(context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION);
    }

    /**
     * Retorna la instancia unica del singleton
     *
     * @param context contexto donde se ejecutarán las peticiones
     * @return Instancia
     */
    public static synchronized FeedDatabase getInstance(Context context) {
        if (singleton == null) {
            singleton = new FeedDatabase(context.getApplicationContext());
        }
        return singleton;
    }



    /**
     * Obtiene todos los registros de la tabla entrada
     *
     * @return cursor con los registros
     */
    public Cursor obtenerEntradas() {
        // Seleccionamos todas las filas de la tabla 'entrada'
        return getWritableDatabase().rawQuery(
                "select * from " + ScriptDatabase.ENTRADA_TABLE_NAME, null);
    }

    /**
     * Inserta un registro en la tabla entrada
     *
     * @param titulo      titulo de la entrada
     * @param descripcion desripcion de la entrada
     * @param url         url del articulo
     * @param thumb_url   url de la miniatura
     */
    public void insertarEntrada(
            String titulo,
            String descripcion,
            String url,
            String thumb_url) {

        ContentValues values = new ContentValues();
        values.put(ScriptDatabase.ColumnEntradas.TITULO, titulo);
        values.put(ScriptDatabase.ColumnEntradas.DESCRIPCION, descripcion);
        values.put(ScriptDatabase.ColumnEntradas.URL, url);
        values.put(ScriptDatabase.ColumnEntradas.URL_MINIATURA, thumb_url);

        // Insertando el registro en la base de datos
        getWritableDatabase().insert(
                ScriptDatabase.ENTRADA_TABLE_NAME,
                null,
                values
        );
    }

    /**
     * Modifica los valores de las columnas de una entrada
     *
     * @param id          identificador de la entrada
     * @param titulo      titulo nuevo de la entrada
     * @param descripcion descripcion nueva para la entrada
     * @param url         url nueva para la entrada
     * @param thumb_url   url nueva para la miniatura de la entrada
     */
    public void actualizarEntrada(int id,
                                  String titulo,
                                  String descripcion,
                                  String url,
                                  String thumb_url) {

        ContentValues values = new ContentValues();
        values.put("titulo", titulo);
        values.put("descripcion", descripcion);
        values.put("url", url);
        values.put("thumb_url", thumb_url);

        // Modificar entrada
        getWritableDatabase().update(
                ScriptDatabase.ENTRADA_TABLE_NAME,
                values,
                ScriptDatabase.ColumnEntradas.ID + "=?",
                new String[]{String.valueOf(id)});

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //crear la tabla inicial
        sqLiteDatabase.execSQL(ScriptDatabase.CREAR_ENTRADA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Añade los cambios que se realizarán en el esquema
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ScriptDatabase.ENTRADA_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
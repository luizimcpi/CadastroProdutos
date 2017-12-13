package cadastroprodutos.devlhse.com.cadastroprodutos.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.DatabaseMetaData;

/**
 * Created by luizhse on 12/12/17.
 */

public class ProdutosDao extends SQLiteOpenHelper{


    private static final String DATABASE = "bdprodutos";
    private static final int VERSION = 1;

    public ProdutosDao(Context context){
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String produto = "CREATE TABLE produtos(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nomeproduto TEXT NOT NULL, descricao TEXT NOT NULL, quantidade INTEGER);";
        sqLiteDatabase.execSQL(produto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String produto = "DROP TABLE IF EXISTS produtos";
        sqLiteDatabase.execSQL(produto);
    }
}

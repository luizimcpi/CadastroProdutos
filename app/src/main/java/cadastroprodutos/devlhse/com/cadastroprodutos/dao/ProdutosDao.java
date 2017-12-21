package cadastroprodutos.devlhse.com.cadastroprodutos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import cadastroprodutos.devlhse.com.cadastroprodutos.model.Produto;

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

    public void salvarProduto(Produto produto){
        ContentValues values = new ContentValues();
        values.put("nomeproduto", produto.getNomeProduto());
        values.put("descricao", produto.getDescricao());
        values.put("quantidade", produto.getQuantidade());
        getWritableDatabase().insert("produtos", null, values);
    }

    public void alterarProduto(Produto produto){
        ContentValues values = new ContentValues();
        values.put("nomeproduto", produto.getNomeProduto());
        values.put("descricao", produto.getDescricao());
        values.put("quantidade", produto.getQuantidade());

        String [] args = {produto.getId().toString()};
        getWritableDatabase().update("produtos", values, "id=?", args);
    }

    public void deletarProduto(Produto produto){
        String [] args = {produto.getId().toString()};
        getWritableDatabase().delete("produtos", "id=?", args);
    }

    public ArrayList<Produto> getProdutos(){
        String[] columns = {"id","nomeproduto","descricao","quantidade"};
        Cursor cursor = getWritableDatabase().query("produtos", columns, null, null, null, null, null, null);
        ArrayList<Produto> produtos = new ArrayList<>();
        while (cursor.moveToNext()){
            Produto produto = new Produto();
            produto.setId(cursor.getLong(0));
            produto.setNomeProduto(cursor.getString(1));
            produto.setDescricao(cursor.getString(2));
            produto.setQuantidade(cursor.getInt(3));
            produtos.add(produto);
        }

        return produtos;
    }
}

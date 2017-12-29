package cadastroprodutos.devlhse.com.cadastroprodutos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import cadastroprodutos.devlhse.com.cadastroprodutos.dao.ProdutosDao;
import cadastroprodutos.devlhse.com.cadastroprodutos.model.Produto;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    ProdutosDao produtosDao;
    ArrayList<Produto> listview_Produtos;
    Produto produto;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCadastrar = (Button) findViewById(R.id.btn_Cadastrar);
        btnCadastrar.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, FormularioProdutos.class);
                startActivity(intent);
            }
        });

        lista = (ListView) findViewById(R.id.listview_Produtos);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapater, View view, int position, long id) {
                Produto produtoEscolhido = (Produto) adapter.getItem(position);
                Intent i = new Intent(MainActivity.this, FormularioProdutos.class);
                i.putExtra("produto-escolhido", produtoEscolhido);
            }
        });

    }

    protected void onResume(){
        super.onResume();
        carregarProduto();
    }

    public void carregarProduto(){
        produtosDao = new ProdutosDao(MainActivity.this);
        listview_Produtos = produtosDao.getProdutos();
        produtosDao.close();

        if(listview_Produtos != null){
            adapter = new ArrayAdapter<Produto>(MainActivity.this, android.R.layout.simple_list_item_1, listview_Produtos);
            lista.setAdapter(adapter);
        }

    }
}

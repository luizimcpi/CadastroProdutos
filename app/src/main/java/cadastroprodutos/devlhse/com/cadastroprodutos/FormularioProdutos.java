package cadastroprodutos.devlhse.com.cadastroprodutos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cadastroprodutos.devlhse.com.cadastroprodutos.dao.ProdutosDao;
import cadastroprodutos.devlhse.com.cadastroprodutos.model.Produto;

public class FormularioProdutos extends AppCompatActivity {

    EditText editText_NomeProd, editText_Descricao, editText_Quantidade;
    Button btn_Cadastro;
    Produto editarProduto, produto;
    ProdutosDao produtosDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_produtos);

        produto = new Produto();
        produtosDao = new ProdutosDao(FormularioProdutos.this);
        Intent intent = getIntent();
        editarProduto = (Produto) intent.getSerializableExtra("produto-escolhido");


        editText_NomeProd = (EditText) findViewById(R.id.editText_NomeProduto);
        editText_Descricao = (EditText)  findViewById(R.id.editText_Descricao);
        editText_Quantidade = (EditText) findViewById(R.id.editText_Quantidade);

        btn_Cadastro = (Button) findViewById(R.id.btn_Cadastro);

        if(editarProduto != null){
            btn_Cadastro.setText("Modificar");
            editText_NomeProd.setText(editarProduto.getNomeProduto());
            editText_Descricao.setText(editarProduto.getDescricao());
            editText_Quantidade.setText(editarProduto.getQuantidade()+"");

            produto.setId(editarProduto.getId());

        }else{
            btn_Cadastro.setText("Cadastrar");
        }

        btn_Cadastro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                produto.setNomeProduto(editText_NomeProd.getText().toString());
                produto.setDescricao(editText_Descricao.getText().toString());
                produto.setQuantidade(Integer.parseInt(editText_Quantidade.getText().toString()));

                if(btn_Cadastro.getText().toString().equals("Cadastrar")){
                    produtosDao.salvarProduto(produto);
                }else{
                    produtosDao.alterarProduto(produto);
                }
                produtosDao.close();
            }
        });
    }
}

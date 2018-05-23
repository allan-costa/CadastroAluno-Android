package br.com.allan.cadastroaluno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.allan.cadastro.modelo.Aluno;


public class ListaAlunos extends AppCompatActivity {

    ListView lista;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_alunos);

        lista = (ListView) findViewById(R.id.lista);

        registerForContextMenu(lista);

        AlunoDAO alunoDAO = new AlunoDAO(this);
        List<Aluno> alunos = alunoDAO.getLista();
        alunoDAO.close();

     int layout = android.R.layout.simple_list_item_1;
     ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, layout, alunos);

     lista.setAdapter(adapter);
     lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
             //Toast.makeText(ListaAlunos.this, "Clique na posição " + posicao, Toast.LENGTH_SHORT).show();
             Aluno alunoClicado = (Aluno) adapter.getItemAtPosition(posicao);

             Intent irParaFormulario = new Intent(ListaAlunos.this, Formulario.class);
             irParaFormulario.putExtra("alunoSelecionado", alunoClicado);
             startActivity(irParaFormulario);
           }
       });

       lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

           @Override
           public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
               aluno = new Aluno();
               aluno = (Aluno) adapter.getItemAtPosition(posicao);

               //System.out.println(aluno.getNome() + " / " + aluno.getId() + " / "  + aluno.getTelefone());
               //Toast.makeText(ListaAlunos.this, "Clique longo em " + adapter.getItemAtPosition(posicao), Toast.LENGTH_SHORT).show();

           return false;
           }
       });
    }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add("Ligar");
            menu.add("Enviar SMS");
            menu.add("Navegar no site");
            MenuItem deletar = menu.add("Deletar");
            deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    AlunoDAO dao = new AlunoDAO(ListaAlunos.this);

                    dao.deletar(aluno);
                    dao.close();

                    carregaLista();
                    return false;
                }
            });

            menu.add("Ver no mapa");
            menu.add("Enviar e-mail");
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater menuInflater = getMenuInflater();

            menuInflater.inflate(R.menu.listagem_alunos, menu);


            return super.onCreateOptionsMenu(menu);
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemClicado = item.getItemId();

        switch (itemClicado) {
            case R.id.novo:
                Intent irParaFormulario = new Intent(this, Formulario.class);

                startActivity(irParaFormulario);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();

    }

    private void carregaLista() {
        AlunoDAO alunoDAO = new AlunoDAO(this);
        List<Aluno> alunos = alunoDAO.getLista();
        alunoDAO.close();

        int layout = android.R.layout.simple_list_item_1;

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, layout, alunos);

        lista.setAdapter(adapter);
    }
}

package br.com.allan.cadastroaluno;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import br.com.allan.cadastro.modelo.Aluno;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by Allan on 24/01/2018.
 */

public class Formulario extends Activity {

    private FormularioHelper helper;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        Intent intent = getIntent();
        final Aluno alunoParaSerAlterado = (Aluno) intent.getSerializableExtra("alunoSelecionado");

        Toast.makeText(this, "Aluno: " + alunoParaSerAlterado, Toast.LENGTH_LONG).show();

        helper = new FormularioHelper(this);

        Button botao = (Button) findViewById(R.id.botao);

        if (alunoParaSerAlterado != null) {
            botao.setText("Alterar");
            helper.colocaAlunoNoFormulario(alunoParaSerAlterado);
        }
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aluno aluno  = helper.pegaAlunoDoFormulario();

                AlunoDAO dao = new AlunoDAO(Formulario.this);

                if(alunoParaSerAlterado == null) {
                    dao.salva(aluno);
                } else {
                    aluno.setId(alunoParaSerAlterado.getId());
                    dao.altera(aluno);
                }
                dao.salva(aluno);
                dao.close();
                finish();
            }
        });













    }
}

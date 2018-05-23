package br.com.allan.cadastroaluno;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.allan.cadastro.modelo.Aluno;

/**
 * Created by Allan on 15/03/2018.
 */

class FormularioHelper {

    private EditText editNome;
    private EditText editSite;
    private EditText editTelefone;
    private EditText editEndereco;
    private RatingBar ratingNota;

        public FormularioHelper(Formulario formulario) {
             editNome = (EditText) formulario.findViewById(R.id.nome);
             editSite = (EditText) formulario.findViewById(R.id.site);
             editTelefone = (EditText) formulario.findViewById(R.id.telefone);
             editEndereco = (EditText) formulario.findViewById(R.id.endereco);
             ratingNota = (RatingBar) formulario.findViewById(R.id.nota);
        }

        public Aluno pegaAlunoDoFormulario() {
            Aluno aluno = new Aluno();

            aluno.setNome(editNome.getText().toString());
            aluno.setSite(editSite.getText().toString());
            aluno.setTelefone(editTelefone.getText().toString());
            aluno.setEndereco(editEndereco.getText().toString());
            aluno.setNota(Double.valueOf(ratingNota.getRating()));

            return aluno;
    }

    public void colocaAlunoNoFormulario(Aluno alunoParaSerAlterado) {
            editNome.setText(alunoParaSerAlterado.getNome());
            editSite.setText(alunoParaSerAlterado.getSite());
            editTelefone.setText(alunoParaSerAlterado.getTelefone());
            editEndereco.setText(alunoParaSerAlterado.getEndereco());
            ratingNota.setRating(alunoParaSerAlterado.getNota().floatValue());
    }
}

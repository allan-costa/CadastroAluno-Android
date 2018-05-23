package br.com.allan.cadastroaluno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.allan.cadastro.modelo.Aluno;

/**
 * Created by Allan on 15/03/2018.
 */

class AlunoDAO extends SQLiteOpenHelper{

    private static  final String DATABASE = "CADASTROALUNOS";
    private static  final int VERSAO = 2;

    public AlunoDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    public  void salva(Aluno aluno) {
        ContentValues values = new ContentValues();

        values.put("nome", aluno.getNome());
        values.put("site", aluno.getSite());
        values.put("endereco", aluno.getEndereco());
        values.put("nota", aluno.getNota());
        values.put("foto", aluno.getFoto());
        values.put("telefone", aluno.getTelefone());

        getWritableDatabase().insert("Aluno", null, values);
    }

    public void altera(Aluno aluno) {
        ContentValues values = new ContentValues();

        values.put("nome", aluno.getNome());
        values.put("site", aluno.getSite());
        values.put("endereco", aluno.getEndereco());
        values.put("nota", aluno.getNota());
        values.put("foto", aluno.getFoto());
        values.put("telefone", aluno.getTelefone());

        String[] args = {aluno.getId().toString()};
        getWritableDatabase().update("Aluno", values, "id=?", args);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE Aluno (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT UNIQUE NOT NULL," +
                "telefone TEXT, endereco TEXT, site TEXT, foto TEXT, nota REAL);";

        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String ddl = "DROP TABLE IF EXISTS Aluno;";
        db.execSQL(ddl);

        this.onCreate(db);
    }

    public List<Aluno> getLista() {
        String[] colunas = {"id", "nome", "site", "telefone", "endereco", "foto", "nota"};
        Cursor cursor  =getWritableDatabase().query("Aluno", colunas, null, null, null, null,null);

        cursor.moveToNext();

        ArrayList<Aluno> alunos = new ArrayList<>();
        while (cursor.moveToNext()) {

            Aluno aluno = new Aluno();

            aluno.setId(cursor.getLong(0));
            aluno.setNome(cursor.getString(1));
            aluno.setSite(cursor.getString(2));
            aluno.setTelefone(cursor.getString(3));
            aluno.setEndereco(cursor.getString(4));
            aluno.setFoto(cursor.getString(5));
            aluno.setNota(cursor.getDouble(6));
            alunos.add(aluno);

            //System.out.println("Supostamente o ID =>" + aluno.getId() + "Nome " + aluno.getNome());
        }


        return alunos;
    }

    public void deletar(Aluno aluno) {
        //System.out.println(aluno.getId() +"   HIIIIIT");
        String[] args = {aluno.getId().toString()};
        getWritableDatabase().delete("Aluno", "id=?", args);

    }
}


package com.ifpb.coletor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Esta Classe � respons�vel por definir o nome do banco. Herdada de BancoManager, implementa os m�todos de criar e atualizar a base de dados.
 */
public class BancoUsuarios extends BancoManager
{
    //nome do banco de dados e vers�o
    public static final String NAME = "coletor_db";
    public static final String TAG_LOG = "coletor_db";
    public static final int VERSAO = 1;
    
    public BancoUsuarios(Context context)
    {
        //defino pelo contrutor do BancoManager a vers�o e o nome do banco
        super(context, NAME, VERSAO);
    }
 
    @Override
    public void onCreate(SQLiteDatabase bd)
    {
        criaTabelas(bd);
    }
 
    /**
     * Este m�todo � chamado automaticamente quando a vers�o � alterada.
     */
    @Override
    public void onUpgrade(SQLiteDatabase bd, int versaoAtual, int versaoNova)
    {
        //realizaa tratamento de upgrade, caso tenha
        //altera��o em tabelas por exemplo.
        Log.e(TAG_LOG, "V.atual: " + versaoAtual);
        Log.e(TAG_LOG, "Nova V.: " + versaoNova);
        //Aqui voc� deve fazer o tratamento do update do banco.
        //no caso estou apagando minha tabela e criando novamente.
        try
        {
            bd.execSQL("drop table leituras;");
            bd.execSQL("drop table mercadorias;");
        } catch (Exception e)
        {
            Log.e(TAG_LOG, "onUpgrade", e);
        }
        criaTabelas(bd);
    }
 
    private void criaTabelas(SQLiteDatabase bd)
    {
        try
        {
            //crio o banco de dados atravez do arquivo create.
            byFile(R.raw.create, bd);
        }
        catch (Exception e)
        {
            Log.e(TAG_LOG, "criaTabelas", e);
        }
    }
}

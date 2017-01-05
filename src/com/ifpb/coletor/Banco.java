package com.ifpb.coletor;

import android.database.sqlite.SQLiteDatabase;
/**
 * Classe responsável por abrir e fechar o banco de dados.
 *
 * @return
 */
public class Banco
{
    private BancoManager bancoManager;
    private SQLiteDatabase sqld;
 
    public Banco(BancoManager bancoManager)
    {
        this.bancoManager = bancoManager;
    }
 
    public void open()
    {
        sqld = bancoManager.getWritableDatabase();
    }
 
    public SQLiteDatabase get()
    {
        if ( sqld != null && sqld.isOpen() )
        {
            return sqld;
        }
        return null;
    }
 
    public void close()
    {
        bancoManager.close();
    }
}
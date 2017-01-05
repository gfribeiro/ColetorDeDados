package com.ifpb.coletor;

import java.io.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class BancoManager extends SQLiteOpenHelper
{
    protected Context context;
    
    /**
     * Classe abstrata, informa os métodos padrões para criar a base de dados e realizar atualizações.
     *
     * @return
     */
    public BancoManager(Context context, String nome, int versao)
    {
        super(context, nome, null, versao);
        this.context = context;
    }
 
    public abstract void onCreate(SQLiteDatabase bd);
 
    public abstract void onUpgrade(SQLiteDatabase bd, int versaoAtual, int versaoNova);
 
    /** Atravez do id do arquivo sql será gerado o banco de dados.
     *
     * @param fileID
     * @param bd
     * @throws IOException
     */
    protected void byFile(int fileID, SQLiteDatabase bd) throws IOException
    {
        StringBuilder sql = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(fileID)));
        String line;
        while ( (line = br.readLine()) != null )
        {
            line = line.trim();
            if ( line.length() > 0 )
            {
                sql.append(line);
                if ( line.endsWith(";") )
                {
                    bd.execSQL(sql.toString());
                    sql.delete(0, sql.length());
                }
            }
        }
    }
}

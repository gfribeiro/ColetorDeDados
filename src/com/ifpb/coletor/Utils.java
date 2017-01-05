package com.ifpb.coletor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;

@SuppressLint("SdCardPath")
public class Utils {

		
	//LER ARQUIVO PRODUTOS.TXT E CARREGA OS DADOS NO BANCO DE DADOS.
	public boolean CarregarProdutos(Banco db){
	
	String lstrNomeArq = "PRODUTOS.txt";
    File arq;
    String barras, mercadoria, preco;
    
    try
    {
    	
    	//ABRE ARQUIVO PRODUTOS.TXT NA RAIZ DO CARTÃO DE MEMÓRIA
        arq = new File("/sdcard/", lstrNomeArq);
        BufferedReader br = new BufferedReader(new FileReader(arq));
        
        //DELETA TODAS AS MERCADORIAS IMPORTADAS PARA IMPORTAR AS NOVAS
        db.get().delete("mercadorias", null, null);
        
        //efetua uma leitura linha a linha do arquivo a carrega
        //a caixa de texto com a informação lida
       
        while (br.ready()) {  
        	  
            String linha = br.readLine(); // lê uma linha...  
            String[] arrayDados = linha.split(";");  // separa os dados por seu delimitador... 
        
            barras = arrayDados[0];
            mercadoria = arrayDados[1];
            preco = arrayDados[2];
            
            //INSERE DADOS NO BANCO
            ContentValues cv = new ContentValues();
            cv.put("barras", barras);
            cv.put("mercadoria", mercadoria);
            cv.put("preco", preco);
            db.get().insert("mercadorias", null, cv);
            
        }
        
        br.close();
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }       
	}
	
	//GRAVA LEITURAS NO ARQUIVO LEITURAS.TXT
	public boolean GravarLeituras(Banco db)
    {
		String lstrNomeArq = "LEITURAS.txt";
        File arq;
        byte[] dados;
        try
        {
            //pega o nome do arquivo a ser gravado
                        
            arq = new File("/sdcard/", lstrNomeArq);
            FileOutputStream fos = new FileOutputStream(arq);
          
            //LER BANCO DE DADOS
            Cursor cursor = db.get().rawQuery("select * from leituras", null);
            
            while (cursor.moveToNext())
            {
                String barras = cursor.getString(cursor.getColumnIndex("barras"));
                
                String quant = cursor.getString(cursor.getColumnIndex("quantidade"));
                
                String linha = barras.toString()+";"+quant.toString()+"\r\n";
                
                dados = linha.getBytes();
                
                //ESCREVE NO ARQUIVO
                fos.write(dados);
            }
            
            //FECHA O CURSOR
            cursor.close();

            //FECHA ARQUIVO
            fos.flush();
            fos.close();
            
            return true;
            
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }       
    }
	
	
}

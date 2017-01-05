package com.ifpb.coletor;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
//import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LeituraActivity extends Activity {

	//DECLARAÇÃO DE CONTROLES
	private Button btn0;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn5;
	private Button btn6;
	private Button btn7;
	private Button btn8;
	private Button btn9;
	private Button btnBkspc;
	private Button btnPonto;
	private Button btnCamera;
	private Button btnSalvar;
	private Button btnLimpar;
	private EditText txQuant;
	private EditText txBarras;
	private TextView txMerc;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leitura_activity);
        Banco db = new Banco(new BancoUsuarios(this));
        db.open();
        
        //INSTANCIA OS BOTÕES
        
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btnBkspc = (Button) findViewById(R.id.btnBkspc);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnLimpar = (Button) findViewById(R.id.btnLimpar);
        btnPonto = (Button) findViewById(R.id.btnPonto);
        btnCamera = (Button) findViewById(R.id.btnCamera);
        txQuant = (EditText) findViewById(R.id.txQuant);
        txQuant.setInputType(InputType.TYPE_NULL);
        txBarras = (EditText) findViewById(R.id.txBarras);
        txBarras.setInputType(InputType.TYPE_NULL);
        txMerc = (TextView) findViewById(R.id.txMerc);
        
        //AÇÕES DOS BOTÕES
		btn0.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {	
				txQuant.setText(txQuant.getText()+"0"); 
			}
		});
		btn1.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {	
				txQuant.setText(txQuant.getText()+"1"); 
			}
		});
		btn2.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {	
				txQuant.setText(txQuant.getText()+"2"); 
			}
		});
		btn3.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {	
				txQuant.setText(txQuant.getText()+"3"); 
			}
		});
		btn4.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {	
				txQuant.setText(txQuant.getText()+"4"); 
			}
		});
		btn5.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {	
				txQuant.setText(txQuant.getText()+"5"); 
			}
		});
		btn6.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {	
				txQuant.setText(txQuant.getText()+"6"); 
			}
		});
		btn7.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {	
				txQuant.setText(txQuant.getText()+"7"); 
			}
		});
		btn8.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {	
				txQuant.setText(txQuant.getText()+"8"); 
			}
		});
		btn9.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {	
				txQuant.setText(txQuant.getText()+"9"); 
			}
		});
		btnBkspc.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {	
				txQuant.setText(""); 
			}
		});
		btnPonto.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {	
				txQuant.setText(txQuant.getText()+"."); 
			}
		});
		btnCamera.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {	
				lerCodigoBarra(v);
			}
		});
		btnSalvar.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {	
				if (txBarras.getText().toString().equals("") || txMerc.getText().toString().equals("MERCADORIA NÃO LOCALIZADA!") || txQuant.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "Erro ao Salvar, verifique os dados!", Toast.LENGTH_LONG).show();
				} else {
					//INSERE REGISTRO NO BANCO
					insertLeitura(txBarras.getText().toString(), txQuant.getText().toString());
					Toast.makeText(getApplicationContext(), "Item Salvo com Sucesso!", Toast.LENGTH_LONG).show();
					LimparDados();
				}
			}
		});
		btnLimpar.setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {	
				LimparDados();
			}
		});
		txBarras.setOnKeyListener(new OnKeyListener() {           
            public boolean onKey(View v, int keyCode, KeyEvent event) {  
                if (event.getAction() == KeyEvent.ACTION_DOWN) {  
                    switch (keyCode) {  
                    case KeyEvent.KEYCODE_ENTER:  
                        selectMerc(txBarras.getText().toString());
                       return true;
                    }
                    
                }
				return false;
            }  
        });
        
    }

    //SALVA CONTROLES DA ACTIVITY	
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
      super.onSaveInstanceState(savedInstanceState);
      savedInstanceState.putString("txMerc", txMerc.getText().toString());
    }
    
    //RECUPERA CONTROLES DA ACTIVITY
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
      super.onRestoreInstanceState(savedInstanceState);

      String txMercString = savedInstanceState.getString("txMerc");
      
      txMerc.setText(txMercString);
    }    
    
    //CRIA MENU DE OPÇÕES
    public boolean onCreateOptionsMenu(Menu menu){
        
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.config_menu, menu);
        return true;
    	
    }
    
    //AÇÕES DO MENU DE OPÇÕES 
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.configurar:
        return true;
        case R.id.sobre:

        return true;
        default:
        return super.onOptionsItemSelected(item);
        }
    }
    
    //DECLARA O CONTEXTO
	Context context;
   
	//CHAMA A APLICAÇÃO BARCODE SCANNER
	public void lerCodigoBarra(View v){
        
        IntentIntegrator integrator = new IntentIntegrator(this);                
    integrator.initiateScan();
        
    }

	//RETORNA OS DADOS DO BARCODE SCANNER
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
             IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
             if (scanResult != null) {
               
                     String barcode;
                     //String typ;
                     //String[] nome;
                     barcode = scanResult.getContents();
                     //typ = scanResult.getFormatName();
                     
                     Banco db = new Banco(new BancoUsuarios(this));
                     db.open();
                     
                     txBarras.setText(barcode);
                     selectMerc(barcode);
                     txQuant.requestFocus();
             }
   } 
    
    //REALIZA CONSULTA NO BANCO PROCURANDO A MERCADORIA E RETORNANDO SEUS DADOS.
    private void selectMerc(String barras) {
    	
    	Banco db = new Banco(new BancoUsuarios(this));
        db.open();
        
        //INSTANCIA CURSOR (RESULTSET NO ANDROID)
        Cursor cursor = db.get().rawQuery("select * from mercadorias where barras='"+barras+"'", null);
        
        //VERIFICA SE A MERCADORIA FOI LOCALIZADA E DEFINE OS DADOS NA TELA.
        if (cursor.getCount() == 0 ){
        	txMerc.setText("MERCADORIA NÃO LOCALIZADA!");
        }
        
        while (cursor.moveToNext())
        {
            String merc = cursor.getString(cursor.getColumnIndex("mercadoria"));
            String preco = cursor.getString(cursor.getColumnIndex("preco"));
            txMerc.setText(merc+" - Valor: "+preco);
        }
        //FECHA O CURSOR
        cursor.close();
        db.close();
    }
    
    //INSERE REGISTRO DA LEITURA NO BANCO
	private void insertLeitura (String barras, String quant) {
	
	Banco db = new Banco(new BancoUsuarios(this));
    db.open();
	
    Float quantidade = 	Float.parseFloat(quant);
	
    //inserir dados
    ContentValues cv = new ContentValues();
    cv.put("barras", barras);
    cv.put("quantidade", quantidade);
    long l = db.get().insert("leituras", null, cv);
    Log.i("LEITURA", "id insert: " + l);
    
    db.close();
    
	}
	
	private void LimparDados() {
		txQuant.setText("");
		txBarras.setText("");
		txMerc.setText("");
		txBarras.requestFocus();
	}
}

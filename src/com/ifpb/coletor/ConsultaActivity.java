package com.ifpb.coletor;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ConsultaActivity extends Activity {

	private ListView listView;
    private ConsultaAdapterListView adapterListView;
    private ArrayList<ConsultaListView> itens;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_activity);
        
        //Pega a referencia do ListView
        listView = (ListView) findViewById(R.id.consultaListView);
        //Define o Listener quando alguem clicar no item.
        registerForContextMenu(listView);
        createListView();
        
    }

   @Override
   public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
   {
           super.onCreateContextMenu(menu, v, menuInfo);
           menu.setHeaderTitle("Selecione a Opção"); 
           menu.add(0, v.getId(), 0, "Remover item"); 
           menu.add(0, v.getId(), 0, "Cancelar");

   }
   
   public boolean onContextItemSelected(MenuItem item)
   { 


               AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
              
       //  info.position will give the index of selected item
                  int IndexSelected = info.position;
                		
                		  
                       if(item.getTitle()=="Remover item")
                       {
                    	   ConsultaListView itemEx = adapterListView.getItem(IndexSelected);
                    	   deleteItem(itemEx.getId());
                    	   createListView();
                       } 
                       else if(item.getTitle()=="Cancelar")
                       {
                          return false;
                                                                                } 
                       else
                       {
                           return false;
                       } 
                       return true; 
              
                          
     }  
    
    public boolean onCreateOptionsMenu(Menu menu){
        
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.consulta_menu, menu);
        return true;
    	
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.consulta_menu1:
        	ExcluirBox();
        return true;
        default:
        return super.onOptionsItemSelected(item);
        }
    }
    
	Context context;
	

private void createListView() {
	
	itens = new ArrayList<ConsultaListView>();
	
	Banco db = new Banco(new BancoUsuarios(this));
    db.open();
    
  //INSTANCIA CURSOR (RESULTSET NO ANDROID)
    Cursor cursor = db.get().rawQuery("select leituras.id as ID, leituras.barras as BAR, leituras.quantidade as QUANT, mercadorias.mercadoria as MERC from leituras inner join mercadorias on leituras.barras = mercadorias.barras", null);
       
    while (cursor.moveToNext())
    {
        String barras = cursor.getString(cursor.getColumnIndex("BAR"));
        Integer id = cursor.getInt(cursor.getColumnIndex("ID"));
        String merc = cursor.getString(cursor.getColumnIndex("MERC"));
        String quant = cursor.getString(cursor.getColumnIndex("QUANT"));
        ConsultaListView item1 = new ConsultaListView(id, barras, merc, quant, R.drawable.box);
        itens.add(item1);
    }
    
    //FECHA O CURSOR
    cursor.close();
    db.close();
	
    //Cria o adapter
    adapterListView = new ConsultaAdapterListView(this, itens);

    //Define o Adapter
    listView.setAdapter(adapterListView);
    //Cor quando a lista é selecionada para ralagem.
    listView.setCacheColorHint(Color.TRANSPARENT);
}

public void ExcluirBox(){
	AlertDialog.Builder dialog = new
	AlertDialog.Builder(this);
	dialog.setTitle("Excluir");
	dialog.setMessage("Deseja realmente excluir todas as leituras?");
	
	//Botão Sim
	dialog.setPositiveButton("Sim", new
	DialogInterface.OnClickListener() {
	@Override
	public void onClick(DialogInterface di, int arg) { 
		deleteAll();
		createListView();
	}
	});
	 
	// botao nao executa algo se o botao nao for pressionado
	dialog.setNegativeButton("Não", new
	DialogInterface.OnClickListener() {
	 
	@Override
	public void onClick(DialogInterface di, int arg) {
	 
	}
	});
	dialog.setTitle("Excluir");
	dialog.show();
	 
	}

//DELETA O ITEM SELECIONADO
private void deleteItem (int item) {
	
	Banco db = new Banco(new BancoUsuarios(this));
    db.open();
    
    db.get().delete("leituras", "id="+item, null);
    
    db.close();
}

//DELETA TODOS OS ITENS
private void deleteAll() {
	
	Banco db = new Banco(new BancoUsuarios(this));
    db.open();
    
    db.get().delete("leituras", null, null);
    
    db.close();
}

}

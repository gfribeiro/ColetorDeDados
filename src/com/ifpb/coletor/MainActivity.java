package com.ifpb.coletor;

import java.util.ArrayList;

import com.ifpb.coletor.AdapterListView;
import com.ifpb.coletor.ItemListView;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity implements OnItemClickListener {

	private ListView listView;
    private AdapterListView adapterListView;
    private ArrayList<ItemListView> itens;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Pega a referencia do ListView
        listView = (ListView) findViewById(R.id.menuListView);
        //Define o Listener quando alguem clicar no item.
        listView.setOnItemClickListener(this);
        
        createListView();
                
    }

    public boolean onCreateOptionsMenu(Menu menu){
        
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.config_menu, menu);
        return true;
    	
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.configurar:
        return true;
        case R.id.sobre:
    		Intent i3 = new Intent(MainActivity.this, SobreActivity.class);
    		startActivity(i3);
        return true;
        default:
        return super.onOptionsItemSelected(item);
        }
    }
    
	Context context;
	

private void createListView() {
    //Criamos nossa lista que preenchera o ListView
    itens = new ArrayList<ItemListView>();
    ItemListView item1 = new ItemListView(1, "Iniciar Leitura", R.drawable.menu_iniciar);
    ItemListView item2 = new ItemListView(2, "Consultar Leitura", R.drawable.menu_consultar);
    ItemListView item3 = new ItemListView(3, "Importar Dados", R.drawable.menu_import);
    ItemListView item4 = new ItemListView(4, "Exportar Dados", R.drawable.menu_export);
    ItemListView item5 = new ItemListView(5, "Sair", R.drawable.menu_sair);

    itens.add(item1);
    itens.add(item2);
    itens.add(item3);
    itens.add(item4);
    itens.add(item5);

    //Cria o adapter
    adapterListView = new AdapterListView(this, itens);

    //Define o Adapter
    listView.setAdapter(adapterListView);
    //Cor quando a lista é selecionada para ralagem.
    listView.setCacheColorHint(Color.TRANSPARENT);
}

public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    //Pega o item que foi selecionado.
    ItemListView item = adapterListView.getItem(arg2);
    Banco db = new Banco(new BancoUsuarios(this));
    final Utils ut = new Utils();
    
    switch (item.getId()){
	
	case 1:
		Intent i = new Intent(MainActivity.this, LeituraActivity.class);
		startActivity(i);
		break;
	case 2:
		Intent i2 = new Intent(MainActivity.this, ConsultaActivity.class);
		startActivity(i2);
		break;
	case 3:
		// CARREGA PROCESSAMENTO
		db.open();
	    //INSTANCIA CLASSE UTILS E CHAMA A FUNÇÃO DE CARREGAR PRODUTOS
	    if (ut.CarregarProdutos(db)){
	    	Toast.makeText(getApplicationContext(), "Dados importados com sucesso!", Toast.LENGTH_LONG).show();
	    	db.close();
	    } else {
	    	Toast.makeText(getApplicationContext(), "Erro ao importar os dados!", Toast.LENGTH_LONG).show();
	    }
	    break;
	    
	case 4:
		db.open();
	    //INSTANCIA CLASSE UTILS E CHAMA A FUNÇÃO DE CARREGAR PRODUTOS
	    if (ut.GravarLeituras(db)){
	    	Toast.makeText(getApplicationContext(), "Dados exportados com sucesso!", Toast.LENGTH_LONG).show();
	    	db.close();
	    } else {
	    	Toast.makeText(getApplicationContext(), "Erro ao exportar os dados!", Toast.LENGTH_LONG).show();
	    }
		break;
	case 5:
		sairBox();
    break;
 
    }

}

public void onBackPressed(){
	sairBox();}

public void sairBox(){
	AlertDialog.Builder dialog = new
	AlertDialog.Builder(this);
	dialog.setTitle("Sair");
	dialog.setMessage("Deseja sair do aplicativo?");
	
	//Botão Sim
	dialog.setPositiveButton("Sim", new
	DialogInterface.OnClickListener() {
	@Override
	public void onClick(DialogInterface di, int arg) { 
		finish();
	}
	});
	 
	// botao nao executa algo se o botao nao for pressionado
	dialog.setNegativeButton("Não", new
	DialogInterface.OnClickListener() {
	 
	@Override
	public void onClick(DialogInterface di, int arg) {
	 
	}
	});
	dialog.setTitle("Aviso");
	dialog.show();
	 
	}

}

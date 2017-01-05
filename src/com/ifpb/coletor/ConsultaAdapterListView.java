package com.ifpb.coletor;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ConsultaAdapterListView extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<ConsultaListView> itens;

    public ConsultaAdapterListView(Context context, ArrayList<ConsultaListView> itens) {
        //Itens que preencheram o listview
        this.itens = itens;
        //responsavel por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Retorna a quantidade de itens
     *
     * @return
     */
    public int getCount() {
        return itens.size();
    }

    /**
     * Retorna o item de acordo com a posicao dele na tela.
     *
     * @param position
     * @return
     */
    public ConsultaListView getItem(int position) {
        return itens.get(position);
    }

    /**
     * Sem implementação
     *
     * @param position
     * @return
     */
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        //Pega o item de acordo com a posção.
    	ConsultaListView item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.item_consultalistview, null);

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) view.findViewById(R.id.consulta_txBarras)).setText("Barras: "+item.getBarras());
        ((TextView) view.findViewById(R.id.consulta_txMerc)).setText("Merc.: "+item.getMercadoria());
        ((TextView) view.findViewById(R.id.consulta_txQuant)).setText("Quant.: "+item.getQuantidade());
        ((ImageView) view.findViewById(R.id.consulta_img)).setImageResource(item.getIconeRid());

        return view;
    }
}
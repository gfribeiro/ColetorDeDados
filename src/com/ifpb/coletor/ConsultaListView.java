package com.ifpb.coletor;

public class ConsultaListView {

	private int id;
	
    private String barras;
    
    private String mercadoria;

    private int iconeRid;
    
    private String quantidade;

    public ConsultaListView() {

    }
 
    public ConsultaListView(int id, String barras, String mercadoria, String quantidade, int iconeRid) {
    	
    	this.id = id;
    	
        this.barras = barras;

        this.mercadoria = mercadoria;

        this.quantidade = quantidade;

        this.iconeRid = iconeRid;

    }

    public int getIconeRid() {
        return iconeRid;
    }

    public void setIconeRid(int iconeRid) {
        this.iconeRid = iconeRid;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarras() {
        return barras;
    }
    public String getMercadoria() {
    	return mercadoria;
    }
    public String getQuantidade() {
    	return quantidade;
    }

    public void setBarras(String barras) {
        this.barras = barras;
    }
    public void setMercadoria(String mercadoria) {
    	this.mercadoria = mercadoria;
    }
    public void setQuantidade(String quantidade) {
    	this.quantidade = quantidade;
    }

}

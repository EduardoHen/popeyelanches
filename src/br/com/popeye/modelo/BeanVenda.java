package br.com.popeye.modelo;

import java.util.ArrayList;

public class BeanVenda {
	private int idVenda;
	private int mesa;
	private String data;
	private double cartao;
	private double total;
	private ArrayList<Integer> produtos;
	private ArrayList<Integer> quantidades;
	
	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public int getMesa() {
		return mesa;
	}
	
	public void setMesa(int mesa) {
		this.mesa = mesa;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		data = data.substring(6, 10) + "-" + data.substring(3, 5) + "-" + data.substring(0, 2);
		this.data = data;
	}
	
	public void setDataB(String data){
		this.data = data;
	}
	
	public double getCartao() {
		return cartao;
	}
	
	public void setCartao(double cartao) {
		this.cartao = cartao;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}

	public ArrayList<Integer> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Integer> produtos) {
		this.produtos = produtos;
	}
	
	public ArrayList<Integer> getQuantidades() {
		return quantidades;
	}

	public void setQuantidades(ArrayList<Integer> quantidades) {
		this.quantidades = quantidades;
	}

}

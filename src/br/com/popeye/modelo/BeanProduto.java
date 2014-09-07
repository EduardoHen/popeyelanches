package br.com.popeye.modelo;

public class BeanProduto {
	private Integer idProduto;
	private String nome;
	private String tipo;
	private Float preco;
	private int quantidade;
	
	public Integer getIdProduto(){
		return this.idProduto;
	}
	
	public void setIdProduto(Integer idProduto){
		this.idProduto = idProduto;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Float getPreco() {
		return preco;
	}
	
	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


}

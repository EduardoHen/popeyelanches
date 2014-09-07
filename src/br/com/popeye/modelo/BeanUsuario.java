package br.com.popeye.modelo;

public class BeanUsuario {
	private int idUsuario;
	private String usuario;
	private String senha;
	private int adm;
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public int getAdm() {
		return adm;
	}
	
	public void setAdm(boolean adm) {
		int a = 0;
		if (adm == true) {
			a = 1;
		}else {
			a = 0;
		}
		this.adm = a;
	}
	

}

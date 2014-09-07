package br.com.popeye.controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import br.com.popeye.modelo.BeanUsuario;

public class ConUsuario {
	private PreparedStatement stmt;
	private Connection con;
	private ImageIcon imagen = new ImageIcon("C:/Program Files/Popeye/popeyeicomenor.png");
	
	private void abreConexao() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/popeye","root","popeye000");
	}
	
	private void fechaConexao() throws SQLException{
		con.close();
	}
	
	public boolean verificaUsuarioSenha(String u, String s) throws ClassNotFoundException, SQLException{
		boolean v1, v2 = false;
		abreConexao();
		stmt = con.prepareStatement("SELECT * FROM usuario;");
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()){
			if((rs.getString("usuario").equals(u))&&(rs.getString("senha").equals(s))){
				v1 = true;
				v2 = v1;
			}else{
				v1 = false;
			}
		}
		fechaConexao();
		return v2;
	}
	
	
	public boolean verificaAdministrador(String usr, String senha) throws ClassNotFoundException, SQLException{
		boolean adm = false;
		abreConexao();
		
		stmt = con.prepareStatement("SELECT adm FROM usuario WHERE usuario = ? AND senha = ?;");
		
		stmt.setString(1, usr);
		stmt.setString(2, senha);
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			adm = rs.getBoolean("adm");
		}
		rs.close();
		stmt.close();
		fechaConexao();
		return adm;
	}
	public boolean verificaAdministrador(String s) throws ClassNotFoundException, SQLException{
		boolean ad = false;
		abreConexao();
		stmt = con.prepareStatement("SELECT adm FROM usuario WHERE senha = ?;");
		
		stmt.setString(1, s);
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			if (rs.getBoolean("adm") == true){
				ad = rs.getBoolean("adm");
			}
		}
		
		fechaConexao();
		return ad;
	}
	
	
	public void insereUsuario(BeanUsuario bu) throws ClassNotFoundException, SQLException{
		abreConexao();
		
		stmt = con.prepareStatement("INSERT INTO usuario (usuario, senha, adm) VALUES (?, ?, ?);");
		
		stmt.setString(1, bu.getUsuario());
		stmt.setString(2, bu.getSenha());
		stmt.setInt(3, bu.getAdm());
		
		stmt.execute();
		
		JOptionPane.showMessageDialog(null, "Novo usuario cadastrado com sucesso!", "Cadastro de usuario!", 1, imagen);
		
		stmt.close();
		fechaConexao();
	}
	
	public ArrayList<BeanUsuario> consultaUsuarios() throws ClassNotFoundException, SQLException{
		ArrayList<BeanUsuario> usuarios = new ArrayList<BeanUsuario>();
		abreConexao();
		
		stmt = con.prepareStatement("SELECT * FROM usuario;");
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			BeanUsuario usr = new BeanUsuario();
			
			usr.setIdUsuario(rs.getInt("idUsuario"));
			usr.setUsuario(rs.getString("usuario"));
			usr.setSenha(rs.getString("senha"));
			usr.setAdm(rs.getBoolean("adm"));
			
			usuarios.add(usr);
		}
		rs.close();
		stmt.close();
		fechaConexao();
		return usuarios;
	}
	
	
	public ArrayList<BeanUsuario> buscarUsuario(String campousuario) throws ClassNotFoundException, SQLException{
		ArrayList<BeanUsuario> usuarios = new ArrayList<BeanUsuario>();
		abreConexao();
		
		stmt = con.prepareStatement("SELECT *  FROM usuario WHERE usuario like ?;");
		
		stmt.setString(1, "%"+campousuario+"%");
		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			BeanUsuario usr = new BeanUsuario();
			
			usr.setIdUsuario(rs.getInt("idUsuario"));
			usr.setUsuario(rs.getString("usuario"));
			usr.setSenha(rs.getString("senha"));
			usr.setAdm(rs.getBoolean("adm"));
			
			usuarios.add(usr);
		}
		rs.close();
		stmt.close();
		fechaConexao();
		return usuarios;
	}
	
	public void alteraUsuario(Boolean verificacao, String usr, String pass, String novoUsr, String novaSenha, boolean ad) throws ClassNotFoundException, SQLException{
		if (verificacao == true) {
			abreConexao();
			
			stmt = con.prepareStatement("UPDATE usuario SET usuario = ?, senha = ?, adm = ? WHERE usuario = ? AND senha = ?;");
			stmt.setString(1, novoUsr);
			stmt.setString(2, novaSenha);
			stmt.setBoolean(3, ad);
			stmt.setString(4, usr);
			stmt.setString(5, pass);
			
			stmt.execute();
			
			JOptionPane.showMessageDialog(null, "Alterado com sucesso!", "Alteração!", JOptionPane.INFORMATION_MESSAGE, imagen);
			
			stmt.close();
			
			fechaConexao();
		}else{
			JOptionPane.showMessageDialog(null, "Usuário ou senha não conferem!", "Erro!", JOptionPane.ERROR_MESSAGE, null);
		}
	}
	
	
	
	public void excluiUsuario(int c) throws ClassNotFoundException, SQLException{
		abreConexao();
		stmt = con.prepareStatement("DELETE FROM usuario WHERE idUsuario = ?;");
		
		stmt.setInt(1, c);
		
		stmt.execute();
		
		JOptionPane.showMessageDialog(null, "Exclusão do usuario "+ c +" efetuada com sucesso!", "Exclusão", JOptionPane.INFORMATION_MESSAGE, imagen);
		
		stmt.close();
		fechaConexao();
	}

}

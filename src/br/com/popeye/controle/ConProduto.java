package br.com.popeye.controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import br.com.popeye.modelo.BeanProduto;

public class ConProduto {
	private Connection con;
	private PreparedStatement stmt;
	private ImageIcon img = new ImageIcon("C:/Program Files/Popeye/popeyeicomenor.png");
	
	
	private void abreConexao() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/popeye","root","popeye000");
	}
	
	
	private void fechaConexao() throws SQLException{
		con.close();
	}
	
	
	public void insereProduto(BeanProduto bp) throws ClassNotFoundException, SQLException{
		abreConexao();

		stmt = con.prepareStatement("INSERT INTO produto(nome,tipo,preco)VALUES(?,?,?);");
		
		stmt.setString(1, bp.getNome());
		stmt.setString(2, bp.getTipo());
		stmt.setFloat(3, bp.getPreco());
		
		stmt.execute();
		
		JOptionPane.showMessageDialog(null, "Produto gravado com sucesso!", "Confirmação", 1, img);
		
		stmt.close();
		
		fechaConexao();
	}
	
	public void consultaProduto(JTable tabela) throws ClassNotFoundException, SQLException{
		abreConexao();
		stmt = con.prepareStatement("SELECT * FROM produto;");
		
		DefaultTableModel tm = (DefaultTableModel) tabela.getModel();
		
		tm.setNumRows(0);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			tm.addRow(new Object[]{
					rs.getInt("idProduto"),
					rs.getString("nome"),
					rs.getString("tipo"),
					"R$ "+rs.getFloat("preco")
			});
			
		}
		rs.close();
		stmt.close();
		fechaConexao();
	}
	
	public void consultaProduto(JTable tabela, String nome) throws ClassNotFoundException, SQLException{
		abreConexao();
		stmt = con.prepareStatement("SELECT * FROM produto WHERE nome LIKE ?;");
		
		stmt.setString(1, "%"+nome+"%");
		
		DefaultTableModel tm = (DefaultTableModel) tabela.getModel();
		
		tm.setNumRows(0);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			tm.addRow(new Object[]{
					rs.getInt("idProduto"),
					rs.getString("nome"),
					rs.getString("tipo"),
					"R$ "+rs.getFloat("preco")
			});
			
		}
		rs.close();
		stmt.close();
		fechaConexao();
	}
	
	public void consultaProduto(Integer codigo, JComboBox<String> tipo, JTextField nome, JTextField preco) throws ClassNotFoundException, SQLException{
		abreConexao();
		stmt = con.prepareStatement("SELECT * FROM produto WHERE idProduto= ?;");
		stmt.setInt(1, codigo);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			nome.setText(rs.getString("nome"));
			switch (rs.getString("tipo")){
				case "Lanche":
					tipo.setSelectedIndex(0);
					break;
				case "Refrigerante":
					tipo.setSelectedIndex(1);
					break;
				case "Suco":
					tipo.setSelectedIndex(2);
					break;
			}
			preco.setText("R$ "+String.valueOf(rs.getFloat("preco")));
		}
		rs.close();
		stmt.close();
		fechaConexao();
	}
	
	public void alteraProduto(BeanProduto bp, Integer codigo) throws ClassNotFoundException, SQLException{
		abreConexao();
		stmt = con.prepareStatement("UPDATE produto SET nome=?, tipo=?, preco=? WHERE idProduto=?;");
		stmt.setString(1, bp.getNome());
		stmt.setString(2, bp.getTipo());
		stmt.setFloat(3, bp.getPreco());
		
		stmt.setInt(4, codigo);
		
		stmt.execute();
		
		JOptionPane.showMessageDialog(null, "ALTERAÇÃO DO PRODUTO "+codigo+ " EFETUADA COM SUCESSO!", "Confirmação", 1, img);
		
		stmt.close();
		fechaConexao();
		
	}
	
	public void excluiProduto(String id) throws ClassNotFoundException, SQLException{
		abreConexao();
		stmt = con.prepareStatement("DELETE FROM produto WHERE idProduto=?;");
		
		stmt.setString(1, id);
		
		stmt.execute();
		
		JOptionPane.showMessageDialog(null, "EXCLUSÃO DO PRODUTO "+id+ " EFETUADA COM SUCESSO!", "Confirmação", 1, img);
		
		stmt.close();
		fechaConexao();
	}

	public void buscaLanchesParaVenda(JTable l, JTable r, JTable s) throws ClassNotFoundException, SQLException{
		DefaultTableModel tl = (DefaultTableModel) l.getModel();
		DefaultTableModel tr = (DefaultTableModel) r.getModel();
		DefaultTableModel ts = (DefaultTableModel) s.getModel();
		tl.setNumRows(0);
		tr.setNumRows(0);
		ts.setNumRows(0);
		
		abreConexao();
		stmt = con.prepareStatement("SELECT idProduto, nome, CONCAT('R$ ', preco) AS preco FROM produto WHERE tipo = 'Lanche';");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			tl.addRow(new Object[]{
					rs.getInt("idProduto"),
					rs.getString("nome"),
					rs.getString("preco")
			});
		}
		
		stmt = con.prepareStatement("SELECT idProduto, nome, CONCAT('R$ ', preco) AS preco FROM produto WHERE tipo = 'Refrigerante';");
		rs = stmt.executeQuery();
		
		while(rs.next()){
			tr.addRow(new Object[]{
					rs.getInt("idProduto"),
					rs.getString("nome"),
					rs.getString("preco")
			});
		}
		
		stmt = con.prepareStatement("SELECT idProduto, nome, CONCAT('R$ ', preco) AS preco FROM produto WHERE tipo = 'Suco';");
		rs = stmt.executeQuery();
		
		while(rs.next()){
			ts.addRow(new Object[]{
					rs.getInt("idProduto"),
					rs.getString("nome"),
					rs.getString("preco")
			});
		}
		
		rs.close();
		stmt.close();
		fechaConexao();
	}
}

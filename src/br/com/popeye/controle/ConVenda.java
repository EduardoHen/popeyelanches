package br.com.popeye.controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import br.com.popeye.modelo.BeanProduto;
import br.com.popeye.modelo.BeanVenda;

public class ConVenda {
	private Connection con;
	private PreparedStatement stmt;
	private ImageIcon imagen = new ImageIcon("C:/Program Files/Popeye/popeyeicomenor.png");
	
	private void abreConexao() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/popeye", "root", "popeye000");
	}
	
	private void fechaConexao() throws SQLException{
		con.close();
	}
	
	public void concluiVenda(BeanVenda bv) throws ClassNotFoundException, SQLException{
		abreConexao();
		
		int venda = 0;
		stmt = con.prepareStatement("INSERT INTO venda(mesa, data, cartao, total) VALUES (?, ?, ?, ?);");
		
		stmt.setInt(1, bv.getMesa());
		stmt.setString(2, bv.getData());
		stmt.setDouble(3, bv.getCartao());
		stmt.setDouble(4, bv.getTotal());
		
		stmt.execute();
		
		stmt = con.prepareStatement("SELECT last_insert_id() as venda;");
		
		ResultSet v = stmt.executeQuery();
		
		while (v.next()) {
			venda = v.getInt("venda");
		}
		v.close();
		stmt = con.prepareStatement("INSERT INTO produtosdavenda (idVenda, idProduto, quantidade) VALUES ("+ venda +", ?, ?);");
		
		for (int c = 0; c < bv.getProdutos().size(); c++) {
			stmt.setInt(1, bv.getProdutos().get(c));
			stmt.setInt(2, bv.getQuantidades().get(c));
			
			stmt.execute();
		}
		
		
		JOptionPane.showMessageDialog(null, "Venda concluída com sucesso!", "Conclusão da venda!", 1, imagen);
		
		stmt.close();
		
		fechaConexao();
	}
	public ArrayList<BeanVenda> resultados(String dataDe, String dataAte) throws ClassNotFoundException, SQLException{
		ArrayList<BeanVenda> bv = new ArrayList<BeanVenda>();
		abreConexao();
		
		stmt = con.prepareStatement("SELECT * FROM venda WHERE data BETWEEN ? AND ? ;");
		stmt.setString(1, dataDe);
		stmt.setString(2, dataAte);
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			BeanVenda b = new BeanVenda();
			
			b.setIdVenda(rs.getInt("idVenda"));
			b.setMesa(rs.getInt("mesa"));
			b.setDataB(rs.getString("data"));
			b.setCartao(rs.getDouble("cartao"));
			b.setTotal(rs.getDouble("total"));
			
			bv.add(b);
		}
		rs.close();
		stmt.close();
		fechaConexao();
		return bv;
	}
	public ArrayList<BeanProduto> consultaProdutosVendidos(int codVenda) throws ClassNotFoundException, SQLException{
		ArrayList<BeanProduto> bp = new ArrayList<BeanProduto>();
		abreConexao();
		stmt = con.prepareStatement("SELECT produtosdavenda.idProduto, produto.nome, quantidade FROM produtosdavenda INNER JOIN produto ON produtosdavenda.idProduto = produto.idProduto WHERE produtosdavenda.idVenda = ?");
		stmt.setInt(1, codVenda);
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			BeanProduto p = new BeanProduto();
			
			p.setIdProduto(rs.getInt("idProduto"));
			p.setNome(rs.getString("nome"));
			p.setQuantidade(rs.getInt("quantidade"));
			
			bp.add(p);
		}
		rs.close();
		stmt.close();
		fechaConexao();
		return bp;
	}

}

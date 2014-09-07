package br.com.popeye.visao;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class TelaProdutosVendidos extends JDialog {

	private static final long serialVersionUID = 1L;
	public JTable tabelaProdutosVendidos;
	public JLabel lblTitulo;

	public void telaProdutosVendidos() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaProdutosVendidos dialog = new TelaProdutosVendidos();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaProdutosVendidos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files\\Popeye\\popeyeicomenor.png"));
		setTitle("Produtos da Venda");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		lblTitulo = new JLabel("Produtos da venda ");
		lblTitulo.setForeground(Color.BLUE);
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 18));
		lblTitulo.setBounds(45, 11, 379, 22);
		getContentPane().add(lblTitulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 414, 169);
		getContentPane().add(scrollPane);
		
		tabelaProdutosVendidos = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int vColIndex){
				return false;
			}
		};
		scrollPane.setViewportView(tabelaProdutosVendidos);
		tabelaProdutosVendidos.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"C\u00F3digo do Produto", "Produto", "Quantidade"
			}
		));
		tabelaProdutosVendidos.getColumnModel().getColumn(0).setPreferredWidth(113);
		tabelaProdutosVendidos.setFont(new Font("Arial", Font.PLAIN, 15));
		
		DefaultTableCellRenderer alinhar = new DefaultTableCellRenderer(){

			private static final long serialVersionUID = 1L;

			public void setValue(Object value){
				setHorizontalAlignment(SwingConstants.CENTER);
				super.setValue(value);
			}
		};
		
		TableColumn tc;
		
		tc = tabelaProdutosVendidos.getColumn("C\u00F3digo do Produto");
		tc.setCellRenderer(alinhar);
		tc = tabelaProdutosVendidos.getColumn("Produto");
		tc.setCellRenderer(alinhar);
		tc = tabelaProdutosVendidos.getColumn("Quantidade");
		tc.setCellRenderer(alinhar);
		
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFechar.setForeground(Color.RED);
		btnFechar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnFechar.setBounds(330, 224, 94, 27);
		getContentPane().add(btnFechar);
	}
}

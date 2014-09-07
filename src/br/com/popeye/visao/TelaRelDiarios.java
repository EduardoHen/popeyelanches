package br.com.popeye.visao;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import br.com.popeye.controle.ConVenda;
import br.com.popeye.modelo.BeanProduto;
import br.com.popeye.modelo.BeanVenda;

import java.awt.Toolkit;

public class TelaRelDiarios extends JDialog {

	private static final long serialVersionUID = 1L;
	private ImageIcon imagen = new ImageIcon("C:/Program Files/Popeye/popeyeicomenor.png");
	private JTable tabelaResultados;
	private JTextField txtTotVendas;
	private JTextField txtTotVendCart;
	private JTextField txtTotal;
	private JFormattedTextField txtDataDe;
	private JFormattedTextField txtDataAte;
	private JButton btnBuscar;

	public void telaRelDiarios() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TelaRelDiarios dialog = new TelaRelDiarios();
					dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TelaRelDiarios() throws ParseException {
		setTitle("Relat\u00F3rios Diarios - Acesso Restrito");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files\\Popeye\\popeyeicomenor.png"));
		setBounds(100, 100, 670, 595);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(100, 149, 237));
		panel.setBounds(0, 0, 654, 110);
		getContentPane().add(panel);
		
		JLabel label = new JLabel(imagen);
		label.setBounds(10, 0, 120, 102);
		panel.add(label);
		
		JLabel lblTitulo = new JLabel("Relat\u00F3rios di\u00E1rios");
		lblTitulo.setForeground(Color.RED);
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 30));
		lblTitulo.setBounds(137, 39, 255, 35);
		panel.add(lblTitulo);
		
		JLabel lblEmissoDeRelatrios = new JLabel("Emiss\u00E3o de relat\u00F3rios de resultados Popeye Lanches");
		lblEmissoDeRelatrios.setForeground(Color.BLUE);
		lblEmissoDeRelatrios.setFont(new Font("Arial", Font.PLAIN, 20));
		lblEmissoDeRelatrios.setBounds(48, 120, 468, 24);
		getContentPane().add(lblEmissoDeRelatrios);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Per\u00EDodo em refer\u00EAncia", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panel_1.setBounds(10, 155, 385, 55);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		 
		txtDataDe = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataDe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					txtDataAte.requestFocus();
				}
				if (arg0.getKeyCode() == KeyEvent.VK_INSERT) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
					txtDataDe.setText(sdf.format(new Date()));
				}
			}
		});
		txtDataDe.setBounds(51, 16, 128, 30);
		panel_1.add(txtDataDe);
		txtDataDe.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JLabel lblDe = new JLabel("De:");
		lblDe.setBounds(10, 19, 31, 24);
		panel_1.add(lblDe);
		lblDe.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JLabel lblAt = new JLabel("At\u00E9:");
		lblAt.setBounds(201, 19, 36, 24);
		panel_1.add(lblAt);
		lblAt.setFont(new Font("Arial", Font.PLAIN, 20));
		
		txtDataAte = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataAte.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					btnBuscar.doClick();
				}
				if (arg0.getKeyCode() == KeyEvent.VK_INSERT) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
					txtDataAte.setText(sdf.format(new Date()));
				}
			}
		});
		txtDataAte.setBounds(247, 16, 128, 30);
		panel_1.add(txtDataAte);
		txtDataAte.setFont(new Font("Arial", Font.PLAIN, 20));
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if ((txtDataDe.getText().equals("  /  /    "))||(txtDataAte.getText().equals("  /  /    "))) {
					JOptionPane.showMessageDialog(null, "Por Favor defina um intervalo de datas!", "Erro", JOptionPane.WARNING_MESSAGE, null);
				}else{
					String dtD = txtDataDe.getText().substring(6, 10)+"-"+txtDataDe.getText().substring(3, 5)+"-"+txtDataDe.getText().substring(0, 2);
					String dtAt = txtDataAte.getText().substring(6, 10)+"-"+txtDataAte.getText().substring(3, 5)+"-"+txtDataAte.getText().substring(0, 2);
					
					ConVenda cv = new ConVenda();
					ArrayList<BeanVenda> bvenda = null;
					try {
						bvenda = cv.resultados(dtD, dtAt);
					} catch (ClassNotFoundException | SQLException e) {
						JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
					}
					DefaultTableModel tabela = (DefaultTableModel) tabelaResultados.getModel();
					tabela.setNumRows(0);
					for (int c = 0; c < bvenda.size(); c++){
						String d = bvenda.get(c).getData();
						d = d.substring(8, 10)+"/"+d.substring(5, 7)+"/"+d.substring(0, 4);
						tabela.addRow(new Object[]{
							bvenda.get(c).getIdVenda(),
							bvenda.get(c).getMesa(),
							d,
							bvenda.get(c).getCartao(),
							bvenda.get(c).getTotal()
						});
					}
				}
				txtTotVendas.setText(String.valueOf(tabelaResultados.getRowCount()));
				Double d = 0.0;
				for(int c = 0; c < tabelaResultados.getRowCount(); c++){
					d = d + Double.valueOf(tabelaResultados.getValueAt(c, 3).toString());
				}
				txtTotVendCart.setText("R$ "+d);
				
				d = 0.0;
				DecimalFormat df = new DecimalFormat(".##");
				for(int c = 0; c < tabelaResultados.getRowCount(); c++){
					d = d + Double.valueOf(tabelaResultados.getValueAt(c, 4).toString());
				}
				txtTotal.setText("R$ "+df.format(d));
			}
		});
		btnBuscar.setForeground(new Color(34, 139, 34));
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnBuscar.setBounds(405, 155, 239, 55);
		getContentPane().add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 227, 383, 319);
		getContentPane().add(scrollPane);
		
		tabelaResultados = new JTable(){

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int vColIndex){
				return false;
			}
		};
		scrollPane.setViewportView(tabelaResultados);
		tabelaResultados.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Mesa", "Data", "Cart\u00E3o", "Total"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tabelaResultados.getColumnModel().getColumn(0).setPreferredWidth(52);
		tabelaResultados.getColumnModel().getColumn(1).setPreferredWidth(53);
		tabelaResultados.getColumnModel().getColumn(2).setPreferredWidth(134);
		tabelaResultados.setFont(new Font("Arial", Font.PLAIN, 15));
		
		DefaultTableCellRenderer letras =
				new DefaultTableCellRenderer() {

				private static final long serialVersionUID = 1L;

				@Override
				public void setValue(Object value) {
				setForeground(Color.BLACK);
				setHorizontalAlignment(SwingConstants.CENTER);
				super.setValue(value);				
				}
			};
		TableColumn tc;
		tc = tabelaResultados.getColumn("C\u00F3digo");
		tc.setCellRenderer(letras);
		tc = tabelaResultados.getColumn("Mesa");
		tc.setCellRenderer(letras);
		tc = tabelaResultados.getColumn("Data");
		tc.setCellRenderer(letras);
		tc = tabelaResultados.getColumn("Cart\u00E3o");
		tc.setCellRenderer(letras);
		tc = tabelaResultados.getColumn("Total");
		tc.setCellRenderer(letras);
		
		JLabel lblTotalDeVendas = new JLabel("Total de vendas:");
		lblTotalDeVendas.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTotalDeVendas.setBounds(405, 231, 107, 18);
		getContentPane().add(lblTotalDeVendas);
		
		JLabel lblTotalEmCarto = new JLabel("Total em cart\u00E3o:");
		lblTotalEmCarto.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTotalEmCarto.setBounds(405, 295, 106, 18);
		getContentPane().add(lblTotalEmCarto);
		
		JLabel lblValorTotal = new JLabel("Valor Total:");
		lblValorTotal.setFont(new Font("Arial", Font.PLAIN, 15));
		lblValorTotal.setBounds(405, 359, 73, 18);
		getContentPane().add(lblValorTotal);
		
		txtTotVendas = new JTextField();
		txtTotVendas.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtTotVendas.setEditable(false);
		txtTotVendas.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTotVendas.setBounds(405, 260, 239, 24);
		getContentPane().add(txtTotVendas);
		txtTotVendas.setColumns(10);
		
		txtTotVendCart = new JTextField();
		txtTotVendCart.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtTotVendCart.setEditable(false);
		txtTotVendCart.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTotVendCart.setBounds(405, 324, 239, 24);
		getContentPane().add(txtTotVendCart);
		txtTotVendCart.setColumns(10);
		
		txtTotal = new JTextField();
		txtTotal.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtTotal.setForeground(new Color(34, 139, 34));
		txtTotal.setEditable(false);
		txtTotal.setFont(new Font("Arial", Font.PLAIN, 25));
		txtTotal.setBounds(405, 388, 239, 37);
		getContentPane().add(txtTotal);
		txtTotal.setColumns(10);
		
		JButton btnImprimirEmPdf = new JButton("Imprimir");
		btnImprimirEmPdf.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					
					String dd = txtDataDe.getText().substring(6, 10)+"-"+txtDataDe.getText().substring(3, 5)+"-"+txtDataDe.getText().substring(0, 2);
					String da = txtDataAte.getText().substring(6, 10)+"-"+txtDataAte.getText().substring(3, 5)+"-"+txtDataAte.getText().substring(0, 2);
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/popeye", "root", "popeye000");
					
					@SuppressWarnings("rawtypes")
					HashMap par = new HashMap();
					par.put("dataD", dd);
					par.put("dataA", da);
					par.put("total", txtTotal.getText());
					par.put("totV", txtTotVendas.getText());
					par.put("totC", txtTotVendCart.getText());
					
					JasperReport jReport;
					JasperPrint jPrint;
					
					jReport = JasperCompileManager.compileReport("C:\\Program Files\\Popeye\\relatorio_diario.jrxml");
					
					jPrint = JasperFillManager.fillReport(jReport, par, con);
					
					JasperViewer.viewReport(jPrint, false);
					
					
					con.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (JRException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnImprimirEmPdf.setForeground(Color.BLUE);
		btnImprimirEmPdf.setFont(new Font("Arial", Font.PLAIN, 15));
		btnImprimirEmPdf.setBounds(405, 436, 239, 27);
		getContentPane().add(btnImprimirEmPdf);
		
		JButton btnNewButton = new JButton("Fechar");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 15));
		btnNewButton.setBounds(549, 519, 95, 27);
		getContentPane().add(btnNewButton);
		
		JButton btnVisualizarItens = new JButton("Visualizar Itens");
		btnVisualizarItens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tabelaResultados.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null, "É preciso selecionar um registro!", "Erro!", JOptionPane.ERROR_MESSAGE, null);
				}else{
					TelaProdutosVendidos pv = new TelaProdutosVendidos();
					ArrayList<BeanProduto> produtos = null;
					ConVenda cv = new ConVenda();
					
					try {
						int cod = (int) tabelaResultados.getValueAt(tabelaResultados.getSelectedRow(), 0);
						
						produtos = cv.consultaProdutosVendidos(cod);
					} catch (ClassNotFoundException | SQLException e) {
						JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
					}
					
					DefaultTableModel tabela = (DefaultTableModel) pv.tabelaProdutosVendidos.getModel();
					tabela.setNumRows(0);
					
					for (int c = 0; c < produtos.size(); c++) {
						tabela.addRow(new Object[]{
								produtos.get(c).getIdProduto(),
								produtos.get(c).getNome(),
								produtos.get(c).getQuantidade()
						});
					}
					pv.lblTitulo.setText(pv.lblTitulo.getText()+ tabelaResultados.getValueAt(tabelaResultados.getSelectedRow(), 0).toString()+"   Dia: "+tabelaResultados.getValueAt(tabelaResultados.getSelectedRow(), 2).toString());
					pv.setLocationRelativeTo(null);
					pv.setVisible(true);
				}
			}
		});
		btnVisualizarItens.setForeground(Color.BLUE);
		btnVisualizarItens.setFont(new Font("Arial", Font.PLAIN, 15));
		btnVisualizarItens.setBounds(405, 474, 239, 27);
		getContentPane().add(btnVisualizarItens);

	}
}

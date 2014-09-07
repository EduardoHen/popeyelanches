package br.com.popeye.visao;

import java.awt.EventQueue;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import br.com.popeye.controle.ConProduto;
import br.com.popeye.controle.ConUsuario;
import br.com.popeye.controle.ConVenda;
import br.com.popeye.controle.ImpressaoNaoFiscal;
import br.com.popeye.modelo.BeanVenda;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;

import java.awt.ComponentOrientation;
import java.awt.Component;

import javax.swing.Box;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class TelaVenda extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable tabelaLanches;
	private JTable tProdAdicionados;
	private JTextField txtMesa;
	private JTable tabelaRefrigerantes;
	private JTable tabelaSucos;
	private JTextField txtDinheiro;
	private JTextField txtTroco;
	private JTextField txtVCartao;
	private JTextField txtTotal;
	private JTextField txtValorAdicional;
	private JTextField txtDesconto;
	private ArrayList<Integer> produtosvendidos = new ArrayList<Integer>();
	private ArrayList<Integer> quantidades = new ArrayList<Integer>();
	private JLabel txtData;
	private JButton btnConcluirVenda;
	private ImageIcon imagen = new ImageIcon("C:/Program Files/Popeye/popeyeicomenor.png");
	private Double valorTotal = 0.00;
	private DecimalFormat df = new DecimalFormat("####################.##");


	public void telaVenda() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TelaVenda dialog = new TelaVenda();
					dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TelaVenda() {
		setTitle("Movimenta\u00E7\u00E3o");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files\\Popeye\\popeyeicomenor.png"));
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		setBounds(100, 100, 1073, 784);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{433, 437, 0};
		gridBagLayout.rowHeights = new int[]{134, 408, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridwidth = 2;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1, 210, 120, 256, 69, 381, 0};
		gbl_panel.rowHeights = new int[]{1, 74, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
		gbc_horizontalGlue.anchor = GridBagConstraints.NORTHWEST;
		gbc_horizontalGlue.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalGlue.gridx = 0;
		gbc_horizontalGlue.gridy = 0;
		panel.add(horizontalGlue, gbc_horizontalGlue);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("C:\\Program Files\\Popeye\\popeyeicomenor.png"));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_label_1.insets = new Insets(0, 0, 0, 5);
		gbc_label_1.gridx = 2;
		gbc_label_1.gridy = 1;
		panel.add(label_1, gbc_label_1);
		
		JLabel lblMovimentao = new JLabel("Movimenta\u00E7\u00E3o");
		lblMovimentao.setForeground(new Color(255, 0, 0));
		lblMovimentao.setFont(new Font("Arial", Font.PLAIN, 40));
		GridBagConstraints gbc_lblMovimentao = new GridBagConstraints();
		gbc_lblMovimentao.anchor = GridBagConstraints.WEST;
		gbc_lblMovimentao.insets = new Insets(0, 0, 0, 5);
		gbc_lblMovimentao.gridx = 3;
		gbc_lblMovimentao.gridy = 1;
		panel.add(lblMovimentao, gbc_lblMovimentao);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(100, 149, 237));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.anchor = GridBagConstraints.EAST;
		gbc_panel_3.fill = GridBagConstraints.VERTICAL;
		gbc_panel_3.gridx = 5;
		gbc_panel_3.gridy = 1;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 30, 132, 57, 31, 0};
		gbl_panel_3.rowHeights = new int[]{18, 53, 11, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JButton btnAbrirGaveta = new JButton("Abrir Gaveta");
		btnAbrirGaveta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JLabel rotulo = new JLabel("Insira a senha do Administrador!");
				JPasswordField txtpass = new JPasswordField(20);
				
				JPanel painel = new JPanel();
				painel.add(rotulo);
				painel.add(txtpass);
				
				JOptionPane.showMessageDialog(null, painel, "Permissão", JOptionPane.PLAIN_MESSAGE);

				@SuppressWarnings("deprecation")
				String senha = txtpass.getText();
				ConUsuario cusr = new ConUsuario();
			
				try {
					if (cusr.verificaAdministrador(senha)==true) {
						ImpressaoNaoFiscal prt = new ImpressaoNaoFiscal();
						prt.acionarGuilhotina();
					}else{
						JOptionPane.showMessageDialog(null, "Senha Incorreta", "Acesso Restrito", 0, imagen);
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
		
				catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAbrirGaveta.setForeground(new Color(0, 100, 0));
		btnAbrirGaveta.setFont(new Font("Arial", Font.PLAIN, 15));
		GridBagConstraints gbc_btnAbrirGaveta = new GridBagConstraints();
		gbc_btnAbrirGaveta.fill = GridBagConstraints.BOTH;
		gbc_btnAbrirGaveta.insets = new Insets(0, 0, 5, 5);
		gbc_btnAbrirGaveta.gridx = 1;
		gbc_btnAbrirGaveta.gridy = 1;
		panel_3.add(btnAbrirGaveta, gbc_btnAbrirGaveta);
		
		final JButton btnNovaVenda = new JButton("Nova Venda");
		btnNovaVenda.setForeground(new Color(0, 0, 205));
		GridBagConstraints gbc_btnNovaVenda = new GridBagConstraints();
		gbc_btnNovaVenda.fill = GridBagConstraints.BOTH;
		gbc_btnNovaVenda.insets = new Insets(0, 0, 5, 5);
		gbc_btnNovaVenda.gridx = 2;
		gbc_btnNovaVenda.gridy = 1;
		panel_3.add(btnNovaVenda, gbc_btnNovaVenda);
		btnNovaVenda.setFont(new Font("Arial", Font.PLAIN, 15));
		btnNovaVenda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				for (int c = 0 ; c < tProdAdicionados.getRowCount(); c++) {
					tProdAdicionados.setValueAt(null, c, 0);
					tProdAdicionados.setValueAt(null, c, 1);
					tProdAdicionados.setValueAt(null, c, 2);
					tProdAdicionados.setValueAt(null, c, 3);
					tProdAdicionados.setValueAt(null, c, 4);
				}
				valorTotal = 0.00;
				txtMesa.setText("");
				txtTotal.setText("R$ 0.00");
				txtValorAdicional.setText("");
				txtDesconto.setText("");
				txtDinheiro.setText("");
				txtTroco.setText("0.00");
				txtVCartao.setText("");
				
				
				tabelaLanches.requestFocus();
				ConProduto cp = new ConProduto();
				
				try {
					cp.buscaLanchesParaVenda(tabelaLanches, tabelaRefrigerantes, tabelaSucos);
				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
				}
			}
		});
		
		JButton btnFechar = new JButton("  Fechar  ");
		btnFechar.setForeground(new Color(255, 0, 0));
		btnFechar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnFechar = new GridBagConstraints();
		gbc_btnFechar.insets = new Insets(0, 0, 5, 5);
		gbc_btnFechar.fill = GridBagConstraints.BOTH;
		gbc_btnFechar.gridx = 3;
		gbc_btnFechar.gridy = 1;
		panel_3.add(btnFechar, gbc_btnFechar);
		btnFechar.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JPanel panelSelecao = new JPanel();
		panelSelecao.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sele\u00E7\u00E3o de produtos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 205)));
		GridBagConstraints gbc_panelSelecao = new GridBagConstraints();
		gbc_panelSelecao.fill = GridBagConstraints.BOTH;
		gbc_panelSelecao.insets = new Insets(0, 0, 0, 5);
		gbc_panelSelecao.gridx = 0;
		gbc_panelSelecao.gridy = 1;
		getContentPane().add(panelSelecao, gbc_panelSelecao);
		GridBagLayout gbl_panelSelecao = new GridBagLayout();
		gbl_panelSelecao.columnWidths = new int[]{367, 0};
		gbl_panelSelecao.rowHeights = new int[]{378, 0};
		gbl_panelSelecao.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelSelecao.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelSelecao.setLayout(gbl_panelSelecao);
		
		final JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		panelSelecao.add(tabbedPane, gbc_tabbedPane);
		tabbedPane.setFont(new Font("Arial", Font.PLAIN, 13));
		
				
		JScrollPane scrollPaneL = new JScrollPane();
		tabbedPane.addTab("Lanches", null, scrollPaneL, null);
		
		tabelaLanches = new JTable(){

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int vColIndex){
				return false;
			}
		};
		
		tabelaLanches.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				switch (arg0.getKeyCode()) {
				
					case KeyEvent.VK_SPACE:
						adicionaProduto(tabelaLanches, tProdAdicionados, txtTotal);
						break;
						
					case KeyEvent.VK_R:
						tabbedPane.setSelectedIndex(1);
						tabelaRefrigerantes.requestFocus();
						break;
						
					case KeyEvent.VK_S:
						tabbedPane.setSelectedIndex(2);
						tabelaSucos.requestFocus();
						break;
						
					case KeyEvent.VK_TAB:
						tabbedPane.setSelectedIndex(1);
						tabelaRefrigerantes.requestFocus();
						break;
						
					case KeyEvent.VK_ENTER:
						txtMesa.requestFocus();
						break;
						
				}
			}
		});
		
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
		
		tabelaLanches.setName("lanches");
		tabelaLanches.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				adicionaProduto(tabelaLanches, tProdAdicionados, txtTotal);
			}
		});
		scrollPaneL.setViewportView(tabelaLanches);
		tabelaLanches.setModel(new DefaultTableModel(
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
				"C\u00F3digo", "Nome", "Pre\u00E7o"
			}
		));
		tabelaLanches.getColumnModel().getColumn(0).setResizable(false);
		tabelaLanches.getColumnModel().getColumn(0).setPreferredWidth(30);
		tabelaLanches.getColumnModel().getColumn(1).setResizable(false);
		tabelaLanches.getColumnModel().getColumn(1).setPreferredWidth(204);
		tabelaLanches.getColumnModel().getColumn(2).setResizable(false);
		tabelaLanches.getColumnModel().getColumn(2).setPreferredWidth(207);
		tabelaLanches.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		tc = tabelaLanches.getColumn("C\u00F3digo");
		tc.setCellRenderer(letras);
		tc = tabelaLanches.getColumn("Nome");
		tc.setCellRenderer(letras);
		tc = tabelaLanches.getColumn("Pre\u00E7o");
		tc.setCellRenderer(letras);
		
		JScrollPane scrollPaneR = new JScrollPane();
		tabbedPane.addTab("Refrigerantes", null, scrollPaneR, null);
		
		tabelaRefrigerantes = new JTable(){

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int vColIndex){
				return false;
			}
		};
		tabelaRefrigerantes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				switch (arg0.getKeyCode()) {
				
				case KeyEvent.VK_SPACE:
					adicionaProduto(tabelaRefrigerantes, tProdAdicionados, txtTotal);
					break;
					
				case KeyEvent.VK_L:
					tabbedPane.setSelectedIndex(0);
					tabelaLanches.requestFocus();
					break;
					
				case KeyEvent.VK_S:
					tabbedPane.setSelectedIndex(2);
					tabelaSucos.requestFocus();
					break;
					
				case KeyEvent.VK_TAB:
					tabbedPane.setSelectedIndex(2);
					tabelaSucos.requestFocus();
					break;
					
				case KeyEvent.VK_ENTER:
					txtMesa.requestFocus();
					break;
					
				}
			}
		});
		tabelaRefrigerantes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tabelaRefrigerantes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				adicionaProduto(tabelaRefrigerantes, tProdAdicionados, txtTotal);
			}
		});
		tabelaRefrigerantes.setName("refrigerantes");
		tabelaRefrigerantes.setModel(new DefaultTableModel(
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
				"C\u00F3digo", "Nome", "Pre\u00E7o"
			}
		));
		tabelaRefrigerantes.getColumnModel().getColumn(0).setResizable(false);
		tabelaRefrigerantes.getColumnModel().getColumn(0).setPreferredWidth(30);
		tabelaRefrigerantes.getColumnModel().getColumn(1).setResizable(false);
		tabelaRefrigerantes.getColumnModel().getColumn(1).setPreferredWidth(204);
		tabelaRefrigerantes.getColumnModel().getColumn(2).setResizable(false);
		tabelaRefrigerantes.getColumnModel().getColumn(2).setPreferredWidth(207);
		scrollPaneR.setViewportView(tabelaRefrigerantes);
		
		tc = tabelaRefrigerantes.getColumn("C\u00F3digo");
		tc.setCellRenderer(letras);
		tc = tabelaRefrigerantes.getColumn("Nome");
		tc.setCellRenderer(letras);
		tc = tabelaRefrigerantes.getColumn("Pre\u00E7o");
		tc.setCellRenderer(letras);
		
		JScrollPane scrollPaneS = new JScrollPane();
		tabbedPane.addTab("Sucos", null, scrollPaneS, null);
		
		tabelaSucos = new JTable(){

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int vColIndex){
				return false;
			}
		};
		tabelaSucos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				switch (arg0.getKeyCode()) {
				
					case KeyEvent.VK_SPACE:
						adicionaProduto(tabelaSucos, tProdAdicionados, txtTotal);
						break;
						
					case KeyEvent.VK_L:
						tabbedPane.setSelectedIndex(0);
						tabelaLanches.requestFocus();
						break;
						
					case KeyEvent.VK_R:
						tabbedPane.setSelectedIndex(1);
						tabelaRefrigerantes.requestFocus();
						break;
						
					case KeyEvent.VK_TAB:
						tabbedPane.setSelectedIndex(0);
						tabelaLanches.requestFocus();
						break;
						
					case KeyEvent.VK_ENTER:
						txtMesa.requestFocus();
						break;
					
				}
			}
		});
		tabelaSucos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tabelaSucos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				adicionaProduto(tabelaSucos, tProdAdicionados, txtTotal);
			}
		});
		tabelaSucos.setName("sucos");
		tabelaSucos.setModel(new DefaultTableModel(
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
				"C\u00F3digo", "Nome", "Pre\u00E7o"
			}
		));
		tabelaSucos.getColumnModel().getColumn(0).setResizable(false);
		tabelaSucos.getColumnModel().getColumn(0).setPreferredWidth(30);
		tabelaSucos.getColumnModel().getColumn(1).setResizable(false);
		tabelaSucos.getColumnModel().getColumn(1).setPreferredWidth(204);
		tabelaSucos.getColumnModel().getColumn(2).setResizable(false);
		tabelaSucos.getColumnModel().getColumn(2).setPreferredWidth(207);
		scrollPaneS.setViewportView(tabelaSucos);
		
		tc = tabelaSucos.getColumn("C\u00F3digo");
		tc.setCellRenderer(letras);
		tc = tabelaSucos.getColumn("Nome");
		tc.setCellRenderer(letras);
		tc = tabelaSucos.getColumn("Pre\u00E7o");
		tc.setCellRenderer(letras);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Conclus\u00E3o da venda", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 205)));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		getContentPane().add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{425, 0};
		gbl_panel_2.rowHeights = new int[]{105, 265, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		panel_2.add(scrollPane_1, gbc_scrollPane_1);
		
		tProdAdicionados = new JTable(){

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int vColIndex){
				return false;
			}
		};
		tProdAdicionados.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_DELETE) {
					JLabel rotulo = new JLabel("Insira a senha do Administrador!");
					JPasswordField txtpass = new JPasswordField(20);
					
					JPanel painel = new JPanel();
					painel.add(rotulo);
					painel.add(txtpass);
					
					JOptionPane.showMessageDialog(null, painel, "Acesso restrito!", JOptionPane.PLAIN_MESSAGE);
					
					@SuppressWarnings({ "deprecation" })
					String senha = txtpass.getText();
					ConUsuario cusr = new ConUsuario();
					
					try {
						if (cusr.verificaAdministrador(senha) == true) {
							valorTotal = valorTotal - Double.valueOf(tProdAdicionados.getValueAt(tProdAdicionados.getSelectedRow(), 3).toString().replace("R$ ", "").replace(",", "."));
							txtTotal.setText("R$ "+String.valueOf(df.format(valorTotal)));
							if ((int)tProdAdicionados.getValueAt(tProdAdicionados.getSelectedRow(), 4) > 1) {
								tProdAdicionados.setValueAt((int)tProdAdicionados.getValueAt(tProdAdicionados.getSelectedRow(), 4) - 1, tProdAdicionados.getSelectedRow(), 4);
							}else{
								int i = tProdAdicionados.getSelectedRow();
								DefaultTableModel tab = (DefaultTableModel) tProdAdicionados.getModel();
								tab.removeRow(i);	
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Senha incorreta, contate o administrador!", "Erro!", JOptionPane.ERROR_MESSAGE, null);
						}
					} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
						JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
					}
					
				}
			}
		});
		scrollPane_1.setViewportView(tProdAdicionados);
		tProdAdicionados.setModel(new DefaultTableModel(
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
			},
			new String[] {
				"C\u00F3digo", "Nome", "Tipo", "Pre\u00E7o", "Quantidade"
			}
		));
		tProdAdicionados.getColumnModel().getColumn(0).setPreferredWidth(77);
		tProdAdicionados.getColumnModel().getColumn(1).setPreferredWidth(152);
		tProdAdicionados.getColumnModel().getColumn(2).setResizable(false);
		tProdAdicionados.getColumnModel().getColumn(2).setPreferredWidth(100);
		tProdAdicionados.getColumnModel().getColumn(3).setPreferredWidth(87);
		tProdAdicionados.getColumnModel().getColumn(4).setResizable(false);
		tProdAdicionados.setShowGrid(false);
		tProdAdicionados.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		tc = tProdAdicionados.getColumn("C\u00F3digo");
		tc.setCellRenderer(letras);
		tc = tProdAdicionados.getColumn("Nome");
		tc.setCellRenderer(letras);
		tc = tProdAdicionados.getColumn("Tipo");
		tc.setCellRenderer(letras);
		tc = tProdAdicionados.getColumn("Pre\u00E7o");
		tc.setCellRenderer(letras);
		tc = tProdAdicionados.getColumn("Quantidade");
		tc.setCellRenderer(letras);
		
		JPanel panelConclusaoDaVenda = new JPanel();
		panelConclusaoDaVenda.setBackground(new Color(100, 149, 237));
		panelConclusaoDaVenda.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Total da Venda", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		GridBagConstraints gbc_panelConclusaoDaVenda = new GridBagConstraints();
		gbc_panelConclusaoDaVenda.fill = GridBagConstraints.BOTH;
		gbc_panelConclusaoDaVenda.gridx = 0;
		gbc_panelConclusaoDaVenda.gridy = 1;
		panel_2.add(panelConclusaoDaVenda, gbc_panelConclusaoDaVenda);
		
		Date hoje = new Date();
		
		DateFormat d = new SimpleDateFormat("dd");
		DateFormat m = new SimpleDateFormat("MM");
		DateFormat a = new SimpleDateFormat("YYYY");
		
		String data = d.format(hoje)+"/"+m.format(hoje)+"/"+a.format(hoje);
		GridBagLayout gbl_panelConclusaoDaVenda = new GridBagLayout();
		gbl_panelConclusaoDaVenda.columnWidths = new int[]{39, 30, 20, 42, 149, 73, 142, 0};
		gbl_panelConclusaoDaVenda.rowHeights = new int[]{39, 173, 98, 52, 0};
		gbl_panelConclusaoDaVenda.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelConclusaoDaVenda.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelConclusaoDaVenda.setLayout(gbl_panelConclusaoDaVenda);
		
		txtData = new JLabel(data);
		txtData.setFont(new Font("Arial", Font.PLAIN, 15));
		GridBagConstraints gbc_txtData = new GridBagConstraints();
		gbc_txtData.anchor = GridBagConstraints.WEST;
		gbc_txtData.insets = new Insets(0, 0, 5, 5);
		gbc_txtData.gridwidth = 2;
		gbc_txtData.gridx = 0;
		gbc_txtData.gridy = 0;
		panelConclusaoDaVenda.add(txtData, gbc_txtData);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(100, 149, 237));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridheight = 2;
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.gridx = 4;
		gbc_panel_1.gridy = 0;
		panelConclusaoDaVenda.add(panel_1, gbc_panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Valor adicional:");
		lblNewLabel.setBounds(10, 12, 174, 29);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		
		JLabel lblNewLabel_1 = new JLabel("Desconto:");
		lblNewLabel_1.setBounds(70, 52, 114, 29);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 25));
		
		txtValorAdicional = new JTextField();
		txtValorAdicional.addFocusListener(new FocusAdapter() {
			Double t;
			Double va;
			@Override
			public void focusLost(FocusEvent arg0) {
				txtValorAdicional.setBackground(Color.LIGHT_GRAY);
				t = valorTotal;
				
				if (txtValorAdicional.getText().equals("")) {
					va = 0.00;
				}else{
					va = Double.valueOf(txtValorAdicional.getText());
				}
			
				t = t + va;
				
				txtTotal.setText("R$ "+String.valueOf(df.format(t)));
				
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				txtValorAdicional.setBackground(new Color(255, 255, 153));
			}
		});
		txtValorAdicional.setBorder(null);
		txtValorAdicional.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				String digitos = "0987654321.";
				
				if (!digitos.contains(arg0.getKeyChar()+"")) {
					arg0.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
					txtDesconto.requestFocus();
				}
			}
		});
		txtValorAdicional.setBounds(192, 12, 174, 29);
		panel_1.add(txtValorAdicional);
		txtValorAdicional.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtValorAdicional.setBackground(Color.LIGHT_GRAY);
		txtValorAdicional.setFont(new Font("Arial", Font.PLAIN, 25));
		txtValorAdicional.setColumns(10);
		
		txtDesconto = new JTextField();
		txtDesconto.addFocusListener(new FocusAdapter() {
			Double t;
			Double d;
			Double va;
			@Override
			public void focusLost(FocusEvent arg0) {
				txtDesconto.setBackground(Color.LIGHT_GRAY);
				t = valorTotal;
				
				if (txtDesconto.getText().equals("")) {
					d = 0.00;
				}
				
				else{
					d = Double.valueOf(txtDesconto.getText());
				}
				
				if (txtValorAdicional.getText().equals("")) {
					va = 0.00;
				}
				
				else{
					va = Double.valueOf(txtValorAdicional.getText().replace("R$ ", "").replace(",", "."));
				}
				
				t = t + va - d;
				txtTotal.setText("R$ "+df.format(t));
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				txtDesconto.setBackground(new Color(255, 255, 153));
			}
		});
		txtDesconto.setBorder(null);
		txtDesconto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String digitos = "0987654321.";
				
				if (!digitos.contains(arg0.getKeyChar()+"")) {
					arg0.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					txtDinheiro.requestFocus();
				}
			}
		});
		txtDesconto.setBounds(194, 52, 172, 29);
		panel_1.add(txtDesconto);
		txtDesconto.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtDesconto.setBackground(Color.LIGHT_GRAY);
		txtDesconto.setFont(new Font("Arial", Font.PLAIN, 25));
		txtDesconto.setColumns(10);
		
		JLabel label_3 = new JLabel("Valor em cart\u00E3o:");
		label_3.setBounds(0, 187, 186, 29);
		panel_1.add(label_3);
		label_3.setFont(new Font("Arial", Font.PLAIN, 25));
		
		txtVCartao = new JTextField();
		txtVCartao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtVCartao.setBackground(new Color(255, 255, 153));
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				txtVCartao.setBackground(Color.LIGHT_GRAY);
			}
		});
		txtVCartao.setBorder(null);
		txtVCartao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String digitos = "0987654321.";
				
				if (!digitos.contains(arg0.getKeyChar()+"")) {
					arg0.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				txtVCartao.setBackground(Color.LIGHT_GRAY);
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					btnConcluirVenda.requestFocus();
				}
			}
		});
		txtVCartao.setBounds(196, 184, 170, 35);
		panel_1.add(txtVCartao);
		txtVCartao.setFont(new Font("Arial", Font.PLAIN, 25));
		txtVCartao.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtVCartao.setColumns(10);
		txtVCartao.setBackground(Color.LIGHT_GRAY);
		
		txtTroco = new JTextField();
		txtTroco.setBorder(null);
		txtTroco.setForeground(Color.RED);
		txtTroco.setText("0.00");
		txtTroco.setEditable(false);
		txtTroco.setBounds(196, 138, 170, 35);
		panel_1.add(txtTroco);
		txtTroco.setFont(new Font("Arial", Font.PLAIN, 25));
		txtTroco.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtTroco.setColumns(10);
		txtTroco.setBackground(Color.LIGHT_GRAY);
		
		txtDinheiro = new JTextField();
		txtDinheiro.addFocusListener(new FocusAdapter() {
			Double desc;
			Double va;
			Double d;
			Double t;
			@Override
			public void focusLost(FocusEvent arg0) {
				txtDinheiro.setBackground(Color.LIGHT_GRAY);
				
				if (txtDesconto.getText().equals("")) {
					desc = 0.00;
				}else{
					desc = Double.valueOf(txtDesconto.getText());
				}
				
				if (txtValorAdicional.getText().equals("")) {
					va = 0.00;
				}else{
					va = Double.valueOf(txtValorAdicional.getText());
				}
				
				if (txtDinheiro.getText().equals("")) {
					d = 0.00;
				}else{
					d = Double.valueOf(txtDinheiro.getText());
				}
				
				t = valorTotal;
				t = d - (t + va - desc);

				txtTroco.setText("R$ "+String.valueOf(df.format((t))));	
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				txtDinheiro.setBackground(new Color(255, 255, 153));
			}
		});
		txtDinheiro.setBorder(null);
		txtDinheiro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String digitos = "0987654321.";
				
				if (!digitos.contains(arg0.getKeyChar()+"")) {
					arg0.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					txtVCartao.requestFocus();
					
				}
			}
		});
		txtDinheiro.setBounds(194, 92, 172, 35);
		panel_1.add(txtDinheiro);
		txtDinheiro.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtDinheiro.setBackground(Color.LIGHT_GRAY);
		txtDinheiro.setFont(new Font("Arial", Font.PLAIN, 25));
		txtDinheiro.setColumns(10);
		
		JLabel lblValorEmCarto = new JLabel("Dinheiro:");
		lblValorEmCarto.setBounds(85, 95, 101, 29);
		panel_1.add(lblValorEmCarto);
		lblValorEmCarto.setFont(new Font("Arial", Font.PLAIN, 25));
		
		JLabel label_2 = new JLabel("Troco:");
		label_2.setBounds(116, 141, 70, 29);
		panel_1.add(label_2);
		label_2.setFont(new Font("Arial", Font.PLAIN, 25));
		
		JLabel lblMesa = new JLabel("Mesa:");
		GridBagConstraints gbc_lblMesa = new GridBagConstraints();
		gbc_lblMesa.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblMesa.insets = new Insets(0, 0, 5, 5);
		gbc_lblMesa.gridx = 0;
		gbc_lblMesa.gridy = 1;
		panelConclusaoDaVenda.add(lblMesa, gbc_lblMesa);
		lblMesa.setFont(new Font("Arial", Font.PLAIN, 15));
		
		txtMesa = new JTextField();
		txtMesa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String digitos = "0987654321";
				
				if (!digitos.contains(arg0.getKeyChar()+"")) {
					arg0.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					txtValorAdicional.requestFocus();
				}
			}
		});
		txtMesa.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtMesa.setBackground(new Color(100, 149, 237));
		GridBagConstraints gbc_txtMesa = new GridBagConstraints();
		gbc_txtMesa.anchor = GridBagConstraints.NORTH;
		gbc_txtMesa.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMesa.insets = new Insets(0, 0, 5, 5);
		gbc_txtMesa.gridwidth = 2;
		gbc_txtMesa.gridx = 1;
		gbc_txtMesa.gridy = 1;
		panelConclusaoDaVenda.add(txtMesa, gbc_txtMesa);
		txtMesa.setFont(new Font("Arial", Font.PLAIN, 15));
		txtMesa.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setForeground(new Color(255, 0, 0));
		lblTotal.setFont(new Font("Arial", Font.PLAIN, 30));
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblTotal.insets = new Insets(0, 0, 0, 5);
		gbc_lblTotal.gridwidth = 2;
		gbc_lblTotal.gridx = 0;
		gbc_lblTotal.gridy = 3;
		panelConclusaoDaVenda.add(lblTotal, gbc_lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtTotal.setText("R$ 0.00");
		txtTotal.setEditable(false);
		txtTotal.setFont(new Font("Arial", Font.PLAIN, 30));
		GridBagConstraints gbc_txtTotal = new GridBagConstraints();
		gbc_txtTotal.anchor = GridBagConstraints.NORTH;
		gbc_txtTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTotal.insets = new Insets(0, 0, 0, 5);
		gbc_txtTotal.gridwidth = 3;
		gbc_txtTotal.gridx = 2;
		gbc_txtTotal.gridy = 3;
		panelConclusaoDaVenda.add(txtTotal, gbc_txtTotal);
		txtTotal.setColumns(10);
		
		btnConcluirVenda = new JButton("Concluir Venda");
		btnConcluirVenda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!txtDesconto.getText().equals("")) {
					JLabel rotulo = new JLabel("Você inseriu um desconto a esta venda por isso é necessária a senha do administrador para concluí-la!");
					JPasswordField txtpass = new JPasswordField(20);
					
					JPanel painel = new JPanel();
					painel.add(rotulo);
					painel.add(txtpass);
					
					JOptionPane.showMessageDialog(null, painel, "Permissão", JOptionPane.PLAIN_MESSAGE);
					
					@SuppressWarnings("deprecation")
					String senha = txtpass.getText();
					ConUsuario cusr = new ConUsuario();
					try {
						if (cusr.verificaAdministrador(senha)==true) {
							String[] conf = new String[2];
							conf[0] = "Sim";
							conf[1] = "Não";
							
							int r = JOptionPane.showOptionDialog(null, "Convluír venda?", "Concluír", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imagen, conf, null);
							if (r == 0) {
								for (int c = 0; c < tProdAdicionados.getRowCount(); c++) {
									if (tProdAdicionados.getValueAt(c, 0) != null) {
										produtosvendidos.add((Integer) tProdAdicionados.getValueAt(c, 0));
									}
									if (tProdAdicionados.getValueAt(c, 4) != null) {
										quantidades.add((Integer) tProdAdicionados.getValueAt(c, 4));
									}
								}
								ImpressaoNaoFiscal imp = new ImpressaoNaoFiscal();
								imp.acionarGuilhotina();
								
								BeanVenda bv = new BeanVenda();
								if (txtMesa.getText().equals("")) {
									bv.setMesa(0);
								}else{
									bv.setMesa(Integer.valueOf(txtMesa.getText()));
								}
								
								bv.setData(txtData.getText());
								if (txtVCartao.getText().equals("")) {
									bv.setCartao(0.00);
								}else{
									bv.setCartao(Double.valueOf(txtVCartao.getText()));
								}
								
								bv.setTotal(Double.valueOf(txtTotal.getText().replace("R$ ", "").replace(",", ".")));
								
								bv.setProdutos(produtosvendidos);
								bv.setQuantidades(quantidades);
								
								ConVenda cv = new ConVenda();
								try {
									cv.concluiVenda(bv);
								} catch (ClassNotFoundException | SQLException e) {
									
									e.printStackTrace();
								}
								btnNovaVenda.doClick();
							}
						}else{
							JOptionPane.showMessageDialog(null, "Senha inválida, contate o administrador!", "Erro", JOptionPane.ERROR_MESSAGE, null);
						}
					} catch (ClassNotFoundException | SQLException e) {
						JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					String[] conf = new String[2];
					conf[0] = "Sim";
					conf[1] = "Não";
					
					int r = JOptionPane.showOptionDialog(null, "Convluír venda?", "Concluír", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imagen, conf, null);
					if (r == 0) {
						for (int c = 0; c < tProdAdicionados.getRowCount(); c++) {
							if (tProdAdicionados.getValueAt(c, 0) != null) {
								produtosvendidos.add((Integer) tProdAdicionados.getValueAt(c, 0));
							}
							if (tProdAdicionados.getValueAt(c, 4) != null) {
								quantidades.add((Integer) tProdAdicionados.getValueAt(c, 4));
							}
						}
						
						
						BeanVenda bv = new BeanVenda();
						if (txtMesa.getText().equals("")) {
							bv.setMesa(0);
						}else{
							bv.setMesa(Integer.valueOf(txtMesa.getText()));
						}
						
						bv.setData(txtData.getText());
						if (txtVCartao.getText().equals("")) {
							bv.setCartao(0.00);
						}else{
							bv.setCartao(Double.valueOf(txtVCartao.getText()));
						}
						
						bv.setTotal(Double.valueOf(txtTotal.getText().replace("R$ ", "").replace(",", ".")));
						
						bv.setProdutos(produtosvendidos);
						bv.setQuantidades(quantidades);
						
						ConVenda cv = new ConVenda();
						try {
							cv.concluiVenda(bv);
						} catch (ClassNotFoundException | SQLException e) {
							
							e.printStackTrace();
						}
						btnNovaVenda.doClick();
					}
				}
			}
		});
		btnConcluirVenda.setForeground(new Color(0, 128, 0));
		btnConcluirVenda.setFont(new Font("Arial", Font.PLAIN, 14));
		GridBagConstraints gbc_btnConcluirVenda = new GridBagConstraints();
		gbc_btnConcluirVenda.fill = GridBagConstraints.VERTICAL;
		gbc_btnConcluirVenda.anchor = GridBagConstraints.EAST;
		gbc_btnConcluirVenda.gridx = 6;
		gbc_btnConcluirVenda.gridy = 3;
		panelConclusaoDaVenda.add(btnConcluirVenda, gbc_btnConcluirVenda);

	}
	private void adicionaProduto(JTable sel, JTable adc, JTextField total){
		
		Double t = Double.valueOf(total.getText().replace("R$ ", "").replace(",", "."));
		Double p = Double.valueOf(sel.getValueAt(sel.getSelectedRow(), 2).toString().replace("R$ ", ""));
		t = t + p;
		total.setText("R$ "+String.valueOf(df.format(t)));
		
		for(int c = 0; c < adc.getRowCount();c++){
			if(sel.getValueAt(sel.getSelectedRow(), 0)==(adc.getValueAt(c, 0))){
				adc.setValueAt(((int)adc.getValueAt(c, 4))+1, c, 4);
				String v = adc.getValueAt(c, 3).toString().replace("R$ ", "").replace(",", ".");
				valorTotal = valorTotal + Double.valueOf(v) ;
				break;
			}else{
				if(adc.getValueAt(c, 0)==(null)){
					adc.setValueAt(sel.getValueAt(sel.getSelectedRow(), 0), c, 0);
					adc.setValueAt(sel.getValueAt(sel.getSelectedRow(), 1), c, 1);
					switch(sel.getName()){
						case "lanches":
							adc.setValueAt("Lanche", c, 2);
							break;
						case "refrigerantes":
							adc.setValueAt("Refrigerante", c, 2);
							break;
						case "sucos":
							adc.setValueAt("Suco", c, 2);
							break;
					}
					adc.setValueAt(sel.getValueAt(sel.getSelectedRow(), 2), c, 3);
					adc.setValueAt(1, c, 4);
					String v = adc.getValueAt(c, 3).toString().replace("R$ ", "").replace(",", ".");
					valorTotal = valorTotal + Double.valueOf(v);
					break;
				}
			}
		}
	}
}

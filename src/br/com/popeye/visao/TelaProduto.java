package br.com.popeye.visao;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.Toolkit;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JRadioButton;

import br.com.popeye.controle.ConProduto;
import br.com.popeye.modelo.BeanProduto;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.ComponentOrientation;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TelaProduto extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	BeanProduto bp = new BeanProduto();
	ConProduto cp = new ConProduto();
	private JTextField txtNome;
	private JTable tabelaConsulta;
	private JTextField txtCodAlt;
	private JTextField txtNomeAlt;
	private JTextField txtPrecoAlt;
	private String tipo;
	private JTable tabelaExclusao;

	public void telaPadrao() {
		try {
			TelaProduto dialog = new TelaProduto();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TelaProduto() {
		setTitle("Produtos");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files\\Popeye\\popeye.jpg"));
		setBounds(100, 100, 631, 502);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBounds(0, 0, 625, 110);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblImg = new JLabel(new ImageIcon("C:\\Program Files\\Popeye\\popeyeicomenor.png"));
		lblImg.setBounds(10, 0, 120, 102);
		panel.add(lblImg);
		
		JLabel lblManutenoDeProdutos = new JLabel("Manuten\u00E7\u00E3o de Produtos");
		lblManutenoDeProdutos.setForeground(new Color(255, 0, 0));
		lblManutenoDeProdutos.setFont(new Font("Arial", Font.PLAIN, 30));
		lblManutenoDeProdutos.setBounds(137, 39, 331, 35);
		panel.add(lblManutenoDeProdutos);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(100, 149, 237));
		panel_1.setBounds(0, 109, 143, 365);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		final JPanel panelExcluir = new JPanel();
		panelExcluir.setBackground(Color.LIGHT_GRAY);
		panelExcluir.setFont(new Font("Arial", Font.PLAIN, 12));
		panelExcluir.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Excluir", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 205)));
		panelExcluir.setBounds(153, 121, 462, 304);
		contentPanel.add(panelExcluir);
		panelExcluir.setLayout(null);
		
		JButton btnExcluir_1 = new JButton("Excluir");
		btnExcluir_1.setForeground(Color.RED);
		btnExcluir_1.setFont(new Font("Arial", Font.PLAIN, 15));
		btnExcluir_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String conf[] = new String[2];
				conf[0] = "Sim";
				conf[1] = "Não";
				
				int r = JOptionPane.showOptionDialog(null, "Confirma exclusão do produto selecionado?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, conf, null);

				if(r==0){
				
					cp = new ConProduto();
					try {
						cp.excluiProduto(String.valueOf(tabelaExclusao.getValueAt(tabelaExclusao.getSelectedRow(), 0)));
					} catch (ClassNotFoundException | SQLException e) {
						JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
					}
					
					try {
						cp .consultaProduto(tabelaExclusao);
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
					}
				}
			}
		});
		btnExcluir_1.setBounds(146, 266, 87, 27);
		panelExcluir.add(btnExcluir_1);
		
		JButton btnCancelar_2 = new JButton("Cancelar");
		btnCancelar_2.setForeground(Color.RED);
		btnCancelar_2.setFont(new Font("Arial", Font.PLAIN, 15));
		btnCancelar_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelExcluir.setVisible(false);
			}
		});
		btnCancelar_2.setBounds(243, 266, 114, 27);
		panelExcluir.add(btnCancelar_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 26, 442, 233);
		panelExcluir.add(scrollPane_1);
		
		tabelaExclusao = new JTable() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int vColIndex, int rowIndex) {
				return false;
			}
		};
		scrollPane_1.setViewportView(tabelaExclusao);
		tabelaExclusao.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nome", "Tipo", "Pre\u00E7o"
			}
		));
		tabelaExclusao.getColumnModel().getColumn(0).setResizable(false);
		tabelaExclusao.getColumnModel().getColumn(0).setPreferredWidth(60);
		tabelaExclusao.getColumnModel().getColumn(1).setResizable(false);
		tabelaExclusao.getColumnModel().getColumn(1).setPreferredWidth(167);
		tabelaExclusao.getColumnModel().getColumn(2).setResizable(false);
		tabelaExclusao.getColumnModel().getColumn(2).setPreferredWidth(139);
		tabelaExclusao.getColumnModel().getColumn(3).setResizable(false);
		tabelaExclusao.getColumnModel().getColumn(3).setPreferredWidth(124);
		tabelaExclusao.setFont(new Font("Arial", Font.PLAIN, 15));
		panelExcluir.setVisible(false);
		
		final JPanel panelAlterar = new JPanel();
		panelAlterar.setBackground(Color.LIGHT_GRAY);
		panelAlterar.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Alterar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 205)));
		panelAlterar.setBounds(153, 121, 462, 283);
		contentPanel.add(panelAlterar);
		panelAlterar.setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCdigo.setBounds(10, 40, 58, 18);
		panelAlterar.add(lblCdigo);
		
		JLabel lblNome_1 = new JLabel("Nome:");
		lblNome_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNome_1.setBounds(10, 80, 58, 18);
		panelAlterar.add(lblNome_1);
		
		txtNomeAlt = new JTextField();
		txtNomeAlt.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNomeAlt.setBounds(78, 77, 374, 20);
		panelAlterar.add(txtNomeAlt);
		txtNomeAlt.setColumns(10);
		
		final JComboBox <String>comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Lanche", "Refrigerante", "Suco"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(78, 123, 123, 20);
		panelAlterar.add(comboBox);
		
		txtCodAlt = new JTextField();
		txtCodAlt.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCodAlt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String caracter = "0987654321";
				if(!caracter.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					cp = new ConProduto();
					try {
						cp.consultaProduto(Integer.valueOf(txtCodAlt.getText()), comboBox, txtNomeAlt, txtPrecoAlt);
					} catch (NumberFormatException | ClassNotFoundException
							| SQLException e) {
						JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
					}
				}
			}
		});
		txtCodAlt.setBounds(78, 39, 86, 20);
		panelAlterar.add(txtCodAlt);
		txtCodAlt.setColumns(10);
		
		JLabel lblTipo_1 = new JLabel("Tipo:");
		lblTipo_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTipo_1.setBounds(10, 124, 58, 18);
		panelAlterar.add(lblTipo_1);
		
		JLabel lblPreo_1 = new JLabel("Pre\u00E7o:");
		lblPreo_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPreo_1.setBounds(211, 124, 50, 18);
		panelAlterar.add(lblPreo_1);
		
		txtPrecoAlt = new JTextField();
		txtPrecoAlt.setFont(new Font("Arial", Font.PLAIN, 15));
		txtPrecoAlt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String caracter = "0987654321.";
				if(!caracter.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
				
			}
		});
		txtPrecoAlt.setBounds(271, 123, 119, 20);
		panelAlterar.add(txtPrecoAlt);
		txtPrecoAlt.setColumns(10);
		
		JButton btnAlterar_1 = new JButton("Alterar");
		btnAlterar_1.setForeground(Color.BLUE);
		btnAlterar_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				switch(comboBox.getSelectedIndex()){
				case 0:
					tipo = "Lanche";
					break;
				case 1:
					tipo = "Refrigerante";
					break;
				case 2:
					tipo = "Suco";
					break;
				}
				bp = new BeanProduto();
				cp = new ConProduto();
				bp.setNome(txtNomeAlt.getText());
				bp.setTipo(tipo);
				bp.setPreco(Float.valueOf(txtPrecoAlt.getText().replace("R$ ","")));
				
				try {
					cp.alteraProduto(bp, Integer.valueOf(txtCodAlt.getText()));
				} catch (NumberFormatException | ClassNotFoundException
						| SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
				}
				
			}
		});
		btnAlterar_1.setFont(new Font("Arial", Font.PLAIN, 15));
		btnAlterar_1.setBounds(102, 249, 110, 23);
		panelAlterar.add(btnAlterar_1);
		
		JButton btnCancelar_1 = new JButton("Cancelar");
		btnCancelar_1.setForeground(Color.RED);
		btnCancelar_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelAlterar.setVisible(false);
			}
		});
		btnCancelar_1.setFont(new Font("Arial", Font.PLAIN, 15));
		btnCancelar_1.setBounds(222, 249, 110, 23);
		panelAlterar.add(btnCancelar_1);
		panelAlterar.setVisible(false);
		
		final JPanel panelConsultar = new JPanel();
		panelConsultar.setBackground(Color.LIGHT_GRAY);
		panelConsultar.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consultar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 205)));
		panelConsultar.setBounds(153, 121, 462, 283);
		contentPanel.add(panelConsultar);
		panelConsultar.setLayout(null);
		
		JLabel lblNomeCon = new JLabel("Nome:");
		lblNomeCon.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNomeCon.setBounds(10, 25, 51, 18);
		panelConsultar.add(lblNomeCon);
		
		final JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(new Color(34, 139, 34));
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					cp.consultaProduto(tabelaConsulta, txtNome.getText());
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
				}
			}
		});
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnBuscar.setBounds(348, 24, 89, 23);
		panelConsultar.add(btnBuscar);
		
		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					btnBuscar.doClick();
				}
			}
		});
		txtNome.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNome.setBounds(71, 25, 267, 20);
		panelConsultar.add(txtNome);
		txtNome.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 427, 216);
		panelConsultar.add(scrollPane);
		
		tabelaConsulta = new JTable(){

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int vColIndex, int rowIndex){
				return false;
			};
		};
		scrollPane.setViewportView(tabelaConsulta);
		tabelaConsulta.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nome", "Tipo", "Pre\u00E7o"
			}
		));
		tabelaConsulta.getColumnModel().getColumn(0).setResizable(false);
		tabelaConsulta.getColumnModel().getColumn(1).setResizable(false);
		tabelaConsulta.getColumnModel().getColumn(1).setPreferredWidth(176);
		tabelaConsulta.getColumnModel().getColumn(2).setResizable(false);
		tabelaConsulta.getColumnModel().getColumn(2).setPreferredWidth(87);
		tabelaConsulta.getColumnModel().getColumn(3).setResizable(false);
		tabelaConsulta.getColumnModel().getColumn(3).setPreferredWidth(94);
		tabelaConsulta.setFont(new Font("Arial", Font.PLAIN, 15));
		panelConsultar.setVisible(false);
		
		final JPanel panelIncluir = new JPanel();
		panelIncluir.setBackground(Color.LIGHT_GRAY);
		panelIncluir.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Inclus\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 205)));
		panelIncluir.setBounds(153, 121, 462, 283);
		contentPanel.add(panelIncluir);
		panelIncluir.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNome.setBounds(10, 64, 47, 14);
		panelIncluir.add(lblNome);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTipo.setBounds(10, 97, 47, 14);
		panelIncluir.add(lblTipo);
		
		final JRadioButton rdbtnLanche = new JRadioButton("Lanche");
		rdbtnLanche.setBackground(Color.LIGHT_GRAY);
		rdbtnLanche.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					textField_1.requestFocus();
				}
			}
		});
		rdbtnLanche.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bp.setTipo("Lanche");
			}
		});
		rdbtnLanche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				bp.setTipo("Lanche");
			}
		});
		rdbtnLanche.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnLanche.setBounds(67, 93, 109, 23);
		panelIncluir.add(rdbtnLanche);
		
		JRadioButton rdbtnRefrigerante = new JRadioButton("Refrigerante");
		rdbtnRefrigerante.setBackground(Color.LIGHT_GRAY);
		rdbtnRefrigerante.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					textField_1.requestFocus();
				}
			}
		});
		rdbtnRefrigerante.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bp.setTipo("Refrigerante");
			}
		});
		rdbtnRefrigerante.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				bp.setTipo("Refrigerante");
			}
		});
		rdbtnRefrigerante.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnRefrigerante.setBounds(178, 93, 109, 23);
		panelIncluir.add(rdbtnRefrigerante);
		
		JRadioButton rdbtnSuco = new JRadioButton("Suco");
		rdbtnSuco.setBackground(Color.LIGHT_GRAY);
		rdbtnSuco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					textField_1.requestFocus();
				}
			}
		});
		rdbtnSuco.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bp.setTipo("Suco");
			}
		});
		rdbtnSuco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				bp.setTipo("Suco");
			}
		});
		rdbtnSuco.setFont(new Font("Arial", Font.PLAIN, 15));
		rdbtnSuco.setBounds(289, 93, 109, 23);
		panelIncluir.add(rdbtnSuco);
		panelIncluir.setVisible(false);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnLanche);
		bg.add(rdbtnRefrigerante);
		bg.add(rdbtnSuco);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					rdbtnLanche.requestFocus();
				}
			}
		});
		textField.setFont(new Font("Arial", Font.PLAIN, 15));
		textField.setBounds(67, 61, 385, 20);
		panelIncluir.add(textField);
		textField.setColumns(10);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o:");
		lblPreo.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPreo.setBounds(10, 122, 47, 14);
		panelIncluir.add(lblPreo);
		
		JLabel lblR = new JLabel("R$");
		lblR.setFont(new Font("Arial", Font.PLAIN, 15));
		lblR.setBounds(67, 122, 19, 14);
		panelIncluir.add(lblR);
		
		final JButton btnGravar = new JButton("Gravar");
		btnGravar.setForeground(Color.BLUE);
		btnGravar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bp.setNome(textField.getText());
				bp.setPreco(Float.valueOf(textField_1.getText()));
				try {
					cp.insereProduto(bp);
					textField.setText("");
					textField_1.setText("");
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
				}
				
			}
		});
		btnGravar.setBounds(240, 123, 109, 23);
		panelIncluir.add(btnGravar);
		btnGravar.setFont(new Font("Arial", Font.PLAIN, 15));
		
		textField_1 = new JTextField();
		textField_1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String caracter = "0987654321.";
				if(!caracter.contains(arg0.getKeyChar()+"")){
					arg0.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					btnGravar.doClick();
				}
			}
		});
		textField_1.setFont(new Font("Arial", Font.PLAIN, 15));
		textField_1.setBounds(96, 123, 101, 20);
		panelIncluir.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.RED);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelIncluir.setVisible(false);
			}
		});
		btnCancelar.setBounds(351, 123, 101, 23);
		panelIncluir.add(btnCancelar);
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelExcluir.setVisible(false);
				panelAlterar.setVisible(false);
				panelConsultar.setVisible(false);
				panelIncluir.setVisible(true);
			}
		});
		btnIncluir.setFont(new Font("Arial", Font.PLAIN, 12));
		btnIncluir.setBounds(10, 24, 123, 23);
		panel_1.add(btnIncluir);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelExcluir.setVisible(false);
				panelAlterar.setVisible(false);
				panelIncluir.setVisible(false);
				panelConsultar.setVisible(true);
				
				try {
					cp .consultaProduto(tabelaConsulta);
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
				}
			}
		});
		btnConsultar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnConsultar.setBounds(10, 58, 123, 23);
		panel_1.add(btnConsultar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelExcluir.setVisible(false);
				panelIncluir.setVisible(false);
				panelConsultar.setVisible(false);
				panelAlterar.setVisible(true);
			}
		});
		btnAlterar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAlterar.setBounds(10, 92, 123, 23);
		panel_1.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelIncluir.setVisible(false);
				panelConsultar.setVisible(false);
				panelAlterar.setVisible(false);
				panelExcluir.setVisible(true);
				
				try {
					cp .consultaProduto(tabelaExclusao);
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
				}
			}
		});
		btnExcluir.setFont(new Font("Arial", Font.PLAIN, 12));
		btnExcluir.setBounds(10, 126, 123, 23);
		panel_1.add(btnExcluir);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setForeground(Color.RED);
		btnFechar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFechar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnFechar.setBounds(515, 436, 100, 27);
		contentPanel.add(btnFechar);
		
		
	}	
}

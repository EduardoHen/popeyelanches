package br.com.popeye.visao;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import br.com.popeye.controle.ConUsuario;
import br.com.popeye.modelo.BeanUsuario;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

public class TelaUsuarios extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton btnConfirmar;
	private JTextField txtUsuario;
	private JTextField txtConfSenha;
	private JTextField txtSenha;
	private JCheckBox chckbxVisualizarSenha = new JCheckBox("Visualizar senha");
	private JCheckBox chckbxAdministrador = new JCheckBox("Administrador");
	private ImageIcon imagem = new ImageIcon("C:/Program Files/Popeye/popeyeicomenor.png");
	private String senha = "";
	private String confSenha = "";
	private boolean adm = false;
	private String alfanum = "0987654321qwertyuiopasdfghjklçzxcvbnm !@#$%¨&*()-_+=|.,<>;:[]{}§¹²³£¢¬°?";
	private JTable tabelaConsUsuarios;
	private JTextField txtBuscUsuario;
	private JButton btnProcurar;
	private boolean senhasVisiveis = false;
	private JTextField txtAltAntUsr;
	private JPasswordField passSenha;
	private JTextField txtNovoAltUsr;
	private JPasswordField txtNovaSenha;
	private JPasswordField txtConfNovaSenhaAlt;
	private JButton btnConfirmarAlt;
	private JTable tabelaExcUsuarios;
	private JButton button_3;


	public void telaUsuarios(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TelaUsuarios dialog = new TelaUsuarios();
					dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TelaUsuarios() {
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		setTitle("Usu\u00E1rios");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files\\Popeye\\popeyeicomenor.png"));
		setBounds(100, 100, 631, 516);
		getContentPane().setLayout(null);
		
		JPanel panelCab = new JPanel();
		panelCab.setLayout(null);
		panelCab.setBackground(new Color(100, 149, 237));
		panelCab.setBounds(0, 0, 615, 110);
		getContentPane().add(panelCab);
		
		JLabel label = new JLabel(new ImageIcon("C:\\Program Files\\Popeye\\popeyeicomenor.png"));
		label.setBounds(10, 0, 120, 102);
		panelCab.add(label);
		
		JLabel lblManutenoDeUsurios = new JLabel("Manuten\u00E7\u00E3o de Usu\u00E1rios");
		lblManutenoDeUsurios.setForeground(Color.RED);
		lblManutenoDeUsurios.setFont(new Font("Arial", Font.PLAIN, 30));
		lblManutenoDeUsurios.setBounds(137, 39, 331, 35);
		panelCab.add(lblManutenoDeUsurios);
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.setLayout(null);
		panelBotoes.setBackground(new Color(100, 149, 237));
		panelBotoes.setBounds(0, 110, 143, 368);
		getContentPane().add(panelBotoes);
		
		final JPanel panelIncluir = new JPanel();
		panelIncluir.setBackground(Color.LIGHT_GRAY);
		panelIncluir.setFont(new Font("Arial", Font.PLAIN, 12));
		panelIncluir.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Inclu\u00EDr", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panelIncluir.setBounds(153, 121, 452, 294);
		panelIncluir.setVisible(false);
		getContentPane().add(panelIncluir);
		panelIncluir.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					txtSenha.requestFocus();
				}
			}
		});
		txtUsuario.setFont(new Font("Arial", Font.PLAIN, 15));
		txtUsuario.setBounds(137, 76, 221, 24);
		panelIncluir.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 15));
		lblUsuario.setBounds(73, 79, 54, 18);
		panelIncluir.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 15));
		lblSenha.setBounds(81, 114, 46, 18);
		panelIncluir.add(lblSenha);
		
		txtConfSenha = new JTextField();
		txtConfSenha.addKeyListener(new KeyAdapter() {
			String cs = "";
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (alfanum.contains(arg0.getKeyChar()+"")) {
					
					if (chckbxVisualizarSenha.isSelected() == false) {
						cs = arg0.getKeyChar()+"";
						arg0.consume();
						confSenha = confSenha + cs;
						txtConfSenha.setText(txtConfSenha.getText()+"*");
					}else{
						cs = arg0.getKeyChar()+"";
						confSenha = confSenha + cs;
					}
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					txtConfSenha.setText("");
					confSenha = "";
				}
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					chckbxAdministrador.requestFocus();
				}
			}
		});
		txtConfSenha.setFont(new Font("Arial", Font.PLAIN, 15));
		txtConfSenha.setBounds(137, 146, 221, 24);
		panelIncluir.add(txtConfSenha);
		txtConfSenha.setColumns(10);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar senha:");
		lblConfirmarSenha.setFont(new Font("Arial", Font.PLAIN, 15));
		lblConfirmarSenha.setBounds(14, 149, 113, 18);
		panelIncluir.add(lblConfirmarSenha);
		chckbxVisualizarSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					btnConfirmar.doClick();
				}
			}
		});
		
		
		chckbxVisualizarSenha.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if ( chckbxVisualizarSenha.isSelected() == true) {
					txtConfSenha.setText(confSenha);
					txtSenha.setText(senha);
				}else {
					txtSenha.setText("");
					for (int c = 0; c < senha.length(); c++) {
						
						txtSenha.setText(txtSenha.getText()+"*");
					}
					txtConfSenha.setText("");
					for (int c = 0; c < confSenha.length(); c++) {					
						txtConfSenha.setText(txtConfSenha.getText()+"*");
					}
				}
			}
		});
		chckbxVisualizarSenha.setBackground(Color.LIGHT_GRAY);
		chckbxVisualizarSenha.setFont(new Font("Arial", Font.PLAIN, 15));
		chckbxVisualizarSenha.setBounds(225, 207, 133, 27);
		panelIncluir.add(chckbxVisualizarSenha);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if ((txtUsuario.getText().equals(""))) {
					JOptionPane.showMessageDialog(null, "O nome do usuario não pode ser em branco!", "Aviso!", JOptionPane.ERROR_MESSAGE, null);
				}else{
					if (senha.equals(confSenha)) {
						String[] conf = new String[2];
						conf[0] = "Sim";
						conf[1] = "Não";
						
						int r = JOptionPane.showOptionDialog(null, "Concluir cadastro do usuario "+txtUsuario.getText(), "Confirmar cadastro!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imagem, conf, null);
						
						if (r == 0){
							BeanUsuario b = new BeanUsuario();
							b.setUsuario(txtUsuario.getText());
							b.setSenha(senha);
							b.setAdm(adm);

							ConUsuario c = new ConUsuario();
							try {
								c.insereUsuario(b);
							} catch (ClassNotFoundException | SQLException e) {
								JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
							}
							
							txtUsuario.setText("");
							txtSenha.setText("");
							txtConfSenha.setText("");
							senha = "";
							confSenha = "";
							chckbxAdministrador.setSelected(false);
							chckbxVisualizarSenha.setSelected(false);
						}
					}else{
						JOptionPane.showMessageDialog(null, "A senha e a confirmação da senha não estão iguais!", "Aviso!", JOptionPane.ERROR_MESSAGE, null);
					}

				}	
			}
		});
		btnConfirmar.setForeground(Color.BLUE);
		btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnConfirmar.setBounds(137, 260, 107, 27);
		panelIncluir.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.RED);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelIncluir.setVisible(false);
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnCancelar.setBounds(254, 260, 104, 27);
		panelIncluir.add(btnCancelar);
		
		JLabel lblInserirNovoUsuario = new JLabel("Inserir novo usuario");
		lblInserirNovoUsuario.setFont(new Font("Arial", Font.PLAIN, 20));
		lblInserirNovoUsuario.setForeground(Color.BLUE);
		lblInserirNovoUsuario.setBounds(137, 26, 171, 24);
		panelIncluir.add(lblInserirNovoUsuario);
		chckbxAdministrador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					chckbxVisualizarSenha.requestFocus();
				}
			}
		});
		
		
		chckbxAdministrador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxAdministrador.isSelected() == true) {
					adm = true;
				}else{
					adm = false;
				}
			}
		});
		chckbxAdministrador.setBackground(Color.LIGHT_GRAY);
		chckbxAdministrador.setFont(new Font("Arial", Font.PLAIN, 15));
		chckbxAdministrador.setBounds(225, 177, 115, 27);
		panelIncluir.add(chckbxAdministrador);
		
		txtSenha = new JTextField();
		txtSenha.addKeyListener(new KeyAdapter() {
			String s = "";
			@Override
			public void keyTyped(KeyEvent arg0) {

				if (alfanum.contains(arg0.getKeyChar()+"")) {
					
					if (chckbxVisualizarSenha.isSelected() == false) {
						s = arg0.getKeyChar()+"";
						senha = senha + s;
						arg0.consume();
						txtSenha.setText(txtSenha.getText()+"*");
					}else{
						s = arg0.getKeyChar()+"";
						senha = senha + s;
					}
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					txtSenha.setText("");
					senha = "";
				}
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					txtConfSenha.requestFocus();
				}
			}
		});
		txtSenha.setFont(new Font("Arial", Font.PLAIN, 15));
		txtSenha.setBounds(137, 111, 221, 24);
		panelIncluir.add(txtSenha);
		txtSenha.setColumns(10);
		
		final JPanel panelConsultar = new JPanel();
		panelConsultar.setBackground(Color.LIGHT_GRAY);
		panelConsultar.setFont(new Font("Arial", Font.PLAIN, 12));
		panelConsultar.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consultar", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panelConsultar.setBounds(153, 121, 452, 294);
		panelConsultar.setVisible(false);
		getContentPane().add(panelConsultar);
		panelConsultar.setLayout(null);
		
		JScrollPane scrollPaneConsultar = new JScrollPane();
		scrollPaneConsultar.setBounds(20, 70, 422, 188);
		panelConsultar.add(scrollPaneConsultar);
		
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
		
		tabelaConsUsuarios = new JTable(){
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isCellEditable(int rowIndex, int vColIndex){
				return false;
			}
		};
		scrollPaneConsultar.setViewportView(tabelaConsUsuarios);
		tabelaConsUsuarios.setModel(new DefaultTableModel(
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
				"C\u00F3digo", "Usu\u00E1rio", "Senha", "Administrador"
			}
		) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tabelaConsUsuarios.getColumnModel().getColumn(0).setResizable(false);
		tabelaConsUsuarios.getColumnModel().getColumn(0).setPreferredWidth(53);
		tabelaConsUsuarios.getColumnModel().getColumn(1).setResizable(false);
		tabelaConsUsuarios.getColumnModel().getColumn(1).setPreferredWidth(122);
		tabelaConsUsuarios.getColumnModel().getColumn(2).setResizable(false);
		tabelaConsUsuarios.getColumnModel().getColumn(2).setPreferredWidth(128);
		tabelaConsUsuarios.getColumnModel().getColumn(3).setResizable(false);
		tabelaConsUsuarios.getColumnModel().getColumn(3).setPreferredWidth(126);
		tabelaConsUsuarios.setFont(new Font("Arial", Font.PLAIN, 15));
		
		final JCheckBox chckbxVisualizarSenhas = new JCheckBox("Visualizar senhas");
		chckbxVisualizarSenhas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxVisualizarSenhas.isSelected() == true) {
					senhasVisiveis = true;
				}else{
					senhasVisiveis = false;
				}
				btnProcurar.doClick();
			}
		});
		chckbxVisualizarSenhas.setBackground(Color.LIGHT_GRAY);
		chckbxVisualizarSenhas.setFont(new Font("Arial", Font.PLAIN, 15));
		chckbxVisualizarSenhas.setBounds(20, 260, 141, 27);
		panelConsultar.add(chckbxVisualizarSenhas);
		
		JLabel lblUsuario_1 = new JLabel("Usuario:");
		lblUsuario_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblUsuario_1.setBounds(20, 41, 54, 18);
		panelConsultar.add(lblUsuario_1);
		
		txtBuscUsuario = new JTextField();
		txtBuscUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					btnProcurar.doClick();
				}
			}
		});
		txtBuscUsuario.setFont(new Font("Arial", Font.PLAIN, 15));
		txtBuscUsuario.setBounds(84, 38, 246, 24);
		panelConsultar.add(txtBuscUsuario);
		txtBuscUsuario.setColumns(10);
		
		btnProcurar = new JButton("Procurar");
		btnProcurar.setForeground(Color.BLUE);
		btnProcurar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<BeanUsuario> u = null;
				ConUsuario c = new ConUsuario();
				try {
					u = c.buscarUsuario(txtBuscUsuario.getText());
				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
				}
				
				DefaultTableModel t = (DefaultTableModel) tabelaConsUsuarios.getModel();
				t.setNumRows(0);
				
				String administrador = "";
				String senha = "";
				for (int cont = 0; cont < u.size(); cont++) {
					switch (u.get(cont).getAdm()) {
					case 0:
						administrador = "Não";
						break;
					case 1:
						administrador = "Sim";
					}
					if (senhasVisiveis == false){
						for (int cont2 = 0; cont2 < u.get(cont).getSenha().length(); cont2++) {
							senha = senha + "*";
						}
					} else {
						senha = u.get(cont).getSenha();
					}
					t.addRow(new Object[]{
							u.get(cont).getIdUsuario(),
							u.get(cont).getUsuario(),
							senha,
							administrador
					});
					senha = "";
				}
				
			}
		});
		btnProcurar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnProcurar.setBounds(340, 37, 102, 27);
		panelConsultar.add(btnProcurar);
		
		JButton btnCancelarCons = new JButton("Cancelar");
		btnCancelarCons.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelConsultar.setVisible(false);
			}
		});
		btnCancelarCons.setForeground(Color.RED);
		btnCancelarCons.setFont(new Font("Arial", Font.PLAIN, 11));
		btnCancelarCons.setBounds(340, 263, 102, 23);
		panelConsultar.add(btnCancelarCons);
		
		tc = tabelaConsUsuarios.getColumn("C\u00F3digo");
		tc.setCellRenderer(letras);
		tc = tabelaConsUsuarios.getColumn("Usu\u00E1rio");
		tc.setCellRenderer(letras);
		tc = tabelaConsUsuarios.getColumn("Senha");
		tc.setCellRenderer(letras);
		tc = tabelaConsUsuarios.getColumn("Administrador");
		tc.setCellRenderer(letras);
		
		final JPanel panelAlterar = new JPanel();
		panelAlterar.setBackground(Color.LIGHT_GRAY);
		panelAlterar.setFont(new Font("Arial", Font.PLAIN, 12));
		panelAlterar.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Alterar", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panelAlterar.setBounds(153, 121, 452, 305);
		panelAlterar.setVisible(false);
		getContentPane().add(panelAlterar);
		panelAlterar.setLayout(null);
		
		JLabel lblAlteraoDeUsurio = new JLabel("Altera\u00E7\u00E3o de Usu\u00E1rio");
		lblAlteraoDeUsurio.setForeground(Color.BLUE);
		lblAlteraoDeUsurio.setFont(new Font("Arial", Font.PLAIN, 20));
		lblAlteraoDeUsurio.setBounds(124, 11, 184, 24);
		panelAlterar.add(lblAlteraoDeUsurio);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Arial", Font.PLAIN, 15));
		lblUsurio.setBounds(101, 49, 54, 18);
		panelAlterar.add(lblUsurio);
		
		txtAltAntUsr = new JTextField();
		txtAltAntUsr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					passSenha.requestFocus();
				}
			}
		});
		txtAltAntUsr.setFont(new Font("Arial", Font.PLAIN, 15));
		txtAltAntUsr.setBounds(165, 46, 251, 24);
		panelAlterar.add(txtAltAntUsr);
		txtAltAntUsr.setColumns(10);
		
		JLabel lblSenha_1 = new JLabel("Senha:");
		lblSenha_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblSenha_1.setBounds(111, 84, 46, 18);
		panelAlterar.add(lblSenha_1);
		
		passSenha = new JPasswordField();
		passSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					txtNovoAltUsr.requestFocus();
				}
			}
		});
		passSenha.setFont(new Font("Arial", Font.PLAIN, 15));
		passSenha.setBounds(165, 81, 251, 24);
		panelAlterar.add(passSenha);
		
		JLabel lblNovaSenha = new JLabel("Nova Senha:");
		lblNovaSenha.setForeground(Color.DARK_GRAY);
		lblNovaSenha.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNovaSenha.setBounds(74, 152, 81, 17);
		panelAlterar.add(lblNovaSenha);
		
		JLabel lblConfirmarNovaSenha = new JLabel("Confirmar nova senha:");
		lblConfirmarNovaSenha.setForeground(Color.DARK_GRAY);
		lblConfirmarNovaSenha.setFont(new Font("Arial", Font.PLAIN, 14));
		lblConfirmarNovaSenha.setBounds(11, 180, 144, 17);
		panelAlterar.add(lblConfirmarNovaSenha);
		
		final JCheckBox chckbxVisualizarSenhas_1 = new JCheckBox("Visualizar Senhas");
		chckbxVisualizarSenhas_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					btnConfirmarAlt.requestFocus();
				}
			}
		});
		chckbxVisualizarSenhas_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxVisualizarSenhas_1.isSelected() == true) {
					txtNovaSenha.setEchoChar((char)0);
					txtConfNovaSenhaAlt.setEchoChar((char)0);
				}else{
					txtNovaSenha.setEchoChar('●');
					txtConfNovaSenhaAlt.setEchoChar('●');
				}
			}
		});
		chckbxVisualizarSenhas_1.setBackground(Color.LIGHT_GRAY);
		chckbxVisualizarSenhas_1.setFont(new Font("Arial", Font.PLAIN, 15));
		chckbxVisualizarSenhas_1.setBounds(273, 233, 143, 27);
		panelAlterar.add(chckbxVisualizarSenhas_1);
		
		final JCheckBox chckbxAdministrador_1 = new JCheckBox("Administrador");
		chckbxAdministrador_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					chckbxVisualizarSenhas_1.requestFocus();
				}
			}
		});
		chckbxAdministrador_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxAdministrador_1.isSelected() == true) {
					adm = true;
				}else{
					adm = false;
				}
				
			}
		});
		chckbxAdministrador_1.setBackground(Color.LIGHT_GRAY);
		chckbxAdministrador_1.setFont(new Font("Arial", Font.PLAIN, 15));
		chckbxAdministrador_1.setBounds(274, 203, 115, 27);
		panelAlterar.add(chckbxAdministrador_1);
		
		btnConfirmarAlt = new JButton("Confirmar");
		btnConfirmarAlt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					btnConfirmarAlt.doClick();
				}
			}
		});
		btnConfirmarAlt.addActionListener(new ActionListener() {
			@Override
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if ((txtAltAntUsr.getText().equals("")) || (passSenha.getText().equals(""))) {
					JOptionPane.showMessageDialog(null, "O campo do usuário está vazio!", "Erro!", JOptionPane.ERROR_MESSAGE, null);
				}else{
					if ((txtNovoAltUsr.getText().equals(""))||(txtNovaSenha.getText().equals(""))||(txtConfNovaSenhaAlt.getText().equals(""))) {
						JOptionPane.showMessageDialog(null, "Algum dos campos com os novos dados está vazio!", "Erro!", JOptionPane.ERROR_MESSAGE, null);
					}else{			
						ConUsuario c = new ConUsuario();
						
						try {
							
							c.alteraUsuario(c.verificaUsuarioSenha(txtAltAntUsr.getText(), passSenha.getText()), txtAltAntUsr.getText(), passSenha.getText(), txtNovoAltUsr.getText(), txtNovaSenha.getText(), adm);
						
						} catch (ClassNotFoundException | SQLException e) {
							JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
						}
						
						txtAltAntUsr.setText("");
						passSenha.setText("");
						txtNovoAltUsr.setText("");
						txtNovaSenha.setText("");
						txtConfNovaSenhaAlt.setText("");
						chckbxAdministrador_1.setSelected(false);
						chckbxVisualizarSenhas_1.setSelected(false);
						
					}
				}
			}
		});
		btnConfirmarAlt.setForeground(Color.BLUE);
		btnConfirmarAlt.setFont(new Font("Arial", Font.PLAIN, 15));
		btnConfirmarAlt.setBounds(124, 267, 115, 27);
		panelAlterar.add(btnConfirmarAlt);
		
		JButton btnCancelarAlt = new JButton("Cancelar");
		btnCancelarAlt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelAlterar.setVisible(false);
			}
		});
		btnCancelarAlt.setForeground(Color.RED);
		btnCancelarAlt.setFont(new Font("Arial", Font.PLAIN, 15));
		btnCancelarAlt.setBounds(249, 267, 115, 27);
		panelAlterar.add(btnCancelarAlt);
		
		JLabel lblNovoUsurio = new JLabel("Novo Usu\u00E1rio:");
		lblNovoUsurio.setForeground(Color.DARK_GRAY);
		lblNovoUsurio.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNovoUsurio.setBounds(67, 124, 88, 17);
		panelAlterar.add(lblNovoUsurio);
		
		txtNovoAltUsr = new JTextField();
		txtNovoAltUsr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					txtNovaSenha.requestFocus();
				}
			}
		});
		txtNovoAltUsr.setBorder(new LineBorder(Color.BLACK));
		txtNovoAltUsr.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNovoAltUsr.setBounds(165, 116, 251, 23);
		panelAlterar.add(txtNovoAltUsr);
		txtNovoAltUsr.setColumns(10);
		
		txtNovaSenha = new JPasswordField();
		txtNovaSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					txtConfNovaSenhaAlt.requestFocus();
				}
			}
		});
		txtNovaSenha.setBorder(new LineBorder(Color.BLACK));
		txtNovaSenha.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNovaSenha.setBounds(165, 146, 251, 23);
		panelAlterar.add(txtNovaSenha);
		
		txtConfNovaSenhaAlt = new JPasswordField();
		txtConfNovaSenhaAlt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					chckbxAdministrador_1.requestFocus();
				}
			}
		});
		txtConfNovaSenhaAlt.setBorder(new LineBorder(Color.BLACK));
		txtConfNovaSenhaAlt.setFont(new Font("Arial", Font.PLAIN, 15));
		txtConfNovaSenhaAlt.setBounds(165, 174, 251, 23);
		panelAlterar.add(txtConfNovaSenhaAlt);
		
		final JPanel panelExcluir = new JPanel();
		panelExcluir.setBackground(Color.LIGHT_GRAY);
		panelExcluir.setFont(new Font("Arial", Font.PLAIN, 12));
		panelExcluir.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Excluir", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panelExcluir.setBounds(153, 121, 452, 305);
		panelExcluir.setVisible(false);
		getContentPane().add(panelExcluir);
		panelExcluir.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 432, 204);
		panelExcluir.add(scrollPane);
		
		tabelaExcUsuarios = new JTable(){

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int vColIndex){
				return false;
			}
		};
		scrollPane.setViewportView(tabelaExcUsuarios);
		tabelaExcUsuarios.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"C\u00F3digo", "Usu\u00E1rio"
			}
		) {
			private static final long serialVersionUID = 1L;
			
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tabelaExcUsuarios.getSelectedRowCount() != 1) {
					JOptionPane.showMessageDialog(null, "É necessário selecionar um registro!", "Erro!", JOptionPane.ERROR_MESSAGE, null);
				}else{
				
					String conf[] = new String[2];
					conf[0] = "Sim";
					conf[1] = "Não";
					
					int r = JOptionPane.showOptionDialog(null, "Deseja realmente excluir este usuário?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imagem, conf, null);
					if (r == 0) {
						ConUsuario con = new ConUsuario();
						try {
							con.excluiUsuario((int) tabelaExcUsuarios.getValueAt(tabelaExcUsuarios.getSelectedRow(), 0));
							button_3.doClick();
						} catch (ClassNotFoundException | SQLException e) {
							JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
						}
					}
				}
			}
		});
		btnExcluir.setForeground(Color.BLUE);
		btnExcluir.setFont(new Font("Arial", Font.PLAIN, 15));
		btnExcluir.setBounds(102, 267, 109, 27);
		panelExcluir.add(btnExcluir);
		
		JButton btnCancelar_1 = new JButton("Cancelar");
		btnCancelar_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelExcluir.setVisible(false);
			}
		});
		btnCancelar_1.setForeground(Color.RED);
		btnCancelar_1.setFont(new Font("Arial", Font.PLAIN, 15));
		btnCancelar_1.setBounds(221, 267, 109, 27);
		panelExcluir.add(btnCancelar_1);
		
		JLabel lblExclusoDeUsurio = new JLabel("Exclusão de Usuário");
		lblExclusoDeUsurio.setFont(new Font("Arial", Font.PLAIN, 20));
		lblExclusoDeUsurio.setForeground(Color.BLUE);
		lblExclusoDeUsurio.setBounds(119, 11, 178, 24);
		panelExcluir.add(lblExclusoDeUsurio);
		tabelaExcUsuarios.getColumnModel().getColumn(0).setResizable(false);
		tabelaExcUsuarios.getColumnModel().getColumn(1).setResizable(false);
		
		tc = tabelaExcUsuarios.getColumn("C\u00F3digo");
		tc.setCellRenderer(letras);
		tc = tabelaExcUsuarios.getColumn("Usu\u00E1rio");
		tc.setCellRenderer(letras);
		
		
		JButton button = new JButton("Incluir");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelExcluir.setVisible(false);
				panelAlterar.setVisible(false);
				panelConsultar.setVisible(false);
				panelIncluir.setVisible(true);
			}
		});
		button.setFont(new Font("Arial", Font.PLAIN, 12));
		button.setBounds(10, 24, 123, 23);
		panelBotoes.add(button);
		
		JButton button_1 = new JButton("Consultar");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<BeanUsuario> u = null;
				ConUsuario c = new ConUsuario();
				try {
					u = c.consultaUsuarios();
				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
				}
				
				DefaultTableModel t = (DefaultTableModel) tabelaConsUsuarios.getModel();
				t.setNumRows(0);
				String administrador = "";
				String senha = ""; 
				for (int cont = 0; cont < u.size(); cont++) {
					
					switch (u.get(cont).getAdm()) {
					
						case 0:
							administrador = "Não";
							break;
							
						case 1:
							administrador = "Sim";
							break;
							
					}
					if (senhasVisiveis == false){
						for (int cont2 = 0; cont2 < u.get(cont).getSenha().length(); cont2++) {
							senha = senha + "*";
						}
					} else {
						senha = u.get(cont).getSenha();
					}
					
					t.addRow(new Object[]{
							u.get(cont).getIdUsuario(),
							u.get(cont).getUsuario(),
							senha,
							administrador
					});
					senha = "";
				}
				
				panelExcluir.setVisible(false);
				panelAlterar.setVisible(false);
				panelIncluir.setVisible(false);
				panelConsultar.setVisible(true);
			}
		});
		button_1.setFont(new Font("Arial", Font.PLAIN, 12));
		button_1.setBounds(10, 58, 123, 23);
		panelBotoes.add(button_1);
		
		JButton button_2 = new JButton("Alterar");
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				adm = false;
				panelExcluir.setVisible(false);
				panelIncluir.setVisible(false);
				panelConsultar.setVisible(false);
				panelAlterar.setVisible(true);
			}
		});
		button_2.setFont(new Font("Arial", Font.PLAIN, 12));
		button_2.setBounds(10, 92, 123, 23);
		panelBotoes.add(button_2);
		
		button_3 = new JButton("Excluir");
		button_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConUsuario cusr = new ConUsuario();
				ArrayList<BeanUsuario> usr = null;
				try {
					usr = cusr.consultaUsuarios();
				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
				}
				
				DefaultTableModel tabela = (DefaultTableModel) tabelaExcUsuarios.getModel();
				tabela.setNumRows(0);
				
				for (int c = 0; c < usr.size(); c++) {
					tabela.addRow(new Object[]{
							usr.get(c).getIdUsuario(),
							usr.get(c).getUsuario()
					});
				}
				
				panelIncluir.setVisible(false);
				panelConsultar.setVisible(false);
				panelAlterar.setVisible(false);
				panelExcluir.setVisible(true);
			}
		});
		button_3.setFont(new Font("Arial", Font.PLAIN, 12));
		button_3.setBounds(10, 126, 123, 23);
		panelBotoes.add(button_3);
		
		JButton button_4 = new JButton("Fechar");
		button_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_4.setFont(new Font("Arial", Font.PLAIN, 15));
		button_4.setBounds(505, 437, 100, 27);
		getContentPane().add(button_4);
		
		

	}
}

package br.com.popeye.visao;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

import br.com.popeye.controle.ConUsuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;

import java.awt.Toolkit;

public class Login extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUser;
	private JPasswordField passSenha;
	ConUsuario c;
	MenuPrincipal mp;

	public void login() {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files\\Popeye\\popeye.jpg"));
		setTitle("Login");
		setResizable(false);
		setBounds(100, 100, 215, 332);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBounds(0, 0, 209, 125);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		new ImageIcon("C:/Program Files/Popeye/popeye.jpg");
		
		JLabel label = new JLabel(new ImageIcon("C:\\Program Files\\Popeye\\popeyeicomenor.png"));
		label.setBounds(44, 11, 120, 102);
		panel.add(label);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setFont(new Font("Arial", Font.PLAIN, 20));
		lblUsurio.setBounds(71, 136, 66, 24);
		contentPanel.add(lblUsurio);
		
		txtUser = new JTextField();
		txtUser.setHorizontalAlignment(SwingConstants.CENTER);
		txtUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					passSenha.requestFocus();
				}
			}
		});
		txtUser.setFont(new Font("Arial", Font.BOLD, 15));
		txtUser.setBounds(22, 171, 164, 20);
		contentPanel.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSenha.setBounds(71, 202, 55, 24);
		contentPanel.add(lblSenha);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 268, 209, 36);
			contentPanel.add(buttonPane);
			buttonPane.setBackground(new Color(100, 149, 237));
			buttonPane.setLayout(null);
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
				cancelButton.setBounds(108, 5, 91, 27);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			
			final JButton btnEntrar = new JButton("Entrar");
			btnEntrar.addActionListener(new ActionListener() {
				@Override
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent arg0) {
					c = new ConUsuario();
					try {
						mp = new MenuPrincipal(c.verificaAdministrador(txtUser.getText(), passSenha.getText()));
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
					}
					String senha = passSenha.getText();
					
						try {
							if(c.verificaUsuarioSenha(txtUser.getText(), senha)==true){
								mp.setLocationRelativeTo(null);
								mp.setVisible(true);
								dispose();
							}else{
								JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!");
								txtUser.requestFocus();
							}
						} catch (ClassNotFoundException e) {
							JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
						}
							
						
				}
			});
			btnEntrar.setFont(new Font("Arial", Font.PLAIN, 14));
			btnEntrar.setBounds(10, 6, 88, 25);
			buttonPane.add(btnEntrar);
			
			
			passSenha = new JPasswordField();
			passSenha.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
						btnEntrar.doClick();
					}
				}
			});
			passSenha.setHorizontalAlignment(SwingConstants.CENTER);
			passSenha.setFont(new Font("Arial", Font.BOLD, 15));
			passSenha.setBounds(22, 237, 164, 20);
			contentPanel.add(passSenha);
		}
	}
}

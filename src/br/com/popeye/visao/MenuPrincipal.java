package br.com.popeye.visao;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TelaProduto p;

	public void menuPrincipal(final Boolean adm) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal(adm);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static Dimension tamanhoDaTela (){  
	    return (  
	        new Dimension (  
	            (int) Toolkit.getDefaultToolkit ().getScreenSize ().getWidth (),  
	            (int) Toolkit.getDefaultToolkit ().getScreenSize ().getHeight ()-50));  
	}

	public MenuPrincipal(Boolean administrador) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Program Files\\Popeye\\popeyeico.png"));
		setTitle("Popeye Lanches - Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 282);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 535, 129);
		panel.setBackground(new Color(100, 149, 237));
		panel.setLayout(null);
		contentPane.add(panel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Program Files\\Popeye\\popeyeicomenor.png"));
		label.setBounds(213, 11, 120, 102);
		panel.add(label);
		
		new ImageIcon("C:/Program Files/Popeye/popeye.jpg");
		
		JButton btnProdutos = new JButton("Produtos");
		btnProdutos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				p = new TelaProduto();
				p.setLocationRelativeTo(null);
				p.setVisible(true);
			}
		});
		btnProdutos.setFont(new Font("Arial", Font.PLAIN, 12));
		btnProdutos.setBounds(10, 140, 120, 85);
		contentPane.add(btnProdutos);
		
		JButton btnVenda = new JButton("Venda");
		btnVenda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TelaVenda tv = new TelaVenda();
				tv.setSize(tamanhoDaTela());
				tv.setLocation(NORMAL, NORMAL);
				tv.setVisible(true);
			}
		});
		btnVenda.setFont(new Font("Arial", Font.PLAIN, 12));
		btnVenda.setBounds(140, 140, 120, 85);
		contentPane.add(btnVenda);
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TelaUsuarios tv = new TelaUsuarios();
				tv.setLocationRelativeTo(null);
				tv.setVisible(true);
			}
		});
		btnUsuarios.setFont(new Font("Arial", Font.PLAIN, 12));
		btnUsuarios.setBounds(270, 140, 120, 85);
		contentPane.add(btnUsuarios);
		
		JButton btnRelatrioDiario = new JButton("Relat\u00F3rio Di\u00E1rio");
		btnRelatrioDiario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					TelaRelDiarios trd = new TelaRelDiarios();
					trd.setLocationRelativeTo(null);
					trd.setVisible(true);
					
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
				}
				
			}
		});
		btnRelatrioDiario.setFont(new Font("Arial", Font.PLAIN, 12));
		btnRelatrioDiario.setBounds(400, 140, 120, 85);
		contentPane.add(btnRelatrioDiario);
		
		JButton btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		btnX.setFont(new Font("Arial", Font.BOLD, 12));
		btnX.setBounds(469, 227, 51, 23);
		contentPane.add(btnX);
		
		if (administrador == false) {
			btnProdutos.setEnabled(false);
			btnRelatrioDiario.setEnabled(false);
			btnUsuarios.setEnabled(false);
		}
	}
}

package br.com.cliente.nomadesvirtuais.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Chat extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMensagem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chat frame = new Chat();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Chat() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(223,230,237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 197);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setForeground(new Color(45, 59, 72));
		textArea.setFont(new Font("Arial", Font.PLAIN, 12));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		txtMensagem = new JTextField();
		txtMensagem.setForeground(new Color(45, 59, 72));
		txtMensagem.setFont(new Font("Arial", Font.PLAIN, 12));
		txtMensagem.setText("Digite o seu texto...");
		txtMensagem.setBounds(10, 219, 282, 31);
		contentPane.add(txtMensagem);
		txtMensagem.setColumns(10);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.setBackground(new Color(32, 120, 104));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.setBounds(302, 219, 122, 31);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Teste");
			}
		});
		
		contentPane.add(btnNewButton);
	}
}

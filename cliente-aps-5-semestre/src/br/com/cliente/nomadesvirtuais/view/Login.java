package br.com.cliente.nomadesvirtuais.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {
	
	private static final String URL = "127.0.0.1";
	private static final int PORT = 3000;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public Login() throws UnknownHostException, IOException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(223,230,237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setForeground(new Color(45, 59, 72));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 414, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("USU\u00C1RIO");
		lblNewLabel_1.setForeground(new Color(45, 59, 72));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 45, 56, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(10, 60, 271, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("SENHA");
		lblNewLabel_2.setForeground(new Color(45, 59, 72));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 91, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 109, 271, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("ENTRAR");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(32, 120, 104));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 12));
		btnNewButton.setBounds(10, 140, 105, 30);
		btnNewButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Socket socket = new Socket(URL, PORT);
					
					OutputStream os = socket.getOutputStream();
					PrintWriter writer = new PrintWriter(new OutputStreamWriter(os), true);
					
					InputStream is = socket.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(is));
					
					// realiza a leitura das mensagens vindas do servidor
					new Thread() {
						@Override
						public void run() {
							while (true) {
								try {
									String login = reader.readLine();
									
									if (login.equals("ok")) {
										new Chat(socket, writer, reader).setVisible(true);
										dispose();
										stop();
									} else {
										socket.close();
										stop();
									}
									
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
						
					}.start();
					
					String code = textField.getText();
					String password = passwordField.getText();
					writer.println(code + ";" + password);
					
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("ESQUECEU SUA SENHA?");
		lblNewLabel_3.setForeground(new Color(126, 117, 244));
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 181, 172, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Contate o RH: rh@esqueciasenha.com");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel_4.setForeground(new Color(45, 59, 72));
		lblNewLabel_4.setBounds(10, 196, 341, 14);
		contentPane.add(lblNewLabel_4);
	}
}

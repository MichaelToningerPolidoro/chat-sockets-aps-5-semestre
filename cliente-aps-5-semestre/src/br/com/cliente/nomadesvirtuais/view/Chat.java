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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.cliente.nomadesvirtuais.utils.ConnectionHandle;

public class Chat extends JFrame {
	/**
	 * TODO: Tentar pegar a conexão socket da outra tela (atributo estatico)
	 * TODO: Lógica para escutar as mensagens do servidor e colocar na TextArea
	 * assim que ela chegar
	 * TODO: lógica nos botões
	 */
	
	private PrintWriter writer;
	
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
	public Chat() throws UnknownHostException, IOException {
		ConnectionHandle.setConnection(new Socket("127.0.0.1", 3000));
		
		OutputStream os = ConnectionHandle.getConnection().getOutputStream();
		writer = new PrintWriter(new OutputStreamWriter(os), true);
		
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
		textArea.setText("");
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
				String message = txtMensagem.getText().trim();
				writer.println(message);
			}
		});
		
		contentPane.add(btnNewButton);
		
		//Thread para ler as mensagens vindas do servidor
		new Thread() {
			
			@Override
			public void run() {
				try {
					InputStream is = ConnectionHandle.getConnection().getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(is));
					while (true) {
						String mensagem = reader.readLine().trim();
						textArea.append("\n" + mensagem);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}

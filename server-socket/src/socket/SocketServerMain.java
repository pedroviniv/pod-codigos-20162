package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerMain {


	public static void main(String[] args) throws IOException {
		//
		System.out.println("Servidor ativo");
		//
		ServerSocket serverSocket = new ServerSocket(10999);
		while(true){
			//recebe a conexão
			Socket socket = serverSocket.accept();
			//leitura do requição
			InputStream input = socket.getInputStream();
			byte[] b = new byte[1024];//<-- 
			input.read(b);
			//converto os bytes em text
			String text = new String(b);
			//imprimir o texto da requisição
			System.out.println("Requesição recebido: " + text);
			//
			throw new RuntimeException();
			//responder o requisitor
			//OutputStream output = socket.getOutputStream();
			//output.write("#".getBytes());//token de validação
			//output.write("OK".getBytes());//resposta
			//encerrar a conexão
			//socket.close();//
		}
		//serverSocket.close();
	}
		
}

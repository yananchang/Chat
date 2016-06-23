import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	boolean started = false;
	ServerSocket ss = null;
	
	List<Client> clients = new ArrayList<Client>();
	
	public static void main(String[] args) {
		new ChatServer().start();
		
	}

	public void start(){
		try {
			ss = new ServerSocket(8888);
<<<<<<< HEAD
			started = true;
=======
>>>>>>> 9ebda800599a6d7be3a8ae9684a67d7b7670f7ac
		}catch (BindException e){
			System.out.println("socket in use......");
			System.out.println("Please turn off the program and restart");
			System.exit(0);
		}catch (IOException e){
			e.printStackTrace();
		}
		
		try{
			
			while(started){
				Socket s = ss.accept();
				Client c = new Client(s);
System.out.println("a client connected!");
<<<<<<< HEAD
				new Thread(c).start();
				clients.add(c);
				//dis.close();
			}
		} catch (IOException e) {
=======
				bConnected = true;
				dis = new DataInputStream(s.getInputStream());
				while(bConnected){
				
					String str = dis.readUTF();
					System.out.println(str);
				}
				//dis.close();
			}
		} catch (EOFException e){
			System.out.println("Client closed!");
		}
		catch (IOException e) {
>>>>>>> 9ebda800599a6d7be3a8ae9684a67d7b7670f7ac
			e.printStackTrace();
		}finally{
			try {
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	class Client implements Runnable{

		private Socket s;
		private DataInputStream  dis = null;
		private DataOutputStream dos = null;
		private boolean bConnected = false;
		public Client(Socket s){
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				bConnected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		public void send(String str){
			try {
				dos.writeUTF(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		public void run() {
			try{
				while(bConnected){
					String str = dis.readUTF();
System.out.println(str);
			for(int i=0;i<clients.size();i++){
				Client c = clients.get(i);
				c.send(str);
			}
			
			/*
			for(Iterator<Client> it =clients.iterator();it.hasNext();){
				Client c = it.next();
				c.send(str);
			}
			*/
			
			
			
				}
			} catch (EOFException e){
				System.out.println("Client closed!");
			}
			catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					if(dis != null) dis.close();
					if(dos != null) dos.close();
					if(s != null) s.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	
}

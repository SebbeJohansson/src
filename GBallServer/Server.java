package GBallServer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;

import Shared.*;

public class Server
{
	private ArrayList<ClientConnection> m_connectedClients = new ArrayList<ClientConnection>();
	private static DatagramSocket m_socket;
	
	public void main(String[] args)
	{
		if (args.length < 1)
		{
			System.err.println("Insert portnumber");
			System.exit(-1);
		}
		
		try
		{
			m_socket = new DatagramSocket(Integer.parseInt(args[0]));
		}
		
		catch (SocketException | NumberFormatException e)
		{
			e.printStackTrace();
		}
		listenForMessages();
	}
	
	private void listenForMessages()
	{
		while (true)
		{
			if (m_connectedClients.size() < 4)
			{
				byte[] buff = new byte[1024];
				DatagramPacket p = new DatagramPacket(buff, buff.length);
				
				try
				{
					m_socket.receive(p);
				}
				
				catch (IOException e)
				{
					e.printStackTrace();
				}	
				
				ByteArrayInputStream bStream = new ByteArrayInputStream(buff);
				ObjectInputStream input = null;
				MsgData receivedMessage = null;
				
				try
				{
					input = new ObjectInputStream(bStream);
					receivedMessage = (MsgData)input.readObject();
				}
				
				catch (IOException | ClassNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(receivedMessage.m_string.equals("join"))
				{
					MsgData handshake = new MsgData(String.valueOf(addClient(p.getAddress(), p.getPort())));				
					ByteArrayOutputStream boStream = new ByteArrayOutputStream();
					ObjectOutputStream output = null;
					
					try
					{
						output = new ObjectOutputStream(boStream);
						output.writeObject(handshake);
					}
					
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					byte[] buf = boStream.toByteArray();
					DatagramPacket packet = new DatagramPacket(buf, buf.length, p.getAddress(), p.getPort());
					
					try
					{
						m_socket.send(packet);
					}
					
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else
				{
					
				}
				System.out.println("IP of new client: " + String.valueOf(p.getAddress() + " Port: " + p.getPort()));
			}
		}
	}
	
	public boolean addClient(InetAddress address, int port) {
		ClientConnection c;
		for (Iterator<ClientConnection> itr = m_connectedClients.iterator(); itr.hasNext();) {
			c = itr.next();
			if (c.getAddress().equals(address) && c.getPort() == port) {
				return false; // Already exists a client with this IP and port.
			}
		}
		System.out.println("Added a new client.");
		m_connectedClients.add(new ClientConnection(address, port));
		return true;
	}
}
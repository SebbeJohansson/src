package GBallClient;

import java.net.DatagramSocket;
import java.net.InetAddress;

import Shared.MsgData;

public class ServerConnection
{
	//beh�ver hantera client sidan p� en handshake f�r att etablera anslutningen n�r clienten ansluter.
	//samt �ven ta hand om att skicka meddelanden till servern under spelets g�ng.
	
	private InetAddress serverAddress;
	private int serverPort;
	
	public ServerConnection(InetAddress address, int port)
	{
		serverAddress = address;
		serverPort = port;
	}
	
	
	public boolean handshake()
	{
		
		
		
		
		if (/*connection successful*/)
			return true;
		
		else
			return false;
		
	}
	
	public void sendMessage()
	{
		
	}
	
	public MsgData receiveMessage()
	{
		MsgData receivedMessage = null;
		
		
		
		return receivedMessage;
	}
}
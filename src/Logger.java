import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Logger {
	public LinkedHashMap<String, nodeInfo> nodes = new LinkedHashMap<String, nodeInfo>();
<<<<<<< HEAD
=======
	public Vector<Message> messageRec = new Vector<Message>();
>>>>>>> dfdcedbb0ff67390750dbc7ba0c524c9ea8abffe
	public ConcurrentHashMap<String, Socket> sockets = new ConcurrentHashMap<String, Socket>();
	public ConcurrentHashMap<String, ObjectOutputStream> streams= new ConcurrentHashMap<String, ObjectOutputStream>();
	public boolean logicalTime;
	public String filename;
	public long last;
	public User user;
	public configFileParse config;
	public int size;
	public int port;
	public Logger(String configuration_filename,boolean lt)
	{
		logicalTime=lt;
		try {
			config = new configFileParse(configuration_filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		filename = configuration_filename;
		File hold  = new File(filename);
		last=hold.lastModified();
		System.out.println("last: "+last);
		port = config.getPortbyName("logger");
		if(port==-1)
		{
			System.out.println("can not find the user info in config");
			return;
		}
		size = config.getSize();
		user = new User("logger", port,messageRec,sockets, streams,nodes,logicalTime);
		new Thread(user).start();
	
	}
	public static void main(String[] args) throws IOException {
		Logger logger = new Logger(args[0], true);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			String command = in.readLine();
			switch (command) {
			case "print":
				printLog();
				break;
			default:
				System.err.println("wrong input!");
				break;
			}
		}
	}
	private static void printLog()
	{
		public LinkedHashMap<VectorTimeStamp,Message> messageRec = new LinkedHashMap<VectorTimeStamp,Message>();
		int len = messageRec.size();
		ArrayList<VectorTimeStamp> keys = new ArrayList<VectorTimeStamp>(messageRec.keySet());
		System.out.println("Current Message"+"\t\t"+"\t\t"+"Concurrent With"+"\t\t"+"Happened After");
		for(int i = 0; i < len; i++)
		{
			ArrayList<Message> happenedBefore = new ArrayList<Message>();
			ArrayList<Message> concurrent = new ArrayList<Message>();
			ArrayList<Message> happenedAfter = new ArrayList<Message>();
			for(int j = 0; j < len; j++)
			{
				if(j != i)
				{
					if(keys.get(i).compareTo(keys.get(j)) ==1 )
					{
						happenedBefore.add(messageRec.get(keys.get(i)));
					}else if(keys.get(i).compareTo(keys.get(j)) ==-1)
					{
						happenedAfter.add(messageRec.get(keys.get(i)));
					}else{
						concurrent.add(messageRec.get(keys.get(i)));
					}
				}
				
			}
			System.out.println("Happened Before:\t\t" + happenedBefore);
			System.out.println("Concurrent:\t\t" + happenedBefore);
			System.out.println("Happened After:\t\t" + happenedBefore);
			
		}
			
		
	}
}

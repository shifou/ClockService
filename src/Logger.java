import java.io.File;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Logger {
	public ArrayList<LogicalTimeStamp> logMat = new ArrayList<LogicalTimeStamp>();
	public ArrayList<VectorTimeStamp> vecMat = new ArrayList<VectorTimeStamp>();
	public LinkedHashMap<String, nodeInfo> nodes = new LinkedHashMap<String, nodeInfo>();
	public ConcurrentLinkedQueue<Message> messageRec = new ConcurrentLinkedQueue<Message>();
	public ConcurrentHashMap<String, Socket> sockets = new ConcurrentHashMap<String, Socket>();
	public ConcurrentHashMap<String, ObjectOutputStream> streams= new ConcurrentHashMap<String, ObjectOutputStream>();
	public boolean logicalTime;
	public String filename;
	public long last;
	public User user;
	public configFileParse config;
	public int size;
	public int port;
	public Logger(String configuration_filename)
	{
		config = new configFileParse(configuration_filename);
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
		user = new User("logger", port,messageRec,sockets, streams,nodes,logMat,vecMat,logicalTime);
		new Thread(user).start();
		for(int i=0;i<size;i++){
			logMat.add(new LogicalTimeStamp(i));
			vecMat.add(new VectorTimeStamp(i,size));
		}
	}
}

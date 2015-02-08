import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
public class Connection implements Runnable {
	private Socket socket;
	ConcurrentLinkedQueue messageQueue;
	private volatile boolean running;
	private ObjectInputStream objInput;
	private ObjectOutputStream objOutput;
	public ArrayList<LogicalTimeStamp> logMat;
	public ArrayList<VectorTimeStamp> vecMat;
	public boolean logicalTime;
	public boolean log;
	public Connection(Socket slaveSocket, ObjectOutputStream out, ObjectInputStream objInput2, ConcurrentLinkedQueue mq) throws IOException {
		// TODO Auto-generated constructor stub
		socket = slaveSocket;
		objOutput = out;
		objOutput.flush();
		objInput = objInput2;
		running=true;
		log=false;
		messageQueue=mq;
	}
	public Connection(Socket slaveSocket, ObjectOutputStream out, ObjectInputStream objInput2, ArrayList<LogicalTimeStamp> logMat, ArrayList<VectorTimeStamp> vecMat,boolean logicalTime) throws IOException 
	{
		socket = slaveSocket;
		objOutput = out;
		objOutput.flush();
		objInput = objInput2;
		running=true;
		this.logMat=logMat;
		this.vecMat=vecMat;
		this.logicalTime=logicalTime;
		log=true;
		
	}
	public void run() {
		try {
			Message mes;
			while (running) {
				try {
					mes = (Message) objInput.readObject();
					//System.out.println("socket rec: "+mes.toString());
					if(log==false)
						messageQueue.offer(mes);
					else
					{
						if(this.logicalTime)
						{
							
						}
						else
						{
							
						}
					}
					
				} catch (ClassNotFoundException e) {
					System.out.println("read disconnected message");
					return;
				}
				catch(EOFException e)
				{
					System.out.println("detect disconnected message");
					return;
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("message error in handle");

		}
	}
	
}

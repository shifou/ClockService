import java.io.Serializable;


public class Message implements Serializable{
	

	private static final long serialVersionUID = 2190639673375742282L;
	public String hostname;
	public String src;
	public String des;
	public String kind;
	public String data;
	public String action;
	public boolean duplicate = false;
	public int seq;
	public int id;
	public boolean logicalTime;
	public LogicalTimeStamp lt;
	public VectorTimeStamp vt;
	public Message(String hostname,String dest, String action, String kind, String data){
		this.hostname=src=hostname;
		des = dest;
		this.action=action;
		this.kind = kind;
		this.data = data;
	}
	public Message(String hostname,int id, String dest, String action, String kind, String data, VectorTimeStamp vt){
		this.hostname=src=hostname;
		des = dest;
		this.action=action;
		this.kind = kind;
		this.data = data;
		this.vt=vt;
		logicalTime=false;
		this.id=id;
	}
	public void set_hostname(String name){
		this.hostname = name;
	}
	public void set_src(String source){
		this.src = source;
	}
	
	public void set_seqNum(int sequenceNumber){
		this.seq = sequenceNumber;
	}
	
	public void set_duplicate(){
		this.duplicate = true;
	}
	
	public void set_action(String action){
		this.action = action;
	}
	public String toString()
	{
		if(logicalTime)
			return src+" to "+des+" "+seq+" "+lt.toString()+" act: "+action+" "+kind+" dup: "+duplicate+" "+data; 
		else
			return src+" to "+des+" "+seq+" "+vt.toString()+" act: "+action+" "+kind+" dup: "+duplicate+" "+data; 
		
	}

	public String getKind() {
		// TODO Auto-generated method stub
		return kind;
	}
	
}

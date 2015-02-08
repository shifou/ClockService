
public class LogicalTimeStamp {
		private int Logical;

		
		public LogicalTimeStamp(){
			setLogical(0);	
		}

		public int getLogical() {
			return Logical;
		}
		public void setLogical(int logical) {
			Logical = logical;
		}
		
		public void Increment()
		{
			Logical++;
		}
		
		public int issueTimeStamp()
		{
			return Logical+1;
		}
		
		public String Compared(Message eventA, Message eventB)
		{
			if(eventA.lt.getLogical() < eventB.lt.getLogical())
			{
				return "happend before";
			}else{
				return null;
			}
		}
}

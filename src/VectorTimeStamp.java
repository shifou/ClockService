
public class VectorTimeStamp {
		private int[] Vector;
		
		public VectorTimeStamp(int size)
		{
			Vector = new int[size];
		}

		public int[] getVector() {
			return Vector;
		}

		public void setVector(int[] vector) {
			Vector = vector;
		}
		
		public int Increment(int ID) // start from 0
		{
			if(ID < Vector.length)
			{
				Vector[ID] ++;
				return 1;
			}else{
				return -1;
			}
		}
		public String Compared(Message eventA, Message eventB)
		{
			int[] eA = eventA.vt.getVector();
			int[] eB = eventB.vt.getVector();
			int idA = eventA.id;
			int idB = eventB.id;
			
			if((eA[idA] < eB[idA] && eA[idB] > eB[idB])||(eA[idA] > eB[idA] && eA[idB] < eB[idB]))
			{
				return "Concurrent with";
			}else if((eA[idA] < eB[idA] && eA[idB] < eB[idB]))
			{
				return "Happened before";
			}else{
				return null;
			}
		}
		
}

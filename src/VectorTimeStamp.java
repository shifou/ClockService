
public class VectorTimeStamp implements Comparable<VectorTimeStamp> {
		private int[] Vector;
		private int len;
		
		public VectorTimeStamp(int size)
		{
			Vector = new int[size];
			len = size;
		}

		public int[] getVector() {
			return Vector;
		}

		public void setVector(int[] vector) {
			Vector = vector;
		}
		
		public int updateTimeStamp(int currentID, int baseID, VectorTimeStamp base) // start from 0
		{
			if(currentID >= len || baseID >= len)   // ID exceeds limitation
			{
				return -1;
			}
			for(int i = 0; i < len; i++)
			{
				if(currentID == i||baseID == i )
				{
					Vector[i] = Math.max(Vector[i], base.getVector()[i]) + 1;
				}
				else
				{
					Vector[i] = Math.max(Vector[i], base.getVector()[i]);
				}
			}
			return 1;
			
		}
		public int updateTimeStamp(int currentID)
		{
			if(currentID<len)
			{
				Vector[currentID] ++;
				return 1;
			}else
			{
				return -1;			// id exceeds the limitation
			}
		}
		
		public int compareTo(VectorTimeStamp o) {
			// TODO Auto-generated method stub
			int len = Vector.length;
			int counter = 0;
			
			for(int i = 0; i < len; i++)
			{
				if(o.Vector[i] >= Vector[i])
				{
					counter ++;
				}
			}
			if(counter == len)
			{
				return 1; 			// return 1 if happened before
			}
			counter = 0;
			for(int i = 0; i < len; i++)
			{
				if(o.Vector[i] <= Vector[i])
				{
					counter ++;
				}
			}
			if(counter == len)
			{
				return -1;			// return -1 if happened after
			}
			
			return 0;				// return 0 if concurrent
			
		}
		// TODO:  add resize the Vector
}

public class Calculation {
	
	public int fibonnacci(int nth)
	{
		int n1 = 0, n2 = 1, n3 = 0;


		for(int i = 0; i < nth - 1; i++)
		{
			n3 = n1 + n2;
			n1 = n2;
			n2 = n3;
		}
		return n3;
	}

	
}

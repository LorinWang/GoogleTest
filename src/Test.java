
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array=new int[]{1,2,3,4,5};
		StringBuffer sb=new StringBuffer("[");
		for (int i:array)
		{
			sb.append(i+", ");
		}
		int len = sb.length();
		System.out.println(sb.delete(len - 2, len).append("]").toString()) ;

	}

}

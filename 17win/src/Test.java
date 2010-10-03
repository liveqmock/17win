public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		java.util.Random r = new java.util.Random();

		for (int i = 0; i < 10; i++) {

			System.out.println(r.nextInt(3));
		}
	}

}

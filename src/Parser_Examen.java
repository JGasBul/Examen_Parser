public class Parser_Examen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parser parser=new Parser();
		parser.parseFicheroXml("examen.xml");
		parser.parseDocument();
		parser.print();
	}

}

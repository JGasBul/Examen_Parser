import java.util.ArrayList;
import java.util.Iterator;

public class Accion {
	private String nombre_banco;
	private ArrayList<Op>op=null;
	public Accion() {
		// TODO Auto-generated constructor stub
	}
	public Accion(String nombre_banco,ArrayList<Op> op) {
		this.nombre_banco = nombre_banco;
		this.op=op;
	}
	public String getNombre_banco() {
		return nombre_banco;
	}
	public void setNombre_banco(String nombre_banco) {
		this.nombre_banco = nombre_banco;
	}
	public void getOp() {
		Iterator it=op.iterator();
		while(it.hasNext()) {
			Op op=(Op) it.next();
			System.out.println("Movimiento de tipo "+op.getTipo()+" en el banco "+nombre_banco+":\n Con una cantidad de "+op.getCantidad()+" articulos con un precio de "+op.getPrecio()+" euros");
		}
	}
	public void setOp(ArrayList<Op> op) {
		this.op = op;
	}
	
	

}

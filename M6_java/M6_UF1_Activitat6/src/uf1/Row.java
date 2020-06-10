package uf1;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
@XmlRootElement(name = "row")

//@XmlAccessorType(XmlAccessType.FIELD) // This line was added
public class Row {

	// Atributos
	private String _id;
	private String _uuid;
	private String _address;
	private int _position;
	
	// Elementos
	private int any;
	private String universitat;
	private double recursos_captats_via;
	private double recursos_captats_via_no;
	private double recursos_captats_total;
	
	
	
	@XmlAttribute(name="position")
	public int get_position() {
		return _position;
	}
	public void set_position(int _position) {
		this._position = _position;
	}
	
	
	@XmlAttribute(name="id")
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	
	
	@XmlAttribute(name="uuid")
	public String get_uuid() {
		return _uuid;
	}
	public void set_uuid(String _uuid) {
		this._uuid = _uuid;
	}
	
	
	@XmlAttribute(name="address")
	public String get_address() {
		return _address;
	}
	public void set_address(String _address) {
		this._address = _address;
	}
	
	
	@XmlElement(name="any")
	public int getAny() {
		return any;
	}
	public void setAny(int any) {
		this.any = any;
	}
	
	
	@XmlElement(name="universitat")
	public String getUniversitat() {
		return universitat;
	}
	public void setUniversitat(String universitat) {
		this.universitat = universitat;
	}
	
	
	@XmlElement(name="recursos_captats_via")
	public double getRecursos_captats_via() {
		return recursos_captats_via;
	}
	public void setRecursos_captats_via(double recursos_captats_via) {
		this.recursos_captats_via = recursos_captats_via;
	}
	
	
	@XmlElement(name="recursos_captats_via_no")
	public double getRecursos_captats_via_no() {
		return recursos_captats_via_no;
	}
	public void setRecursos_captats_via_no(double recursos_captats_via_no) {
		this.recursos_captats_via_no = recursos_captats_via_no;
	}
	
	
	@XmlElement(name="recursos_captats_total")
	public double getRecursos_captats_total() {
		return recursos_captats_total;
	}
	public void setRecursos_captats_total(double recursos_captats_total) {
		this.recursos_captats_total = recursos_captats_total;
	}
	
	
}

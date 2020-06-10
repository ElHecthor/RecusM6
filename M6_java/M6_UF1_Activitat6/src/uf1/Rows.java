package uf1;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rows {
	
	private Row[] rows;

	public Row[] getRows() {
		return rows;
	}

	public void setRows(Row[] rows) {
		this.rows = rows;
	}
}

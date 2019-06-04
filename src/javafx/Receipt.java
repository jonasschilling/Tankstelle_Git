package javafx;


public class Receipt {
	private String date;
	private String type;
	private String path;

	public Receipt(String date, String type, String path) {
		this.date = date;
		this.type = type;
		this.path = path;

	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

}

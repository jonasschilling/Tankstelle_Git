package javafx;

/**
 * @author Jonas Schilling
 *
 */
public class FuelPump {
	
	private int id;
	private String gasKind;
	private float amountRefilled, priceComp;
	
	public FuelPump (int id, float amountRefilled, float priceComp) {
		this.id = id;
		this.amountRefilled = amountRefilled;
		this.priceComp = priceComp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAmountRefilled() {
		return amountRefilled;
	}

	public void setAmountRefilled(float amountRefilled) {
		this.amountRefilled = amountRefilled;
	}

	public float getPriceComp() {
		return priceComp;
	}

	public void setPriceComp(float priceComp) {
		this.priceComp = priceComp;
	}
	
	public String getGasKind() {
		return gasKind;
	}

	public void setGasKind(String gasKind) {
		this.gasKind = gasKind;
	}

	
	
}
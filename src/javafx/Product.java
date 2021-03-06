package javafx;


/**
 * @author Thore Bedey
 *
 */
public class Product {

     private String name;
     private float priceBuy;
     private float priceSell;
     private int prodNumber;
     private String type;
     private int amount;
     private int maxAmount;

     public Product(int prodNumber, String name, String type, int amount, int maxAmount, float priceBuy) {
          
          this.prodNumber = prodNumber;
          this.name = name;
          this.type = type;
          this.amount = amount;
          this.maxAmount = maxAmount;
          this.priceBuy = priceBuy;


     }

     public String getName() {
          return name;
     }
     public void setName(String name) {
    	 this.name=name;
     }

     public float getPriceBuy() {
          return priceBuy;
     }

     public void setPriceBuy(float priceBuy) {
          this.priceBuy = priceBuy;
     }

     public float getPriceSell() {
          return (float) Math.round(priceSell * 100) / 100;
     }

     public void setPriceSell(float priceSell) {
          this.priceSell = priceSell;
     }
     
     public int getProdNumber() {
          return prodNumber;
     }
     public void setProdNumber(int prodNumber) {
    	 this.prodNumber=prodNumber;
     }
     
     public String getType() {
          return type;
     }
     public void setType(String type) {
    	 this.type=type;
     }

     public int getAmount() {
          return amount;
     }
     
     public void setAmount(int amount) {
          this.amount = amount;
     }
     public int getMaxAmount() {
          return maxAmount;
     }

     public void setMaxAmount(int maxAmount) {
          this.maxAmount = maxAmount;
     }
     
     public void incAmount(int amount) {
          this.amount += amount;
     }

     public void decAmount(int amount) {
          this.amount -= amount;
     }
     //gibt alles Attribute eines Produkts in einem String zurueck, jeweils mit einem Semikolon getrennt.
     public String getAttributes() {
          
          String prodNumber = Integer.toString(getProdNumber());
          String name = getName();
          String type = getType();
          String amount = Integer.toString(getAmount());
          String maxAmount = Integer.toString(getMaxAmount());
          String priceBuy = Float.toString(getPriceBuy());
          String attributes = prodNumber + ";" + name + ";" + type + ";" + amount + ";" + maxAmount + ";" + priceBuy;
          
          return attributes;
     }
     
     public void setAttributes(Product p) {
    	 
    	 
    	 
     }

}

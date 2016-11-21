

package by.zhdanovich.vouch.entity;

public class Cost {
   	private int wholCost;    
    private TypeOfMoney typeMoney;
   
    public enum TypeOfMoney{    	
    	EURO, USD , RUB;
    }

    public int getWholCost() {
        return wholCost;
    }

    public void setWholCost(int value) {
        this.wholCost = value;
    }

   
    public TypeOfMoney getTypeMoney() {
        return typeMoney;
    }

   
    public void setTypeMoney(TypeOfMoney value) {
        this.typeMoney = value;
    }

    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((typeMoney == null) ? 0 : typeMoney.hashCode());
		result = prime * result + wholCost;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cost other = (Cost) obj;
		if (typeMoney != other.typeMoney)
			return false;
		if (wholCost != other.wholCost)
			return false;
		return true;
	}
	@Override
    public String toString(){
    	return "Whol Cost:\n			"+wholCost +"\n		"+ "Type of money:\n			"+ typeMoney+"\n";
    }
}

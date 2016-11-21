package by.zhdanovich.vouch.entity;

public class Food {
    private boolean isFood;   
    private TypeFood typeFood;
    
    public enum TypeFood{
    	AL, BB , HB, 
    }
    
    public boolean isIsFood() {
        return isFood;
    }
    
    public void setIsFood(boolean value) {
        this.isFood = value;
    }
   
    public TypeFood getTypeFood() {
        return typeFood;
    }

   
    public void setTypeFood(TypeFood value) {
        this.typeFood = value;
    }
  
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isFood ? 1231 : 1237);
		result = prime * result + ((typeFood == null) ? 0 : typeFood.hashCode());
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
		Food other = (Food) obj;
		if (isFood != other.isFood)
			return false;
		if (typeFood != other.typeFood)
			return false;
		return true;
	}
	@Override
	public String toString(){
    	return "Is Food:\n				"+isFood+"\n			" + "Type food:\n				"+ typeFood+"\n";
    }
}

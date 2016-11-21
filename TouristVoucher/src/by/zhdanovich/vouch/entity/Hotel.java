package by.zhdanovich.vouch.entity;

public class Hotel {
	
    private String name;   
    private Food food;   
    private TypeRoom typeRoom;    
    private int amountOfStars;
    
    public enum TypeRoom {  
        SINGLE,    DOUBLE,    TRIPLE;    
    }
    
    public Hotel(){
    	food=new Food();
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String value) {
        this.name = value;
    }

     
    public Food getFood() {
        return food;
    }

    
    public void setFood(Food value) {
        this.food = value;
    }

    
    public TypeRoom getTypeRoom() {
        return typeRoom;
    }

    
    public void setTypeRoom(TypeRoom value) {
        this.typeRoom = value;
    }

    
    public int getAmountOfStars() {
        return amountOfStars;
    }

    
    public void setAmountOfStars(Integer value) {
        this.amountOfStars = value;
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amountOfStars;
		result = prime * result + ((food == null) ? 0 : food.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((typeRoom == null) ? 0 : typeRoom.hashCode());
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
		Hotel other = (Hotel) obj;
		if (amountOfStars != other.amountOfStars)
			return false;
		if (food == null) {
			if (other.food != null)
				return false;
		} else if (!food.equals(other.food))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (typeRoom != other.typeRoom)
			return false;
		return true;
	}

	@Override
	public String toString(){
    	return "Name of hotel:\n			"+name +"\n		"+"Food:\n			"+food.toString()+ "\n		"+"Type room:\n			"+ typeRoom+"\n		"+ "Amount of stars:\n			" + amountOfStars+"\n";
    }

}

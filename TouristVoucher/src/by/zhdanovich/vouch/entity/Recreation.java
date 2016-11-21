package by.zhdanovich.vouch.entity;

public class Recreation extends Voucher{
	
    private TypeOfRest typeOfRest;
    
    public enum TypeOfRest{
    BEACH, SKI, EXOTIC, HEALING, INDEFINDED
    }
    
    public TypeOfRest getTypeOfRest() {
       if (typeOfRest == null) {
            return TypeOfRest.INDEFINDED;
        } else {
            return typeOfRest;
        }
    }

    public void setTypeOfRest(TypeOfRest value) {
        this.typeOfRest = value;
    }
    
   
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((typeOfRest == null) ? 0 : typeOfRest.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recreation other = (Recreation) obj;
		if (typeOfRest != other.typeOfRest)
			return false;
		return true;
	}
	@Override
	public String toString(){    	
    		return "Type of rest\n		"+ getTypeOfRest()+"\n" +super.toString();
    	}
    }



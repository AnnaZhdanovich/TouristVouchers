package by.zhdanovich.vouch.entity;


public class Client {   
    private String name;   
    private String numberOfPasport;
   
    public String getName() {
        return name;
    }
    
    public void setName(String value) {
        this.name = value;
    }

    public String getNumberOfPasport() {
        return numberOfPasport;
    }

    public void setNumberOfPasport(String value) {
        this.numberOfPasport = value;
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((numberOfPasport == null) ? 0 : numberOfPasport.hashCode());
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
		Client other = (Client) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numberOfPasport == null) {
			if (other.numberOfPasport != null)
				return false;
		} else if (!numberOfPasport.equals(other.numberOfPasport))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
    	return "Name:\n			"+ name +"\n		"+ "Number of pasport:\n			"+ numberOfPasport+"\n";
    }
}

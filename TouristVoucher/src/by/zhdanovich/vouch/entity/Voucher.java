package by.zhdanovich.vouch.entity;

public abstract class Voucher {    
	private Client client;
	private String country;
	private int amountOfDay;
	private int amountOfNight;
	private Transport transport;
	private Hotel hotel;
	private Cost cost;
	
	public enum Transport {    
	    AVIA,   RAILWAY,  AUTO,    SHIP;   
	}
	
	public Voucher(){
		client=new Client();
		hotel=new Hotel();
		cost=new Cost();		
	}
	
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client value) {
		this.client = value;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String value) {
		this.country = value;
	}

	public int getAmountOfDay() {
		return amountOfDay;
	}

	public void setAmountOfDay(int value) {
		this.amountOfDay = value;
	}

	public int getAmountOfNight() {
		return amountOfNight;
	}

	public void setAmountOfNight(int value) {
		this.amountOfNight = value;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport value) {
		this.transport = value;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel value) {
		this.hotel = value;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost value) {
		this.cost = value;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amountOfDay;
		result = prime * result + amountOfNight;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((hotel == null) ? 0 : hotel.hashCode());
		result = prime * result + ((transport == null) ? 0 : transport.hashCode());
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
		Voucher other = (Voucher) obj;
		if (amountOfDay != other.amountOfDay)
			return false;
		if (amountOfNight != other.amountOfNight)
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (hotel == null) {
			if (other.hotel != null)
				return false;
		} else if (!hotel.equals(other.hotel))
			return false;
		if (transport != other.transport)
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return  "Client:\n		" + client.toString()+"\n"	+"Country:\n		"+ country+"\n"	+	"Amount of day:\n		"+amountOfDay +"\n"+"Amount of night:\n		"+ amountOfNight +"\n"+ "Transport:\n		"+ transport	+"\n"+ "Hotel:\n		" +  hotel.toString() +"\n"+"Cost:\n		" + cost.toString();
	}
	
	
	
}

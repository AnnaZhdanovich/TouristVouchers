package by.zhdanovich.vouch.entity;

public class ExcoursionVoucher extends Voucher {

	private int amountOfExcoursion;

	public int getAmountOfExcoursion() {
		return amountOfExcoursion;
	}

	public void setAmountOfExcoursion(int value) {
		this.amountOfExcoursion = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + amountOfExcoursion;
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
		ExcoursionVoucher other = (ExcoursionVoucher) obj;
		if (amountOfExcoursion != other.amountOfExcoursion)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + "Amount of Excoursion\n		 " + amountOfExcoursion;
	}
}

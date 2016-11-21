package by.zhdanovich.vouch.builder;
import by.zhdanovich.vouch.entity.TouristVouchers;

public abstract class AbstractVoucherBuilder {
	protected TouristVouchers vouchers;
	
	public AbstractVoucherBuilder() { 		
	} 
	
	abstract public void buildSetVouchers(String fileName);
}

package by.zhdanovich.vouch.entity;


import java.util.HashSet;

import java.util.Set;

public class TouristVouchers { 
	
    private Set< Voucher> voucher ;
    
    public TouristVouchers(){
      voucher=new HashSet< Voucher>();
    }
    
    public Set< Voucher> getVoucher() {
      return this.voucher;
    }

}

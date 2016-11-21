package by.zhdanovich.vouch.entity;

import by.zhdanovich.vouch.exception.WrongDataException;

public enum VoucherEnum {
	TOURIST_VOUCHERS("touristVouchers"),
	EXCOURSION_VOUCHERS("excoursionVoucher"),
	RECREATION("recreation"),	
	CLIENT("client"),
	HOTEL("hotel"),
	FOOD("food"),	
	TYPE_FOOD("typeFood"),
	TYPE_MONEY("typeMoney"),
	AMOUNT_OF_STARS("amountOfStars"),
	NUMBER_OF_PASPORT("numberOfPasport"),
	TYPE_OF_REST("typeOfRest"),
	COST ("cost"),	
	TRANSPORT("transport"),
	TYPE_ROOM("typeRoom"),
	WHOL_COST("wholCost"),	
	NAME("name"),
	COUNTRY("country"),
	AMOUNT_OF_DAY("amountOfDay"),
	AMOUNT_OF_NIGHT("amountOfNight"),	
	NAME_OF_HOTEL("nameOfHotel"),	
	IS_FOOD("isFood"),	
	AMOUNT_OF_EXCOURSION("amountOfExcoursion");
	
	
	 private String value;
	 
	 private VoucherEnum(String value) {		 
		 this.value = value;  
	 } 
	 
	 public String getValue() { 
		 return value;   
	 } 
	 
	 public static VoucherEnum fromValue(String v) throws WrongDataException {
         for (VoucherEnum c: VoucherEnum.values()) {
             if (c.value.equals(v)) {
                 return c;
             }
         }
         throw new WrongDataException("Input data is not correct");
        }
}

package by.zhdanovich.vouch.builder;

public class VoucherBuilderFactory {
	private enum TypeParser { 
		SAX, STAX, DOM;
		} 
	public AbstractVoucherBuilder createStudentBuilder(String typeParser) {
		TypeParser type = TypeParser.valueOf(typeParser.toUpperCase()); 
		switch (type) {  
		case DOM:
			return new VoucherDOMBuilder();  
		case STAX: 
			return new VoucherStAXBuilder(); 
		case SAX: 
			return new VoucherSAXBuilder();  
	 default: 
		 throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
	}
	}

}

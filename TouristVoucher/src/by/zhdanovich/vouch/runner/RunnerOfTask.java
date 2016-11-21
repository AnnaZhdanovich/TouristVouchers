package by.zhdanovich.vouch.runner;

import by.zhdanovich.vouch.builder.AbstractVoucherBuilder;
import by.zhdanovich.vouch.builder.VoucherBuilderFactory;
import by.zhdanovich.vouch.validator.Validation;

public class RunnerOfTask {
	public final static String FILE = "xmlData/touristVouchers.xml";
	public final static String SCHEMA = "xmlData/voucher.xsd";
	public final static String STAX = "stax";
	public final static String SAX = "sax";
	public final static String DOM = "dom";

	public static void main(String[] args) {
		Validation validator = new Validation();
		validator.toValidate(FILE, SCHEMA);
		VoucherBuilderFactory sFactory = new VoucherBuilderFactory();
		AbstractVoucherBuilder builderStAX = sFactory.createStudentBuilder(STAX);
		AbstractVoucherBuilder builderSAX = sFactory.createStudentBuilder(SAX);
		AbstractVoucherBuilder builderDOM = sFactory.createStudentBuilder(DOM);
		builderStAX.buildSetVouchers(FILE);
		builderSAX.buildSetVouchers(FILE);		
		builderDOM.buildSetVouchers(FILE);

	}
}
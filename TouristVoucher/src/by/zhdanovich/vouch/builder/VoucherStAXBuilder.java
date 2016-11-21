package by.zhdanovich.vouch.builder;

import javax.xml.stream.XMLInputFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.zhdanovich.vouch.analyzer.stax.VouchersStAXParser;
import by.zhdanovich.vouch.entity.Voucher;

public class VoucherStAXBuilder extends AbstractVoucherBuilder {	
	private static Logger log = LogManager.getLogger(VoucherStAXBuilder.class);
	private XMLInputFactory inputFactory; 
	
	public VoucherStAXBuilder() {
		inputFactory = XMLInputFactory.newInstance();
		}
	
	@Override 
	public void buildSetVouchers(String fileName) {
		VouchersStAXParser staxBuilder = new VouchersStAXParser();
		this.vouchers = staxBuilder.buildSetVouchers(fileName, inputFactory);	
		
		for(Voucher v: vouchers.getVoucher() ){
			log.info("Parsing of StAX\n"+ v.toString());
	    }
		}
	}


package by.zhdanovich.vouch.builder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.zhdanovich.vouch.analyzer.dom.VouchersDOMParser;
import by.zhdanovich.vouch.entity.Voucher;


public class VoucherDOMBuilder extends AbstractVoucherBuilder {
	private static Logger log = LogManager.getLogger(VoucherDOMBuilder.class);
    private DocumentBuilder docBuilder; 
	
	public VoucherDOMBuilder() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		   try { 
			   docBuilder = factory.newDocumentBuilder();
			   } catch (ParserConfigurationException e) {
				   log.fatal("Mistake of confuration of parser",e);
				   throw new RuntimeException();
			   }
		} 
	
		@Override
		public void buildSetVouchers(String fileName) { 
			VouchersDOMParser domBuilder = new VouchersDOMParser();
			this.vouchers = domBuilder.buildSetVouchers(fileName, docBuilder); 
			for(Voucher v: vouchers.getVoucher() ){
				 log.info("Parsing of DOM\n"+ v.toString());
			}
		}
		
	}


package by.zhdanovich.vouch.builder;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.zhdanovich.vouch.analyzer.sax.VoucherHandler;
import by.zhdanovich.vouch.entity.Voucher;

public class VoucherSAXBuilder extends AbstractVoucherBuilder {
	private static Logger log = LogManager.getLogger(VoucherSAXBuilder.class);
	private VoucherHandler vh;
	private XMLReader reader;

	public VoucherSAXBuilder() {
		vh = new VoucherHandler();
		try {
			reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(vh);
		} catch (SAXException e) {
			log.fatal("Parsing failure: ", e);
			throw new RuntimeException();
		}
	}

	@Override
	public void buildSetVouchers(String fileName) {
		try {
			reader.parse(fileName);
		} catch (SAXException e) {
			log.fatal("Parsing failure: ", e);
			throw new RuntimeException();
		} catch (IOException e) {
			log.fatal("File error or I/O error: ", e);
			throw new RuntimeException();
		}
		this.vouchers =  vh.getVouchers();
		
		for(Voucher v: vouchers.getVoucher()){
			log.info("Parsing of SAX\n"+ v.toString());
	    }
	}
}

package by.zhdanovich.vouch.validator;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

public class Validation {
	private static Logger log = LogManager.getLogger(Validation.class);

	public void toValidate(String fileName, String schemaName) {
		String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		SchemaFactory factory = SchemaFactory.newInstance(language);
		File schemaLocation = new File(schemaName);
		try {
			Schema schema = factory.newSchema(schemaLocation);
			Validator validator = schema.newValidator();
			Source source = new StreamSource(fileName);
			validator.validate(source);			
			log.info(fileName + " is valid.");
		} catch (SAXException e) {
			log.fatal("validation " + fileName + " is not valid because " + e.getMessage());
			throw new RuntimeException();
		} catch (IOException e) {
			log.fatal(fileName + " is not valid because " + e.getMessage());
			throw new RuntimeException();
		}
	}
}

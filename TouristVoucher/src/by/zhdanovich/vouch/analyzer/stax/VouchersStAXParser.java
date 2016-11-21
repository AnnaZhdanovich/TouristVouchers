package by.zhdanovich.vouch.analyzer.stax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.zhdanovich.vouch.entity.Cost.TypeOfMoney;
import by.zhdanovich.vouch.entity.ExcoursionVoucher;
import by.zhdanovich.vouch.entity.Food.TypeFood;
import by.zhdanovich.vouch.entity.Hotel.TypeRoom;
import by.zhdanovich.vouch.entity.Recreation;
import by.zhdanovich.vouch.entity.TouristVouchers;
import by.zhdanovich.vouch.entity.Voucher;
import by.zhdanovich.vouch.entity.Voucher.Transport;
import by.zhdanovich.vouch.entity.VoucherEnum;
import by.zhdanovich.vouch.exception.WrongDataException;
import by.zhdanovich.vouch.entity.Recreation.TypeOfRest;

public class VouchersStAXParser {
	private static Logger log = LogManager.getLogger(VouchersStAXParser.class);
	private TouristVouchers vouchers;
	private Voucher voucher = null;

	public VouchersStAXParser() {
		vouchers = new TouristVouchers();
	}

	public TouristVouchers getVouchers() {
		return vouchers;
	}

	public TouristVouchers buildSetVouchers(String fileName, XMLInputFactory inputFactory) {
		FileInputStream inputStream = null;
		XMLStreamReader reader = null;
		String name;
		try {
			inputStream = new FileInputStream(new File(fileName));
			reader = inputFactory.createXMLStreamReader(inputStream);
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					try {
						name = reader.getLocalName();
						if (name.equals(VoucherEnum.EXCOURSION_VOUCHERS.getValue())) {
							voucher = new ExcoursionVoucher();
							buildVoucher(reader);
						}
						if (name.equals(VoucherEnum.RECREATION.getValue())) {
							voucher = new Recreation();
							buildVoucher(reader);
						}
					} catch (WrongDataException e) {
						log.error("Input data is not correct", e);
					}
				}
			}
		} catch (XMLStreamException e) {
			log.error("StAX parsing error!", e);
			throw new RuntimeException();
		} catch (FileNotFoundException e) {
			log.fatal("File  error: ", e);
			throw new RuntimeException();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				log.error("Impossible close file " + fileName + " : " + e);
			}
		}
		return vouchers;
	}

	private void buildVoucher(XMLStreamReader reader) throws XMLStreamException, WrongDataException {
		if (voucher instanceof Recreation) {
			if (reader.getAttributeValue(null, VoucherEnum.TYPE_OF_REST.getValue()) == null) {
				((Recreation) voucher).setTypeOfRest(TypeOfRest.INDEFINDED);
			} else {
				((Recreation) voucher).setTypeOfRest(TypeOfRest
						.valueOf(reader.getAttributeValue(null, VoucherEnum.TYPE_OF_REST.getValue()).toUpperCase()));
			}
		}
		String name;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (VoucherEnum.fromValue(name)) {
				case CLIENT:
					voucher.getClient().setNumberOfPasport(
							reader.getAttributeValue(null, VoucherEnum.NUMBER_OF_PASPORT.getValue()));

					getXMLClient(reader);

					break;
				case COUNTRY:
					voucher.setCountry(getXMLText(reader));

					break;
				case AMOUNT_OF_DAY:
					name = getXMLText(reader);
					voucher.setAmountOfDay(Integer.parseInt(name));

					break;
				case AMOUNT_OF_NIGHT:
					name = getXMLText(reader);
					voucher.setAmountOfNight(Integer.parseInt(name));

					break;
				case HOTEL:
					voucher.getHotel().setAmountOfStars(
							Integer.parseInt(reader.getAttributeValue(null, VoucherEnum.AMOUNT_OF_STARS.getValue())));

					getXMLHotel(reader);
					break;
				case TRANSPORT:
					voucher.setTransport(Transport.valueOf(getXMLText(reader).toUpperCase()));

					break;
				case COST:
					voucher.getCost().setTypeMoney(TypeOfMoney
							.valueOf(reader.getAttributeValue(null, VoucherEnum.TYPE_MONEY.getValue()).toUpperCase()));

					getXMLCost(reader);
					break;
				case AMOUNT_OF_EXCOURSION:
					if (voucher instanceof ExcoursionVoucher) {
						((ExcoursionVoucher) voucher).setAmountOfExcoursion(Integer.parseInt(getXMLText(reader)));
					}
					break;
				default:
					throw new WrongDataException("Input data is not correct");
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (name.equals(VoucherEnum.EXCOURSION_VOUCHERS.getValue())
						|| (name.equals(VoucherEnum.RECREATION.getValue()))) {

					vouchers.getVoucher().add(voucher);
					voucher = null;
					return;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag Student");
	}

	private void getXMLClient(XMLStreamReader reader) throws XMLStreamException {
		int type;
		String name;
		while (reader.hasNext()) {
			type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();

				if (name.equals(VoucherEnum.NAME.getValue())) {
					voucher.getClient().setName(getXMLText(reader));
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (name.equals(VoucherEnum.NAME.getValue())) {
					return;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag Client");
	}

	private void getXMLHotel(XMLStreamReader reader) throws XMLStreamException, WrongDataException {
		int type;
		String name;
		while (reader.hasNext()) {
			type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (VoucherEnum.fromValue(name)) {
				case NAME_OF_HOTEL:
					voucher.getHotel().setName(getXMLText(reader));

					break;
				case FOOD:
					if ((reader.getAttributeValue(null, VoucherEnum.TYPE_FOOD.getValue()) != null)) {
						voucher.getHotel().getFood().setTypeFood(TypeFood.valueOf(
								reader.getAttributeValue(null, VoucherEnum.TYPE_FOOD.getValue()).toUpperCase()));

					}
					getXMLFood(reader);
					break;
				case TYPE_ROOM:
					voucher.getHotel().setTypeRoom(TypeRoom.valueOf(getXMLText(reader).toUpperCase()));

					break;
				default:
					throw new WrongDataException("Input data is not correct");

				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (name.endsWith(VoucherEnum.HOTEL.getValue())) {
					return;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag Hotel");
	}

	private void getXMLFood(XMLStreamReader reader) throws XMLStreamException {
		int type;
		String name;
		while (reader.hasNext()) {
			type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				if (name.equals(VoucherEnum.IS_FOOD.getValue())) {
					voucher.getHotel().getFood().setIsFood(Boolean.valueOf(getXMLText(reader)));

				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (name.equals(VoucherEnum.IS_FOOD.getValue())) {
					return;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag Food");
	}

	private void getXMLCost(XMLStreamReader reader) throws XMLStreamException {
		int type;
		String name;
		while (reader.hasNext()) {
			type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				if (name.equals(VoucherEnum.WHOL_COST.getValue())) {
					voucher.getCost().setWholCost(Integer.parseInt(getXMLText(reader)));
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (name.equals(VoucherEnum.WHOL_COST.getValue())) {
					return;

				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag Cost");
	}

	private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
		String text = null;
		if (reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		return text;
	}
}

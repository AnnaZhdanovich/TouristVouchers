package by.zhdanovich.vouch.analyzer.dom;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import by.zhdanovich.vouch.entity.Cost.TypeOfMoney;
import by.zhdanovich.vouch.entity.ExcoursionVoucher;
import by.zhdanovich.vouch.entity.Food.TypeFood;
import by.zhdanovich.vouch.entity.Recreation;
import by.zhdanovich.vouch.entity.Recreation.TypeOfRest;
import by.zhdanovich.vouch.entity.TouristVouchers;
import by.zhdanovich.vouch.entity.Voucher;
import by.zhdanovich.vouch.entity.Voucher.Transport;
import by.zhdanovich.vouch.entity.VoucherEnum;
import by.zhdanovich.vouch.exception.WrongDataException;

public class VouchersDOMParser {
	private static Logger log = LogManager.getLogger(VouchersDOMParser.class);
	private TouristVouchers vouchers;

	public VouchersDOMParser() {
		vouchers = new TouristVouchers();
	}

	public TouristVouchers getVouchers() {
		return vouchers;
	}

	public TouristVouchers buildSetVouchers(String fileName, DocumentBuilder docBuilder) {
		Document doc = null;
		try {
			doc = docBuilder.parse(fileName);
			Element root = doc.getDocumentElement();
			NodeList excoursionVoucherList = root.getElementsByTagName(VoucherEnum.EXCOURSION_VOUCHERS.getValue());
			NodeList recreationList = root.getElementsByTagName(VoucherEnum.RECREATION.getValue());
			getSetVouchers(excoursionVoucherList);
			getSetVouchers(recreationList);
		} catch (IOException e) {
			log.fatal("File error or I/O error: ", e);
			throw new RuntimeException();

		} catch (SAXException e) {
			log.fatal("Parsing failure: ", e);
			throw new RuntimeException();
		}
		return vouchers;
	}

	private void getSetVouchers(NodeList list) {
		try {
			for (int i = 0; i < list.getLength(); i++) {
				Element voucherElement = (Element) list.item(i);
				Voucher voucher = buildStudent(voucherElement);
				vouchers.getVoucher().add(voucher);
			}
		} catch (WrongDataException e) {
			log.error("Input data is wrong", e);
		}

	}

	private Voucher buildStudent(Element voucherElement) throws WrongDataException {
		Voucher voucher = null;
		if (voucherElement.getTagName().equals(VoucherEnum.EXCOURSION_VOUCHERS.getValue())) {
			voucher = new ExcoursionVoucher();
			Integer amount = Integer
					.parseInt(getElementTextContent(voucherElement, VoucherEnum.AMOUNT_OF_EXCOURSION.getValue()));
			((ExcoursionVoucher) voucher).setAmountOfExcoursion(amount);
		} else {
			if (voucherElement.getTagName().equals(VoucherEnum.RECREATION.getValue())) {
				voucher = new Recreation();
				if (!(voucherElement.getAttribute(VoucherEnum.TYPE_OF_REST.getValue()).isEmpty())) {
					((Recreation) voucher).setTypeOfRest(TypeOfRest
							.valueOf(voucherElement.getAttribute(VoucherEnum.TYPE_OF_REST.getValue()).toUpperCase()));
				}
			} else {
				throw new WrongDataException("No correct input data");
			}
		}
		Element clientElement = (Element) voucherElement.getElementsByTagName(VoucherEnum.CLIENT.getValue()).item(0);
		voucher.getClient().setNumberOfPasport(voucherElement.getAttribute(VoucherEnum.NUMBER_OF_PASPORT.getValue()));
		voucher.getClient().setName(getElementTextContent(clientElement, VoucherEnum.NAME.getValue()));
		voucher.setCountry(getElementTextContent(voucherElement, VoucherEnum.COUNTRY.getValue()));
		Integer amountOfDay = Integer
				.parseInt(getElementTextContent(voucherElement, VoucherEnum.AMOUNT_OF_DAY.getValue()));
		voucher.setAmountOfDay(amountOfDay);
		Integer amountOfNight = Integer
				.parseInt(getElementTextContent(voucherElement, VoucherEnum.AMOUNT_OF_NIGHT.getValue()));
		voucher.setAmountOfNight(amountOfNight);
		voucher.setTransport(Transport
				.valueOf(getElementTextContent(voucherElement, VoucherEnum.TRANSPORT.getValue()).toUpperCase()));
		Element hotelElement = (Element) voucherElement.getElementsByTagName(VoucherEnum.HOTEL.getValue()).item(0);
		Integer amountOfStars = Integer.parseInt(hotelElement.getAttribute(VoucherEnum.AMOUNT_OF_STARS.getValue()));
		voucher.getHotel().setAmountOfStars(amountOfStars);
		voucher.getHotel().setName(getElementTextContent(hotelElement, VoucherEnum.NAME_OF_HOTEL.getValue()));
		Element foodElement = (Element) hotelElement.getElementsByTagName(VoucherEnum.FOOD.getValue()).item(0);
		if (!(foodElement.getAttribute(VoucherEnum.TYPE_FOOD.getValue())).isEmpty()) {
			voucher.getHotel().getFood().setTypeFood(
					TypeFood.valueOf(foodElement.getAttribute(VoucherEnum.TYPE_FOOD.getValue()).toUpperCase()));
		}
		voucher.getHotel().getFood()
				.setIsFood(Boolean.valueOf(getElementTextContent(voucherElement, VoucherEnum.IS_FOOD.getValue())));
		Element costElement = (Element) voucherElement.getElementsByTagName(VoucherEnum.COST.getValue()).item(0);
		Integer wholCost = Integer.parseInt(getElementTextContent(costElement, VoucherEnum.WHOL_COST.getValue()));
		voucher.getCost().setWholCost(wholCost);
		voucher.getCost().setTypeMoney(
				TypeOfMoney.valueOf(costElement.getAttribute(VoucherEnum.TYPE_MONEY.getValue()).toUpperCase()));
		return voucher;
	}

	private static String getElementTextContent(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}
}

package by.zhdanovich.vouch.analyzer.sax;

import java.util.EnumSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import by.zhdanovich.vouch.entity.ExcoursionVoucher;
import by.zhdanovich.vouch.entity.Food.TypeFood;
import by.zhdanovich.vouch.entity.Hotel.TypeRoom;
import by.zhdanovich.vouch.entity.Recreation;

import by.zhdanovich.vouch.entity.Recreation.TypeOfRest;
import by.zhdanovich.vouch.entity.TouristVouchers;
import by.zhdanovich.vouch.entity.Voucher;
import by.zhdanovich.vouch.entity.Voucher.Transport;
import by.zhdanovich.vouch.entity.VoucherEnum;
import by.zhdanovich.vouch.exception.WrongDataException;
import by.zhdanovich.vouch.entity.Cost.TypeOfMoney;

public class VoucherHandler extends DefaultHandler {
	private static Logger log = LogManager.getLogger(VoucherHandler.class);
	private TouristVouchers vouchers;
	private Voucher current = null;
	private VoucherEnum simpleEnum = null;
	private VoucherEnum complexEnum = null;
	private EnumSet<VoucherEnum> simpleText;
	private EnumSet<VoucherEnum> complexText;

	public VoucherHandler() {
		vouchers = new TouristVouchers();
		simpleText = EnumSet.range(VoucherEnum.TRANSPORT, VoucherEnum.AMOUNT_OF_EXCOURSION);
		complexText = EnumSet.range(VoucherEnum.CLIENT, VoucherEnum.COST);
	}

	public TouristVouchers getVouchers() {
		return vouchers;
	}

	public void startElement(String uri, String localName, String qName, Attributes attrs) {
		try {
			if (VoucherEnum.EXCOURSION_VOUCHERS.getValue().equals(localName)) {
				current = new ExcoursionVoucher();
			} else {
				if ((VoucherEnum.RECREATION.getValue().equals(localName))) {
					current = new Recreation();
					if (current instanceof Recreation) {
						if (attrs.getValue(0) != null) {
							((Recreation) current).setTypeOfRest(TypeOfRest.valueOf(attrs.getValue(0).toUpperCase()));
						}
					}
				} else {
					VoucherEnum temp;

					temp = VoucherEnum.fromValue(localName);
					if (complexText.contains(temp)) {
						complexEnum = temp;
					}
					if (complexEnum != null) {
						switch (complexEnum) {
						case CLIENT:
							current.getClient().setNumberOfPasport(attrs.getValue(0));
							break;
						case HOTEL:
							if (attrs.getValue(0) != null) {
								current.getHotel().setAmountOfStars(Integer.parseInt(attrs.getValue(0)));
							}
							break;
						case FOOD:
							if (attrs.getValue(0) != null) {
								current.getHotel().getFood()
										.setTypeFood(TypeFood.valueOf(attrs.getValue(0).toUpperCase()));
							}
							break;
						case COST:
							current.getCost().setTypeMoney(TypeOfMoney.valueOf(attrs.getValue(0).toUpperCase()));
							break;
						default:
							log.error("Input data is not correct");
							throw new EnumConstantNotPresentException(complexEnum.getDeclaringClass(),
									complexEnum.name());
						}
						complexEnum = null;
					}

					if (simpleText.contains(temp)) {
						simpleEnum = temp;
					}
				}
			}
		} catch (WrongDataException e) {
			log.error("Input data is wrong", e);
		}
	}

	public void endElement(String uri, String localName, String qName) {

		if ("excoursionVoucher".equals(localName)) {
			vouchers.getVoucher().add(current);

		}
		if ("recreation".equals(localName)) {
			vouchers.getVoucher().add(current);

		}
	}

	public void characters(char[] ch, int start, int length) {
		String s = new String(ch, start, length).trim();
		if (simpleEnum != null) {
			switch (simpleEnum) {
			case TRANSPORT:
				current.setTransport(Transport.valueOf(s.toUpperCase()));
				break;
			case TYPE_ROOM:
				current.getHotel().setTypeRoom(TypeRoom.valueOf(s.toUpperCase()));
				break;
			case WHOL_COST:
				current.getCost().setWholCost(Integer.parseInt(s));
				break;
			case NAME:
				current.getClient().setName(s);
				break;
			case COUNTRY:
				current.setCountry(s);
				break;
			case AMOUNT_OF_DAY:
				current.setAmountOfDay(Integer.parseInt(s));
				break;
			case AMOUNT_OF_NIGHT:
				current.setAmountOfNight(Integer.parseInt(s));
				break;
			case NAME_OF_HOTEL:
				current.getHotel().setName(s);
				break;
			case IS_FOOD:
				current.getHotel().getFood().setIsFood(Boolean.valueOf(s));
				break;
			case AMOUNT_OF_EXCOURSION:
				if (current instanceof ExcoursionVoucher) {
					((ExcoursionVoucher) current).setAmountOfExcoursion(Integer.parseInt(s));
				}
				break;
			default:
				log.error("Input data is not correct");
				throw new EnumConstantNotPresentException(simpleEnum.getDeclaringClass(), simpleEnum.name());
			}
			simpleEnum = null;
		}
	}

}

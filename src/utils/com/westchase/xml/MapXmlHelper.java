package com.westchase.xml;

import java.io.StringWriter;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.westchase.persistence.model.Company;
import com.westchase.persistence.model.Property;
import com.westchase.utils.HtmlUtils;

/**
 * @author marc
 *
 */
public class MapXmlHelper {
	
	private static final Log log = LogFactory.getLog(MapXmlHelper.class);
	
	private static final String DOCUMENT_MARKERS = "markers";
	private static final String ELEMENT_MARKER = "marker";
	private static final String ATTRIBUTE_NAME = "name";
	private static final String ATTRIBUTE_ADDRESS = "address";
//	private static final String ATTRIBUTE_DESCRIPTION = "description";
	private static final String ATTRIBUTE_LATITUDE = "lat";
	private static final String ATTRIBUTE_LONGITUDE = "lng";
	private static final String ATTRIBUTE_CATEGORY = "category";
	
	private static final String SPACE = " ";
	
	private static final String fixCharacters(String xml) {
		if (StringUtils.isBlank(xml)) {
			return null;
		}
		String fixed = StringUtils.replace(xml, "'", "&#8217;");
		fixed = StringUtils.replace(fixed, "&", "&amp;");
		return fixed;
	}
	
	public static final String propertiesToMapXml(List<Property> properties) {
		String xml = null;
		
		Element markers = new Element(DOCUMENT_MARKERS);
		Document document = new Document(markers);
		
		if (properties != null && !properties.isEmpty()) {
			for (Property property : properties) {
				Element marker = new Element(ELEMENT_MARKER);
				
				marker.setAttribute(new Attribute(ATTRIBUTE_NAME, fixCharacters(property.getBuildingName())));

				marker.setAttribute(new Attribute(ATTRIBUTE_ADDRESS, getAddress(property)));

//				marker.setAttribute(new Attribute(ATTRIBUTE_DESCRIPTION, fixCharacters(getDescription(property))));

				marker.setAttribute(new Attribute(ATTRIBUTE_LATITUDE, property.getLatitude()));
				marker.setAttribute(new Attribute(ATTRIBUTE_LONGITUDE, property.getLongitude()));

				if (property.getPropertyType() != null) {
					marker.setAttribute(new Attribute(ATTRIBUTE_CATEGORY, fixCharacters(property.getPropertyType().getName())));
				}
				markers.addContent(marker);
			}
			
		}
		
		try {
			StringWriter writer = new StringWriter();
			XMLOutputter output = new XMLOutputter();
			output.output(document, writer);
			xml = writer.toString();
		} catch (Exception e) {
			log.error("Error generating XML", e);
		}
		
		
		return xml;
	}
	
	private static final String getAddress(Property property) {
		StringBuilder address = new StringBuilder();
		address.append(fixCharacters(property.getGeoNumber())).append(SPACE)
			   .append(fixCharacters(WordUtils.capitalizeFully(property.getGeoAddress())))
			   .append(HtmlUtils.BREAK_ENCODED)
			   .append(fixCharacters(WordUtils.capitalizeFully(property.getGeoCity())))
			   .append(",").append(SPACE).append(fixCharacters(property.getGeoState())).append(SPACE).
			   append(fixCharacters(property.getGeoZipCode()));
		return address.toString();
	}

	public static String companiesToMapXml(List<Company> companies) {
		String xml = null;
		
		Element markers = new Element(DOCUMENT_MARKERS);
		Document document = new Document(markers);
		
		if (companies != null && !companies.isEmpty()) {
			for (Company company : companies) {
				Element marker = new Element(ELEMENT_MARKER);
				
				marker.setAttribute(new Attribute(ATTRIBUTE_NAME, fixCharacters(company.getCompany())));

				marker.setAttribute(new Attribute(ATTRIBUTE_ADDRESS, getAddress(company)));

//				marker.setAttribute(new Attribute(ATTRIBUTE_DESCRIPTION, fixCharacters(getDescription(property))));

				marker.setAttribute(new Attribute(ATTRIBUTE_LATITUDE, company.getLatitude()));
				marker.setAttribute(new Attribute(ATTRIBUTE_LONGITUDE, company.getLongitude()));

				if (company.getCompanyType() != null) {
					marker.setAttribute(new Attribute(ATTRIBUTE_CATEGORY, fixCharacters(company.getCompanyType().getName())));
				}
				markers.addContent(marker);
			}
			
		}
		
		try {
			StringWriter writer = new StringWriter();
			XMLOutputter output = new XMLOutputter();
			output.output(document, writer);
			xml = writer.toString();
		} catch (Exception e) {
			log.error("Error generating XML", e);
		}
		
		
		return xml;
	}
	
	private static final String getAddress(Company company) {
		StringBuilder address = new StringBuilder();
		address.append(fixCharacters(company.getStNumber())).append(SPACE).append(fixCharacters(WordUtils.capitalizeFully(company.getStAddress())));
		String roomNo = company.getRoomNo();
		if (StringUtils.isNotBlank(roomNo)) {
			address.append(SPACE).append(fixCharacters(roomNo));
		}
		address.append(HtmlUtils.BREAK_ENCODED).append(fixCharacters(WordUtils.capitalizeFully(company.getCity())))
			.append(",").append(SPACE).append(fixCharacters(company.getState())).append(SPACE).append(fixCharacters(company.getZipCode()));
		return address.toString();
	}
	
//	private static final String getDescription(Property property) {
//		StringBuilder desc = new StringBuilder();
//		
//		return desc.toString();
//	}
	
}

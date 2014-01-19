package com.westchase.web.action.cms;

import org.apache.commons.lang.StringUtils;

import com.westchase.ejb.PropertyService;
import com.westchase.persistence.model.Property;
import com.westchase.persistence.model.PropertyHcad;
import com.westchase.utils.ejb.ServiceLocator;
import com.westchase.web.action.AbstractWestchaseAction;

public class PropertyHcadAction extends AbstractWestchaseAction {

	private Integer id;
	private Integer propertyId;
	private PropertyHcad propertyHcad;
	
	public PropertyHcadAction() {
		super();
	}
	
	public String edit() {
		if (id != null) {
			PropertyService propServ = ServiceLocator.lookupPropertyService();
			if (propServ != null) {
				propertyHcad = propServ.getPropertyHcad(id);
			}
		} else if (propertyId != null) {
			propertyHcad = new PropertyHcad();
			propertyHcad.setProperty(new Property(propertyId));
		}
		return SUCCESS;
	}
	
	public String save() {
		if (propertyHcad != null) {
			if (StringUtils.isBlank(propertyHcad.getHcad())) {
				addActionError("HCAD is required.");
			} else {
				PropertyService propServ = ServiceLocator.lookupPropertyService();
				if (propServ != null) {
					try {
						id = propServ.saveOrUpdateHcad(propertyHcad);
			        	addActionMessage("Saved hcad #" + id);
			        	setPropertyId(propertyHcad.getProperty().getId());
					} catch (Exception e) {
						addActionError("Error saving hcad.");
						log.error("", e);
					}
				}
			}
		}
		return SUCCESS;
	}
	
	public String delete() {
		if (id != null) {
			PropertyService propServ = ServiceLocator.lookupPropertyService();
			if (propServ != null) {
				setPropertyId(propServ.getPropertyIdForHcad(id));
				if (propServ.deletePropertyHcad(id)) {
		        	getRequest().getSession(true).setAttribute("WCActionMessage", "Deleted hcad #" + id);
				}
			}
		}
		return SUCCESS;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public PropertyHcad getPropertyHcad() {
		return propertyHcad;
	}

	public void setPropertyHcad(PropertyHcad propertyHcad) {
		this.propertyHcad = propertyHcad;
	}

}

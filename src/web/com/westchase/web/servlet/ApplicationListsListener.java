package com.westchase.web.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.westchase.ejb.CompanyService;
import com.westchase.ejb.PropertyService;
import com.westchase.persistence.model.State;
import com.westchase.web.bean.KeyValue;

/**
 * @author marc
 *
 */
public class ApplicationListsListener implements ServletContextListener {
	
	private static Log log = LogFactory.getLog(ApplicationListsListener.class);

    private PropertyService propServ;
    private CompanyService compServ;
    
	@Override
	public void contextDestroyed(ServletContextEvent context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		List<KeyValue> availableBusinessTypes = new ArrayList<KeyValue>();
        availableBusinessTypes.add(new KeyValue("Development Site", "Development Site"));
        availableBusinessTypes.add(new KeyValue("Hotel", "Hotel"));
        availableBusinessTypes.add(new KeyValue("Industrial", "Industrial"));
        availableBusinessTypes.add(new KeyValue("Multi-Family: Apartment", "Multi-Family: Apartment"));
        availableBusinessTypes.add(new KeyValue("Multi-Family: Condos/Townhomes", "Multi-Family: Condos/Townhomes"));
        availableBusinessTypes.add(new KeyValue("Non-Commercial", "Non-Commercial"));
        availableBusinessTypes.add(new KeyValue("Office", "Office"));
        availableBusinessTypes.add(new KeyValue("Public", "Public"));
        availableBusinessTypes.add(new KeyValue("Retail Center", "Retail Center"));
        availableBusinessTypes.add(new KeyValue("Retail Single", "Retail Single"));
        availableBusinessTypes.add(new KeyValue("Service Center", "Service Center"));
        context.getServletContext().setAttribute("availableBusinessTypes", availableBusinessTypes);
        
        try {
            InitialContext ctx = new InitialContext();
            propServ = (PropertyService) ctx.lookup("westchase/PropertyServiceBean/local");
            compServ = (CompanyService) ctx.lookup("westchase/CompanyServiceBean/local");
        } catch (Exception e) {
            log.error("", e); 
        }         	

    	if (compServ != null) {
    		List<State> availableStates = compServ.findAllStates();
    		context.getServletContext().setAttribute("availableStates", availableStates);
    	}
    	
    	List<KeyValue> availableClassifications = new ArrayList<KeyValue>();
        availableClassifications.add(new KeyValue("WC Business", "WC Business"));
        availableClassifications.add(new KeyValue("Non WC Business", "Non WC Business"));
        context.getServletContext().setAttribute("availableClassifications", availableClassifications);
        
        List<KeyValue> availableSubClassifications = new ArrayList<KeyValue>();
        availableSubClassifications.add(new KeyValue("Owner", "Owner"));
        availableSubClassifications.add(new KeyValue("Tenant", "Tenant"));
        context.getServletContext().setAttribute("availableSubClassifications", availableSubClassifications);
	}

}

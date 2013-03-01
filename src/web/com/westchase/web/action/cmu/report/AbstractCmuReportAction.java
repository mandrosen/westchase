package com.westchase.web.action.cmu.report;


import java.text.NumberFormat;
import java.util.List;

import javax.naming.InitialContext;

import com.opensymphony.xwork2.Preparable;
import com.westchase.ejb.CmuReportService;
import com.westchase.persistence.model.CmuQuarter;
import com.westchase.web.action.report.AbstractReportAction;

/**
 * @author marc
 *
 */
public abstract class AbstractCmuReportAction extends AbstractReportAction implements Preparable {
	
	private int quarterId;
	private List<CmuQuarter> cmuQuarters;
	
    protected CmuReportService reportServ;
	
	private NumberFormat percentFmt;

	public String input() {
		return SUCCESS;
	}

	@Override
	public void prepare() throws Exception {
		try {
			InitialContext ctx = new InitialContext();
			reportServ = (CmuReportService) ctx.lookup("westchase/CmuReportServiceBean/local");
		} catch (Exception e) {
			log.error("", e);
		}
		if (reportServ != null) {
			try {
				setCmuQuarters(reportServ.listCmuQuarters());
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}
	
	protected String formatPercent(Double percent) {
		String percentStr = "Unknown";
		if (percentFmt == null) {
			percentFmt = NumberFormat.getPercentInstance();
			percentFmt.setMaximumFractionDigits(1);
		}
		if (percent != null) {
			percentStr = percentFmt.format(percent.doubleValue() / 100);
		}
		return percentStr;
	}

	public int getQuarterId() {
		return quarterId;
	}

	public void setQuarterId(int quarterId) {
		this.quarterId = quarterId;
	}

	public List<CmuQuarter> getCmuQuarters() {
		return cmuQuarters;
	}

	public void setCmuQuarters(List<CmuQuarter> cmuQuarters) {
		this.cmuQuarters = cmuQuarters;
	}
	
}

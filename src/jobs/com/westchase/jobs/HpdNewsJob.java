package com.westchase.jobs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.NodeVisitor;
import org.jboss.varia.scheduler.Schedulable;

import com.westchase.ejb.HpdNewsService;
import com.westchase.email.HtmlEmail2;
import com.westchase.jobs.beans.NewsInfo;
import com.westchase.persistence.model.HpdNewsRelease;
import com.westchase.utils.EmailUtils;

/**
 * @author marc
 * 
 */
public class HpdNewsJob implements Schedulable {
	
	private static final Log log = LogFactory.getLog(HpdNewsJob.class);

	private final DateFormat DATE_FORMAT1 = new SimpleDateFormat("m/d/yy");
	
	private HpdNewsService hpdServ;

	@Override
	public void perform(Date timeOfCall, long remainingRepititions) {
		List<NewsInfo> newsInfos = getNewsInfos();
		if (newsInfos != null && !newsInfos.isEmpty()) {
			try {
				InitialContext ctx = new InitialContext();
				hpdServ = (HpdNewsService) ctx.lookup("westchase/HpdNewsServiceBean/remote");
			} catch (Exception e) {
				log.error("", e);
			}
			if (hpdServ != null) {
				List<HpdNewsRelease> releases = hpdServ.findNewReleases(newsInfos);
				if (releases != null && !releases.isEmpty()) {
					if (notify(releases)) {
						hpdServ.saveReleases(releases);
					}
				}
			}
		}
	}

	private List<NewsInfo> getNewsInfos() {
		List<NewsInfo> newsInfos = new ArrayList<NewsInfo>();

		try {
			Parser parser = new Parser("http://www.houstontx.gov/police/news.htm");
			NewsFinder newsFinder = new NewsFinder();
			parser.visitAllNodesWith(newsFinder);
			newsInfos = newsFinder.getNewsInfos();
		} catch (Exception e) {
			log.error("", e);
		}

		return newsInfos;
	}

	static class NewsFinder extends NodeVisitor {
		private List<NewsInfo> newsInfos = new ArrayList<NewsInfo>();

		@Override
		public void visitTag(Tag tag) {
			if (tag.getTagName().equalsIgnoreCase("td")) {
				NodeList children = tag.getChildren();
				if (children != null) {
					Node firstChild = children.elementAt(0);
					if (firstChild != null && firstChild instanceof LinkTag) {
						LinkTag linkTag = (LinkTag) firstChild;
						String href = linkTag.getLink();
						if (href.startsWith("nr/") || href.startsWith("http://www.houstontx.gov/police/nr/")) {
							String url = href;
							if (href.startsWith("nr")) {
								url = "http://www.houstontx.gov/police/" + href;
							}
							String title = linkTag.getLinkText();
							if (tag.getParent() != null && tag.getParent() instanceof TableRow) {
								TableRow tr = (TableRow) tag.getParent();
								if (tr.getColumns() != null && tr.getColumnCount() == 2) {
									TableColumn dateColumn = tr.getColumns()[0];
									String dateStr = dateColumn.getStringText();
									if (StringUtils.isNotBlank(dateStr)) {
										newsInfos.add(new NewsInfo(dateStr, title, url));
									}
								}
							}
						}
					}
				}
			}
		}

		public List<NewsInfo> getNewsInfos() {
			return newsInfos;
		}

	}


	private boolean notify(List<HpdNewsRelease> releases) {
		if (releases != null && !releases.isEmpty()) {
			StringBuffer msg = new StringBuffer();
			msg.append("<p>New stories from the HPD news release:</p>");
			msg.append("<table>");
			for (HpdNewsRelease release : releases) {
				msg.append("<tr>");
				msg.append("<td>").append("<a href='").append(release.getUrl()).append("'>")
						.append(DATE_FORMAT1.format(release.getReleaseDate())).append("</a></td>");
				msg.append("<td>").append(release.getTitle()).append("<td>");
				msg.append("</tr>");
			}
			msg.append("</table>");

			try {
				HtmlEmail2 email = new HtmlEmail2();

				email.setMailSessionFromJNDI("java:/Mail");
				email.setFrom("MRosenthal@westchasedistrict.com", "Westchase HPD News Job");

				EmailUtils.addAddress(email, "MHubenak@westchasedistrict.com", "Mark Hubenak");
				// TESTING ONLY
				email.addTo("mandrosen@gmail.com", "Marc Rosenthal");
				

				email.setSubject("New Releases from HPD News");

				email.setMsg(msg.toString());

				// send the email
				email.send();
				return true;
			} catch (Exception e) {
				log.error("", e);
			}
		}
		return false;
	}


	public static void main(String[] args) {
		HpdNewsJob job = new HpdNewsJob();
		job.getNewsInfos();
	}
}

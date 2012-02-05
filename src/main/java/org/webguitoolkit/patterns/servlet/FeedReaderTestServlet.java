package org.webguitoolkit.patterns.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RSSFeedAuthenticationServlet
 * 
 * @author Alexander Sattler
 */
public class FeedReaderTestServlet extends HttpServlet {
	// Constants
	private static final long serialVersionUID = 1L;
	public static int FEED_REQUEST_INTERVAL; // in hours

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FeedReaderTestServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml; charset=utf-8");
		PrintWriter out = response.getWriter();
		String testFeed = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n"
				+ "<?xml-stylesheet type=\"text/xsl\" media=\"screen\" href=\"/~d/styles/rss2full.xsl\"?><?xml-stylesheet type=\"text/css\" media=\"screen\" href=\"http://rss.cnn.com/~d/styles/itemcontent.css\"?><rss xmlns:media=\"http://search.yahoo.com/mrss/\" xmlns:feedburner=\"http://rssnamespace.org/feedburner/ext/1.0\" version=\"2.0\"><channel>\n"
				+ "<title>An example feed provided by a servlet</title>\n"
				+ "<link>http://www.cnn.com/WORLD/?eref=rss_world</link>\n"
				+ "<description>CNN.com delivers up-to-the-minute news and information on the latest top stories, weather, entertainment, politics and more.</description>\n"
				+ "<language>en-us</language>\n"
				+ "<copyright>© 2009 Cable News Network LP, LLLP.</copyright>\n"
				+ "<pubDate>Tue, 16 Jun 2009 01:28:48 EDT</pubDate>\n"
				+ "<ttl>10</ttl>\n"
				+ "<image>\n"
				+ "<title>An example feed provided by a servlet</title>\n"
				+ "<link>http://www.cnn.com/WORLD/?eref=rss_world</link>\n"
				+ "<url>http://i2.cdn.turner.com/cnn/.element/img/1.0/logo/cnn.logo.rss.gif</url>\n"
				+ "<width>144</width>\n"
				+ "<height>33</height>\n"
				+ "<description>CNN.com delivers up-to-the-minute news and information on the latest top stories, weather, entertainment, politics and more.</description>\n"
				+ "</image>\n"
				+ "<atom10:link xmlns:atom10=\"http://www.w3.org/2005/Atom\" rel=\"self\" href=\"http://rss.cnn.com/rss/cnn_world\" type=\"application/rss+xml\" /><item>\n"
				+ "<title>Analysis: The changing fight in Pakistan</title>\n"
				+ "<guid isPermaLink=\"false\">http://www.cnn.com/2009/WORLD/asiapcf/06/16/pakistan.taliban.robertson/index.html?eref=rss_world</guid>\n"
				+ "<link>http://rss.cnn.com/~r/rss/cnn_world/~3/nIxLIgLR_0Y/index.html</link>\n"
				+ "<description>What I am finding on this visit is as alarming as it is reassuring, CNN's Nic Robertson says. Checkpoints are becoming more common, but so are Taliban attacks. The government's commitment to defeating the Taliban appears as firm as it was when it began the Swat offensive during my last visit. But as the contours of the fight become clearer, the prospect of a swift victory looks ever more distant.&lt;div class=\"feedflare\"&gt;\n"
				+ "&lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=nIxLIgLR_0Y:CY5XCgqKafU:yIl2AUoC8zA\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=yIl2AUoC8zA\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=nIxLIgLR_0Y:CY5XCgqKafU:7Q72WNTAKBA\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=7Q72WNTAKBA\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=nIxLIgLR_0Y:CY5XCgqKafU:V_sGLiPBpWU\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?i=nIxLIgLR_0Y:CY5XCgqKafU:V_sGLiPBpWU\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=nIxLIgLR_0Y:CY5XCgqKafU:qj6IDK7rITs\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=qj6IDK7rITs\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=nIxLIgLR_0Y:CY5XCgqKafU:gIN9vFwOqvQ\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?i=nIxLIgLR_0Y:CY5XCgqKafU:gIN9vFwOqvQ\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt;\n"
				+ "&lt;/div&gt;&lt;img src=\"http://feeds2.feedburner.com/~r/rss/cnn_world/~4/nIxLIgLR_0Y\" height=\"1\" width=\"1\"/&gt;</description>\n"
				+ "<pubDate>Tue, 16 Jun 2009 01:26:40 EDT</pubDate>\n"
				+ "<feedburner:origLink>http://www.cnn.com/2009/WORLD/asiapcf/06/16/pakistan.taliban.robertson/index.html?eref=rss_world</feedburner:origLink></item>\n"
				+ "<item>\n"
				+ "<title>Iran challenger vows to 'pay any cost'</title>\n"
				+ "<guid isPermaLink=\"false\">http://www.cnn.com/2009/WORLD/meast/06/15/iran.elections.protests/index.html?eref=rss_world</guid>\n"
				+ "<link>http://rss.cnn.com/~r/rss/cnn_world/~3/4gBI1GSsDeo/index.html</link>\n"
				+ "<description>Iranian presidential candidate Mir Hossein Moussavi told followers Monday he will \"pay any cost\" to contest the country's presidential election results, but said he had little hope his challenge would succeed. Moussavi made his first public appearance since Friday's vote during a massive rally in Tehran.Iranian presidential candidate Mir Hossein Moussavi told followers Monday he will \"pay any cost\" to contest the country's presidential election results, but said he had little hope his challenge would succeed. Moussavi made his first public appearance since Friday's vote during a massive rally in Tehran.Iranian presidential candidate Mir Hossein Moussavi told followers Monday he will \"pay any cost\" to contest the country's presidential election results, but said he had little hope his challenge would succeed. Moussavi made his first public appearance since Friday's vote during a massive rally in Tehran.Iranian presidential candidate Mir Hossein Moussavi told followers Monday he will \"pay any cost\" to contest the country's presidential election results, but said he had little hope his challenge would succeed. Moussavi made his first public appearance since Friday's vote during a massive rally in Tehran.&lt;div class=\"feedflare\"&gt;\n"
				+ "&lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=4gBI1GSsDeo:el5pyE5Dy6U:yIl2AUoC8zA\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=yIl2AUoC8zA\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=4gBI1GSsDeo:el5pyE5Dy6U:7Q72WNTAKBA\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=7Q72WNTAKBA\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=4gBI1GSsDeo:el5pyE5Dy6U:V_sGLiPBpWU\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?i=4gBI1GSsDeo:el5pyE5Dy6U:V_sGLiPBpWU\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=4gBI1GSsDeo:el5pyE5Dy6U:qj6IDK7rITs\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=qj6IDK7rITs\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=4gBI1GSsDeo:el5pyE5Dy6U:gIN9vFwOqvQ\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?i=4gBI1GSsDeo:el5pyE5Dy6U:gIN9vFwOqvQ\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt;\n"
				+ "&lt;/div&gt;&lt;img src=\"http://feeds2.feedburner.com/~r/rss/cnn_world/~4/4gBI1GSsDeo\" height=\"1\" width=\"1\"/&gt;</description>\n"
				+ "<pubDate>Mon, 15 Jun 2009 22:06:46 EDT</pubDate>\n"
				+ "<feedburner:origLink>http://www.cnn.com/2009/WORLD/meast/06/15/iran.elections.protests/index.html?eref=rss_world</feedburner:origLink></item>\n"
				+ "<item>\n"
				+ "<title>Russia vetoes U.N. mission in Georgia</title>\n"
				+ "<guid isPermaLink=\"false\">http://www.cnn.com/2009/WORLD/europe/06/15/un.georgia/index.html?eref=rss_world</guid>\n"
				+ "<link>http://rss.cnn.com/~r/rss/cnn_world/~3/w6svbRoKIaY/index.html</link>\n"
				+ "<description>The rift between Russia and Western powers over Russia burst back into full view on the U.N. Security Council when Russia vetoed a resolution that would have extended the U.N. observer mission in Georgia.&lt;div class=\"feedflare\"&gt;\n"
				+ "&lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=w6svbRoKIaY:SdpdrI3tjqA:yIl2AUoC8zA\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=yIl2AUoC8zA\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=w6svbRoKIaY:SdpdrI3tjqA:7Q72WNTAKBA\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=7Q72WNTAKBA\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=w6svbRoKIaY:SdpdrI3tjqA:V_sGLiPBpWU\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?i=w6svbRoKIaY:SdpdrI3tjqA:V_sGLiPBpWU\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=w6svbRoKIaY:SdpdrI3tjqA:qj6IDK7rITs\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=qj6IDK7rITs\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=w6svbRoKIaY:SdpdrI3tjqA:gIN9vFwOqvQ\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?i=w6svbRoKIaY:SdpdrI3tjqA:gIN9vFwOqvQ\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt;\n"
				+ "&lt;/div&gt;&lt;img src=\"http://feeds2.feedburner.com/~r/rss/cnn_world/~4/w6svbRoKIaY\" height=\"1\" width=\"1\"/&gt;</description>\n"
				+ "<pubDate>Mon, 15 Jun 2009 21:38:48 EDT</pubDate>\n"
				+ "<feedburner:origLink>http://www.cnn.com/2009/WORLD/europe/06/15/un.georgia/index.html?eref=rss_world</feedburner:origLink></item>\n"
				+ "<item>\n"
				+ "<title>Space shuttle launch now set for Wednesday</title>\n"
				+ "<guid isPermaLink=\"false\">http://www.cnn.com/2009/TECH/space/06/15/shuttle.launch.reschedule/index.html?eref=rss_world</guid>\n"
				+ "<link>http://rss.cnn.com/~r/rss/cnn_world/~3/TFG101leRck/index.html</link>\n"
				+ "<description>NASA has rescheduled the launch of space shuttle Endeavour for 5:40 a.m. ET Wednesday, pushing back the planned launch of a separate lunar mission.&lt;div class=\"feedflare\"&gt;\n"
				+ "&lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=TFG101leRck:G17shU6FP-s:yIl2AUoC8zA\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=yIl2AUoC8zA\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=TFG101leRck:G17shU6FP-s:7Q72WNTAKBA\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=7Q72WNTAKBA\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=TFG101leRck:G17shU6FP-s:V_sGLiPBpWU\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?i=TFG101leRck:G17shU6FP-s:V_sGLiPBpWU\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=TFG101leRck:G17shU6FP-s:qj6IDK7rITs\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=qj6IDK7rITs\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=TFG101leRck:G17shU6FP-s:gIN9vFwOqvQ\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?i=TFG101leRck:G17shU6FP-s:gIN9vFwOqvQ\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt;\n"
				+ "&lt;/div&gt;&lt;img src=\"http://feeds2.feedburner.com/~r/rss/cnn_world/~4/TFG101leRck\" height=\"1\" width=\"1\"/&gt;</description>\n"
				+ "<pubDate>Mon, 15 Jun 2009 17:45:30 EDT</pubDate>\n"
				+ "<feedburner:origLink>http://www.cnn.com/2009/TECH/space/06/15/shuttle.launch.reschedule/index.html?eref=rss_world</feedburner:origLink></item>\n"
				+ "<item>\n"
				+ "<title>U.S.: N. Korea has made nuclear progress</title>\n"
				+ "<guid isPermaLink=\"false\">http://www.cnn.com/2009/WORLD/asiapcf/06/15/U.S.north.korea/index.html?eref=rss_world</guid>\n"
				+ "<link>http://rss.cnn.com/~r/rss/cnn_world/~3/b2v7oLcIpRY/index.html</link>\n"
				+ "<description>The U.S. intelligence community believes that North Korea tested a nuclear device last month with an explosive yield of several kilotons, considerably more powerful than its first test nearly three years ago.&lt;div class=\"feedflare\"&gt;\n"
				+ "&lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=b2v7oLcIpRY:0TjCE9YlHCM:yIl2AUoC8zA\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=yIl2AUoC8zA\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=b2v7oLcIpRY:0TjCE9YlHCM:7Q72WNTAKBA\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=7Q72WNTAKBA\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=b2v7oLcIpRY:0TjCE9YlHCM:V_sGLiPBpWU\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?i=b2v7oLcIpRY:0TjCE9YlHCM:V_sGLiPBpWU\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=b2v7oLcIpRY:0TjCE9YlHCM:qj6IDK7rITs\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=qj6IDK7rITs\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=b2v7oLcIpRY:0TjCE9YlHCM:gIN9vFwOqvQ\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?i=b2v7oLcIpRY:0TjCE9YlHCM:gIN9vFwOqvQ\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt;\n"
				+ "&lt;/div&gt;&lt;img src=\"http://feeds2.feedburner.com/~r/rss/cnn_world/~4/b2v7oLcIpRY\" height=\"1\" width=\"1\"/&gt;</description>\n"
				+ "<pubDate>Mon, 15 Jun 2009 17:51:36 EDT</pubDate>\n"
				+ "<feedburner:origLink>http://www.cnn.com/2009/WORLD/asiapcf/06/15/U.S.north.korea/index.html?eref=rss_world</feedburner:origLink></item>\n"
				+ "<item>\n"
				+ "<title>Anger boils in Mexico over 46 deaths at day care</title>\n"
				+ "<guid isPermaLink=\"false\">http://www.cnn.com/2009/WORLD/americas/06/15/mexico.day.care.anger/index.html?eref=rss_world</guid>\n"
				+ "<link>http://rss.cnn.com/~r/rss/cnn_world/~3/R03Qet5f8Mk/index.html</link>\n"
				+ "<description>Anger is growing in Mexico over a fire at a government-run day care center that claimed its 46th child this weekend.&lt;div class=\"feedflare\"&gt;\n"
				+ "&lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=R03Qet5f8Mk:TvizMAgx5-0:yIl2AUoC8zA\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=yIl2AUoC8zA\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=R03Qet5f8Mk:TvizMAgx5-0:7Q72WNTAKBA\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=7Q72WNTAKBA\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=R03Qet5f8Mk:TvizMAgx5-0:V_sGLiPBpWU\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?i=R03Qet5f8Mk:TvizMAgx5-0:V_sGLiPBpWU\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=R03Qet5f8Mk:TvizMAgx5-0:qj6IDK7rITs\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=qj6IDK7rITs\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=R03Qet5f8Mk:TvizMAgx5-0:gIN9vFwOqvQ\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?i=R03Qet5f8Mk:TvizMAgx5-0:gIN9vFwOqvQ\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt;\n"
				+ "&lt;/div&gt;&lt;img src=\"http://feeds2.feedburner.com/~r/rss/cnn_world/~4/R03Qet5f8Mk\" height=\"1\" width=\"1\"/&gt;</description>\n"
				+ "<pubDate>Mon, 15 Jun 2009 21:01:55 EDT</pubDate>\n"
				+ "<feedburner:origLink>http://www.cnn.com/2009/WORLD/americas/06/15/mexico.day.care.anger/index.html?eref=rss_world</feedburner:origLink></item>\n"
				+ "<item>\n"
				+ "<title>India concerned by student attacks in Australia</title>\n"
				+ "<guid isPermaLink=\"false\">http://www.cnn.com/2009/WORLD/asiapcf/06/15/india.australia.student.attacks/index.html?eref=rss_world</guid>\n"
				+ "<link>http://rss.cnn.com/~r/rss/cnn_world/~3/hri7leoMmaE/index.html</link>\n"
				+ "<description>Anjali Thakur is living in fear in India. She is a mother afraid for her son. \"We are all having sleepless nights,\" Thakur says.&lt;div class=\"feedflare\"&gt;\n"
				+ "&lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=hri7leoMmaE:JG2J8cVGZQA:yIl2AUoC8zA\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=yIl2AUoC8zA\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=hri7leoMmaE:JG2J8cVGZQA:7Q72WNTAKBA\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=7Q72WNTAKBA\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=hri7leoMmaE:JG2J8cVGZQA:V_sGLiPBpWU\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?i=hri7leoMmaE:JG2J8cVGZQA:V_sGLiPBpWU\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=hri7leoMmaE:JG2J8cVGZQA:qj6IDK7rITs\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=qj6IDK7rITs\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=hri7leoMmaE:JG2J8cVGZQA:gIN9vFwOqvQ\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?i=hri7leoMmaE:JG2J8cVGZQA:gIN9vFwOqvQ\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt;\n"
				+ "&lt;/div&gt;&lt;img src=\"http://feeds2.feedburner.com/~r/rss/cnn_world/~4/hri7leoMmaE\" height=\"1\" width=\"1\"/&gt;</description>\n"
				+ "<pubDate>Mon, 15 Jun 2009 14:23:45 EDT</pubDate>\n"
				+ "<feedburner:origLink>http://www.cnn.com/2009/WORLD/asiapcf/06/15/india.australia.student.attacks/index.html?eref=rss_world</feedburner:origLink></item>\n"
				+ "<item>\n"
				+ "<title>Anti-war campaigners slam 'secret' Iraq probe</title>\n"
				+ "<guid isPermaLink=\"false\">http://www.cnn.com/2009/WORLD/europe/06/15/uk.iraq.inquiry/index.html?eref=rss_world</guid>\n"
				+ "<link>http://rss.cnn.com/~r/rss/cnn_world/~3/hYTjg8VjsaU/index.html</link>\n"
				+ "<description>British Prime Minister Gordon Brown will announce a government investigation into the Iraq war on Monday, a spokesman said.&lt;div class=\"feedflare\"&gt;\n"
				+ "&lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=hYTjg8VjsaU:PBNUplKV0Lc:yIl2AUoC8zA\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=yIl2AUoC8zA\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=hYTjg8VjsaU:PBNUplKV0Lc:7Q72WNTAKBA\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=7Q72WNTAKBA\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=hYTjg8VjsaU:PBNUplKV0Lc:V_sGLiPBpWU\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?i=hYTjg8VjsaU:PBNUplKV0Lc:V_sGLiPBpWU\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=hYTjg8VjsaU:PBNUplKV0Lc:qj6IDK7rITs\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=qj6IDK7rITs\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=hYTjg8VjsaU:PBNUplKV0Lc:gIN9vFwOqvQ\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?i=hYTjg8VjsaU:PBNUplKV0Lc:gIN9vFwOqvQ\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt;\n"
				+ "&lt;/div&gt;&lt;img src=\"http://feeds2.feedburner.com/~r/rss/cnn_world/~4/hYTjg8VjsaU\" height=\"1\" width=\"1\"/&gt;</description>\n"
				+ "<pubDate>Mon, 15 Jun 2009 22:05:22 EDT</pubDate>\n"
				+ "<feedburner:origLink>http://www.cnn.com/2009/WORLD/europe/06/15/uk.iraq.inquiry/index.html?eref=rss_world</feedburner:origLink></item>\n"
				+ "<item>\n"
				+ "<title>Yemen says 3 female hostages killed</title>\n"
				+ "<guid isPermaLink=\"false\">http://www.cnn.com/2009/WORLD/meast/06/15/yemen.hostages/index.html?eref=rss_world</guid>\n"
				+ "<link>http://rss.cnn.com/~r/rss/cnn_world/~3/BnRl-8NAoP8/index.html</link>\n"
				+ "<description>Seven foreign workers seized by militants in Yemen reportedly have been killed, according to the Yemen Post newspaper.&lt;div class=\"feedflare\"&gt;\n"
				+ "&lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=BnRl-8NAoP8:-rBWD0cnqTk:yIl2AUoC8zA\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=yIl2AUoC8zA\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=BnRl-8NAoP8:-rBWD0cnqTk:7Q72WNTAKBA\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=7Q72WNTAKBA\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=BnRl-8NAoP8:-rBWD0cnqTk:V_sGLiPBpWU\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?i=BnRl-8NAoP8:-rBWD0cnqTk:V_sGLiPBpWU\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=BnRl-8NAoP8:-rBWD0cnqTk:qj6IDK7rITs\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?d=qj6IDK7rITs\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt; &lt;a href=\"http://rss.cnn.com/~ff/rss/cnn_world?a=BnRl-8NAoP8:-rBWD0cnqTk:gIN9vFwOqvQ\"&gt;&lt;img src=\"http://feeds2.feedburner.com/~ff/rss/cnn_world?i=BnRl-8NAoP8:-rBWD0cnqTk:gIN9vFwOqvQ\" border=\"0\"&gt;&lt;/img&gt;&lt;/a&gt;\n"
				+ "&lt;/div&gt;&lt;img src=\"http://feeds2.feedburner.com/~r/rss/cnn_world/~4/BnRl-8NAoP8\" height=\"1\" width=\"1\"/&gt;</description>\n"
				+ "<pubDate>Mon, 15 Jun 2009 15:36:23 EDT</pubDate>\n"
				+ "<feedburner:origLink>http://www.cnn.com/2009/WORLD/meast/06/15/yemen.hostages/index.html?eref=rss_world</feedburner:origLink></item>\n"
				+ "</channel></rss>";
		out.print(testFeed);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void init() {
		try {
			super.init();
		}
		catch (ServletException e) {
		}

	}
}

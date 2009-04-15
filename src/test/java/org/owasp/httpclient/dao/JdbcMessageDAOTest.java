package org.owasp.httpclient.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.owasp.httpclient.Conversation;
import org.owasp.httpclient.Request;
import org.owasp.httpclient.RequestHeader;
import org.owasp.httpclient.Response;
import org.owasp.httpclient.ResponseHeader;
import org.owasp.httpclient.util.AsciiString;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

public class JdbcMessageDAOTest {

	private static JdbcMessageDAO dao = null;

	private static DriverManagerDataSource dataSource = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:webscarab3;DB_CLOSE_DELAY=-1");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		dao = new JdbcMessageDAO();
		dao.setDataSource(dataSource);
	}

	@Before
	public void setUp() {
		dao.createTables();
	}

	@After
	public void tearDown() {
	}

	private void dump() {
		dump("SELECT * FROM contents");
		dump("SELECT * FROM headers");
		dump("SELECT * FROM requests");
		dump("SELECT * FROM conversations");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao.getJdbcTemplate().execute("SHUTDOWN");
	}

	@Test
	public void testSaveMessageContent() {
		Request request = new Request.Impl();
		request.setHost("localhost");
		request.setPort(80);
		request.setSsl(false);
		request.setHeader(AsciiString
				.getBytes("GET / HTTP/1.0\r\nHost: localhost\r\n\r\n"));
		Response response = new Response.Impl();
		response.setHeader(AsciiString
				.getBytes("HTTP/1.0 200 Ok\r\nContent-Type: text\r\n\r\n"));
		byte[] cont = AsciiString.getBytes("Some content");
		response.setContent(cont);

		dao.saveRequest(request);
		dao.saveResponse(response);

		int id = dao.saveConversation(request.getId(), response.getId(), 0, 0,
				0);

		System.out.println("ADDED conversation");
		dump();
		System.out.println("##############################################");

		Conversation c = dao.getConversation(id);

		RequestHeader reqh = dao.loadRequestHeader(c.getRequestId());
		ResponseHeader resph = dao.loadResponseHeader(c.getResponseId());

		assertTrue(Arrays.equals(request.getHeader(), reqh.getHeader()));
		assertEquals(request.getHost(), reqh.getHost());
		assertEquals(request.getPort(), reqh.getPort());
		assertEquals(request.isSsl(), reqh.isSsl());
		assertTrue("Response headers differ", Arrays.equals(response
				.getHeader(), resph.getHeader()));

		byte[] content = dao.loadMessageContent(dao.getMessageContentId(c
				.getRequestId()));

		assertNull(content);

		content = dao.loadMessageContent(dao.getMessageContentId(c
				.getResponseId()));

		assertTrue(Arrays.equals(cont, content));

		assertTrue("Delete failed", dao.deleteConversation(id));

		dump();
	}

	private static void dump(String sql) {
		System.out.println("\n" + sql);
		SqlRowSet rs = dao.getJdbcTemplate().queryForRowSet(sql);
		try {
			SqlRowSetMetaData rsmd = rs.getMetaData();
			int c = rsmd.getColumnCount();
			for (int i = 1; i <= c; i++) {
				System.out.print(rsmd.getColumnLabel(i));
				System.out.print(i == c ? "\n" : "\t");
			}
			while (rs.next()) {
				for (int i = 1; i <= c; i++) {
					System.out.print(rs.getObject(i));
					System.out.print(i == c ? "\n" : "\t");
				}
			}
			System.out.println("================\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
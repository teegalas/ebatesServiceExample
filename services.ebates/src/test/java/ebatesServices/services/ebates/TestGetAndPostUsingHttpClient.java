package ebatesServices.services.ebates;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class TestGetAndPostUsingHttpClient extends TestCase {

	CloseableHttpClient httpclient = HttpClients.createDefault();

	/**
	 * Tests httpClient post.
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void testPostResponseUsingHttpClient() throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost("http://httpbin.org/post");
		String json = "{\"hello\":\"world\"}";
		StringEntity data = new StringEntity(json);
		httpPost.setEntity(data);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");

		CloseableHttpResponse response = httpclient.execute(httpPost);
		int statusCode = response.getStatusLine().getStatusCode();
		assertEquals("HttpClint Get request Falied", 200, statusCode);
		// Printing Response Code.
		System.out.println("Response Code:" + String.valueOf(statusCode));

		HttpEntity entity = response.getEntity();
		String responseBody = EntityUtils.toString(entity);
		// Printing Response Body
		System.out.println("Response Body: ");
		System.out.println(responseBody);

		Header[] headers = response.getAllHeaders();
		// Printing Response Headers.
		System.out.println("Response All Headers: ");
		for (Header header : headers) {
			System.out.println(header.getName() + " : " + header.getValue());
		}

		response.close();

	}

	/**
	 * Test HttpClint Get request.
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void testGetResponseUsingHttpClientTest() throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet("http://httpbin.org/user-agent");
		CloseableHttpResponse response = httpclient.execute(httpGet);
		int statusCode = response.getStatusLine().getStatusCode();
		assertEquals("HttpClint Get request Falied", 200, statusCode);
		HttpEntity entity = response.getEntity();
		String responseBody = EntityUtils.toString(entity);
		// Printing response body.
		System.out.println(responseBody);
		response.close();
	}
}

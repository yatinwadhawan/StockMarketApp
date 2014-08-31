
import java.io.*;
import java.text.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.XML;


public class RequestInfoExample extends HttpServlet {


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
		String symbol="", xml="", str="";
		symbol = request.getParameter("symbol").toString();
		try {
			URL url = new URL(
					"http://default-environment-qyvixmbfpn.elasticbeanstalk.com/?symbol="
							+ symbol);
			URLConnection urlConn = url.openConnection();
			urlConn.setAllowUserInteraction(false);
			InputStream input = url.openStream();
			BufferedReader bufferReader = new BufferedReader(
					new InputStreamReader(input));
			while ((str = bufferReader.readLine()) != null) {
				xml = xml + str;
			}
			bufferReader.close();
			String json = convertXMLToJson(xml);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	public String convertXMLToJson(String data) {
		return XML.toJSONObject(data).toString();
	}


}


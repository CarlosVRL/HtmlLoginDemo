package chains;

import java.net.URLConnection;
import java.util.*;

public class CookieUtils
{
    private static final String SET_COOKIE_HEADER = "Set-Cookie";
    private static final String EQUALS_DELIMITER = "=";
    private static final String INTERIM_DELIMITER = ";";

    private Map<String, String> map = new HashMap<String, String>();

    /**
     * Load cookies into the instance map.
     *
     * This method is useful to "store" cookies from a
     * given connection, that can be used downstream.
     *
     * @param http the connection with cookies to load
     */
    public void loadCookies(URLConnection http)
    {
        Map<String, List<String>> headers = http.getHeaderFields();
        for (Map.Entry<String, List<String>> header : headers.entrySet())
        {
            if (header.getKey() != null && header.getKey().equalsIgnoreCase(SET_COOKIE_HEADER))
            {
                StringTokenizer stringTokenizer = new StringTokenizer(header.getValue().get(0), EQUALS_DELIMITER);
                String name = stringTokenizer.nextToken();
                String value = stringTokenizer.nextToken();
                String val = value.substring(0, value.indexOf(INTERIM_DELIMITER));
                this.map.put(name, val);
            }
        }
    }

    /**
     * Save the cookies from the instance onto the connection.
     *
     * Use this method to prepare the passed connection with
     * any existing cookies from the instance map.
     *
     * @param http the connection to save cookies to
     */
    public void saveCookies(URLConnection http)
    {
        StringBuilder str = new StringBuilder();
        Set<String> keys = this.map.keySet();
        for (String key : keys)
        {
            String value = this.map.get(key);
            if (str.length() > 0)
            {
                str.append("; ");
            }
            str.append(key + "=" + value);
        }
        http.setRequestProperty("Cookie", str.toString());
    }

    public Map<String, String> getMap()
    {
        return this.map;
    }

    public void echoMap()
    {
        for (Map.Entry<String, String> entry : this.map.entrySet())
        {
            System.out.println("Name : " + entry.getKey());
            System.out.println("Value : " + entry.getValue());
        }
    }
}

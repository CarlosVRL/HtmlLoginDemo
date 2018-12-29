package chains;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class HtmlUtils
{
    /**
     * A simple utility for retrieving the body (typically HTML) from
     * the passed HTTP connection.
     *
     * @param http
     * @return
     * @throws IOException
     */
    public static String downloadPage(HttpURLConnection http) throws IOException
    {
        final int BUFFER_SIZE = 8192; // typical page size
        StringBuilder result = new StringBuilder();
        byte buffer[] = new byte[BUFFER_SIZE];
        InputStream s = http.getInputStream();
        int size = 0;
        do
        {
            size = s.read(buffer);
            if (size != -1)
                result.append(new String(buffer, 0, size));
        } while (size != -1);
        return result.toString();
    }
}

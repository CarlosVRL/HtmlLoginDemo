package chains;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChainsEngine
{
    private static final String BASE_URL = "https://chains.cc";
    private static final String SIGN_IN_URL = BASE_URL + "/registrations/sign_in";

    /**
     * Process resource URL's to obtain workspace HTML.
     *
     * The general steps are:
     * 1. GET initial form data required for authentication
     * 2. POST authentication to get session cookies
     * 3. RE-DIRECT to workspace and echo contents
     *
     * @param uid
     * @param pass
     * @throws IOException
     */
    public void process(String uid, String pass) throws IOException
    {
        System.out.println("Attempting to login with uid : " + uid + ", pass : " + pass + " at : " + BASE_URL);

        // Get the login HTML document and Cookies
        HttpURLConnection loginHttp = getLoginHttp();
        Document loginDocument = getLoginPage(loginHttp);
        CookieUtils cookieUtils = getLoginCookies(loginHttp);

        // Prepare form data
        LoginData loginData = new LoginData(loginDocument, uid, pass);
        String formData = loginData.constructFormBody();

        // Submit the login request
        HttpURLConnection signInHttp = getSignInHttp();
        signInHttp.setDoOutput(true);
        signInHttp.setInstanceFollowRedirects(false);
        cookieUtils.saveCookies(signInHttp);
        writeFormBody(signInHttp, formData);
        cookieUtils.loadCookies(signInHttp);

        // Assign authentication and get workspace HTML
        HttpURLConnection workspaceUrl = getLoginHttp();
        cookieUtils.saveCookies(workspaceUrl);
        String workspaceHtml = HtmlUtils.downloadPage(workspaceUrl);

        System.out.println(workspaceHtml);
    }

    private HttpURLConnection getLoginHttp() throws IOException
    {
        URL url = new URL(BASE_URL);
        return (HttpURLConnection) url.openConnection();
    }

    private HttpURLConnection getSignInHttp() throws IOException
    {
        URL url = new URL(SIGN_IN_URL);
        return (HttpURLConnection) url.openConnection();
    }

    private void writeFormBody(HttpURLConnection http, String data) throws IOException
    {
        OutputStream os = http.getOutputStream();
        os.write(data.getBytes());
    }

    private Document getLoginPage(HttpURLConnection http) throws IOException
    {
        String loginHtml = HtmlUtils.downloadPage(http);
        return Jsoup.parse(loginHtml);
    }

    private CookieUtils getLoginCookies(HttpURLConnection http) throws IOException
    {
        CookieUtils cookieUtils = new CookieUtils();
        cookieUtils.loadCookies(http);
        return cookieUtils;
    }
}

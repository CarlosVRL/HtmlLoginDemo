package chains;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LoginData
{
    private String utf8;
    private String authenticityToken;
    private String commit;
    private String rememberMe0;
    private String rememberMe1;
    private String uid;
    private String pass;

    public LoginData(Document document, String uid, String pass)
    {
        assignUtf8();
        assignAuthenticityToken(document);
        assignCommit();
        assignRememberMe();
        assignLogin(uid, pass);
    }

    private void assignUtf8()
    {
        utf8 = "Check Mark";
    }

    private void assignCommit()
    {
        this.commit = "Login";
    }

    private void assignAuthenticityToken(Document document)
    {
        Element input = document.select("input[name=authenticity_token]").first();
        this.authenticityToken = input.attr("value");
    }

    private void assignLogin(String uid, String pass)
    {
        this.uid = uid;
        this.pass = pass;
    }

    private void assignRememberMe()
    {
        this.rememberMe0 = "0";
        this.rememberMe1 = "1";
    }

    public String getUtf8() {
        return utf8;
    }

    public String getUtf8_Encoded()
    {
        return "%E2%9C%93";
    }

    public void setUtf8(String utf8) {
        this.utf8 = utf8;
    }

    public String getAuthenticityToken() {
        return authenticityToken;
    }

    public String getAuthenticityToken_Encoded()
    {
        return encode(this.getAuthenticityToken());
    }

    public void setAuthenticityToken(String authenticityToken) {
        this.authenticityToken = authenticityToken;
    }

    public String getCommit() {
        return commit;
    }

    public String getCommit_Encoded()
    {
        return encode(this.getCommit());
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getRememberMe0() {
        return rememberMe0;
    }

    public String getRememberMe0_Encoded()
    {
        return encode(getRememberMe0());
    }

    public void setRememberMe0(String rememberMe0) {
        this.rememberMe0 = rememberMe0;
    }

    public String getRememberMe1() {
        return rememberMe1;
    }

    public String getRememberMe1_Encoded()
    {
        return encode(getRememberMe1());
    }

    public void setRememberMe1(String rememberMe1) {
        this.rememberMe1 = rememberMe1;
    }

    public String getUid() {
        return uid;
    }

    public String getUid_Encoded()
    {
        return encode(getUid());
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPass() {
        return pass;
    }

    public String getPass_Encoded()
    {
        return encode(getPass());
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    private String encode(String decoded)
    {
        try
        {
            return URLEncoder.encode(decoded, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            return "";
        }
    }

    public void echoStandardValues()
    {
        System.out.println(">> Standard Form Values");
        System.out.println(getUtf8());
        System.out.println(getAuthenticityToken());
        System.out.println(getUid());
        System.out.println(getPass());
        System.out.println(getCommit());
        System.out.println(getRememberMe0());
        System.out.println(getRememberMe1());
    }

    public void echoEncodedValues()
    {
        System.out.println(">> Encoded Form Values");
        System.out.println(getUtf8_Encoded());
        System.out.println(getAuthenticityToken_Encoded());
        System.out.println(getUid_Encoded());
        System.out.println(getPass_Encoded());
        System.out.println(getCommit_Encoded());
        System.out.println(getRememberMe0_Encoded());
        System.out.println(getRememberMe1_Encoded());
    }

    public String constructFormBody()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("utf8=" + getUtf8_Encoded() + "&");
        stringBuilder.append("authenticity_token=" + getAuthenticityToken_Encoded() + "&");
        stringBuilder.append(encode("user[login]") + "=" + getUid_Encoded() + "&");
        stringBuilder.append(encode("user[password]") + "=" + getPass_Encoded() + "&");
        stringBuilder.append("commit=" + getCommit_Encoded() + "&");
        stringBuilder.append(encode("user[remember_me]") + "=0" + "&");
        stringBuilder.append(encode("user[remember_me]") + "=1");
        return stringBuilder.toString();
    }
}

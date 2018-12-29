# HtmlLoginDemo
An HTTP form login demo in Java using chains.cc

This is a simple Java application that uses the

```
HttpURLConnection
``` 

class to collect form cookies and post a login.

Once logged in (assuming credentials are valid),
the signal is re-directed to the workspace, where
it echo's out the HTML.

Usage
```
# Update credentials in ChainsApp
# Run main class in ChainsApp
```

Most of the routing and cookie management is handled
in `ChainsEngine`, but there are a couple of useful
utility classes that are re-usable when working
with HTTP projects:

* CookieUtils
* HtmlUtils

Finally, there is a small DTO class `LoginData` that
can encode regular imports, and maintains knowledge
about specific fields required for chains.cc.
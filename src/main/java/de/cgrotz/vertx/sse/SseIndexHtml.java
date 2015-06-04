package de.cgrotz.vertx.sse;

/**
 * Created by Christoph Grotz on 04.06.15.
 */
public class SseIndexHtml {
  // From http://www.w3schools.com/html/html5_serversentevents.asp
  public static final String CONTENT = "<html>\n" +
    "<body>\n" +
    "\n" +
    "<h1>Getting server updates</h1>\n" +
    "<div id=\"result\"></div>\n" +
    "\n" +
    "<script>\n" +
    "if(typeof(EventSource) !== \"undefined\") {\n" +
    "    var source = new EventSource(\"/events\");\n" +
    "    source.onmessage = function(event) {\n" +
    "        document.getElementById(\"result\").innerHTML += event.data + \"<br>\";\n" +
    "    };\n" +
    "} else {\n" +
    "    document.getElementById(\"result\").innerHTML = \"Sorry, your browser does not support server-sent events...\";\n" +
    "}\n" +
    "</script>\n" +
    "\n" +
    "</body>\n" +
    "</html>";
}

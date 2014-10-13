package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE html>\r\n");
      out.write("<!--\r\n");
      out.write("To change this license header, choose License Headers in Project Properties.\r\n");
      out.write("To change this template file, choose Tools | Templates\r\n");
      out.write("and open the template in the editor.\r\n");
      out.write("-->\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>Testbed</title>\r\n");
      out.write("        <meta charset=\"UTF-8\">\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/menu.css\"  type=\"text/css\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/template.css\"  type=\"text/css\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/jquery-ui.css\" type=\"text/css\"/>\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/jquery-ui.theme.css\" type=\"text/css\"  />\r\n");
      out.write("\r\n");
      out.write("        <script src=\"js/jquery-1.11.1.min.js\"></script>\r\n");
      out.write("        <script src=\"js/jquery-ui-1.11.1.js\"></script>\r\n");
      out.write("        <script src=\"js/menu.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <div id=\"wrap\">\r\n");
      out.write("            <div id=\"header\">\r\n");
      out.write("                <h3>This is the header.</h3>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div id=\"menu\">\r\n");
      out.write("                <h3>Security</h3>\r\n");
      out.write("                <div>\r\n");
      out.write("                    <ul>\r\n");
      out.write("                        <li><a href='#'><span>Door</span></a></li>\r\n");
      out.write("                        <li><a href='#'><span>Window</span></a></li>\r\n");
      out.write("                        <li><a href='#'><span>CO</span></a></li>\r\n");
      out.write("                        <li><a href='#'><span>Fire</span></a></li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </div>\r\n");
      out.write("                <h3>Water</h3>\r\n");
      out.write("                <div>\r\n");
      out.write("                    <ul>\r\n");
      out.write("                        <li><a href='sprinkler.jsp'><span>Sprinkler</span></a></li>\r\n");
      out.write("                        <li class=\"ui-state-disabled\"><a href='#'><span>Report</span></a></li>\r\n");
      out.write("\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </div>\r\n");
      out.write("                <h3>Electricity</h3>\r\n");
      out.write("                <div>\r\n");
      out.write("                    <ul>\r\n");
      out.write("                        <li class=\"ui-state-disabled\"><a href='#'><span>HV/AC</span></a></li>\r\n");
      out.write("                        <li class=\"ui-state-disabled\"><a href='#'><span>Light</span></a></li>\r\n");
      out.write("                        <li class=\"ui-state-disabled\"><a href='#'><span>Report</span></a></li>\r\n");
      out.write("\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <div id=\"main\">\r\n");
      out.write("\r\n");
      out.write("                <h3>Welcome to the SHAS Testbed!</h3>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <div id=\"footer\">\r\n");
      out.write("                <h3>This is the footer.</h3>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

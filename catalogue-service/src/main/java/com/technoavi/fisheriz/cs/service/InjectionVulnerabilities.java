package com.technoavi.fisheriz.cs.service;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;

public class InjectionVulnerabilities {

    // VULN 1: LDAP Injection - Authentication bypass
    public boolean authenticateLDAP(String username, String password) {
        try {
            InitialContext ctx = new InitialContext();
            // VULNERABLE - User input not escaped
            String filter = "(&(uid=" + username + ")(userPassword=" + password + "))";
            DirContext dirCtx = (DirContext) ctx.lookup("ldap://directory.company.com");
            SearchControls controls = new SearchControls();
            NamingEnumeration<SearchResult> results = dirCtx.search("cn=users", filter, controls);
            return results.hasMore();  // VULNERABLE
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return false;
    }

    // VULN 2: LDAP Injection - User enumeration
    public String searchUserByEmail(String email) {
        try {
            InitialContext ctx = new InitialContext();
            // VULNERABLE - Allows wildcard injection
            String filter = "(mail=" + email + ")";
            DirContext dirCtx = (DirContext) ctx.lookup("ldap://directory.company.com");
            SearchControls controls = new SearchControls();
            NamingEnumeration<SearchResult> results = dirCtx.search("cn=users", filter, controls);
            if (results.hasMore()) {
                return results.next().getName();
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 3: XPath Injection - XML data extraction
    public String getProductDescription(Document xmlDoc, String productId) {
        try {
            // VULNERABLE - XPath Injection
            XPath xpath = XPathFactory.newInstance().newXPath();
            String xpathExpr = "//product[id='" + productId + "']/description/text()";
            return xpath.evaluate(xpathExpr, xmlDoc);  // VULNERABLE
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 4: XPath Injection - Authentication
    public boolean authenticateViaXML(Document userXML, String username, String password) {
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            // VULNERABLE - Bypass with: ' or '1'='1
            String xpathExpr = "//user[username='" + username + "' and password='" + password + "']/role/text()";
            String result = xpath.evaluate(xpathExpr, userXML);
            return !result.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // VULN 5: XPath Injection - User enumeration
    public boolean userExists(Document userDB, String username) {
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            // VULNERABLE - XPath injection for blind enumeration
            String xpathExpr = "count(//user[username='" + username + "'])";
            String result = xpath.evaluate(xpathExpr, userDB);
            return !result.equals("0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // VULN 6: Expression Language (EL) Injection
    public String processCatalogueExpression(String userExpression) {
        try {
            // VULNERABLE - Evaluates user input as EL
            javax.el.ExpressionFactory factory = javax.el.ExpressionFactory.newInstance();
            javax.el.ELContext context = new javax.el.ELContext() {};
            // User input directly in expression
            javax.el.ValueExpression expr = factory.createValueExpression(context, userExpression, Object.class);
            return expr.getValue(context).toString();  // VULNERABLE
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 7: Template Injection
    public String renderProductTemplate(String templateString, String productName) {
        try {
            // VULNERABLE - User input in template
            String result = templateString.replace("${productName}", productName);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 8: JNDI Injection
    public Object lookupCatalogService(String serviceName) {
        try {
            InitialContext ctx = new InitialContext();
            // VULNERABLE - User input in JNDI lookup
            return ctx.lookup(serviceName);  // Can be exploited with rmi:// or ldap://
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 9: SpEL (Spring Expression Language) Injection
    public Object evaluateProductExpression(String expression) {
        try {
            // VULNERABLE - Evaluates user input
            org.springframework.expression.ExpressionParser parser =
                new org.springframework.expression.spel.standard.SpelExpressionParser();
            org.springframework.expression.Expression expr = parser.parseExpression(expression);
            return expr.getValue();  // VULNERABLE - RCE possible
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 10: Groovy code injection
    public Object executeProductRule(String groovyCode) {
        try {
            // VULNERABLE - Executes user input as code
            groovy.lang.GroovyShell shell = new groovy.lang.GroovyShell();
            return shell.evaluate(groovyCode);  // VULNERABLE - RCE
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 11: JavaScript injection via Nashorn
    public Object evaluateProductScript(String jsCode) {
        try {
            // VULNERABLE - Executes JavaScript
            javax.script.ScriptEngineManager manager = new javax.script.ScriptEngineManager();
            javax.script.ScriptEngine engine = manager.getEngineByName("nashorn");
            return engine.eval(jsCode);  // VULNERABLE
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // VULN 12: Unsafe string concatenation in filter
    public String buildDynamicFilter(String field, String value) {
        // VULNERABLE - Multiple injection types
        return "(" + field + "=" + value + ")";  // LDAP, XPATH, etc.
    }
}

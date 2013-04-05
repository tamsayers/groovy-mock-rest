package mock.rest.test.jetty

import org.eclipse.jetty.server.Connector
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.ContextHandlerCollection
import org.eclipse.jetty.server.nio.SelectChannelConnector
import org.eclipse.jetty.webapp.WebAppContext


class JettyRunner {
    private static final Boolean REITH_PROXY_ENABLED = Boolean.TRUE

    private static final String CONTEXT_PATH = '/rest'
    private static final String IP = '0.0.0.0'
    private static final int PORT = 8080

    static void main(String[] args) throws Exception {
        def time = new Date().time
        setProxySystemProperties()
        createServer().with {
            start()
            join()
        }
        println("Jetty started on port $PORT in ${new Date().time - time}ms")
    }

    public static Server createServer() {
        ContextHandlerCollection contexts = new ContextHandlerCollection()
        contexts.handlers = [createWebapp()]
        new Server().with {
            addConnector(createConnector())
            handler = contexts
            it
        }
    }

    private static WebAppContext createWebapp() {
        new WebAppContext().with {
            contextPath = CONTEXT_PATH
            resourceBase = 'src/main/webapp/'
            parentLoaderPriority = false
            copyWebDir = true
            it
        }
    }

    private static Connector createConnector() {
        new SelectChannelConnector(). with {
            port = PORT
            host = IP
            it
        }
    }

    private static void setProxySystemProperties() {
        if (REITH_PROXY_ENABLED) {
            System.out.println('Setting proxy properties')
            System.setProperty('kl.usingJetty', 'true')
            System.setProperty('socksProxyHost', '')
            System.setProperty('https.proxyHost', 'www-cache.reith.bbc.co.uk')
            System.setProperty('https.proxyPort', '80')
            System.setProperty('https.nonProxyHosts', 'localhost|*.sandbox.dev.bbc.uk')
        }
    }
}

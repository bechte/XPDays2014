package xpdays2014.samples.webserver.hierarchicalcontextrunner;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import xpdays2014.samples.webserver.SocketServer;
import xpdays2014.samples.webserver.doubles.ClosingSocketService;
import xpdays2014.samples.webserver.doubles.EchoSocketService;
import xpdays2014.samples.webserver.doubles.ReadingSocketService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import static org.junit.Assert.*;

@RunWith(HierarchicalContextRunner.class)
public class SocketServerTest {
  private SocketServer server;
  private int port;

  @Before
  public void setup() {
    port = 8042;
  }

  @After
  public void tearDown() throws Exception {
    if (server != null)
      server.stop();
  }

  public class WithClosingSocketService {
    private ClosingSocketService service;

    @Before
    public void setUp() throws Exception {
      service = new ClosingSocketService();
      server = new SocketServer(port, service);
    }

    @After
    public void tearDown() throws Exception {
      server.stop();
    }

    @Test
    public void instantiate() throws Exception {
      assertEquals(port, server.getPort());
      assertEquals(service, server.getService());
    }

    @Test
    public void canStartAndStopServer() throws Exception {
      server.start();
      assertTrue(server.isRunning());

      server.stop();
      assertFalse(server.isRunning());
    }

    @Test
    public void acceptsAnIncomingConnection() throws Exception {
      server.start();

      new Socket("localhost", port);
      synchronized(service) {
        service.wait();
      }

      assertEquals(1, service.connections);
    }

    @Test
    public void acceptsMultipleIncomingConnections() throws Exception {
      server.start();

      new Socket("localhost", port);
      synchronized(service) {
        service.wait();
      }

      new Socket("localhost", port);
      synchronized(service) {
        service.wait();
      }

      assertEquals(2, service.connections);
    }
  }

  public class WithReadingSocketService {
    private ReadingSocketService service;

    @Before
    public void setup() throws Exception {
      service = new ReadingSocketService();
      server = new SocketServer(port, service);
    }

    @Test
    public void canSendAndReceiveData() throws Exception {
      server.start();

      Socket s = new Socket("localhost", port);
      OutputStream os = s.getOutputStream();
      os.write("hello\n".getBytes());
      synchronized(service) {
        service.wait();
      }

      assertEquals("hello", service.message);
    }
  }

  public class WithEchoSocketService {
    private EchoSocketService service;

    @Before
    public void setup() throws Exception {
      service = new EchoSocketService();
      server = new SocketServer(port, service);
    }

    @Test
    public void canEcho() throws Exception {
      server.start();

      Socket s = new Socket("localhost", port);
      OutputStream os = s.getOutputStream();
      os.write("echo\n".getBytes());
      synchronized(service) {
        service.wait();
      }

      InputStream is = s.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      String response = br.readLine();
      assertEquals("echo", response);
    }
  }
}
package xpdays2014.samples.webserver.defaultrunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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

public class SocketServerTest {
  private ClosingSocketService closingSocketService;
  private ReadingSocketService readingSocketService;
  private EchoSocketService echoSocketService;
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

  @Test
  public void withClosingSocketService_instantiate() throws Exception {
    initClosingSocketService();
    assertEquals(port, server.getPort());
    assertEquals(closingSocketService, server.getService());
  }

  @Test
  public void withClosingSocketService_canStartAndStopServer() throws Exception {
    initClosingSocketService();

    server.start();
    assertTrue(server.isRunning());

    server.stop();
    assertFalse(server.isRunning());
  }

  @Test
  public void withClosingSocketService_acceptsAnIncomingConnection() throws Exception {
    initClosingSocketService();
    server.start();

    new Socket("localhost", port);
    synchronized(closingSocketService) {
      closingSocketService.wait();
    }

    assertEquals(1, closingSocketService.connections);
  }

  @Test
  public void withClosingSocketService_acceptsMultipleIncomingConnections() throws Exception {
    initClosingSocketService();
    server.start();

    new Socket("localhost", port);
    synchronized(closingSocketService) {
      closingSocketService.wait();
    }

    new Socket("localhost", port);
    synchronized(closingSocketService) {
      closingSocketService.wait();
    }

    assertEquals(2, closingSocketService.connections);
  }

  @Test
  public void withReadingSocketService_canSendAndReceiveData() throws Exception {
    initReadingSocketService();
    server.start();

    Socket s = new Socket("localhost", port);
    OutputStream os = s.getOutputStream();
    os.write("hello\n".getBytes());
    synchronized(readingSocketService) {
      readingSocketService.wait();
    }

    assertEquals("hello", readingSocketService.message);
  }

  @Test
  public void withEchoSocketService_canEcho() throws Exception {
    initEchoSocketService();
    server.start();

    Socket s = new Socket("localhost", port);
    OutputStream os = s.getOutputStream();
    os.write("echo\n".getBytes());
    synchronized(echoSocketService) {
      echoSocketService.wait();
    }

    InputStream is = s.getInputStream();
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);
    String response = br.readLine();
    assertEquals("echo", response);
  }

  private void initClosingSocketService() throws Exception {
    closingSocketService = new ClosingSocketService();
    server = new SocketServer(port, closingSocketService);
  }

  private void initReadingSocketService() throws Exception {
    readingSocketService = new ReadingSocketService();
    server = new SocketServer(port, readingSocketService);
  }

  private void initEchoSocketService() throws Exception {
    echoSocketService = new EchoSocketService();
    server = new SocketServer(port, echoSocketService);
  }
}
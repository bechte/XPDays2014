package xpdays2014.samples.webserver.doubles;

import xpdays2014.samples.webserver.SocketService;

import java.io.IOException;
import java.net.Socket;

public abstract class TestSocketService implements SocketService {
  public void serve(Socket s) {
    try {
      doService(s);
      synchronized(this) { notify(); }
      s.close();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  protected abstract void doService(Socket s) throws IOException;
}

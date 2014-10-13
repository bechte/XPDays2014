package xpdays2014.samples.webserver.doubles;

import java.io.IOException;
import java.net.Socket;

public class ClosingSocketService extends TestSocketService {
  public int connections;

  protected void doService(Socket s) throws IOException {
    connections++;
  }
}

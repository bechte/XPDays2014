package xpdays2014.samples.webserver.doubles;

import java.io.*;
import java.net.Socket;

public class EchoSocketService extends TestSocketService {

  protected void doService(Socket s) throws IOException {
    InputStream is = s.getInputStream();
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);
    String message = br.readLine();
    OutputStream os = s.getOutputStream();
    os.write(message.getBytes());
    os.flush();
  }
}

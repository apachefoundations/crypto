import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class Client {
    public static void main(String[] args) {
        try {
            KeyStore ts = KeyStore.getInstance("JKS");
            ts.load(new FileInputStream("samlKeystore.jks"), "password".toCharArray());

            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ts);

            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, tmf.getTrustManagers(), null);

            SSLSocketFactory sf = sc.getSocketFactory();
            SSLSocket socket = (SSLSocket) sf.createSocket("localhost", 9999);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("Hello from client!");
            String response = in.readLine();
            System.out.println("📝 Server says: " + response);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class Server {
    public static void main(String[] args) {
        try {
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream("samlKeystore.jks"), "password".toCharArray());

            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, "password".toCharArray());

            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(kmf.getKeyManagers(), null, null);

            SSLServerSocketFactory ssf = sc.getServerSocketFactory();
            SSLServerSocket ss = (SSLServerSocket) ssf.createServerSocket(9999);
            System.out.println("Server running on port 9999...");

            SSLSocket socket = (SSLSocket) ss.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String clientMessage = in.readLine();
            System.out.println("📨 Received: " + clientMessage);
            out.println(" Message received by server.");

            socket.close();
            ss.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}











#keytool -genkeypair -alias mykey -keyalg RSA -keystore samlKeystore.jks -keysize 2048#

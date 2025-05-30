import java.math.BigInteger;
import java.util.Scanner;

public class dh {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a prime number (p): ");
        BigInteger p = sc.nextBigInteger();

        System.out.print("Enter a primitive root (g): ");
        BigInteger g = sc.nextBigInteger();

        System.out.print("Enter private key of Sender (x): ");
        BigInteger x = sc.nextBigInteger();

        System.out.print("Enter private key of Receiver (y): ");
        BigInteger y = sc.nextBigInteger();

        BigInteger A = g.modPow(x, p);
        BigInteger B = g.modPow(y, p);

        BigInteger keySender = B.modPow(x, p);
        BigInteger keyReceiver = A.modPow(y, p);

        System.out.println("Sender's Public Key (A): " + A);
        System.out.println("Receiver's Public Key (B): " + B);
        System.out.println("Shared Key at Sender Side: " + keySender);
        System.out.println("Shared Key at Receiver Side: " + keyReceiver);

        if (keySender.equals(keyReceiver)) {
            System.out.println("✔ Shared keys match! Secure connection established.");
        } else {
            System.out.println("❌ Keys do not match! Check your input.");
        }

        sc.close();
    }
}

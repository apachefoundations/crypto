import java.math.BigInteger;
import java.util.Random;

public class DSA
 {
    public static void main(String[] args) {
        BigInteger p = new BigInteger("23");
        BigInteger q = new BigInteger("11");
        BigInteger g = new BigInteger("4");

        BigInteger x = new BigInteger("3");
        BigInteger y = g.modPow(x, p);

        BigInteger k = new BigInteger("7");
        BigInteger h = new BigInteger("9");

        BigInteger r = g.modPow(k, p).mod(q);
        BigInteger kInv = k.modInverse(q);
        BigInteger s = kInv.multiply(h.add(x.multiply(r))).mod(q);

        System.out.println("Signature: r = " + r + ", s = " + s);

        BigInteger w = s.modInverse(q);
        BigInteger u1 = h.multiply(w).mod(q);
        BigInteger u2 = r.multiply(w).mod(q);
        BigInteger v = g.modPow(u1, p).multiply(y.modPow(u2, p)).mod(p).mod(q);

        System.out.println("Verification value v = " + v);

        if (v.equals(r)) {
            System.out.println("Signature Verified!");
        } else {
            System.out.println("Invalid Signature!");
        }
    }
}

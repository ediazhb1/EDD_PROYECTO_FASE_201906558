package principal.Merkles;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CodificacionHash{
    public static String sha256(String cadena) throws NoSuchAlgorithmException {
        String codigo = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(cadena.getBytes("utf8"));
            codigo = String.format("%064x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return codigo;

    }
}

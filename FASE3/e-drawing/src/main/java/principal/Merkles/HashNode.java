package principal.Merkles;

public class HashNode {
    String info;
    HashNode derecha,izquierda;

    public HashNode(HashNode derecha,HashNode izquierda, String info) {
        this.info = info;
        this.derecha = derecha;
        this.izquierda = izquierda;
    }

    public String getHash() {
        return info;
    }

    public HashNode getRight() {
        return derecha;
    }

    public HashNode getLeft() {
        return izquierda;
    }
}

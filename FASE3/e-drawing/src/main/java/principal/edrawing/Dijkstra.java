package principal.edrawing;

public class Dijkstra {
    public int[] ejecutar(int[][] grafo, int nodo) {
        final boolean[] visitados = new boolean[grafo.length];
        final int[] distanciasCortas = new int[grafo.length];
        visitados[0] = true;
        for (int i = 1; i < distanciasCortas.length; i++) {
            if (grafo[nodo][i] != 0) {
                distanciasCortas[i] =
                        grafo[nodo][i];
            } else {
                distanciasCortas[i] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < (distanciasCortas.length - 1); i++) {
            final int siguiente = proximoVertice(distanciasCortas, visitados);

            visitados[siguiente] = true;
            for (int j = 0; j < grafo[0].length; j++) {
                final int d = distanciasCortas[siguiente] + grafo[siguiente][j];
                if (distanciasCortas[j] > d) {
                    distanciasCortas[j] = distanciasCortas[siguiente] + grafo[siguiente][j];
                }
            }

        }
        return distanciasCortas;
    }

    private static int proximoVertice(int[] distanciasCortas, boolean[] visitados) {
        int x = Integer.MAX_VALUE;
        int y = -1;
        for (int i = 0; i < distanciasCortas.length; i++) {
            if (!visitados[i] && distanciasCortas[i] < x) {
                y = i;
                x = distanciasCortas[i];
            }
        }
        return y;
    }
}
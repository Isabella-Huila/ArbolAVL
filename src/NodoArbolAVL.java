public class NodoArbolAVL<T> {
    T data;
    int fe;
    NodoArbolAVL<T> hijoIzquierdo, hijoDerecho;

    public NodoArbolAVL(T data) {
        this.data = data;
        this.fe = 0;
        this.hijoDerecho = null;
        this.hijoIzquierdo = null;
    }
}

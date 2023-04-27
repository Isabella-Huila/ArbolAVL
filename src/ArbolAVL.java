import javax.swing.JOptionPane;
import java.util.LinkedList;
import java.util.Queue;
public class ArbolAVL<T extends Comparable<T>> {
    private NodoArbolAVL<T> raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public NodoArbolAVL<T> getRaiz() {
        return raiz;
    }

    public NodoArbolAVL<T> obtenerRaiz() {
        return raiz;
    }

    public NodoArbolAVL<T> buscar(T d, NodoArbolAVL<T> r) {
        if (raiz == null) {
            return null;
        } else if (r.data.compareTo(d) == 0) {
            return r;
        } else if (r.data.compareTo(d) < 0) {
            return buscar(d, r.hijoDerecho);
        } else {
            return buscar(d, r.hijoIzquierdo);
        }
    }

    public int obtenerFE(NodoArbolAVL<T> x) {
        if (x == null) {
            return -1;
        } else {
            return x.fe;
        }
    }

    public NodoArbolAVL<T> rotacionIzquierda(NodoArbolAVL<T> c) {
        NodoArbolAVL<T> auxiliar = c.hijoIzquierdo;
        c.hijoIzquierdo = auxiliar.hijoDerecho;
        auxiliar.hijoDerecho = c;
        c.fe = Math.max(obtenerFE(c.hijoIzquierdo), obtenerFE(c.hijoDerecho)) + 1;
        auxiliar.fe = Math.max(obtenerFE(auxiliar.hijoIzquierdo), obtenerFE(auxiliar.hijoDerecho)) + 1;
        return auxiliar;
    }

    public NodoArbolAVL<T> rotacionDerecha(NodoArbolAVL<T> c) {
        NodoArbolAVL<T> auxiliar = c.hijoDerecho;
        c.hijoDerecho = auxiliar.hijoIzquierdo;
        auxiliar.hijoIzquierdo = c;
        c.fe = Math.max(obtenerFE(c.hijoIzquierdo), obtenerFE(c.hijoDerecho)) + 1;
        auxiliar.fe = Math.max(obtenerFE(auxiliar.hijoIzquierdo), obtenerFE(auxiliar.hijoDerecho)) + 1;
        return auxiliar;
    }

    public NodoArbolAVL<T> rotacionDobleIzquierda(NodoArbolAVL<T> c) {
        NodoArbolAVL<T> temporal;
        c.hijoIzquierdo = rotacionDerecha(c.hijoIzquierdo);
        temporal = rotacionIzquierda(c);
        return temporal;
    }

    public NodoArbolAVL<T> rotacionDobleDerecha(NodoArbolAVL<T> c) {
        NodoArbolAVL<T> temporal;
        c.hijoDerecho = rotacionIzquierda(c.hijoDerecho);
        temporal = rotacionDerecha(c);
        return temporal;
    }


    public NodoArbolAVL<T> insertarAVL(NodoArbolAVL<T> nuevo, NodoArbolAVL<T> subAr){
        NodoArbolAVL<T> nuevoPadre = subAr;
        if(nuevo.data.compareTo(subAr.data) < 0){
            if(subAr.hijoIzquierdo == null){
                subAr.hijoIzquierdo = nuevo;
            }else{
                subAr.hijoIzquierdo = insertarAVL(nuevo, subAr.hijoIzquierdo);
                if((obtenerFE(subAr.hijoIzquierdo) - obtenerFE(subAr.hijoDerecho) == 2)){
                    if(nuevo.data.compareTo(subAr.hijoIzquierdo.data) < 0){
                        nuevoPadre = rotacionIzquierda(subAr);
                    }else{
                        nuevoPadre = rotacionDobleIzquierda(subAr);
                    }
                }
            }
        }else if(nuevo.data.compareTo(subAr.data) > 0){
            if(subAr.hijoDerecho == null){
                subAr.hijoDerecho = nuevo;
            }else{
                subAr.hijoDerecho = insertarAVL(nuevo, subAr.hijoDerecho);
                if((obtenerFE(subAr.hijoDerecho) - obtenerFE(subAr.hijoIzquierdo) == 2)){
                    if(nuevo.data.compareTo(subAr.hijoDerecho.data) > 0){
                        nuevoPadre = rotacionDerecha(subAr);
                    }else{
                        nuevoPadre = rotacionDobleDerecha(subAr);
                    }
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Nodo duplicado");
        }

        //actualizar altura
        if((subAr.hijoIzquierdo == null) && (subAr.hijoDerecho != null)){
            subAr.fe = subAr.hijoDerecho.fe + 1;
        }else if((subAr.hijoDerecho == null) && (subAr.hijoIzquierdo != null)){
            subAr.fe = subAr.hijoIzquierdo.fe + 1;
        }else{
            subAr.fe = Math.max(obtenerFE(subAr.hijoIzquierdo), obtenerFE(subAr.hijoDerecho)) + 1;
        }
        return nuevoPadre;
    }


    public void insertar(T d){
        NodoArbolAVL<T> nuevo = new NodoArbolAVL<>(d);
        if(raiz == null){
            raiz = nuevo;
        }else{
            raiz = insertarAVL(nuevo, raiz);
        }
    }

    public void inOrden(NodoArbolAVL<T> r){
        if(r != null){
            inOrden(r.hijoIzquierdo);
            System.out.println(r.data);
            inOrden(r.hijoDerecho);
        }
    }


    //recorrer en preorden
    public void preorden(NodoArbolAVL r) {
        if (r != null) {
            System.out.println(r.data);
            preorden(r.hijoIzquierdo);
            preorden(r.hijoDerecho);
        }
    }

    //recorrer postorden
    public void postOrden(NodoArbolAVL r) {
        if (r != null) {
            postOrden(r.hijoIzquierdo);
            postOrden(r.hijoDerecho);
            System.out.println(r.data);
        }
    }

    public void levelOrder(){
        //Write your code here
        if (raiz == null) return;

        Queue<NodoArbolAVL<T>> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            NodoArbolAVL<T> node = cola.poll();
            System.out.print(node.data + " ");

            if (node.hijoIzquierdo != null) {
                cola.add(node.hijoIzquierdo);
            }

            if (node.hijoDerecho != null) {
                cola.add(node.hijoDerecho);
            }
        }
    }

    /** public NodoArbolAVL eliminar(int valor) {
     NodoArbolAVL nodo = null;
     if (nodo == null) {
     return null;
     }
     if (valor < nodo.dato) {
     nodo.hijoIzquierdo = eliminar(valor);
     } else if (valor > nodo.dato) {
     nodo.hijoDerecho = eliminar(valor);
     } else {
     if (nodo.hijoIzquierdo == null || nodo.hijoDerecho == null) {
     NodoArbolAVL temp = null;
     if (temp == nodo.hijoIzquierdo) {
     temp = nodo.hijoDerecho;
     } else {
     temp = nodo.hijoIzquierdo;
     }
     if (temp == null) {
     temp = nodo;
     nodo = null;
     } else {
     nodo = temp;
     }
     } else {
     NodoArbolAVL temp = nodo.hijoDerecho;
     while (temp.hijoIzquierdo != null) {
     temp = temp.hijoIzquierdo;
     }
     nodo.dato = temp.dato;
     nodo.hijoDerecho = eliminar(temp.dato);
     }
     }
     if (nodo == null) {
     return nodo;
     }
     nodo.fe = Math.max(obtenerFE(nodo.hijoIzquierdo), obtenerFE(nodo.hijoDerecho)) + 1;
     int balance = obtenerFE(nodo.hijoIzquierdo) - obtenerFE(nodo.hijoDerecho);
     if (balance > 1 && obtenerFE(nodo.hijoIzquierdo.hijoIzquierdo) - obtenerFE(nodo.hijoIzquierdo.hijoDerecho) >= 0) {
     return rotacionDerecha(nodo);
     }
     if (balance > 1 && obtenerFE(nodo.hijoIzquierdo.hijoIzquierdo) - obtenerFE(nodo.hijoIzquierdo.hijoDerecho) < 0) {
     nodo.hijoIzquierdo = rotacionIzquierda(nodo.hijoIzquierdo);
     return rotacionDerecha(nodo);
     }
     if (balance < -1 && obtenerFE(nodo.hijoDerecho.hijoDerecho) - obtenerFE(nodo.hijoDerecho.hijoIzquierdo) >= 0) {
     return rotacionIzquierda(nodo);
     }
     if (balance < -1 && obtenerFE(nodo.hijoDerecho.hijoDerecho) - obtenerFE(nodo.hijoDerecho.hijoIzquierdo) < 0) {
     nodo.hijoDerecho = rotacionDerecha(nodo.hijoDerecho);
     return rotacionIzquierda(nodo);
     }
     return nodo;
     } **/

    public void eliminar(T d){
        raiz= eliminar(d, raiz);
    }


    public NodoArbolAVL<T> eliminar(T d, NodoArbolAVL<T> r) {
        if (r == null) {
            return null;
        } else {
            if (d.compareTo(r.data) < 0) {
                r.hijoIzquierdo = eliminar(d, r.hijoIzquierdo);
            } else if (d.compareTo(r.data) > 0) {
                r.hijoDerecho = eliminar(d, r.hijoDerecho);
            } else {
                if (r.hijoIzquierdo == null && r.hijoDerecho == null) {
                    r = null;
                } else if (r.hijoIzquierdo == null) {
                    r = r.hijoDerecho;
                } else if (r.hijoDerecho == null) {
                    r = r.hijoIzquierdo;
                } else {
                    NodoArbolAVL<T> sucesor = buscarSucesor(r.hijoDerecho);
                    r.data = sucesor.data;
                    r.hijoDerecho = eliminar(sucesor.data, r.hijoDerecho);
                }
            }
            if (r != null) {
                r.fe = Math.max(obtenerFE(r.hijoIzquierdo), obtenerFE(r.hijoDerecho)) + 1;
                int balance = obtenerFE(r.hijoIzquierdo) - obtenerFE(r.hijoDerecho);
                if (balance > 1) {
                    if (obtenerFE(r.hijoIzquierdo.hijoIzquierdo) >= obtenerFE(r.hijoIzquierdo.hijoDerecho)) {
                        r = rotacionIzquierda(r);
                    } else {
                        r = rotacionDobleIzquierda(r);
                    }
                } else if (balance < -1) {
                    if (obtenerFE(r.hijoDerecho.hijoDerecho) >= obtenerFE(r.hijoDerecho.hijoIzquierdo)) {
                        r = rotacionDerecha(r);
                    } else {
                        r = rotacionDobleDerecha(r);
                    }
                }
            }
            return r;
        }
    }

    private NodoArbolAVL<T> buscarSucesor(NodoArbolAVL<T> r) {
        while (r.hijoIzquierdo != null) {
            r = r.hijoIzquierdo;
        }
        return r;
    }




}

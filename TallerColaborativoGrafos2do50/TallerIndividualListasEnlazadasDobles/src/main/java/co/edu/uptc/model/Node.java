package co.edu.uptc.model;

public class Node<T> {

    //Atributos
    private T info;
    private Node<T> next;
    private Node<T> previous;

    //Constructor
    public Node(T info) {
        this.info = info;
        this.next = null;
        this.previous = null;
    }

    //Getters y Setters
    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    /**
     * Establece el nodo anterior
     * @param previous Nodo anterior
     */
    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }
}

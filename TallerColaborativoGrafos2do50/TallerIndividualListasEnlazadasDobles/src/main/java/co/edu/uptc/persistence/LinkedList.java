package co.edu.uptc.persistence;

import java.util.Vector;

public class LinkedList<T> {

    //Atributos
    private Node<T> head;
    private Node<T> last;
    private int size;

    //Constructor
    public LinkedList() {
        this.head = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * Verificar si la lista está vacía
     * @return true si está vacía, false si no
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Añadir un nodo al inicio de la lista
     * @param data Información del nodo
     */
    public void addNodeFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = last = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        size++;
    }

    /**
     * Añadir un nodo al final de la lista
     * @param data Información del nodo
     */
    public void addNodeLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = last = newNode;
        } else {
            last.setNext(newNode);
            newNode.setPrevious(last);
            last = newNode;
        }
        size++;
    }

    /**
     * Añadir un nodo después de un nodo dado
     * @param node Nodo dado
     * @param data Información del nodo
     */
    public void addNodeAfterTo(Node<T> node, T data) {
        Node<T> newNode = new Node<>(data);
        Node<T> nextNode = node.getNext();
        node.setNext(newNode);
        newNode.setPrevious(node);
        newNode.setNext(nextNode);
        if (nextNode != null) {
            nextNode.setPrevious(newNode);
        } else {
            last = newNode;
        }
        size++;
    }

    /**
     * Añadir un nodo antes de un nodo dado
     * @param node Nodo dado
     * @param data Información del nodo
     */
    public void addNodeBeforeTo(Node<T> node, T data) {
        Node<T> newNode = new Node<>(data);
        Node<T> previousNode = node.getPrevious();
        node.setPrevious(newNode);
        newNode.setNext(node);
        newNode.setPrevious(previousNode);
        if (previousNode != null) {
            previousNode.setNext(newNode);
        } else {
            head = newNode;
        }
        size++;
    }

    /**
     * Añadir un nodo ordenado
     * @param data Información del nodo
     */
    public void addNodeSorted(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = last = newNode;
        } else {
            Node<T> current = head;
            while (current != null && current.getInfo().toString().compareTo(data.toString()) < 0) {
                current = current.getNext();
            }
            if (current == head) {
                newNode.setNext(head);
                head.setPrevious(newNode);
                head = newNode;
            } else if (current == null) {
                last.setNext(newNode);
                newNode.setPrevious(last);
                last = newNode;
            } else {
                Node<T> previous = current.getPrevious();
                previous.setNext(newNode);
                newNode.setPrevious(previous);
                newNode.setNext(current);
                current.setPrevious(newNode);
            }
        }
        size++;
    }

    /**
     * Buscar un nodo por su información
     * @param id Información a buscar
     * @return Nodo encontrado o null si no existe
     */
    public Node<T> findNode(String id) {
        Node<T> current = head;
        while (current != null) {
            if (current.getInfo().toString().contains(id)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Buscar la información de un nodo por su información
     * @param id Información a buscar
     * @return Información del nodo encontrado o null si no existe
     */
    public T findInfo(String id) {
        Node<T> node = findNode(id);
        return node != null ? node.getInfo() : null;
    }

    /**
     * Mostrar la lista enlazada
     * @param forward true para mostrarla de forma ascendente, false para mostrarla de forma descendente
     * @return Lista enlazada
     */
    public Vector<T> getLinkedList(boolean forward) {
        Vector<T> list = new Vector<>();
        Node<T> current = forward ? head : last;
        while (current != null) {
            list.add(current.getInfo());
            current = forward ? current.getNext() : current.getPrevious();
        }
        return list;
    }

    /**
     * Eliminar un nodo por su información
     * @param id Información del nodo a eliminar
     * @return Información del nodo eliminado o null si no existe
     */
    public T deleteNode(Node<T> node) {
        if (node == head) {
            head = node.getNext();
            if (head != null) {
                head.setPrevious(null);
            } else {
                last = null;
            }
        } else if (node == last) {
            last = node.getPrevious();
            last.setNext(null);
        } else {
            node.getPrevious().setNext(node.getNext());
            node.getNext().setPrevious(node.getPrevious());
        }
        size--;
        return node.getInfo();
    }

    /**
     * Eliminar un nodo por su información
     * @param id Información del nodo a eliminar
     * @return Información del nodo eliminado o null si no existe
     */
    public int getSize() {
        return size;
    }

    /**
     * Obtener la información de un nodo por su índice
     * @param index Índice del nodo
     * @return Información del nodo encontrado o null si no existe
     */
    public T getObject(int index) {
        Node<T> current = head;
        int count = 0;
        while (current != null && count < index) {
            current = current.getNext();
            count++;
        }
        return current != null ? current.getInfo() : null;
    }

    /**
     * Obtener el primer nodo
     * @return Información del primer nodo
     */
    public T getFirst() {
        return head != null ? head.getInfo() : null;
    }

    /**
     * Obtener el último nodo
     * @return Información del último nodo
     */
    public T getLast() {
        return last != null ? last.getInfo() : null;
    }
}

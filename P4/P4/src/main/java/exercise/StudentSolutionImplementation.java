package main.java.exercise;

import main.java.framework.StudentInformation;
import main.java.framework.StudentSolution;

public class StudentSolutionImplementation implements StudentSolution {

    @Override
    public StudentInformation provideStudentInformation() {
        return new StudentInformation(
                "Christoph", // Vorname
                "Zeitler", // Nachname
                "12224224" // Matrikelnummer
        );
    }

    // Implementieren Sie hier Ihre Lösung für das Auslesen eines Zeichens
    public char getCharAt(String s, int i) {
        if (i >= 0 && i > s.length()) return '{';
        else return s.charAt(i);
    }

    // Implementieren Sie hier Ihre Lösung für das Suchen nach einem Schlüssel
    public boolean lookupKey(InnerNode root, Key key) {

        InnerNode currentInnerNode = root;

        for (int i = 0; currentInnerNode != null; i++) {
            char c = key.charAt(i);
            currentInnerNode = (InnerNode) currentInnerNode.getChild(c);
            Node currentNode = currentInnerNode.getChild(c);

            if (currentInnerNode == null) {
                return false; // Kindknoten nicht gefunden
            }

            if (currentInnerNode.isLeaf()) {
                LeafNode leafNode = (LeafNode) currentNode;
                return leafNode.containsKey(key); // Überprüfen, ob der Schlüssel im Blattknoten vorhanden ist
            }
        }

        return false; // Alle Zeichen des Schlüssels durchlaufen, aber kein Blattknoten erreicht

    }

    // Implementieren Sie hier Ihre Lösung für das Einfügen eines Schlüssels
    public void insertKey(InnerNode root, LeafNode leafNode) {
        InnerNode currentNode = root;
        int keyLength = leafNode.getKeyLength();


        for (int i = 0; i < keyLength; i++) {
            char c = leafNode.keyCharAt(i);
            Node childNode = currentNode.getChild(c);

            if (childNode == null) {
                if (i == keyLength - 1) {
                    currentNode.attachLeafNode(c, leafNode); // Blattknoten anhängen, wenn es keinen Kindknoten gibt und letztes Zeichen erreicht wurde
                } else {
                    InnerNode innerNode = currentNode.attachInnerNode(c); // Inneren Knoten anhängen, wenn es keinen Kindknoten gibt
                    currentNode = innerNode; // Aktuellen Knoten aktualisieren
                }
            } else if (childNode.isLeaf()) {
                LeafNode existingLeafNode = (LeafNode) childNode;
                if (existingLeafNode.hasSameKey(leafNode)) {
                    return; // Wenn der Schlüssel bereits vorhanden ist, wird nichts eingefügt
                }

                // Erstelle einen inneren Knoten und ersetze den vorhandenen Blattknoten
                InnerNode innerNode = currentNode.attachInnerNode(c);
                currentNode = innerNode;

                // Hänge den vorhandenen Blattknoten und den neuen Blattknoten an den inneren Knoten
                innerNode.attachLeafNode(existingLeafNode.keyCharAt(i + 1), existingLeafNode);
                innerNode.attachLeafNode(leafNode.keyCharAt(i + 1), leafNode);
                return;
            } else {
                currentNode = (InnerNode) childNode; // Aktuellen Knoten aktualisieren
            }
        }

    }
}

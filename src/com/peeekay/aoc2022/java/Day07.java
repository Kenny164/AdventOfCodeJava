package com.peeekay.aoc2022.java;

import com.peeekay.aoc2022.kotlin.AOCPuzzle;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Day07 extends AOCPuzzle {
    private final List<String> inp = resourceAsList();
    private final Node rootNode = Node.getTree(inp);

    public Day07(boolean isTest) { super(7, isTest); }

    static class Node {
        String name;
        Node parent;
        List<Node> children;
        long size;
        boolean isFile;

        public Node(String name, Node parent, boolean isFile) {
            this.name = name;
            this.parent = parent;
            this.isFile = isFile;
            this.children = new ArrayList<Node>();
            this.size = 0L;
        }

        public Node getParent() {
            return parent;
        }

        public void addChild(Node child) {
            this.children.add(child);
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public static long getTotalSizeWithLimitRec(Node node, long limit) {
            long total = 0L;
            for (Node c : node.children) {
                if (c.size <= limit && c.size > 0L) {
                    total += c.size;
                }
                total += Node.getTotalSizeWithLimitRec(c, limit);
            }
            return total;
        }

        public static long getTotalSize(Node node) {
            long total = 0L;
            for (Node c : node.children) {
                if (c.size > 0L) {
                    total += c.size;
                }
                total += Node.getTotalSize(c);
            }
            return total;
        }

        public static List<Node> flattenNodes(Node node) {
            List<Node> res = new ArrayList<>();
            //res.add(node);
            for (Node c : node.children) {
                if (!c.isFile) {
                    List<Node> cn = Node.flattenNodes(c);
                    res.addAll(cn);
                }
                res.add(c);
            }
            return res;
        }

        public static Node getTree(List<String> inp) {
            Node rootNode = new Node("/", null, false);
            Node cwd = rootNode;
            for (String line : inp) {
                String[] tokens = line.split(" ");
                if ( line.startsWith("$ cd /") ){
                    cwd = rootNode;
                }
                else if (line.startsWith("$ cd ..")) {
                    cwd = cwd.getParent();
                }
                else if (line.startsWith("$ cd ")) {
                    Node childNode = new Node(tokens[2], cwd, false);
                    cwd.addChild(childNode);
                    cwd = childNode;
                }
                else if ( !line.equals("$ ls") && !line.startsWith("dir")) {
                    Node childNode = new Node(tokens[1], cwd, true);
                    childNode.setSize( Long.parseLong(tokens[0]) );
                    cwd.addChild(childNode);
                }
            }
            return rootNode;
        }

    }

    @Nullable
    @Override
    public Object partOne() {
        List<Node> flattened = Node.flattenNodes(rootNode);

        return flattened.stream()
                .filter(n -> !n.isFile)
                .map(Node::getTotalSize)
                .filter(it -> it <= 100_000)
                .reduce(Long::sum)
                .orElse(0L);
    }

    @Nullable
    @Override
    public Object partTwo() {
        List<Node> flattened = Node.flattenNodes(rootNode);
        long totalCapacity = 70_000_000L;
        long requiredSpace = 30_000_000L;
        long currentlyFreeSpace = totalCapacity - Node.getTotalSize(rootNode);
        long neededSpace = requiredSpace - currentlyFreeSpace;

        return flattened.stream()
                .filter(n -> !n.isFile)
                .mapToLong(Node::getTotalSize)
                .filter(it -> it >= neededSpace)
                .min()
                .orElse(0L);
    }
}

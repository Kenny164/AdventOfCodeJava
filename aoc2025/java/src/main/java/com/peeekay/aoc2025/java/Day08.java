package com.peeekay.aoc2025.java;

import com.peeekay.aocCommon.AOCPuzzle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Day08 extends AOCPuzzle {
    final int LIMIT = isTest ? 10 : 1000;
    List<Point3D> inp = resourceAsList().stream()
            .map(Point3D::of)
            .toList();
    List<Link> links = new ArrayList<>();

    public Day08(boolean isTest) {
        super(2025, 8, isTest);
        buildLinks();
    }

    record Link(double distance, Point3D a, Point3D b) {
        static Link from(Point3D a, Point3D b) {
            return new Link(a.getDistanceTo(b), a, b);
        }
    }

    record Point3D(int x, int y, int z) {

        static Point3D of(String line) {
            var parts = line.split(",");
            return new Point3D(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        }
        double getDistanceTo(Point3D other) {
            return sqrt(pow(this.x - other.x, 2)
                    + pow(this.y - other.y, 2)
                    + pow(this.z - other.z, 2));
        }
    }

    private void buildLinks() {
        for (int i = 0; i < inp.size() - 1; i++) {
            for (int j = i + 1; j < inp.size(); j++) {
                links.add(Link.from(inp.get(i), inp.get(j)));
            }
        }
        links.sort(Comparator.comparing(Link::distance));
    }

    @Override
    public Object part1() {
        List<Set<Point3D>> circuits = new ArrayList<>();
        for (int i = 0; i < LIMIT; i++) {
            var a = findFromCircuits(circuits, links.get(i).a());
            var b = findFromCircuits(circuits, links.get(i).b());

            if (a != null && b != null) {
                if (a.equals(b)) {
                    continue;
                }
                Set<Point3D> circuitToMerge = circuits.get(b);
                circuits.get(a).addAll(circuitToMerge);
                circuits.remove(circuitToMerge);
            } else if (a == null && b == null) {
                Set<Point3D> newCircuit = new HashSet<>();
                newCircuit.add(links.get(i).a());
                newCircuit.add(links.get(i).b());
                circuits.add(newCircuit);
            } else if (a != null) {
                circuits.get(a).add(links.get(i).b());
            } else {
                circuits.get(b).add(links.get(i).a());
            }
        }
        circuits.sort(Comparator.comparing(Set::size));
        int lastCircuit = circuits.size()-1;
        return circuits.get(lastCircuit).size() * circuits.get(lastCircuit-1).size() * circuits.get(lastCircuit-2).size();
    }

    private Integer findFromCircuits(List<Set<Point3D>> circuits, Point3D point) {
        for (int i = 0; i < circuits.size(); i++) {
            if (circuits.get(i).contains(point)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public Object part2() {
        List<Set<Point3D>> circuits = new ArrayList<>();
        Set<Point3D> pointsSeen = new HashSet<>();
        for (Link link : links) {
            var a = findFromCircuits(circuits, link.a());
            var b = findFromCircuits(circuits, link.b());

            pointsSeen.add(link.a());
            pointsSeen.add(link.b());

            if (a != null && b != null) {
                if (a.equals(b)) {
                    continue;
                }
                Set<Point3D> circuitToMerge = circuits.get(b);
                circuits.get(a).addAll(circuitToMerge);
                circuits.remove(circuitToMerge);
            } else if (a == null && b == null) {
                Set<Point3D> newCircuit = new HashSet<>();
                newCircuit.add(link.a());
                newCircuit.add(link.b());
                circuits.add(newCircuit);
            } else if (a != null) {
                circuits.get(a).add(link.b());
            } else {
                circuits.get(b).add(link.a());
            }

            if (pointsSeen.size() >= inp.size() && circuits.size() == 1) {
                return (long) link.a().x() * link.b().x();
            }
        }
        return null;
    }
}
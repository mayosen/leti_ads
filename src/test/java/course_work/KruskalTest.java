package course_work;

import lab_1.ArrayList;
import lab_1.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KruskalTest {
    List<String> getSpanningTree(String filename) {
        List<Kruskal.Edge> treeEdges = Defence.getSpanningTreeFromFile("src/test/resources/course_work/" + filename);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < treeEdges.size(); i++) {
            result.add(treeEdges.get(i).getEdgeString());
        }
        return result;
    }

    @Test
    void tree() {
        List<String> actual = getSpanningTree("tree.txt");
        List<String> expected = new ArrayList<>(new String[]{"AB", "BC", "CE", "DE", "CF", "FG"});
        assertEquals(expected, actual);
    }

    @Test
    void treeWiki() {
        List<String> actual = getSpanningTree("tree_wiki.txt");
        List<String> expected = new ArrayList<>(new String[]{"AD", "CE", "DF", "AB", "BE", "EG"});
        assertEquals(expected, actual);
    }
}

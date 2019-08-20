package hashset;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


public class MyHashSetTest {
    private MyHashSet<String> myHashSet = new MyHashSet<>();
    private List<String> startingData = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        startingData.add("one");
        startingData.add("two");
        startingData.add("three");
        startingData.add("four");
        startingData.add("five");
        startingData.add("six");
        startingData.add("seven");
        startingData.add("eight");
        startingData.add("nine");
        startingData.add("ten");
        startingData.forEach(el -> myHashSet.add(el));
    }

    @After
    public void setBack() {
        myHashSet = new MyHashSet<>();
        startingData.forEach(el -> myHashSet.add(el));
    }

    @Test
    public void add() {
        Assert.assertFalse(myHashSet.contains("test"));
        myHashSet.add("test");
        Assert.assertTrue(myHashSet.contains("test"));
    }

    @Test
    public void contains() {
        startingData.forEach(el -> Assert.assertTrue(myHashSet.contains(el)));
    }

    @Test
    public void remove() {
        myHashSet.add("test");
        Assert.assertTrue(myHashSet.contains("test"));
        Assert.assertTrue(myHashSet.remove("test"));
        Assert.assertFalse(myHashSet.contains("test"));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorMainWork() {
        Iterator<String> iterator = myHashSet.iterator();
        while (iterator.hasNext()) {
            Assert.assertTrue(startingData.contains(iterator.next()));
        }
        iterator.next();
    }

    @Test(expected = IllegalStateException.class)
    public void iteratorRemoveWork() {
        myHashSet.add("test");
        Iterator<String> iterator = myHashSet.iterator();
        iterator.remove();
        while (iterator.hasNext()) {
            if (iterator.next().equals("test")) {
                iterator.remove();
            }
        }
        Assert.assertFalse(myHashSet.contains("test"));
    }

//    @Test
//    public void testIter() {
//        myHashSet.add("eleven");
//        myHashSet.add("12");
//        myHashSet.add("13");
//        myHashSet.add("14");
//        myHashSet.forEach(System.out::println);
//    }
}
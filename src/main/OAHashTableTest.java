package main;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

public class OAHashTableTest {
    OAHashTable<Integer, String> tableOne = new OAHashTable<>();

    @Test
    public void contains(){
        tableOne.put(1, "ttt");
        Assert.assertTrue(tableOne.containsKey(1));
        Assert.assertTrue(tableOne.containsValue("ttt"));
    }

    OAHashTable<Integer, String> tableTwo = new OAHashTable<>(1);

    @Test
    public void size(){
        tableTwo.put(2, "sss");
        tableTwo.put(3, "sss");
        Assert.assertEquals(2, tableTwo.size());
    }

    @Test
    public void bigSizeCheck(){
        tableOne.clear();
        tableTwo.clear();
        for (int i = 0; i < 99; i++) {
            tableOne.put(i*2, "string" + i);
            tableTwo.put(i*2, "string" + i);
        }
        Assert.assertEquals(99, tableOne.size());
        Assert.assertEquals(tableOne, tableTwo);
    }

    @Test
    public void isEmpty(){
        tableTwo.clear();
        Assert.assertTrue(tableTwo.isEmpty());
    }

    @Test
    public void put() {
        tableOne.clear();
        tableOne.put(12312, "test");
        Assert.assertEquals("test", tableOne.get(12312));
        tableOne.put(12312, "t");
        Assert.assertEquals("t", tableOne.get(12312));
        Assert.assertEquals(1, tableOne.size());
    }
    @Test
    public void putAll() {
        tableTwo.clear();
        tableTwo.put(1,"t");
        tableTwo.put(2, "tt");
        OAHashTable<Integer, String> tableThree = new OAHashTable<>();
        tableThree.putAll(tableTwo);
        Assert.assertEquals(tableTwo,tableThree);
        Assert.assertEquals(2, tableThree.size());
        tableOne.clear();
        tableOne.put(3,"ttt");
        tableThree.putAll(tableOne);
        Assert.assertEquals(3,tableThree.size());
    }

    OAHashTable<Integer, String> tableTest1 = new OAHashTable<>();
    OAHashTable<Integer, String> tableTest2 = new OAHashTable<>();

    @Test
    public void hashCodes() {

        for (int i = 0; i < 100; i++) {
            tableTest1.put(i, "testtest" + i);
            tableTest2.put(i, "testtest" + i);
        }
        Assert.assertEquals(tableTest1.hashCode(), tableTest2.hashCode());
    }
    @Test
    public void iterator(){
        Iterator<Map.Entry<Integer, String>> it1 = tableTest1.iterator();
        Iterator<Map.Entry<Integer, String>> it2 = tableTest2.iterator();
        while (it1.hasNext()) {
            Assert.assertEquals(it1.next(), it2.next());
        }
    }

    @Test
    public void remove() {
        tableTest1.clear();
        tableTest1.put(1, "1");
        tableTest1.put(2, "2");
        tableTest1.put(3, "3");
        tableTest1.put(4, "4");
        Assert.assertEquals("1", tableTest1.remove(1));
        Assert.assertFalse(tableTest1.containsKey(null));
        Assert.assertFalse(tableTest1.containsValue("1"));
        Assert.assertFalse(tableTest1.containsValue(null));
        Assert.assertFalse(tableTest1.containsKey(1));
        Assert.assertTrue(tableTest1.containsKey(4));

    }
    @Test
    public void sets(){
        tableTest1.clear();
        tableTest1.put(3,"3");
        tableTest1.put(4,"4");
        tableTest2.put(3,"3");
        tableTest2.put(4,"4");
        Assert.assertEquals(tableTest2.values(), tableTest1.values());
        Assert.assertEquals(tableTest2.keySet(), tableTest1.keySet());
    }

    @Test
    public void toArray(){
        tableTest1.clear();
        tableTest1.put(0,"0");
        tableTest1.put(1,"1");
        tableTest1.put(2,"2");
        tableTest1.put(17,"3");
        Object[] testArr = tableTest1.toArray();
        Assert.assertEquals(new OAHashTable.Entry<>(0,"0"), testArr[0]);
        Assert.assertEquals(new OAHashTable.Entry<>(1,"1"), testArr[1]);
        Assert.assertEquals(new OAHashTable.Entry<>(2,"2"), testArr[2]);
        Assert.assertEquals(new OAHashTable.Entry<>(17,"3"), testArr[3]);
    }
}

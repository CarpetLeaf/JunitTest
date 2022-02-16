package ru.ac.uniyar.testingcourse;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

import static org.assertj.core.api.Assertions.*;

public class MyTest {

    //тест кейс проверяет, есть ли у  нас есть следующий элемент в итерацмм
    @Test
    void hasNextTest() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        assertThat(it.hasNext()).isTrue();
    }


    //тест кейс проверяет, если у нас нет следующего элемента в итерации
    @Test
    void hasNextListEmptyTest() {
        TreeSet<Integer> set = new TreeSet<>();
        Iterator<Integer> it = set.iterator();
        assertThat(it.hasNext()).isFalse();
    }


    //тест кейс проверяет, что метод next() возвращает следующий элемент в итерации
    @Test
    void nextTest(){
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        it.next();
        assertThat(it.next()).isEqualTo(2);
    }


    //тест кейс проверяет, что если в итерации нет больше элементов, то метод next() вызывает исключение NoSuchElementException
    @Test
    void nextNoSuchElementExceptionTest(){
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        Iterator<Integer> it = set.iterator();
        it.next();
        assertThatThrownBy(()->it.next()).isInstanceOf(NoSuchElementException.class);
    }

    //тест кейс проверяет, что при вызове метода remove(), удаляется последнией элемент, возвращаемый итератором
    @Test
    void removeTest(){
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        it.next();
        it.next();
        it.remove();
        assertThat(set).doesNotContain(2);
    }


    //тест кейс проверяет, что если не вызвали метод next(), то метод remove() вызовет исключение IllegalStateException
    @Test
    void removeIfNextMethodNotCalled(){
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        assertThatThrownBy(()-> it.remove()).isInstanceOf(IllegalStateException.class);
    }


    //next -> remove -> remove
    @Test
    void removeAfterNext(){
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        it.next();
        it.remove();
        assertThatThrownBy(()-> it.remove()).isInstanceOf(IllegalStateException.class);
    }


    //тест кейс проверяет, что операция remove не поддерживается данным итератором
    @Test
    void removeNotSupportedByIterator(){
        Set<String> set = new TreeSet<>();

        set.add("hello");
        set.add("world");

        Set<String> unmodifiableSet = Collections.unmodifiableSet(set);

        Iterator<String> it = unmodifiableSet.iterator();
        it.next();
        assertThatThrownBy(()-> it.remove()).isInstanceOf(UnsupportedOperationException.class);
    }

    //Проверяет, что метод removeIf удаляет хотя бы 1 элемент из коллекции по заданному фильтру
    @Test
    void removeIfTest(){
        LinkedBlockingDeque<Integer> deq = new LinkedBlockingDeque<Integer>(3);
        deq.add(2);
        deq.add(4);
        deq.add(1);
        assertThat(deq.removeIf(num -> num % 2 == 0)).isTrue();
    }

    //Проверяет , что метод removeIf не удаляет ни один элемент из коллекции по заданному фильтру
    @Test
    void removeIfNoElementTest(){
        LinkedBlockingDeque<Integer> deq = new LinkedBlockingDeque<Integer>(3);
        deq.add(2);
        deq.add(4);
        deq.add(6);
        assertThat(deq.removeIf(num -> num % 7 == 0)).isFalse();
    }

    //Проверка на получение исключения при условии, что filter is null
    @Test
    void removeIfNullPointerExceptionTest(){
        LinkedBlockingDeque<Integer> deq = new LinkedBlockingDeque<Integer>(3);
        deq.add(2);
        deq.add(4);
        deq.add(6);
        assertThatThrownBy(()->deq.removeIf(null)).isInstanceOf(NullPointerException.class);
    }

    //Проверка на получение исключения при условии, что вставляемый элемент равен null
    @Test
    void putNullPointerExceptionTest() throws InterruptedException{
        LinkedBlockingDeque<Integer> deq = new LinkedBlockingDeque<Integer>(3);
        assertThatThrownBy(()->deq.put(null)).isInstanceOf(NullPointerException.class);
    }
}

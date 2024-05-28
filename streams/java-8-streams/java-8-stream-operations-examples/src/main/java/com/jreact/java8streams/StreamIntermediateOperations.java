package com.jreact.java8streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/*
* Stream intermediate operations return another Stream which allows you to call multiple operations
* in the form of a query.
* Stream intermediate operations do not get executed until a terminal operation is invoked.
* All Intermediate operations are lazy, so they’re not executed until a result of processing is actually needed.
*
* Here is the list of all Stream intermediate operations:
* stream.concat - Creates a lazily concatenated stream whose elements are all the elements of the first stream followed by all the elements of the second stream.
* filter() -   Returns a stream consisting of the elements of this stream that match the given predicate.
*
* map() -      The Java Stream map() method converts (maps) an element to another object.
*              For instance, if you had a list of strings it could convert each string to lowercase, uppercase,
*              or to a substring of the original string, or something completely else.
*
* flatMap() -  The Stream.flatMap() function, as the name suggests, is the combination of a map and a flat operation.
*              This means you first apply the map function and then flatten the result.
*              To understand what flattening a stream consists in, consider a structure like [ [1,2,3],[4,5,6],[7,8,9] ] which has "two levels".
*              It's basically a big List containing three more List.
*              Flattening this means transforming it in a "one level" structure e.g. [ 1,2,3,4,5,6,7,8,9 ] i.e. just one list.
*
* distinct() - The Java Stream distinct() method is a non-terminal operation that returns a new Stream
*              that will only contain the distinct elements from the original stream. Any duplicates will be eliminated.
*
* limit() -    It will truncate the remaining elements from the stream.
*
* peek() -     Stream<T> peek(Consumer<? super T> action) This intermediate operation returns a stream consisting of the elements of this stream,
*              additionally performing the provided action on each element as elements are consumed from the resulting stream.
*              The peek method is the intermediate method, so it will not execute until the terminal method is invoked.
*
* skip() -     This method skips the given n elements and returns a Stream.
*
* sorted() -   Returns a stream consisting of the elements of this stream, sorted according to the natural order.
*/
public class StreamIntermediateOperations {

    public void concatStringStreams() {
        Stream<String> s1 = Stream.of("one", "two", "three");
        Stream<String> s2 = Stream.of("four", "five", "six");

        Stream<String> s3 = Stream.concat(s1, s2);
        s3.forEach(System.out::println);
    }

    public void concatLongStreams() {
        LongStream Stream1 = LongStream.of(1456, 1537);
        LongStream Stream2 = LongStream.of(1891, 2087);

        LongStream.concat(Stream1, Stream2).forEach(System.out::println);
    }

    public void concatCollectionsStreams() {
        Collection<String> s1 = Arrays.asList("zjc", "stream");
        Collection<String> s2 = Arrays.asList("collect", "collections");

        Stream<String> combinedStream = Stream.of(s1, s2).flatMap(Collection::stream);
        Collection<String> collectionCombined = combinedStream.toList();

        collectionCombined.forEach(System.out::println);
    }

    // Returns a stream consisting of the elements of this stream that
    // match the given predicate.
    public void streamFilter() {
        Stream<Integer> intStream = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> subStream = intStream.filter(value -> value > 3);
        long count = subStream.count();
        System.out.println(count);
    }

    // List of String you convert to a List of Integer. Use map() to do so.
    public void streamMap() {
        List<String> listOfStrings = Arrays.asList("1", "2", "3", "4", "5");

        List<Integer> listOfIntegers = listOfStrings.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        System.out.println(listOfIntegers);
    }

    // Below you can see that we have three lists that are merged into one by using a flatMap() function.
    public void streamFlatMap() {
        List<Integer> evens = Arrays.asList(2, 4, 6);
        List<Integer> odds = Arrays.asList(3, 5, 7);
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11);
        List<Integer> numbers = Stream.of(evens, odds, primes)
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());
        System.out.println("flattend list: " + numbers);
    }

    // distinct() Operation
    public void streamDistinct() {
        Stream<String> fruitsStream = Stream.of("Apple", "Jack Fruit", "Water Melon", "Apple");
        Stream<String> distinctStream = fruitsStream.distinct();
        distinctStream.forEach(name -> System.out.println(name));
    }

    // It will truncate the remaining elements from the stream.
    public void streamLimit() {
        Stream.of("one", "two", "three", "four").limit(2).forEach(item -> System.out.println(item));
    }

    // There will be no output.
    public void streamPeek01() {
        Stream.of(10, 20, 30).peek(e -> System.out.println(e));
    }

    //  The peek method is used mainly for debugging.
    public void streamPeek02() {
        Stream.of(10, 11, 12, 13)
                .filter(n -> n % 2 == 0)
                .peek(e -> System.out.println("Debug filtered value: " + e))
                .map(n -> n * 10)
                .peek(e -> System.out.println("Debug mapped value: " + e))
                .collect(Collectors.toList());
    }

    // java 9
    public void streamPeek03() {
        long cnt = Stream.of(10, 11, 12, 13)
                .peek(e -> System.out.println("Debug: " + e))
                .count();
        System.out.println(cnt);
    }

    // This method skips the given n elements and returns a Stream.
    public void streamSkip() {
        Stream.of("one", "two", "three", "four", "five").skip(2).forEach(item -> System.out.println(item));
    }

    public void streamSorted() {
        Stream<String> vegStream = Stream.of("cabbage", "Green Chilli", "Potato", "Beet root");
        Stream<String> sortedStream = vegStream.sorted();
        sortedStream.forEach(name -> System.out.println(name));
    }
}

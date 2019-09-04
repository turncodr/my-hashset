# my-hashset
A simple implementation of a HashSet using separate chaining with linked lists

## Challenge
Implement an efficient Iterator for the class HashSet. HashSet implements Iterable, hence, you have to implement the iterator() method of the class. The class already contains a stub for this method.

The requirements of an Iterator are formulated in the Javadoc of the Iterator interface:
https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

What does 'efficient' mean in this context?
- Running time: The internal data structure is an ArrayList of LinkedLists. It is in the nature of hashing that the internal structure is sparse. That means only few positions in the ArrayList really contain a LinkedList with hopefully only very few (ideal: only one) elements. Before an iteration, we don't no these positions, i.e. we have to check each position of the ArrayList sequentially until we find a next element. Therefore, an iteration of the HashSet is efficient if each place in the structure is visited only once!
- Space: We want to be efficient concerning usage of memory, too. That means, we want to avoid copying elements or lists of the internal structure. Nevertheless, it is definitely necessary to store the current state of the iteration in the Iterator object (how can this be done best?), since there might be some time between subsequent calls to the next() method. Be careful, the hasNext() method should not change this state, since it can be called several times without calling the next() method.
- BTW, our HashSet class guarantees no order for the iteration (e.g. insertion order or natural order)!
- Currently, I skipped the remove() method of the iterator to reduce complexity. So in a first draft I am happy with a read only iterator. If you are bored by that, don't hesitate to implement the remove() method as well.

## Git Workflow
Before you start:
* Fork the repository.
* Clone your fork and set my repo as upstream remote.
* Implement your Iterator in a new branch.
* If you have finished create a Pull-Request.

Following these rules will help me to compare your solutions easily (and to integrate the best one in the master branch:-))

## Getting started
The repository contains a gradle project. You can build it best by using the contained gradle wrapper:
* Linux/macOS> ./gradlew build
* Windows> gradlew.bat build

Feel free to use the power of the other gradle tasks (like e.g. test) as well. The project contains only two Java classes. The class HashSet and the corresponding test class HashSetTest. If you start the gradle build task everything is compiled and the test are executed. Since the implementation of the Iterator is missing, the respective test method fails. In the build folder you'll find a nice html page presenting the test results. After your implementation the tests should be green.

## Finally

I hope that I did not make to many bugs! ;-)
Have fun!

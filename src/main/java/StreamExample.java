import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

//STREAMS 101
//1. LAMBDA EXPRESSIONS THROUGH FUNCTIONAL INTERFACE
//2. STREAMS, INTERMEDIATE AND TERMINAL OPERATIONS
//3. EXAMPLES

public class StreamExample {

    //Access modifiers, return type, name declaration, parameters/properties, logic...
    public static int oldWayToSquareNumbers(int x) {
        return x * x;
    }

    public static void main(String[] args) {

    ////////////////////////////lambda expressions and FUNCTIONAL INTERFACE////////////////////////////////////////////

        final Function<Integer, Integer> squareFunction = x -> x * x;
        final Predicate<String> usingStringInVariable = type -> type.contains("Cargo");

    ////////////////////////////Streams//////////////////////////////////////////////////////

        final List<String> allShipTypes = List.of("Break Bulk Cargo", "Neo Bulk Cargo", "Unitized Cargo", "Liquid Bulk", "Dry Bulk");
        final List<Integer> listOfNumbers = List.of(1,2,3,4,5);
        final List<List<Integer>> listOfNumberLists = List.of(listOfNumbers, listOfNumbers, listOfNumbers);

        System.out.println("---------------List of Lists - nested structure--------------------");
        listOfNumberLists.forEach(System.out::println);

        System.out.println("------------------------Old way of writing code-------------------------");
        for (int i = 0; i < allShipTypes.size(); i++)
        {
            if (allShipTypes.get(i).contains("Cargo"))
            {
                System.out.println(allShipTypes.get(i));
            }
        }

        System.out.println("----------Using stream just to print our filtered results----------------");
        allShipTypes.stream()//entering abstraction
                    .filter(type -> type.contains("Cargo"))//Intermediate operation - result is always stream
                    .forEach(System.out::println);//Terminal operation - result is what we expect or need

        final List<String> onlyCargoType = allShipTypes.stream()
                    .filter(type -> type.contains("Cargo"))
                    .collect(Collectors.toList());
        System.out.println("-------This is our new collection that we got using Collector class------");
        onlyCargoType.forEach(System.out::println);

        final String gettingOnlyOneOfShips = onlyCargoType.stream()
                    .filter(oneCargoShip -> oneCargoShip.contains("Break"))
                    .findFirst()
                    .orElse("there is no element matching given criteria");
        System.out.println("--------------Getting only one item from our new collection--------------");
        System.out.println(gettingOnlyOneOfShips);

        System.out.println("--------------Example of using map() intermediate operation--------------");
        final List<Integer> newListOfSquaredNumbers = listOfNumbers.stream()
                .map(x -> x * x)
                .collect(Collectors.toList());
            newListOfSquaredNumbers.forEach(System.out::println);

        System.out.println("--------------Example of using flatmap() intermediate operation--------------");
        final List<Integer> newFlattenedList = listOfNumberLists.stream()
                .flatMap(numbers -> numbers.stream())
                .map(x -> x * x)
                .collect(Collectors.toList());
            newFlattenedList.forEach(System.out::println);

        System.out.println("--------------More terminal operations such as count--------------");
        System.out.println(newListOfSquaredNumbers.stream().count());

    }
}

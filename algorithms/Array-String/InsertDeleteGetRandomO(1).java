/**
 * Implement the RandomizedSet class:
 *  RandomizedSet() Initializes the RandomizedSet object.
 *  bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 *  bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 *  int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class RandomizedSet {

    private final List<Integer> list;
    private final Map<Integer, Integer> map;
    private final Random random;

    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }
    
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        // Запоминаем индекс, под которым элемент встанет в ArrayList
        map.put(val, list.size());
        list.add(val);
        return true;
    }
    
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        
        // Получаем индекс удаляемого элемента
        int indexToRemove = map.get(val);
        int lastElement = list.get(list.size() - 1);
        
        // Если удаляемый элемент не последний, меняем его местами с последним
        if (indexToRemove != list.size() - 1) {
            list.set(indexToRemove, lastElement);
            map.put(lastElement, indexToRemove); // Обновляем индекс бывшего последнего элемента в Map
        }
        
        // Безопасно удаляем последний элемент за O(1)
        list.remove(list.size() - 1);
        map.remove(val);
        
        return true;
    }
    
    public int getRandom() {
        // Возвращаем случайный элемент из списка по случайному индексу
        return list.get(random.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */


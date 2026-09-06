/**
 * Given an array of strings strs, group the together. You can return the answer in any order.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */ 

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // Если массив пустой, возвращаем пустой список 
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        // Карта для группировки, ключ - слово отсортированное по алфавиту, значение - список анаграмм 
        Map<String, List<String>> map = new HashMap<>();
        
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            // Получаем отсортированную строку, которая служит уникальным ключом 
            String key = String.valueOf(chars);

            // Если такого ключа еще нет, создаем для него новый список 
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(s);
        }

        // Возвращаем списки отсортированных слов 
        return new ArrayList<>(map.values());
    }
}


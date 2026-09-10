/**
 * You are given an absolute path for a Unix-style file system, which always begins with a slash '/'. Your task is to transform this absolute path into its simplified canonical path.
 * The rules of a Unix-style file system are as follows:
 *   A single period '.' represents the current directory.
 *   A double period '..' represents the previous/parent directory.
 *   Multiple consecutive slashes such as '//' and '///' are treated as a single slash '/'.
 *   Any sequence of periods that does not match the rules above should be treated as a valid directory or file name. For example, '...' and '....' are valid directory or file names.
 * The simplified canonical path should follow these rules:
 *   The path must start with a single slash '/'.
 *   Directories within the path must be separated by exactly one slash '/'.
 *   The path must not end with a slash '/', unless it is the root directory.
 *   The path must not have any single or double periods ('.' and '..') used to denote current or parent directories.
 * Return the simplified canonical path.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) 
 * 
 * @author Alexander Kuziv <makklays@gmail.com>
 */ 

class Solution {
    public String simplifyPath(String path) {
        // Разделяем путь по слэшам. Дубликаты слэшей превратятся в пустые строки ""
        String[] components = path.split("/");
        Deque<String> stack = new ArrayDeque<>();

        for (String component : components) {
            // Если элемент пустой или ".", ничего не делаем
            if (component.isEmpty() || component.equals(".")) {
                continue;
            }
            
            // Если "..", возвращаемся на уровень выше (удаляем из стека)
            if (component.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.removeLast();
                }
            } else {
                // Любое другое имя папки (включая "...") добавляем в стек
                stack.addLast(component);
            }
        }

        // Собираем итоговую строку
        StringBuilder sb = new StringBuilder();
        for (String dir : stack) {
            sb.append("/").append(dir);
        }

        // Если стек был пуст, вернется "/", иначе собранный путь
    }
}


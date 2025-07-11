package by.it.group451003.deverilin.lesson06;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/


public class C_LongNotUpSubSeq {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_LongDivComSubSeq.class.getResourceAsStream("dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        int[] tail = new int[n];
        int[] pos = new int[n];
        int[] prev = new int[n];
        int len = 0;

        for (int i = 0; i < n; i++) {
            int current = m[i];
            int left = 0;
            int right = len;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tail[mid] < current) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            int j = left;

            if (j == len) {
                tail[len] = current;
                pos[len] = i;
                len++;
            } else {
                tail[j] = current;
                pos[j] = i;
            }

            prev[i] = (j > 0) ? pos[j - 1] : -1;
        }

        System.out.println(len);

        if (len == 0) {
            return len;
        }

        List<Integer> indices = new ArrayList<>();
        int current = pos[len - 1];
        while (current != -1) {
            indices.add(current + 1);
            current = prev[current];
        }
        Collections.reverse(indices);

        StringBuilder sb = new StringBuilder();
        for (int idx : indices) {
            sb.append(idx).append(' ');
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb);

        return len;

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }

}
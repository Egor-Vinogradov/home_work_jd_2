import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class DataContainer<T> implements Iterable<T> {
    private T[] data;

    /**
     * default конструктор
     * Создает новый массив с 10 элементами
     */
    public DataContainer() {
        this.data = (T[]) new Object[10];
    }

    /**
     * конструктор принимает массив и передает его в массив класса
     * @param data принимаемый массив
     */
    public DataContainer(T[] data) {
        this.data = data;
    }

    /**
     * метод принимает значение и передает его в массив класса
     * @param item переданный элемент
     * @return возвращает -1 если передан null, либо возвращает номер индекса массива
     */
    public int add(T item) {
        int index = this.data.length;

        // проверка на null
        if (item == null) {
            return -1;
        }

        // цикл ищет первый null и вставляет значение
        for (int i = 0; i < index; i++) {
            if (this.data[i] == null) {
                this.data[i] = item;
                return i;
            }
        }

        // проверка на переполнение и вставка элемента
        this.data = Arrays.copyOf(this.data, index + 1);
        this.data[index] = item;

        return index;
    }

    /**
     * возвращает элемент массива по переданому индексу
     * @param index индекс
     * @return элемент
     */
    public T get(int index) {
        if (index < this.data.length - 1 && index > -1) {
            return this.data[index];
        }
        return null;
    }

    /**
     * Возвращает весь массив
     * @return массив
     */
    public T[] getItems() {
        return this.data;
    }

    /**
     * Удаляет элемент из массива по переданному индексу. Уменльшает длину массива на один элемент
     * @param index индекс массива
     * @return возвращает значение true либо false, в зависимости от результата
     */
    public boolean delete(int index) {
        // возвращает false если передан индекс не в диапазоне массива
        if (index > this.data.length - 1 || index < 0) return false;

        // уменьшает длину массива на 1 и возвращает true
        T[] copyArray = (T[]) new Object[this.data.length - 1];
        int indexArray = 0;
        this.data[index] = null;
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] != null) {
                copyArray[indexArray] = this.data[i];
                indexArray++;
            }
        }
        this.data = copyArray;
        return true;
    }

    /**
     * Удаляет переданный элемент из массива. Уменьшает длину массива на один элемент
     * @param item элемент массива
     * @return возвращает значение true либо false, в зависимости от результата
     */
    public boolean delete(T item) {
        T[] copyArray = (T[]) new Object[this.data.length - 1];
        int indexArray = 0;
        // проходим по массиву, если находим, тогда первую часть пишем в новый массив
        for (int i = 0; i < this.data.length; i++) {
            if (item.equals(this.data[i])) {
                break;
            }
            // проверяем на переполнение. если переполнен, тогда возвращаем false, т.к. элемент не найден
            if (indexArray >= this.data.length - 1) return false;

            copyArray[indexArray] = this.data[i];
            indexArray++;
        }

        // добавляем вторую часть массива, после удаленного элемента
        System.arraycopy(this.data, indexArray + 1, copyArray, indexArray,
                this.data.length - indexArray - 1);
        this.data = copyArray;
        return true;
    }

    /**
     * Сортирует массив при помощи переданного компоратора
     * @param comparator компоратор для сортировки. Может быть null
     */
    public void sort(Comparator<? super T> comparator) {
        // проверка на наличие компоратора
        // если компоратор не передан
        if (comparator == null) {
            // пока такой вариант при переданом null
            try {
                for (int i = 0; i < this.data.length; i++) {
                    // проверка на null в элементах массива
                    // применяется, если в массиве среди элементов есть null
                    if (this.data[i] == null) {
                        continue;
                    }
                    // сортировка при помощи Comparable
                    for (int j = i; j > 0 && ((Comparable) this.data[j - 1]).compareTo(this.data[j]) > 0; j--) {
                        swap(this.data, j, j - 1);
                    }
                }
            } catch (Exception e) {
                System.out.println("Значение массива не является значением примитивного типа!");
            }
            // если компоратор передан
        } else {
            for (int i = 0; i < this.data.length; i++) {
                for (int j = i; j > this.data.length && comparator.compare(this.data[j - 1], this.data[j]) > 0; j--)
                    swap(this.data, j, j - 1);
            }
        }
    }

    /**
     * Метод для перестановки значений для сортировки
     * @param x переданный массив
     * @param a индекс первоначальный
     * @param b индекс куда переставлять
     */
    private static void swap(Object[] x, int a, int b) {
        Object t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    @Override
    public String toString() {
        int indexArray = 0;

        // проходим по массиву, считаем индексы без null
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) indexArray++;
        }

        // создаем новый массив
        T[] copyArray = (T[]) new Object[indexArray];

        // пишем элементы не null в новый массив
        indexArray = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                copyArray[indexArray] = data[i];
                indexArray++;
            }
        }

        return "data = " + Arrays.toString(copyArray);
    }

    /**
     * Статический метод сортирует переданный в него объект типа DataContainer по
     * переданному в него Comparator
     * @param container объект типа DataContainer
     * @param comparator Comparator для сортировки
     */
    public static void sort(DataContainer<?> container, Comparator<Object> comparator) {
        // получаем размер переданного массива и создаем новый массив из переданного объекта
        int size = container.getItems().length;
        Object[] newArray = container.getItems();

        // проверка на наличие компоратора
        // если компоратор не передан
        if (comparator == null) {
            for (int i = 0; i < size; i++) {
                // проверка на null в элементах массива
                // применяется, если в массиве среди элементов есть null
                if (newArray[i] == null) {
                    continue;
                }
                // сортировка при помощи Comparable
                for (int j = i; j > 0 && ((Comparable) newArray[j - 1]).compareTo(newArray[j]) > 0; j--) {
                    swap(newArray, j, j - 1);
                }
            }
            // если компоратор передан
        } else {
            for (int i = 0; i < size; i++) {
                for (int j = i; j > size && comparator.compare(newArray[j - 1], newArray[j]) > 0; j--)
                    swap(newArray, j, j - 1);
            }
        }
    }

    /**
     * Реализация взята из урока
     * @param container массив, насследуемый от компоратора
     * @param <Z> тип компоратора
     */
    public static <Z extends Comparable> void sort(DataContainer<Z> container) {
        if(container != null) {
            for (int i = 0; i < container.data.length - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < container.data.length; j++) {
                    if(container.data[j].compareTo(container.data[minIndex]) < 0) {
                        minIndex = j;
                    }
                    swap(container.data, j, j - 1);
                }
            }
        }
    }

    /**
     * Реализация интерфейса Iterable
     * @return возвращает Iterator
     */
    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new Iterator<T>() {

            int currentIndex = 0;
            int size = data.length;

            @Override
            public boolean hasNext() {
                return currentIndex < size && data[currentIndex] != null;
            }

            @Override
            public T next() {
                return data[currentIndex++];
            }
        };
        return iterator;
    }
}

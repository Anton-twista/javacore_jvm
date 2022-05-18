package org.example;


public class JvmComprehension {

//      Сначала класс JvmComprehension отдаётся для загрузки Bootstrap -> Platform -> Application
//      Ищет на каждом уровне и загружает, если находит.
//      Далее проходит проверку, подготовку, разрешение ссылок и инициализацию
//      Помещается в хранилище Metaspace.

//      Далее выделяются области памяти Heep и Stack
//      В стеке создаётся новый фрейм(кадр) с названием Main
    public static void main(String[] args) {

        int i = 1;                      // 1 - В фрейме Main, в Stack создаётся переменная int i = 1;

        Object o = new Object();        // 2 - New Object создаётся в Heep,
                                        // а в фрейме Main ссылка на этот объект

        Integer ii = 2;                 // 3 - Integer ii = 2; создаётся в Heep, а в фрейме Main ссылка на него

        printAll(o, i, ii);             // 4 - В Stack создаётся новый фрейм PrintAll со ссылками

        System.out.println("finished"); // 7 - В Stack создаётся новый фрейм sout со строкой которая ссылается на String в Heep


    }

    private static void printAll(Object o, int i, Integer ii) {
        Integer uselessVar = 700;                   // 5 - Integer uselessVar = 700; создаётся в Heep,
                                                    // а в фрейме printAll ссылка

        System.out.println(o.toString() + i + ii);  // 6
        // В Stack поверх фрейма printAll, создаётся фрейм sout, в нём вызывается
        //ссылка "о"(которая ссылается на объект в Heep),
        //вызывает метод toString(), который создаёт фрэйм toString поверх sout;
        //далее ссылка на переменную i = 1;
        //далее ссылка ii ссылается на ту что находится в heep равна 2;

        //Завершив метод printAll, он удалится из стека со в всеми вызванными в нём методами sout и toString,
        //оставив после себя незадействованную ссылку uselessVar в куче,
        //которую в дальнейшем может удалить сборщик мусора
    }

}

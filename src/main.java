import org.jetbrains.annotations.NotNull;

import java.util.Scanner;



public class main {

    protected enum NumRoman {
        I ,
        II ,
        III,
        IV,
        V,
        VI,
        VII,
        VIII,
        IX,
        X }




    public  static void main (String[] args){
        System.out.println("Введите строку, содержащую выражение для вычисления( Пример: 3+5 или V-II");
        System.out.println("Ограничения: арифметические операторы +,-,*,/");
        System.out.println("Аргументы: числа арабские, римские  натуральные 0-10, ");
        System.out.println("Аргументы могут быть одновременно только арабскими или римскими цифрами");
        System.out.println("Остановка работы калькулятора - строка stop");

        Scanner locale=new Scanner (System.in);
        while (true) {
            System.out.print(":::");
            if (locale.hasNextLine()) {
                String myString = locale.nextLine();
                if (myString.equals("stop")) {
                    System.out.println("Работа калькулятора завершена");
                    break;
                } else {

                    System.out.println(Calc(myString));
                }

            } else {
                System.out.println("Ошибка ввода строки, повторите ввод или наберите Stop, для выхода из программы");
            }
        }




    }

    private static @NotNull String Calc (@NotNull String myString)
    {

        String testString= myString;
        testString=testString.trim();

        if  (testString.matches("[+\\-*/]")) {
          System.out.println("Выражение корректно. Операция встречается всего 1 раз");
        }
        String[] exStrings= new String[2];
        int arg1;
        int arg2;
        int resultInt=0;
        String resultString="";
        String resultCalc="";
        String resultCalcEnd="";
        String resultBeginEndFlag = "";
        String operation="";
        boolean exists = true;


        if (testString.length()>2) {  //проверяем на минимальную длину выражения (минимум 3 символа)

                try {
                    exStrings = testString.split("[+\\-*/]");
                    // NumRoman.valueOf(testString.substring(0, 1));
                    int len=exStrings[0].length();
                    operation=testString.substring(len, len+1);
                    exStrings[0]=exStrings[0].trim();
                    exStrings[1]=exStrings[1].trim();

                    //operation=testString.substring(len, len+1);
                    System.out.println("Разобрали  строку "+exStrings[0]+"_"+exStrings[1]);
                    System.out.println("Операция "+operation);

                } catch (IllegalArgumentException e) {
                    exists = false;
                    resultCalcEnd = " Ошибка деления строки на подстроки";
                }
                try {
                    if (exists & (exStrings[0].trim().matches("[IVX]{1,4}")) & exStrings[1].trim().matches("[IVX]{1,4}")) { // (exists & (NumRoman.valueOf(exStrings[0]) & NumRoman.valueOf(exStrings[1]))

                        NumRoman arg1Enum = NumRoman.valueOf(exStrings[0]);
                        NumRoman arg2Enum = NumRoman.valueOf(exStrings[1]);
                        arg1 = arg1Enum.ordinal()+1;
                        arg2 = arg2Enum.ordinal()+1;

                        resultCalc = "... Аргументы римские цифры: "+arg1+" "+arg2;
                        switch (operation) {
                                case "+":
                                    resultInt = arg1 + arg2;
                                    break;

                                case "*":
                                    resultInt = arg1 * arg2;
                                    break;

                                case "-":
                                    if (arg1>arg2) {
                                        resultInt = arg1 - arg2;
                                    }
                                    else {
                                        System.out.println("Результат выражения римскими цифрами не может быть <=0");
                                    }
                                    break;

                                case "/":
                                    resultInt = arg1 / arg2;
                                    break;
                                default:
                                    System.out.println("Операция не распознана");
                        }
                        resultString= arabic2Roman100(resultInt);

                    } else if (exists & (exStrings[0].trim().matches("\\d{1,2}")) & exStrings[1].trim().matches("[\\d]{1,2}")) {
                        resultCalc = "... Проверка успешна - аргументы арабские цифры";
                        arg1 =     Integer.parseInt(exStrings[0]);
                        arg2 =     Integer.parseInt(exStrings[1]);
                        if (arg1<11&arg1>=0&arg2<11&arg2>=0) {
                            switch (operation) {
                                case "+" -> resultInt = arg1 + arg2;
                                case "*" -> resultInt = arg1 * arg2;
                                case "-" -> resultInt = arg1 - arg2;
                                case "/" -> resultInt = arg1 / arg2;
                                default -> System.out.println("Операция не распознана");
                            }
                            resultString= Integer.toString(resultInt);
                        } else {
                            System.out.println("Значения аргументов больше 10");
                        }
                    } else {
                        resultCalc = "... Аргументы НЕ римские цифры и не арабские!!! или не одинакового типа с двух сторон";
                    }
                } catch (IllegalArgumentException e)  {
                exists = false;
                resultCalcEnd = " Выражение не соответствует условию...";
            }

        } else {
                //
            resultCalc="";

           // resultBeginEndFlag = " Кол-во символов выражения меньше минимального (3 символа)";
           // throw new MyException ("String can not be empty!");
        }
        return resultCalc+resultCalcEnd+resultBeginEndFlag+" результат: "+resultString;

    }

    private static @NotNull String arabic2Roman100 (int arabicNum) {

        String romanNum100;
        NumRoman[] romanArrNum10;
        romanArrNum10=NumRoman.values();
        String[] romanArrStr10= new String[11];
        romanArrStr10[0]="";
        for (int i=1 ; i<=10; i++) {
            romanArrStr10[i]=romanArrNum10[i-1].toString();
        }

        if (arabicNum<=10) {
            romanNum100= romanArrStr10[arabicNum];
        } else if (arabicNum<20) {
            romanNum100="X"+ romanArrStr10[arabicNum%10];
        } else if (arabicNum<30) {
            romanNum100="XX"+ romanArrStr10[arabicNum%10];
        }
        else if (arabicNum<40)  {
            romanNum100 = "XXX" + romanArrStr10[arabicNum%10 ];
        }
        else if (arabicNum<50) {
            romanNum100 = "XL" + romanArrStr10[arabicNum%10 ];
        }
        else if (arabicNum<60) {
            romanNum100 = "L" + romanArrStr10[arabicNum%10 ];
        }
        else if (arabicNum<70) {
            romanNum100 = "LX" + romanArrStr10[arabicNum%10 ];
        }
        else if (arabicNum<80) {
            romanNum100 = "LXX" + romanArrStr10[arabicNum%10 ];
        }
        else if (arabicNum<90) {
            romanNum100 = "LXXX" + romanArrStr10[arabicNum%10 ];
        }
        else if (arabicNum<100) {
            romanNum100 = "XC" + romanArrStr10[arabicNum%10 ];
        }
        else {
            romanNum100 = "C";
        }

        return romanNum100;
    }
}

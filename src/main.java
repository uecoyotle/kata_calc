

import com.sun.tools.javac.Main;

import java.util.*;
import java.lang.*;
import java.text.*;
import java.math.*;


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

    //private NumRoman arg1Enum;
    //private NumRoman arg2Enum;


    public static void main(String[] args){
        System.out.println("������� ������, ���������� ��������� ��� ����������( ������: 3+5 ��� V-II");
        System.out.println("�����������: �������������� ��������� +,-,*,/");
        System.out.println("���������: ����� ��������, �������  ����������� 0-10, ");
        System.out.println("��������� ����� ���� ������������ ������ ��������� ��� �������� �������");
        System.out.println("��������� ������ ������������ - ������ stop");

        Scanner locale=new Scanner (System.in);
        while (true) {
            System.out.print(":::");
            if (locale.hasNextLine()) {
                String myString = locale.nextLine();
                if (myString.equals("stop")) {
                    System.out.println("������ ������������ ���������");
                    break;
                } else {

                    System.out.println(Calc(myString));
                }

            } else {
                System.out.println("������ ����� ������, ��������� ���� ��� �������� Stop, ��� ������ �� ���������");
            }
        }




    };

    private static String Calc (String myString)
    {

        String testString= myString;
        testString=testString.trim();
        String ss= myString.trim();
        //testString=testString.replace(' ',''); //������� ��������� ������� � ���������

        //System.out.println("������������ �������:" +testString.matches("[^\\d\\s]")); // [\+\-\*/] [^\d\s+-/*IVX]  [^[\d\s[IVX]]]
        if  (testString.matches("[+\\-*/]{1}")) {
          System.out.println("��������� ���������. �������� ����������� ����� 1 ���");
        }
        String[] exStrings= new String[2];
        int arg1 = 0;
        int arg2 = 0;
        int resultInt = 0;
        String resultString="";
        String resultCalc="";
        String resultCalcEnd="";
        String resultBeginEndFlag = "";
        String operation="";
        boolean exists = true;


        if (testString.length()>2) {  //��������� �� ����������� ����� ��������� (������� 3 �������)

                try {
                    exStrings = testString.split("[\\+\\-\\*/]");
                    // NumRoman.valueOf(testString.substring(0, 1));
                    int len=exStrings[0].length();
                    operation=testString.substring(len, len+1);
                    exStrings[0]=exStrings[0].trim();
                    exStrings[1]=exStrings[1].trim();

                    //operation=testString.substring(len, len+1);
                    System.out.println("���������  ������ "+exStrings[0]+"_"+exStrings[1]);
                    System.out.println("�������� "+operation);

                } catch (IllegalArgumentException e) {
                    exists = false;
                    resultCalcEnd = " ������ ������� ������ �� ���������";
                }
                try {
                    if (exists & (exStrings[0].trim().matches("[IVX]{1,4}")) & exStrings[1].trim().matches("[IVX]{1,4}")) { // (exists & (NumRoman.valueOf(exStrings[0]) & NumRoman.valueOf(exStrings[1]))

                        NumRoman arg1Enum = NumRoman.valueOf(exStrings[0]);
                        NumRoman arg2Enum = NumRoman.valueOf(exStrings[1]);
                        arg1 = arg1Enum.ordinal()+1;
                        arg2 = arg2Enum.ordinal()+1;

                        resultCalc = "... ��������� ������� �����: "+arg1+" "+arg2;
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
                                        System.out.println("��������� ��������� �������� ������� �� ����� ���� <=0");
                                    }
                                    break;

                                case "/":
                                    resultInt = arg1 / arg2;
                                    break;
                                default:
                                    System.out.println("�������� �� ����������");
                        }
                        resultString= arabic2Roman100(resultInt);

                    } else if (exists & (exStrings[0].trim().matches("[\\d]{1,2}")) & exStrings[1].trim().matches("[\\d]{1,2}")) {
                        resultCalc = "... �������� ������� - ��������� �������� �����";
                        arg1 =     Integer.parseInt(exStrings[0]);
                        arg2 =     Integer.parseInt(exStrings[1]);
                        if (arg1<11&arg1>=0&arg2<11&arg2>=0) {
                            switch (operation) {
                                case "+":
                                    resultInt = arg1 + arg2;
                                    break;

                                case "*":
                                    resultInt = arg1 * arg2;
                                    break;

                                case "-":
                                    resultInt = arg1 - arg2;
                                    break;

                                case "/":
                                    resultInt = arg1 / arg2;
                                    break;
                                default:
                                    System.out.println("�������� �� ����������");
                            }
                            resultString= Integer.toString(resultInt);
                        } else {
                            System.out.println("�������� ���������� ������ 10");
                        }
                    } else {
                        resultCalc = "... ��������� �� ������� ����� � �� ��������!!! ��� �� ����������� ���� � ���� ������";
                    }
                } catch (IllegalArgumentException e)  {
                exists = false;
                resultCalcEnd = " ��������� �� ������������� �������...";
            }

        } else {
                //
            resultCalc="";

           // resultBeginEndFlag = " ���-�� �������� ��������� ������ ������������ (3 �������)";
           // throw new MyException ("String can not be empty!");
        }
        return resultCalc+resultCalcEnd+resultBeginEndFlag+" ���������: "+resultString;

    }

    private static String arabic2Roman100 (int arabicNum) {

        String romanNum100="";
        NumRoman[] romanArrNum10= new NumRoman[];
        romanArrNum10=NumRoman.values();
        String[] romanArrStr10= new String[11];
        romanArrStr10[0]="";
        for (int i=1 ; i<=10; i++) {
            romanArrStr10[i]=romanArrNum10[arabicNum].toString();
        }

        if (arabicNum<=10) {
            romanNum100=romanArrNum10[arabicNum-1].toString();
        } else if (arabicNum<=20) {
            romanNum100="X"+romanArrNum10[arabicNum%10-1].toString();
        } else if (arabicNum<=30) {
            romanNum100="XX"+romanArrNum10[arabicNum%10-1].toString();

        }
        else if (arabicNum<40)  {
            romanNum100 = "XX" + romanArrNum10[arabicNum%10 - 1].toString();
        }
        else if (arabicNum==40) {
            romanNum100 = "XL";
        }
        else if (arabicNum<=50) {
            romanNum100 = "XL" + romanArrNum10[arabicNum%10 - 1].toString();
        }
        else if (arabicNum<=60) {
            romanNum100 = "L" + romanArrNum10[arabicNum%10 - 1].toString();
        }
        else if (arabicNum<=70) {
            romanNum100 = "LX" + romanArrNum10[arabicNum%10 - 1].toString();
        }
        else if (arabicNum<=80) {
            romanNum100 = "LXX" + romanArrNum10[arabicNum%10 - 1].toString();
        }
        else if (arabicNum<90) {
            romanNum100 = "LXXX" + romanArrNum10[arabicNum%10 - 1].toString();
        }
        else if (arabicNum==90) {
            romanNum100 = "XC";
        }
        else if (arabicNum<100) {
            romanNum100 = "XC" + romanArrNum10[arabicNum%10 - 1].toString();
        }
        else {
            romanNum100 = "C";
        }

        return romanNum100;
    }
}

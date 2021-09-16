package com.enercomn.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

@Slf4j
public class Calculator {

    private static final boolean DEBUG = true;
    private static final String SLOWEST = "Slowest";
    private static final int DEF_DIV_SCALE = 10;
    /**
     * 运算符优先级，越小越优先
     */
    private static final Map<String, Integer> OPERATOR_PRECEDENCE = new HashMap<>();

    static {
        OPERATOR_PRECEDENCE.put("(", Integer.MAX_VALUE - 1);
        //算法上左括号特殊处理，处理方式表现为比其他运算符都要慢
        //右括号同样特殊处理，作为闭合括号的标志，本身不会存入栈，不需要设置优先级
        OPERATOR_PRECEDENCE.put("*", 3);
        OPERATOR_PRECEDENCE.put("/", 3);

        OPERATOR_PRECEDENCE.put("max", 40);
        OPERATOR_PRECEDENCE.put("min", 40);

        OPERATOR_PRECEDENCE.put("+", 10);
        OPERATOR_PRECEDENCE.put("-", 10);
        //最慢符，用于填充栈底
        OPERATOR_PRECEDENCE.put(SLOWEST, Integer.MAX_VALUE);
    }

    /**
     * 储存多目运算符的List，暂时没找到更好的区分多目运算符的方法
     */
    private static final List<String> MULTIMODAL_OPERATOR = Arrays.asList("max", "min");

    /**
     * 存储运算符对应的lambda表达式，后续调用
     */
    private static final Map<String, BiFunction<Double, Double, Double>> CALCULATES_MAP = new HashMap<>();

    static {
        CALCULATES_MAP.put("+", Calculator::plus);
        CALCULATES_MAP.put("-", Calculator::minus);
        CALCULATES_MAP.put("*", Calculator::multipy);
        CALCULATES_MAP.put("/", Calculator::divide);
        CALCULATES_MAP.put("max", Math::max);
        CALCULATES_MAP.put("min", Math::min);


    }

    private static Double plus(Double a, Double b) {
        return add(a,b);
    }

    private static Double minus(Double a, Double b) {
        return sub(a,b) ;
    }

    private static Double multipy(Double a, Double b) {
        return mul(a,b);
    }

    private static Double divide(Double a, Double b) {
        return div(a,b);
    }

    /**
     * * 两个Double数相加 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    public static Double add(Double v1, Double v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return new Double(b1.add(b2).doubleValue());
    }

    /**
     * * 两个Double数相减 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    public static Double sub(Double v1, Double v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return new Double(b1.subtract(b2).doubleValue());
    }

    /**
     * * 两个Double数相乘 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    public static Double mul(Double v1, Double v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return new Double(b1.multiply(b2).doubleValue());
    }

    /**
     * * 两个Double数相除 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    public static Double div(Double v1, Double v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return new Double(b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP)
                .doubleValue());
    }


    /**
     * 计算支持 )/+-*( MAX MIN 操作符的表达式的值<br>
     *
     * @param expression 中缀表达式
     * @return 计算结果
     */
    public static Double computeInfixExpression(String expression) {
        expression = expression.trim();
        if(!expression.startsWith("MIN") &&!expression.startsWith("min") && !expression.startsWith("MAX") && !expression.startsWith("max")){
            JexlEngine jexl = new JexlEngine();
            Expression jexlExpression = jexl.createExpression(expression);
            JexlContext jc = new MapContext();

            Object obj = (Object) jexlExpression.evaluate(jc);

            if (obj.getClass().toString().indexOf("Double") != -1) {
                Double result = (Double) obj;
                return result;
            }else {
                return Double.parseDouble(String.valueOf(obj));
            }
        }else{
            printLog("original expression " + expression);
            // 1. 给所有的操作符左右增加空格，方便字符串分割
            String splitExpression = insertBlankIntoExpression(expression);
            printLog("splitExpression " + splitExpression);
            // 2. 将min max 这种操作符转换成中缀表达式，方便计算
            String allInfixExpression = convertMaxMinOperator2InfixExpression(splitExpression);
            printLog("allInfixExpression " + allInfixExpression);
            // 3. 将转换好的中缀表达式转换成后缀表达式
            List<String> suffixExpression = infixToSuffix(allInfixExpression);
            printLog("suffixExpression " + String.join(" ", suffixExpression).trim());
            //4. 对后缀表达式计算最后结果
            return computeSuffixExpression(suffixExpression);
        }

    }

    private static void printLog(String s) {
        if (DEBUG) {
            log.info(s);
        }
    }

    /**
     * 将max/min操作符转换成中缀表达式的操作
     *
     * @param originalExpression 原始表达式
     * @return 转换后端中缀表达式
     */
    private static String convertMaxMinOperator2InfixExpression(String originalExpression) {
        String[] splitedExpression = originalExpression.split("\\s+");
        //用于储存当前括号属于哪个运算符
        Stack<String> transformOperator = new Stack<>();
        List<String> transformedExpression = new ArrayList<>();

        for (String str : splitedExpression) {
            switch (str) {
                case ",":
                    //遇到逗号直接改成当前括号的运算符，除了多目运算符不会有其他的使用逗号的情况
                    transformedExpression.add(transformOperator.peek());
                    break;
                case "(":
                    //不论前一个元素是什么左括号都推进当前运算符栈
                    transformOperator.push(transformedExpression.get(transformedExpression.size() - 1));
                    //如果前一个元素是多目运算符
                    if (transformedExpression.size() > 0 && MULTIMODAL_OPERATOR.contains(transformedExpression.get(transformedExpression.size() - 1))) {
                        //移除掉这个运算符
                        transformedExpression.remove(transformedExpression.size() - 1);
                    }
                    //不论前一个元素是什么左括号都存进去
                    transformedExpression.add("(");
                    break;

                case ")":
                    //右括号
                    //弹出这个括号的运算符
                    transformOperator.pop();
                    transformedExpression.add(")");
                    break;
                default:
                    //其他运算符和数字
                    //一概直接存入
                    transformedExpression.add(str);

            }
        }

        return String.join(" ", transformedExpression).trim();
    }


    /**
     * 传入一个算式，所有符号两边增加空格以分隔各元素，方便split
     *
     * @param expression 传入的表达式
     * @return 增加空格后端表达式
     */
    private static String insertBlankIntoExpression(String expression) {

        return expression.toLowerCase(Locale.ENGLISH).replaceAll("[+*/(),]|max|min", " $0 ").trim();
    }


    /**
     * 百度给出的算法：https://baike.baidu.com/item/%E5%90%8E%E7%BC%80%E8%A1%A8%E8%BE%BE%E5%BC%8F
     * <p>
     * 计算机实现转换：
     * 将中缀表达式转换为后缀表达式的算法思想：
     * <p>
     * ·开始扫描；
     * ·数字时，加入后缀表达式；
     * ·运算符：
     * a. 若为 '('，入栈；
     * b. 若为 ')'，则依次把栈中的的运算符加入后缀表达式中，直到出现'('，从栈中删除'(' ；
     * c. 若为 除括号外的其他运算符， 当其优先级高于除'('以外的栈顶运算符时，直接入栈。否则从栈顶开始，依次弹出比当前处理的运算符优先级高和优先级相等的运算符，直到一个比它优先级低的或者遇到了一个左括号为止，然后将其自身压入栈中（先出后入）。
     * ·当扫描的中缀表达式结束时，栈中的的所有运算符出栈；
     * <p>
     * 传入以不少于一个空格为分隔的算式，包括括号也要用空格分隔开
     * <p>
     * 将中缀表达式转换为后缀表达式
     *
     * @param infixExpression 中缀表达式
     * @return 转换后的后缀表达式
     */
    private static List<String> infixToSuffix(String infixExpression) {


        String[] afterSplitExpression = splitExpression(infixExpression);

        Stack<String> stack = new Stack<>();
        //先把最慢符压入栈底，有最慢符兜底后续运算不需要考虑空栈情况
        stack.push(SLOWEST);

        List<String> suffixExpression = new ArrayList<>();

        for (String str : afterSplitExpression) {
            if (isNumber(str)) {
                suffixExpression.add(str);
                continue;
            }

            if (Objects.equals(str, "(")) {
                stack.push(str);
                continue;
            }

            if (Objects.equals(str, ")")) {
                while (true) {
                    String current = stack.pop();

                    if (Objects.equals(current, "(")) {
                        break;
                    }

                    suffixExpression.add(current);
                }
                continue;
            }

            //当前运算符的优先级高于栈顶运算符，左括号的优先级设置为仅次于最慢符
            if (OPERATOR_PRECEDENCE.get(str) < OPERATOR_PRECEDENCE.get(stack.peek())) {
                stack.push(str);
            } else {
                while (OPERATOR_PRECEDENCE.get(stack.peek()) <= OPERATOR_PRECEDENCE.get(str)) {
                    suffixExpression.add(stack.pop());
                }

                stack.push(str);
            }

        }

        //把缓存栈里剩余的除最慢符之外的运算符都拿出来存进后缀表达式里
        while (!stack.peek().equals(SLOWEST)) {
            suffixExpression.add(stack.pop());
        }
        return suffixExpression;
    }

    /**
     * Regex.Split(String input, String pattern)：将 input 字符串在 pattern 正则匹配到的位置裁剪开，产生多个字符串
     * <p>
     * \s      ->      空格的转义字符
     * \\s     ->      两次转义的空格
     * \\s+    ->      匹配一个或多个连续的空格
     * 以空格为分隔，把每个部分分割开形成新的字符串
     *
     * @param expression
     * @return
     */
    private static String[] splitExpression(String expression) {

        return expression.trim().split("\\s+");

    }

    /**
     * Regex.IsMatch(String input, String pattern)：检测输入的字符串是否匹配正则表达式
     * <p>
     * ^[-]?[0-9]+(\\.[0-9]+)?$    解释：
     * <p>
     * 中间部分前面说了
     * <p>
     * ^      ->    匹配必须从字符串或一行的开头开始
     * $      ->    匹配必须出现在字符串或一行的结尾的 \n 之前
     * <p>
     * ^[-]?[0-9]+(\\.[0-9]+)?$  ->  从字符串开头开始，到字符串结尾位置为止，是一个正或负的整数或小数
     * <p>
     * 正则判断是否是数字
     *
     * @param string 字符串
     * @return 是否是数字
     */
    private static boolean isNumber(String string) {
        return Pattern.matches("^[-]?[0-9]+(\\.[0-9]+)?$", string);

    }


    /**
     * 计算后缀表达式的值<br>
     *
     * @param suffixExpressionArray 传入一个后缀表达式字符串数组
     * @return 计算结果
     */
    private static Double computeSuffixExpression(List<String> suffixExpressionArray) {
        Stack<Double> stack = new Stack<>();

        for (String str : suffixExpressionArray) {
            if (isNumber(str)) {
                //是数字的话压入栈
                stack.push(new Double(str));
            } else {
                //取出栈顶元素，这是第二个运算值
                Double b = stack.pop();

                //再次取出栈顶元素，这次是第一个运算值
                Double a = stack.pop();

                stack.push(calculate(a, b, str));
            }
        }

        return stack.pop();
    }

    private static Double calculate(Double a, Double b, String operatorString) {
        BiFunction<Double, Double, Double> calculate = CALCULATES_MAP.get(operatorString);
        if (Objects.nonNull(calculate)) {
            return calculate.apply(a, b);
        }

        System.out.println("发现未知运算符" + operatorString + "无法进行运算");
        return new Double("0");
    }


//    public static void main(String[] args) {
////         System.out.println(new Calculator().computeInfixExpression("MIN(32,22)"));
////        System.out.println(new Calculator().computeInfixExpression("min(32,22)"));
////        System.out.println(new Calculator().computeInfixExpression("MAX(32,22)"));
////        System.out.println(new Calculator().computeInfixExpression("max(32,22)"));
////        System.out.println(new Calculator().computeInfixExpression("MAX(32,22)"));
////          System.out.println(new Calculator().computeInfixExpression("6.5 / 10 + 8 / 100"));
////          System.out.println(new Calculator().computeInfixExpression("6.5/10 + 8/100"));
////       System.out.println(new Calculator().computeInfixExpression("1-(-25)"));
////        System.out.println(new Calculator().computeInfixExpression("1-（-25）"));
////        System.out.println(new Calculator().computeInfixExpression("-25 * -1"));
////        System.out.println(new Calculator().computeInfixExpression("0--2.5"));
// //       System.out.println(new Calculator().computeInfixExpression("(6.5/10)+(8/100)"));
////        System.out.println(CommUtil.convertToCode("1*2+(3/54)+5"));
//        System.out.println(new Calculator().computeInfixExpression("5.3990024E7-5.3975548E7"));
////System.out.println(new BigDecimal("0.648").setScale(2, BigDecimal.ROUND_HALF_UP));
////        System.out.println(new BigDecimal("0.648").setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//    }
}



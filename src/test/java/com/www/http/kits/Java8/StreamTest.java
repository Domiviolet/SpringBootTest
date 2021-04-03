package com.www.http.kits.Java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    /**
     * Stream 的三个操作步骤
     *  1.得到流
     *      1.Collection系列集合,中的stream()方法可以,如list,set集合的实现类
     *      2.Arrays数组工具类的静态stream()方法
     *      3.通过Stream类中的 静态方法of()
     *      4.创建无限流.
     *              迭代: Stream.iterate()
     *              生成: Stream.generate()
     *
     *  2.中间操作(中间操作不会有任何的结果,必须进行终止操作,才会有输出结果),如终止操作的forEach(System.out::println)
     *      1.筛选与切片 filter() 接收lambda,从流中排除某些元素
     *      2.limit() 截断流,使其元素不超过给定数量,只保留limt限制的数目,如limit(4),只保留前4个,而skip(4),则相反,去掉前4个,取剩余的
     *      3.skip() 跳过元素,返回一个扔掉了前n个元素的流,如流中元素不足n个,则返回一个空流,与limit(n)互补
     *      4.distinct() 去重,通过流所生产元素的hashCode()和equals()去重
     *      5.map() 映射 .接收Lambda,将元素转换成其他形式或提取信息.接收一个函数作为参数,该函数会被应用到每个元素上,并将其映射成一个新的元素
     *      6.flatMap() 接收一个函数作为参数,将流中的每个值都换成另一个流,然后把所有的流连接成一个新的流
     *      7.sorted() 自然排序
     *      8.sorted(Comparator comparator) 定制排序
     *
     *  3.终止操作(终端操作),中间操作不会执行全部内容,直到终止操作,才全部执行
     *         1. forEach(System.out::println)
     *         2.allMatch() 检查是否匹配所有的元素
     *         3.anyMatch() 检查是否匹配至少一个元素
     *         4.noneMatch() 检查是否没有匹配所有元素
     *         5.findFirst() 返回一个元素
     *         6.findAny() 返回当前流中的任意一个元素
     *         7.count() 返回流中元素的总个数
     *         8.max() 返回流中的最大值
     *         9.min() 返回流中的最小值
     *         10.reduce(T identity,BinaryOperator) / reduce(BinaryOperator) 可以将流中元素反复结合起来,得到一个值
     *         11.collect() 将流转换为其他形式.接收一个Collector接口的实现,用于给Stream中元素做汇总的方法
     *                      collector接口中方法的实现决定了如何对流执行收集操作(如收集到List,Set,Map).但是Collectors实用类
     *                      提供了很多静态方法,可以方便的创建常见的收集器实例
     */


    /**
     * 产生无限流
     * 方式一: 通过迭代
     */
    @Test
    public void test00() {
        Stream<Integer> streamIterate = Stream.iterate(0, x -> x + 2);
        //产生的前4个流,并进行遍历操作
        streamIterate.limit(4).forEach(System.out::println);

    }

    /**
     * 产生无限流
     * 方式二: 通过Stream类的generate()方法
     */
    @Test
    public void test01() {
        Stream<Double> generate = Stream.generate(() -> Math.random());
        generate.limit(6).forEach(System.out::println);
    }

    /**
     * 方法的测试
     */
    @Test
    public void test02() {
        List<Employee> employeeList = Arrays.asList(
                new Employee("张一", 15, 994.9),
                new Employee("张二", 38, 499.3),
                new Employee("张三", 50, 9456.9),
                new Employee("张三", 116, 44444.9),
                new Employee("王二", 23, 96644.9),
                new Employee("赵四", 56, 13456.9),
                new Employee("小该", 34, 96645.9));

//        Stream<Employee> stream = employeeList.stream().filter(e -> {
//            System.out.println("StremaApI的中间操作"); //没有终止操作,中间操作不会执行
//            return e.getAge() > 50;
//        });
//        stream.forEach(System.out::println);

        employeeList.stream().skip(1).forEach(System.out::println);
    }

    /**
     * map() 映射(返回的是一个流) .接收Lambda,将元素转换成其他形式或提取信息.接收一个函数作为参数,该函数会被应用到每个元素上,并将其映射成一个新的元素
     */
    @Test
    public void test03() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "eee");
        list.stream().map(x -> x.length()).forEach(System.out::println); //x表示Function函数中的apply()方法,需要参数
        /*这里map()中需要引用Function函数,Function(T t ,R r),T表示参数类型,R表示返回值类型  */
    }

    /**
     * 提取员工的姓名
     */
    @Test
    public void test04() {
        List<Employee> employeeList = Arrays.asList(
                new Employee("张一", 15, 994.9),
                new Employee("张二", 38, 499.3),
                new Employee("张三", 50, 9456.9),
                new Employee("张三", 116, 44444.9),
                new Employee("王二", 23, 96644.9),
                new Employee("赵四", 56, 13456.9),
                new Employee("小该", 34, 96645.9));
        employeeList.stream().map(Employee::getName).forEach(System.out::println);
        /* 获取年龄,工资也类似: map(Employee::getName), map(Employee::getSalary) */
    }

    /**
     * 把下面集合中的元素,一个一个,分离型的提取出来
     * 通过map()  这里的map作用是把已知数组转成: {{a,a,a,},{b,b,b}..}
     */
//    @Test
//    public void test05(){
//        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "eee","rrtte");
//        Stream<Stream<Character>> streamStream = list.stream().map(StreamTest::filterCharacter); //这里的map作用是把已知数组转成: {{a,a,a,},{b,b,b}..}
//        streamStream.forEach(stream->{ //对流中的流进行遍历
//           stream.forEach(System.out::println);
//        });
//    }
//    public static Stream<Character> filterCharacter(String string){
//        List<Character> list = new ArrayList<>();
//        for(Character character : string.toCharArray()){
//            list.add(character);
//        }
//        return list.stream();
//    }

    /**
     * 对上面的改进
     * flatMap() 接收一个函数作为参数,将流中的每个值都换成另一个流,然后把所有的流连接成一个新的流
     *  flatMap()在map()的基础上,又进行了一次转换,转换后的结果{a,a,a,b,b,b,....}
     *  补充: 集合中的add()方法和 addAll()方法区别
     *        如果传过来的是一个集合,用add()方法,是把整个集合添加到现有集合中,这里整个集合是作为一个整体的
     *        而addAll()方法则是,把这个集合给拆分成每个元素,然后再把所有的元素添加到现有集合中
     */
    @Test
    public void test06() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "eee");
        list.stream().flatMap(StreamTest::filterCharacter).forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String string) {
        List<Character> list = new ArrayList<>();
        for (Character character : string.toCharArray()) {
            list.add(character);
        }
        return list.stream();
    }

    /**
     * sorted()自然排序法(Comparable)
     */
    @Test
    public void test07(){
        List<String> list = Arrays.asList("aaa","fff", "ccc", "eee");
        list.stream().sorted().forEach(System.out::println);
    }

    /**
     * 重要!!!
     * sorted(Comparator comparator) 定制排序
     */
    /*需求: 先按年龄从小到大,如果年龄相同,再按姓名排序 ,注意字符串的比较,用compareTo()方法 */
    @Test
    public void test08(){
        List<Employee> list = Arrays.asList(
                new Employee("张一", 15, 994.9),
                new Employee("里二", 38, 499.3),
                new Employee("张三", 50, 9456.9),
                new Employee("吴三", 50, 44444.9),
                new Employee("王二", 23, 96644.9),
                new Employee("赵四", 56, 13456.9),
                new Employee("小该", 34, 96645.9));
        list.stream().sorted((x,y)->{
            int i = x.getAge() -y.getAge() ;
            if(x.getAge().equals(y.getAge())){
                return x.getName().compareTo(y.getName());
            }
            return  i ;
        }).forEach(System.out::println);
    }

    /**
     * allMatch() 是否全部匹配
     * anyMatch() 是否至少有一个匹配
     * noneMatch() 是否都没有匹配的元素
     * findFirst() 返回找到的第一个元素
     * findAny() 返回找到的随便一个
     */

    @Test
    public void test09(){
        List<Employee> list = Arrays.asList(
                new Employee("张一", 15, 994.9, Employee.Status.BUSY),
                new Employee("里二", 38, 499.3, Employee.Status.BUSY),
                new Employee("张三", 50, 9456.9, Employee.Status.VOCATION),
                new Employee("吴三", 50, 44444.9, Employee.Status.FREE),
                new Employee("王二", 23, 96644.9, Employee.Status.BUSY),
                new Employee("赵四", 56, 13456.9, Employee.Status.FREE),
                new Employee("小该", 34, 96645.9, Employee.Status.VOCATION));
        boolean b = list.stream().allMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);

        System.out.println(list.stream().anyMatch(e -> e.getStatus().equals(Employee.Status.BUSY)));

        //需求,按工资从低到高排序,返回第一个元素
        System.out.println(list.stream().sorted((x, y) -> (int) (x.getSalary()-y.getSalary())).findFirst());
        /* 这里强转的原因是因为,Comparator接口中的实现方法的返回值类型就是Integer型,Lambda表达式中的值也要保持一致 */
    }
    /**
     * count() 计数,有多少个
     * max() 返回流中最大值
     * min() 返回流中最小的值
     */
    @Test
    public void test10(){
        List<Employee> list = Arrays.asList(
                new Employee("张一", 15, 994.9, Employee.Status.BUSY),
                new Employee("里二", 38, 499.3, Employee.Status.BUSY),
                new Employee("张三", 50, 9456.9, Employee.Status.VOCATION),
                new Employee("吴三", 50, 44444.9, Employee.Status.FREE),
                new Employee("王二", 23, 96644.9, Employee.Status.BUSY),
                new Employee("赵四", 56, 13456.9, Employee.Status.FREE),
                new Employee("小该", 34, 96645.9, Employee.Status.VOCATION));
        long count = list.stream().count();
        System.out.println(count);

        //返回工资最大值
        Optional<Double> max = list.stream().map(Employee::getSalary).max((x, y) -> x.compareTo(y));
        System.out.println(max);

        System.out.println(list.stream().max((x, y) -> x.getSalary().compareTo(y.getSalary())));
    }

    /**
     * reduce()归约,通过Function函数,进行加减乘除等运算
     */
    @Test
    public void test11(){
        List<Integer> list = Arrays.asList(1, 2, 3);

        Integer reduce = list.stream().reduce(0, (x, y) -> x + y); /*先把0,作为x,然后依次从数组总取1,2,3,累加*/
        System.out.println(reduce);

        System.out.println(list.stream().reduce(2, (x, y) -> x * y));

        System.out.println(list.stream().reduce((x, y) -> x - y));

        List<Employee> listEmployee = Arrays.asList(
                new Employee("张一", 15, 994.9, Employee.Status.BUSY),
                new Employee("里二", 38, 499.3, Employee.Status.BUSY),
                new Employee("张三", 50, 9456.9, Employee.Status.VOCATION),
                new Employee("吴三", 50, 44444.9, Employee.Status.FREE),
                new Employee("王二", 23, 96644.9, Employee.Status.BUSY),
                new Employee("赵四", 56, 13456.9, Employee.Status.FREE),
                new Employee("小该", 34, 96645.9, Employee.Status.VOCATION));
        //求员工工资的总和
        Optional<Double> reduce1 = listEmployee.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(reduce1);
    }

    /**
     * collection() 收集 括号填写 Collectors.集合名 . Collectors类似Arrays工具类,内部有大量静态方法
     */
    @Test
    public void test12(){
        //把员工的名字收集到List集合中
        List<Employee> listEmployee = Arrays.asList(
                new Employee("张一", 15, 994.9, Employee.Status.BUSY),
                new Employee("里二", 38, 499.3, Employee.Status.BUSY),
                new Employee("张三", 50, 9456.9, Employee.Status.VOCATION),
                new Employee("吴三", 50, 44444.9, Employee.Status.FREE),
                new Employee("王二", 23, 96644.9, Employee.Status.BUSY),
                new Employee("赵四", 56, 13456.9, Employee.Status.FREE),
                new Employee("小该", 34, 96645.9, Employee.Status.VOCATION));
        List<String> collectList = listEmployee.stream().map(Employee::getName).collect(Collectors.toList());
        System.out.println(collectList);

        //名字加入到HashSet集合中
        HashSet<String> collectHashSet = listEmployee.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        System.out.println(collectHashSet);

        //获取工资的平均值
        Double aDouble = listEmployee.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(aDouble);
    }

    /**
     * 分组collect(Collectors.groupingBy(Employee::getStatus))
     */
    @Test
    public void test13(){
        List<Employee> listEmployee = Arrays.asList(
                new Employee("张一", 15, 994.9, Employee.Status.BUSY),
                new Employee("里二", 38, 499.3, Employee.Status.BUSY),
                new Employee("张三", 50, 9456.9, Employee.Status.VOCATION),
                new Employee("吴三", 50, 44444.9, Employee.Status.FREE),
                new Employee("王二", 23, 96644.9, Employee.Status.BUSY),
                new Employee("赵四", 56, 13456.9, Employee.Status.FREE),
                new Employee("小该", 34, 96645.9, Employee.Status.VOCATION));
        //按状态分组
        Map<Employee.Status, List<Employee>> collect = listEmployee.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(collect);
        //对分组后的map进行遍历
        for (Employee.Status key : collect.keySet()){
            System.out.println(key);
            System.out.println(collect.get(key));
        }
    }

    /**
     * 多级分组
     * 利用stream()中的collect()方法,collect方法中含有丰富的结构,功能
     */
    @Test
    public void test14(){

        List<Employee> listEmployee = Arrays.asList(
                new Employee("张一", 15, 994.9, Employee.Status.BUSY),
                new Employee("里二", 38, 499.3, Employee.Status.BUSY),
                new Employee("张三", 50, 9456.9, Employee.Status.VOCATION),
                new Employee("吴三", 50, 44444.9, Employee.Status.FREE),
                new Employee("王二", 23, 96644.9, Employee.Status.BUSY),
                new Employee("赵四", 56, 13456.9, Employee.Status.FREE),
                new Employee("小该", 34, 96645.9, Employee.Status.VOCATION));
        //先按状态分组,如果状态相等,再按年龄段分
        Map<Employee.Status, Map<String, List<Employee>>> mapMap = listEmployee.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
            if (((Employee) e).getAge() <= 35) {
                return "青年";
            } else if (e.getAge() <= 50) {
                return "中年";
            } else {
                return "老年";
            }
        })));
        //遍历
        for(Employee.Status key: mapMap.keySet()){
            System.out.println(key);
            System.out.println(mapMap.get(key));
        }
    }
    /**
     * 分区
     */
    @Test
    public void test15(){
        List<Employee> listEmployee = Arrays.asList(
                new Employee("张一", 15, 994.9, Employee.Status.BUSY),
                new Employee("里二", 38, 499.3, Employee.Status.BUSY),
                new Employee("张三", 50, 9456.9, Employee.Status.VOCATION),
                new Employee("吴三", 50, 44444.9, Employee.Status.FREE),
                new Employee("王二", 23, 96644.9, Employee.Status.BUSY),
                new Employee("赵四", 56, 13456.9, Employee.Status.FREE),
                new Employee("小该", 34, 96645.9, Employee.Status.VOCATION));
        //按工资大于8000的分区
        Map<Boolean, List<Employee>> listMap = listEmployee.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 8000));
        System.out.println(listMap);

        System.out.println();
    }





}

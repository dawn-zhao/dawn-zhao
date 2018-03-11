package com.dawn.zhao.jvm;


import com.dawn.zhao.bean.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class DawnZhao {
    public static String zhao = "zhao";
    final String dawn = "dawn";
    private int age = 21;


    public static void main(String[] args) throws  Exception {
        //协议的反查
//		File file = new File("CGV-Advert/CGV-UOP/src/main/resources/dev-adv/redis-config.properties");
//		URL url = file.toURI().toURL();
//		URLConnection urlConnection = url.openConnection();
//		InputStream inputStream = urlConnection.getInputStream();
//		System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));

        //实现'dawn'协议sun.net.www.protocol.dawn.Handler
//		ClassLoader classLoader = ApplicationContext.class.getClassLoader();
//		URL url = new URL("dawn://META-INF/notice.txt");
//		InputStream inputStream = url.openStream();
//		resources
//		System.out.println(StreamUtils.copyToString(inputStream,Charset.forName("UTF-8")));

//        ResourceLoader resources = new DefaultResourceLoader();
//        Resource resource = resources.getResource("dawn://dev-adv/redis-config.properties");
//        URL url = resource.getURL();
//        System.out.println(StreamUtils.copyToString(url.openStream(), Charset.forName("UTF-8")));
//        testJvm();

//        int i;
//        int j=0;
//        List<Object> objects = new ArrayList<>();
//        while (j<1000){
//            i=0;
//            while (i<1000){
//                System.out.println("i : "+i);
//                objects.add(new Object());
//                i++;
//            }
//            Thread.sleep(100);
//            j++;
//        }

//        1073741824
//        Map<Object,Object> objectMap = new HashMap<>((1<<30)+10);
//        System.out.println(1<<31);
//        System.out.println(0xffffff);
//        System.out.println(Integer.MAX_VALUE);

//        Map<Object,Object> objectMap = new HashMap<>();
//        for (int i=0;i<10;i++){
//            System.out.println(objectMap.put("a"+i,"aaab"+i));
//        }
//        Map<Object,Object> objectMap2 = new HashMap<>();
//        for (int i=0;i<100;i++){
//            System.out.println(objectMap2.put("a"+i,"aaab"+i));
//        }


//        Converter<String, Integer> converter = Integer::valueOf;
//        Integer converted = converter.convert("sasad");
//        System.out.println(converted);   // 123

//        User user = new User(1,"dawn","185...");
//        Converter<byte[], String> converter = user::toString;
//        System.out.println(converter.convert("a"));

//        List<String> names = Arrays.asList("peter", "anna", "dawn", "zhao", "mike", "xenia");
//
//        Collections.sort(names, (String a, String b) -> a.compareTo(b));
//        System.out.println(names);

//        UserFactory<User> userFactory = User::new;
//        User user = userFactory.create(1,"dawn.zhao","185...");
//
//        Converter<byte[], String> converter = user::toString;
//        System.out.println(converter.convert(new byte[0]));
//        user.setUserName("Dawn Zhao");
//        System.out.println(converter.convert(new byte[0]));

//        int num = 1;
//        Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
//        System.out.println(stringConverter.convert(100));

//        User user = new User(1,"dawn","185...");
//        Supplier<String> personSupplier = user::toString;
//        System.out.println(personSupplier.get());

        //filter
//        List<String> stringCollection = new ArrayList<String>();
//        stringCollection.add("ddd2");
//        stringCollection.add("aaa2");
//        stringCollection.add("bbb1");
//        stringCollection.add("aaa1");
//        stringCollection.add("bbb3");
//        stringCollection.add("ccc");
//        stringCollection.add("bbb2");
//        stringCollection.add("ddd1");
//        String word = "aa";
//
//        stringCollection
//                .stream()
//                .filter((s) -> s.contains(word))
//                .forEach(System.out::println);
//
//        Function<Integer, Integer> toInteger = Integer::valueOf;
//        Function<Integer, String> backToString = toInteger.andThen(String::valueOf);
//        System.out.println(backToString.apply(111).getClass());
//        if(true){
//            return;
//        }



//        Supplier<User> userSupplier = User::new;
//        System.out.println(userSupplier.get().toString());;   // new User

//        Comparator<User> comparator = (p1, p2) -> p1.getUserName().compareTo(p2.getUserName());
//        User p1 = new User(1,"John", "Doe");
//        User p2 = new User(2,"John", "Wonderland");
//        System.out.println(comparator.compare(p1, p2));             // > 0
//        System.out.println(comparator.reversed().compare(p1, p2));  // < 0

        //sorted
//        List<String> stringCollection = new ArrayList<String>();
//        stringCollection.add("ddd2");
//        stringCollection.add("aaa2");
//        stringCollection.add("bbb3");
//        stringCollection.add("ccc");
//        stringCollection.add("bbb2");
        //sorted
//        String word = "aa";
//
//        stringCollection
//                .stream()
//                .sorted()
//                .forEach(System.out::println);
        //count
//        long startsWithB =
//                stringCollection
//                        .stream()
//                        .count();
//        System.out.println(startsWithB);    // 3
//        Thread.sleep(3000);


//        int max = 10000000;
//        List<String> values = new ArrayList<>(max);
//        for (int i = 0; i < max; i++) {
//            UUID uuid = UUID.randomUUID();
//            values.add(uuid.toString());
//        }
//
//        System.out.println("begin");
//        long t0 = System.nanoTime();
//        User user = new User();
//        //并行排序不可采用,多线程排序集合,顺序不可保证,对大集合数据过滤及相关业务可采用
//        //串行
////        values.stream().sorted().filter((s) -> s.contains("ab")).forEach(user::getThread);
//        //并行
//        values.parallelStream().sorted().filter((s) -> s.contains("ab")).forEach(user::getThread);
//
////        System.out.println(count);
//        long t1 = System.nanoTime();
//
//        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
//        System.out.println(String.format("sequential sort took: %d ms", millis));

//        Thread.sleep(10000);
//
//        //内存泄漏 1
//        Vector v = new Vector(10);
//
//        User o = null;
//        for (int i = 1; i < 10000000; i++) {
//            o = new User(1, "dawnzhao" + i, "phone");
//            v.add(o);
//        }
//        Thread.sleep(100000000);

        //内存泄漏 2,此案例与集合泛型对象的hashCode方法有关,hashCode计算方式决定了程序的效率
//        Set<User> set = new HashSet<>();
//        User p1 = new User(25, "唐僧","pwd1");
//        User p2 = new User(26, "孙悟空","pwd2");
//        User p3 = new User(27, "猪八戒","pwd3");
//        set.add(p1);
//        set.add(p2);
//        set.add(p3);
//        System.out.println("总共有:"+set.size()+" 个元素!"); //结果：总共有:3 个元素!
//        p3.setUserId(2); //修改p3的id,此时p3元素对应的hashcode值发生改变
//        System.out.println(set.remove(p3)); //此时remove不掉，造成内存泄漏
//        System.out.println(set.add(p3));; //重新添加，居然添加成功
//        System.out.println("总共有:"+set.size()+" 个元素!"); //结果：总共有:4 个元素!
//        for (User user : set) {
//            System.out.println(user);
//        }
        //内存泄漏除了以上两个案例外
        // 对于中间件或者DB的连接Connection不调用相关的close是不会被GC回收的,必需显式的关闭连接
        // 单例对象持有外部引用,外部引用对象是不会被回收的,需要保证外部引用对象的结构不会是一个很复杂的对象或者集合

//        List<User> users = new ArrayList<>();
//        Thread.sleep(10000);
//        while (users.size()<20000000){
//
//            User user = new User();
//            user.setAddress("address");
//            user.setCountry("country");
//            users.add(user);
//            user = null;
//        }
//        System.out.println(users.size());

//        Map<Integer, String> map = new HashMap<>();
//        for (int i = 0; i < 10; i++) {
//            map.putIfAbsent(i, "value" + i);
//        }
//        //包含key
////        System.out.println(map.containsKey(9));
//        //Merge做的事情是如果key不存在则插入，否则则对原key对应的值做合并操作并重新插入到map中
//        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
//        System.out.println(map.get(9));           // val9

//        Map<Integer, User> map = new HashMap<>();
//        for (int i = 0; i < 10; i++) {
//            map.putIfAbsent(i, new User(i, "userName"+i, "phone"+i));
//        }
//        //包含key
////        System.out.println(map.containsKey(9));
//        //Merge的规则需要在lambda定义
//        User user = new User(9, "new name 9","new phone 9");
//        map.merge(9, user, (value, newValue) -> new User(1,"",""));
//        System.out.println(map.get(9));

//        int numCount = 1;
////        int[] intArray = {7,2,4,3,5,8,5,6,7};
//        int[] intArray = new int[numCount];
//        for (int i=0;i<numCount;i++){
//            intArray[i] = (int)(Math.random()*1000000)+1;
//        }
//
//        long t0 = System.nanoTime();
//
//        int i,j,k,num;

//      插入排序
//        insertSort(intArray);

//      插入排序 v1.0
//        for (i=1;i<intArray.length;i++){
//            for (j=0;j<i&&intArray[i]<intArray[i-1];j++){
//                if(intArray[j]>intArray[i]){
//                    num = intArray[i];
//                    for (int k=i;k>j;k--){
//                        intArray[k] = intArray[k-1];
//                    }
//                    intArray[j] = num;
//                    break;
//                }
//            }
//        }

//      插入排序优化 v1.1
//        for (i=1;i<intArray.length;i++){
//            num = intArray[i];
//            for (j=i-1;j>=0&&num<intArray[j];j--){
//                intArray[j+1] = intArray[j];
//            }
//            intArray[j+1] = num;
//        }

//      冒泡排序 7,2,4,3,5,8,5,6,7
//        for (i=0;i<intArray.length;i++){
//            for (j=intArray.length-1;j>i;j--){
//                if(intArray[j-1]>intArray[j]){
//                    num = intArray[j-1];
//                    intArray[j-1] = intArray[j];
//                    intArray[j] = num;
//                }
//            }
//        }

//      选择排序
//        for(i=0;i<intArray.length;i++){
//            k=i;
//            for (j=i;j<intArray.length;j++){
//                if(intArray[j]<intArray[k]){
//                    k=j;
//                }
//            }
//            if(k!=i){
//                num = intArray[k];
//                intArray[k] = intArray[i];
//                intArray[i] = num;
//            }
//        }

//      快速排序
//        quickSort(intArray,0,numCount-1);

//      希尔排序
//        ShellSort.sort(intArray);
//        ShellSort.sort1(intArray);

        // 大型数字类型超过long的长度,做操作运算例如 100!(阶乘)
//        outIntArray(factorial(100000));

//        arrayListTest();

//        List<User> arrayList = new ArrayList<User>();
//        arrayList.add(new User());
//        classLoader();

//        long t1 = System.nanoTime();
//        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
//        for (i=0;i<intArray.length;i++){
//            System.out.println(intArray[i]);
//        }
//        System.out.println(String.format("排序的耗时为: %d ms", millis));

        Map<String, Object> map = new ConcurrentHashMap<String, Object>();
        map.put("","");

    }

    public static void classLoader(){

        Class clazz = DawnZhao.class;
        System.out.println(clazz+" 类加载器为 ==》 "+clazz.getClassLoader());
        ClassLoader loader = clazz.getClassLoader();
        while (loader!=null){
            clazz = loader.getParent().getClass();
            loader = clazz.getClassLoader();
            System.out.println(clazz+" 类加载器为 ==》 "+clazz.getClassLoader());
        }

    }

    public static void printIntArray(int[] intArray){
        for (int i=0;i<intArray.length;i++){
            System.out.print(intArray[i]);
        }
        System.out.println();
    }

    public static void arrayListTest(){
        List<Object> list = new ArrayList<Object>();
        int i;
        for (i=0;i<1000000;i++){
            list.add(new Object());
        }

        long t0 = System.nanoTime();

//        List<User> linList = new LinkedList<User>();
//        linList.add(new User(1,"dawn zhao","18513354558", "china","beijing","764562461"));
//        linList.add(new User(1,"dawn zhao","18513354558", "china","beijing","764562461"));
//        System.out.println(JSONObject.toJSONString(linList));
        //后入式删除
//        for (i=list.size()-1;i>=0;i--){
//            list.remove(i);
//        }
        //顺序删除
        i = 0;
        while (i<list.size()){
            list.remove(0);
            i++;
        }

//        list.remove(list.size()-1);
//        list.remove(0);

        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("ArrayList 操作时间: %d ms", millis));
    }


    /**
     * 阶乘算法
     * @param factorialNum
     * @return
     */
    public static int[] factorial(int factorialNum){
        int i;
        int[] factorialNums = new int[factorialNum];
        for (i=factorialNum;i>0;i--){
            factorialNums[i-1] = i;
        }
        int[] sum = new int[stirling_formula(factorialNum)];
        sum[sum.length-1] = 1;
        for (i=1;i<factorialNums.length;i++){
            multiplication(sum,factorialNums[i]);
        }
        return sum;
    }

    private static int[] multiplication(int[] multiplier1, int multiplier2){
        int i;
        for (i=multiplier1.length-1;i>=0;i--) {
            multiplier1[i] *= multiplier2;
        }

        for (i=multiplier1.length-1;i>0;i--){
            multiplier1[i-1] += multiplier1[i]/10;
            multiplier1[i] = multiplier1[i]%10;
        }
        return multiplier1;
    }

    /**
     * stirling 斯特林公式
     * @param factorialNum
     * @return
     */
    public static int stirling_formula(int factorialNum){
        if(factorialNum<=1)return 1;
        double PI = Math.acos(-1);
        double e = Math.exp(1);
        return (int)Math.floor(Math.log10(Math.sqrt(2*PI*factorialNum))+factorialNum*Math.log10(factorialNum/e))+1;
    }

    public static void insertSort(int a[]) {
        int len = a.length;
        for (int i = 1; i < len; i++) {
            int temp = a[i];// 待插入的值
            int index = i;// 待插入的位置
            while (index > 0 && a[index - 1] > temp) {
                a[index] = a[index - 1];// 待插入的位置重新赋更大的值
                index--;// 位置往前移
            }
            a[index] = temp;
        }
    }

    public static void quickSort(int a[], int low, int height) {
        if (low < height) {
            int result = partition(a, low, height);
            quickSort(a, low, result - 1);
            quickSort(a, result + 1, height);
        }
    }

    public static int partition(int a[], int low, int height) {
        int key = a[low];
        while (low < height) {
            while (low < height && a[height] >= key)
                height--;
            a[low] = a[height];
            while (low < height && a[low] <= key)
                low++;
            a[height] = a[low];
        }
        a[low] = key;
        return low;
    }

    public static void testJvm(){
        int a = 0,b=a;
        int c = a+b+9;
        int d = -1;
        DawnZhao dawnZhao = new DawnZhao();
        dawnZhao.testJvm("a",1);
        System.out.println(d+":"+c);
        return;
    }

    private String testJvm(String a,int b){
        return "testJvm";
    }
}

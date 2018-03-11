package com.dawn.zhao.demo.serializable;

import com.alibaba.fastjson.JSON;
import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.dawn.zhao.bean.Group;
import com.dawn.zhao.bean.User;
import com.dawn.zhao.demo._static.StaticAttr;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;


public class SerializeDemo {

    private static User user1 = new User(1, "dawn zhao", "18513354558", "china", "beijing", "764562461");


    /**
     * 序列化(将对象序列化到项目的根目录下)
     */
    public static void serialize() {
        try {
            ObjectOutputStream oStream = new ObjectOutputStream(new FileOutputStream(new File("user")));
            User person = new User(1, "dawn zhao", "18513354558", "china", "beijing", "764562461");

            oStream.writeObject(person);
            System.out.println("序列化成功！");
            oStream.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void unSerialize() {

        try {
            ObjectInputStream iStream = new ObjectInputStream(new FileInputStream(new File("user")));
            User user = (User) iStream.readObject();
            System.out.println("反序列化成功!");
            System.out.println(user);
            iStream.close();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //序列化
//        serialize();

        //反序列化
//        unSerialize();

        //深克隆
//        beanClone();

//        excuteWithJack();

//        excuteWithFastJson();

//        excuteWithProtoBuf();//对应序列化字段需加注解或配置

//        excuteWithHessian();

//        System.out.println(StaticAttr.name);

//        StaticAttr sa = new StaticAttr();
//        System.out.println(sa.getName());
//
//        System.out.println(" staticAttr 1 类加载开始 ");
//        Class c = Class.forName("com.dawn.zhao.demo._static.StaticAttr",false,SerializeDemo.class.getClassLoader());
//        SerializeDemo.class.getClassLoader().loadClass("com.dawn.zhao.demo._static.StaticAttr");
//        Object o = c.newInstance();
//        System.out.println(" staticAttr 1 类加载结束 ");

//        System.out.println(" staticAttr 2 类加载开始 ");
//        Class c1 = Class.forName("com.dawn.zhao.demo._static.StaticAttr",false,SerializeDemo.class.getClassLoader());
//        Object o1= c1.newInstance();
//        System.out.println(" staticAttr 2 类加载结束 ");

//
//        StaticAttr sa1 = new StaticAttr();
//        System.out.println(sa1.getName());

    }

    public static void beanClone() {

        User user = new User(1, "dawn zhao", "18513354558", "china", "beijing", "764562461");
        user.setGroup(new Group(1, "zdwx"));
        try {
            System.out.println(user.deepClone());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void excuteWithJack() throws IOException {

        user1 = new User(1, "dawn zhao", "18513354558", "china", "beijing", "764562461");
        ObjectMapper mapper = new ObjectMapper();
        byte[] writeBytes = null;
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            writeBytes = mapper.writeValueAsBytes(user1);
        }
        System.out.println("Json序列化：" + (System.currentTimeMillis() - start) + "ms : " +
                "总大小->" + writeBytes.length);

        user1 = mapper.readValue(writeBytes, User.class);
        System.out.println(user1);

    }


    private static void excuteWithFastJson() throws IOException {
        user1 = new User(1, "dawn zhao", "18513354558", "china", "beijing", "764562461");
        String text = null;
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            text = JSON.toJSONString(user1);
        }
        System.out.println("fastjson序列化：" + (System.currentTimeMillis() - start) + "ms : " +
                "总大小->" + text.getBytes().length);
        user1 = JSON.parseObject(text, User.class);
        System.out.println(user1);
    }

    private static void excuteWithProtoBuf() throws IOException {
        user1 = new User(1, "dawn zhao", "18513354558", "china", "beijing", "764562461");
        Codec<User> personCodec = ProtobufProxy.create(User.class, false);

        Long start = System.currentTimeMillis();
        byte[] bytes = null;
        for (int i = 0; i < 1000000; i++) {
            bytes = personCodec.encode(user1);
        }
        System.out.println("protobuf序列化：" + (System.currentTimeMillis() - start) + "ms : " +
                "总大小->" + bytes.length);

        user1 = personCodec.decode(bytes);
        System.out.println(user1);
    }

    private static void excuteWithHessian() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        HessianOutput ho=new HessianOutput(os);
        Long start=System.currentTimeMillis();
        for(int i=0;i<1000000;i++){
            ho.writeObject(user1);
            if(i==0){
                System.out.println(os.toByteArray().length);
            }
        }
        System.out.println("Hessian序列化："+(System.currentTimeMillis()-start)+"ms : " +
                "总大小->"+os.toByteArray().length);

        HessianInput hi=new HessianInput(new ByteArrayInputStream(os.toByteArray()));
        user1=(User)hi.readObject();
        System.out.println(user1);
    }
}

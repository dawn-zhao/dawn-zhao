package com.dawn.zhao.string;

public class VersionCompare {

    public static void main(String[] args) {
        System.out.println(versionCompare("1.8.0", "1.8.12") > 0);
    }

    public static int versionCompare(String newVersion,String oldVersion){
        int i=0,j=0,x=0,y=0;
        int v1Len=newVersion.length();
        int v2Len=oldVersion.length();
        char c;
        do {
            while(i<v1Len){//计算出V1中的点之前的数字
                c=newVersion.charAt(i++);
                if(c>='0' && c<='9'){
                    x=x*10+(c-'0');//c-‘0’表示两者的ASCLL差值
                }else if(c=='.'){
                    break;//结束
                }else{
                    //无效的字符
                }
            }
            while(j<v2Len){//计算出V2中的点之前的数字
                c=oldVersion.charAt(j++);
                if(c>='0' && c<='9'){
                    y=y*10+(c-'0');
                }else if(c=='.'){
                    break;//结束
                }else{
                    //无效的字符
                }
            }
            if(x<y){
                return -1;
            }else if(x>y){
                return 1;
            }else{
                x=0;y=0;
                continue;
            }

        } while ((i<v1Len) || (j<v2Len));
        return 0;
    }
}

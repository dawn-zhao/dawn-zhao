package com.dawn.zhao.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class Geohash {

    private static int numbits = 6 * 5;
    final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    final static HashMap<Character, Integer> lookup = new HashMap<>();

    public Geohash() {
        super();
        setMap();
    }

    /**
     * 在纬度相等的情况下:
     * 经度每隔0.00001度，距离相差约1米;
     * 每隔0.0001度，距离相差约10米;
     * 每隔0.001度，距离相差约100米;
     * 每隔0.01度，距离相差约1000米;
     * 每隔0.1度，距离相差约10000米;

     * 在经度相等的情况下:
     * 纬度每隔0.00001度，距离相差约1.1米;
     * 每隔0.0001度，距离相差约11米;
     * 每隔0.001度，距离相差约111米;
     * 每隔0.01度，距离相差约1113米;
     * 每隔0.1度，距离相差约11132米;
     *
     * geohash 的位数是6位数的时候，大概为附近1千米
     *
     */

    public static double[] decode(String geohash) {
        StringBuilder buffer = new StringBuilder();
        for (char c : geohash.toCharArray()) {

            int i = lookup.get(c) + 32;
            buffer.append( Integer.toString(i, 2).substring(1) );
        }

        BitSet lonset = new BitSet();
        BitSet latset = new BitSet();

        //even bits
        int j =0;
        for (int i=0; i< numbits*2;i+=2) {
            boolean isSet = false;
            if ( i < buffer.length() )
                isSet = buffer.charAt(i) == '1';
            lonset.set(j++, isSet);
        }

        j=0;
        for (int i=1; i< numbits*2;i+=2) {
            boolean isSet = false;
            if ( i < buffer.length() )
                isSet = buffer.charAt(i) == '1';
            latset.set(j++, isSet);
        }

        double lon = decode(lonset, -180, 180);
        double lat = decode(latset, -90, 90);

        return new double[] {lat, lon};
    }

    private static double decode(BitSet bs, double floor, double ceiling) {
        double mid = 0;
        for (int i=0; i<bs.length(); i++) {
            mid = (floor + ceiling) / 2;
            if (bs.get(i))
                floor = mid;
            else
                ceiling = mid;
        }
        return mid;
    }


    public static String encode(double lat, double lon) {
        BitSet latbits = getBits(lat, -90, 90);
        BitSet lonbits = getBits(lon, -180, 180);
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < numbits; i++) {
            buffer.append( (lonbits.get(i))?'1':'0');
            buffer.append( (latbits.get(i))?'1':'0');
        }
        return base32(Long.parseLong(buffer.toString(), 2));
    }

    private static BitSet getBits(double lat, double floor, double ceiling) {
        BitSet buffer = new BitSet(numbits);
        for (int i = 0; i < numbits; i++) {
            double mid = (floor + ceiling) / 2;
            if (lat >= mid) {
                buffer.set(i);
                floor = mid;
            } else {
                ceiling = mid;
            }
        }
        return buffer;
    }

    public static String base32(long i) {
        char[] buf = new char[65];
        int charPos = 64;
        boolean negative = (i < 0);
        if (!negative)
            i = -i;
        while (i <= -32) {
            buf[charPos--] = digits[(int) (-(i % 32))];
            i /= 32;
        }
        buf[charPos] = digits[(int) (-i)];
        if (negative)
            buf[--charPos] = '-';
        return new String(buf, charPos, (65 - charPos));
    }

    private static String BASE32 = "0123456789bcdefghjkmnpqrstuvwxyz";
    private static Map<String, String> BORDERS = new HashMap<>();
    private static Map<String, String> NEIGHBORS = new HashMap<>();

    static {
        int i = 0;
        for (char c : digits)
            lookup.put(c, i++);
        setMap();
    }

    public static void setMap() {
        NEIGHBORS.put("right:even", "bc01fg45238967deuvhjyznpkmstqrwx");
        NEIGHBORS.put("left:even", "238967debc01fg45kmstqrwxuvhjyznp");
        NEIGHBORS.put("top:even", "p0r21436x8zb9dcf5h7kjnmqesgutwvy");
        NEIGHBORS.put("bottom:even", "14365h7k9dcfesgujnmqp0r2twvyx8zb");

        NEIGHBORS.put("right:odd", "p0r21436x8zb9dcf5h7kjnmqesgutwvy");
        NEIGHBORS.put("left:odd", "14365h7k9dcfesgujnmqp0r2twvyx8zb");
        NEIGHBORS.put("top:odd", "bc01fg45238967deuvhjyznpkmstqrwx");
        NEIGHBORS.put("bottom:odd", "238967debc01fg45kmstqrwxuvhjyznp");

        BORDERS.put("right:even", "bcfguvyz");
        BORDERS.put("left:even", "0145hjnp");
        BORDERS.put("top:even", "prxz");
        BORDERS.put("bottom:even", "028b");

        BORDERS.put("right:odd", "prxz");
        BORDERS.put("left:odd", "028b");
        BORDERS.put("top:odd", "bcfguvyz");
        BORDERS.put("bottom:odd", "0145hjnp");
    }

    /**
     * 获取九个点的矩形编码
     *
     * @param geohash
     * @return
     */
    public static List<String> getGeoHashExpand(String geohash) {
        List<String> codes = new ArrayList<>();

        String geohashTop = calculateAdjacent(geohash, "top");
        String geohashBottom = calculateAdjacent(geohash, "bottom");
        String geohashLeft = calculateAdjacent(geohash, "left");
        String geohashRight = calculateAdjacent(geohash, "right");

        codes.add(geohashTop);
        codes.add(geohashBottom);
        codes.add(geohashLeft);
        codes.add(geohashRight);

        codes.add(calculateAdjacent(geohashLeft, "top"));
        codes.add(calculateAdjacent(geohashRight, "top"));
        codes.add(calculateAdjacent(geohashLeft, "bottom"));
        codes.add(calculateAdjacent(geohashRight, "bottom"));

        codes.add(geohash);

        return codes;
    }

    public static void main(String[] args) {
        System.out.println(JSONObject.toJSONString(getGeoHashExpand("wx4g45mr")));
        System.out.println(JSONObject.toJSONString(getGeoHashExpand("wx4g45m")));
    }

    /**
     * 分别计算每个点的矩形编码
     *
     * @param srcHash
     * @param dir
     * @return
     */
    public static String calculateAdjacent(String srcHash, String dir) {
        srcHash = srcHash.toLowerCase();
        char lastChr = srcHash.charAt(srcHash.length() - 1);
        int a = srcHash.length() % 2;
        String type = (a > 0) ? "odd" : "even";
        String base = srcHash.substring(0, srcHash.length() - 1);
        if (BORDERS.get(dir + ":" + type).indexOf(lastChr) != -1) {
            base = calculateAdjacent(base, dir);
        }
        base = base + BASE32.toCharArray()[(NEIGHBORS.get(dir + ":" + type).indexOf(lastChr))];
        return base;
    }


    private static final double EARTH_RADIUS = 6371;// 赤道半径(单位m)

    /**
     * 转化为弧度(rad)
     * */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     *
     * @param lng1
     *            第一点的精度
     * @param lat1
     *            第一点的纬度
     * @param lng2
     *            第二点的精度
     * @param lat2
     *            第二点的纬度
     * @return 返回的距离，单位 m
     * */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double a, b, R;
        R = 6378137; // 地球半径
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (lng1 - lng2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
        return d;
    }

}

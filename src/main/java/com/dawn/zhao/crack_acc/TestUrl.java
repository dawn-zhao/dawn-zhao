package com.dawn.zhao.crack_acc;


public class TestUrl {
    private static final String TAG = "TestUrl";
    private static String FORMAT = "aac";
    private static String BITRATE = "48k";

    // 发布时换到正常的域名
    private static final String OLD_HOST = "http://mobi.kuwo.cn/mobi.s?f=kuwo&q=";

    private static final byte[] OLD_SECRET_KEY = "ylzsxkwm".getBytes(); // des密钥
    private static final int OLD_SECRET_KEY_LENGTH = OLD_SECRET_KEY.length;

    // 手机IMEI号
    public static String DEVICE_ID = "";
    // 版本名
    public static String VERSION_NAME = "";

    public static void main(String[] args) {
        String res = getSongUrl(113244609, "acc", "320kmp3");
        System.out.println(res);
//        http://mobi.kuwo.cn/mobi.s?f=kuwo&q=rxSWWOXCep508N6jXVwtmzIoSAi5h4W0DyNBU+jYjUtE+3BMjOA9Gn6zVG8JfgMZYKxpEVnaU/UKE48KMznKcg==
//        http://mobi.kuwo.cn/mobi.s?f=kuwo&q=rxSWWOXCep508N6jXVwtmzIoSAi5h4W0DyNBU+jYjUtE+3BMjOA9GhsRpGv5EZZnYKxpEVnaU/UKE48KMznKcg==
    }


    /**
     * 获取歌曲防盗链
     *
     * @param rid    歌曲的rid, 如，34564564
     * @param format 客户端支持的音频文件格式，若服务器上存在多种格式的音频资源，则按先后顺序返回第一种，如，mp3|aac
     * @param rate   资源类型和码率，大小写不区分，如，128kmp3|48kaac|192kmp3|320kmp3
     * @return
     */
    public static String getSongUrl(int rid, String format, String rate) {
        StringBuilder buffer = new StringBuilder(getSongParams());
        buffer.append("&type=convert_url2");
        buffer.append("&br=").append(rate);
        buffer.append("&format=").append(format);
        buffer.append("&rid=").append(rid);
        return OLD_HOST + encryptOld(buffer.toString());
    }

    private static String mSongParameter;
    private static String getSongParams() {
        if (isNullOrEmpty(mSongParameter)) {
            StringBuilder sb = new StringBuilder();
            sb.append("&uid=").append(DEVICE_ID);
            sb.append("&v=").append(VERSION_NAME);
//            sb.append("&ch=").append(AppContext.UMENG_CHANNEL_STR);
            mSongParameter = sb.toString();
        }
        return mSongParameter;
    }

    public static String encryptOld(String initial) {
//        System.out.println(initial);
        byte[] paramsBytes = initial.getBytes();
        byte[] bytes = KuwoDES.encrypt(paramsBytes, paramsBytes.length, OLD_SECRET_KEY, OLD_SECRET_KEY_LENGTH);
        String b64Params = new String(Base64Coder.encode(bytes, bytes.length));
        System.out.println(b64Params);
        return b64Params;
    }

    public static boolean isNullOrEmpty(final String str) {
        return str == null || "".equals(str) || "null".equals(str);
    }

}

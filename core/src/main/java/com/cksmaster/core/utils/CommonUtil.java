package com.cksmaster.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 公用Util
 * User: jeff
 * Date: 13-7-30
 * Time: 上午10:32
 */
public class CommonUtil {

    public static final TypeReference<Map<String, Object>> TYPE_REFERENCE_MAP_STRING_OBJECT = new TypeReference<Map<String, Object>>() {
    };
    /**
     * 去除IPHONE的token传统问题
     */
    private static final Pattern APPLE_TOKEN_FIX_PATTERN = Pattern.compile("[<>\\s]");
    /**
     * 苹果的token的格式
     */
    private static final Pattern APPLE_TOKEN_PATTERN = Pattern.compile("^[0-9a-fA-F]*$");
    /**
     * 正则 - IPAD UA
     */
    private static final Pattern UA_IPAD = Pattern.compile(".*ipad.*", Pattern.CASE_INSENSITIVE);
    /**
     * 正则 - IPOD UA
     */
    private static final Pattern UA_IPOD = Pattern.compile(".*ipod.*", Pattern.CASE_INSENSITIVE);
    /**
     * 正则 - IPHONE UA
     */
    private static final Pattern UA_IPHONE = Pattern.compile(".*iphone.*", Pattern.CASE_INSENSITIVE);
    /**
     * 正则 - 安卓 UA
     */
    private static final Pattern UA_ANDROID = Pattern.compile(".*android.*", Pattern.CASE_INSENSITIVE);
    /**
     * 正则 - 微信 UA
     */
    private static final Pattern UA_WEIXIN = Pattern.compile(".*micromessenger.*", Pattern.CASE_INSENSITIVE);
    /**
     * 正则 - 微信 UA
     */
    private static final Pattern UA_MMBANG = Pattern.compile(".*mmbang.*", Pattern.CASE_INSENSITIVE);
    /**
     * 正则 - ios 7.0
     */
    private static final Pattern IOS_7_0 = Pattern.compile(".*7_0.*", Pattern.CASE_INSENSITIVE);
    /**
     * 正则 - ios 7.1
     */
    private static final Pattern IOS_7_1 = Pattern.compile(".*7_1.*", Pattern.CASE_INSENSITIVE);
    /**
     * 36进制字母数组
     */
    private static char[] BASE_36_CHAR = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static String genUUID(boolean includeDelimiter) {
        String uuid = UUID.randomUUID().toString();
        if (!includeDelimiter) {
            uuid = uuid.replaceAll("-", "");
        }
        return uuid;
    }

    /**
     * 生成36进制字符串
     *
     * @param length 长度
     * @return 36进制字符串
     */
    public static String genRandomNum(int length) {
        StringBuilder buf = new StringBuilder();
        while (length > 0) {
            buf.append(BASE_36_CHAR[MathUtil.nextInt(BASE_36_CHAR.length)]);
            length--;
        }
        return buf.toString();
    }

    /**
     * 生成N位随机字符(数字加英文大小写)
     * @param length 长度
     * @return N位随机字符
     */
    public static String genRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(62);
            buf.append(str.charAt(num));
        }
        return buf.toString();
    }

    /**
     * 生成N位随机数字串
     * @param length 长度
     * @return N位随机数字串
     */
    public static String genRandomNumber(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(10);
            buf.append(str.charAt(num));
        }
        return buf.toString();
    }

    /**
     * @return 生成的mac
     */
    public static String genMac() {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (i > 0) {
                buf.append(":");
            }
//            buf.append(BASE_36_CHAR[MathUtil.nextInt(BASE_36_CHAR.length)])
//                    .append(BASE_36_CHAR[MathUtil.nextInt(BASE_36_CHAR.length)]);
            int num = MathUtil.random(0, 255);
            if (i == 0) {
                if (num % 2 == 1) {
                    num = num - 1;
                }
                if (num < 0) {
                    num = 0;
                }
            }
            String hex = Integer.toHexString(num);
            if (hex.length() < 2) {
                hex += "0";
            }
            buf.append(hex);
        }
        return buf.toString();
    }

    /**
     * @param token apple Token
     * @return 修复后的apple Token
     */
    public static String fixAppleToken(String token) {
        // TODO 【代码优化】【from 芋艿 to 芋艿】【[^0-9a-fA-F]】
        return APPLE_TOKEN_FIX_PATTERN.matcher(token).replaceAll("");
    }

    /**
     * 校验苹果推送token是否合法
     *
     * @param token 苹果token
     * @return 是否合法
     */
    public static boolean validAppleToken(String token) {
        return !StringUtil.isEmpty(token)
                && token.length() == 64
                && APPLE_TOKEN_PATTERN.matcher(token).matches();
    }

    /**
     * @param uglyJSONString 未格式化的JSON字符串
     * @return 格式化后的字符串
     */
    public static String jsonFormatter(String uglyJSONString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(uglyJSONString);
        return gson.toJson(je);
    }

    /**
     * 判断该BitField的值第index位是否有设置，即为1。
     *
     * @param val   BitField的值
     * @param index 从右往左数，并且从0开始
     * @return true/false
     */
    public static boolean isBitFieldSet(Integer val, int index) {
        return val != null && ((val >> index) & 1) > 0;
    }

    public static Integer bitFieldSet(Integer val, int index) {
        if (val == null) {
            val = 0;
        }
        return val | (1 << index);
    }

    /**
     * @param ua UA。必须全部小写
     * @return 是否为ipad的UA
     */
    public static boolean isIpadUA(String ua) {
        return UA_IPAD.matcher(ua).matches();
    }

    /**
     * @param ua UA。必须全部小写
     * @return 是否为iphone的UA
     */
    public static boolean isIphoneUA(String ua) {
        return UA_IPHONE.matcher(ua).matches();
    }

    /**
     * @param ua UA。必须全部小写
     * @return 是否为ipod的UA
     */
    public static boolean isIpodUA(String ua) {
        return UA_IPOD.matcher(ua).matches();
    }

    /**
     * @param ua UA。必须全部小写
     * @return 是否为安卓的UA
     */
    public static boolean isAndroidUA(String ua) {
        return UA_ANDROID.matcher(ua).matches();
    }

    /**
     * @param ua 浏览器UA
     * @return 是否为微信内置浏览器
     */
    public static boolean isWeiXin(String ua) {
        return StringUtils.hasText(ua) && UA_WEIXIN.matcher(ua).matches();
    }

    /**
     * @param ua 浏览器UA
     * @return 是否为妈妈帮浏览器
     */
    public static boolean isMmBang(String ua) {
        return StringUtils.hasText(ua) && UA_MMBANG.matcher(ua).matches();
    }

    /**
     * 是否IOS7.1
     *
     * @param ua
     * @return
     */
    public static boolean isIOS7_1(String ua) {
        return isIphoneUA(ua) && IOS_7_1.matcher(ua).matches();
    }

    /**
     * 是否IOS7.0
     *
     * @param ua
     * @return
     */
    public static boolean isIOS7_0(String ua) {
        return isIphoneUA(ua) && IOS_7_0.matcher(ua).matches();
    }

    /**
     * 将json对象里，指定的key的value转化为int型
     *
     * @param obj  json对象
     * @param keys 需要转化的key数组
     * @return json对象
     */
    public static JSONObject convertInt(JSONObject obj, String... keys) {
        if (keys.length > 0) {
            for (String key : keys) {
                Object value = obj.remove(key);
                if (value == null) {
                    continue;
                }
                String val = String.valueOf(value);
                if (NumberUtils.isNumber(val)) {
                    obj.put(key, Integer.valueOf(val));
                }
            }
        }
        return obj;
    }

    public static void debug(Object object, boolean prettyFormat) {
        System.out.println(JSON.toJSONString(object, prettyFormat));
    }

    public static boolean validJSON(String text) {
        try {
            JSON.parse(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Object checkJSON(String text) {
        try {
            return JSON.parse(text);
        } catch (Exception e) {
            throw e;
        }
    }



    public static void downLoadExcel(HttpServletResponse response, String fileName, HSSFWorkbook wb) {
        fileName = fileName + ".xls";
        response.setContentType("application/x-msexcel; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        OutputStream os = null;
        try {
            response.setHeader("Content-Disposition", "attachment; filename=\""
                    + new String(fileName.getBytes("UTF-8"), "iso8859-1")
                    + "\"");
            os = response.getOutputStream();
            wb.write(os);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                os = null;
            }
        }
    }

    /**
     * 下载文件公共方法
     *
     * @param response 服务器response
     * @param filePath 文件路径：示例：http://fs.921cha.com/22/316d45943bf14b909f5ce470a6e6d174.jpg
     * @param fileName 保存文件名：示例：abc.jpg
     */
    // TODO 【code review】from 芋艿 to 江奎：看下IOUtils，可以让这个精简超级多。
    public static void downLoadFile(HttpServletResponse response, String filePath, String fileName) {
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        OutputStream out = null;
        try {
            response.setHeader("Content-Disposition", "attachment;filename=\""
                    + new String(fileName.getBytes("GBK"), "iso8859-1")
                    + "\"");
            URL url = new URL(filePath);
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            out = new BufferedOutputStream(response.getOutputStream());
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inStream.read(buffer)) != -1) {
                out.write(buffer, 0, len);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out = null;
            }
        }
    }

    /**
     * @param request 请求
     * @param url URL
     * @return url重写,带k/udid/v/t/k/_c/_SDK等
     */
    public static String urlX(HttpServletRequest request, String url) {
        return url
                + "?udid=" + request.getParameter("udid")
                + "&v=" + request.getParameter("v")
                + "&t=" + request.getParameter("t")
                + "&k=" + request.getParameter("k")
                + "&_c=" + request.getParameter("_c")
                + "&_SDK=" + request.getParameter("_SDK");
    }

    public static void main(String[] args) {
        System.out.println(CommonUtil.genRandomNum(6));
//        System.out.println(validJSON(""));
//        System.out.println(validJSON(null));
//        System.out.println(validJSON("{1: 2}"));
//        System.out.println(validJSON("[]"));
//        System.out.println(bitFieldSet(bitFieldSet(0, 0), 1));
//        Integer audioConvertBits = 0;
//        audioConvertBits = CommonUtil.bitFieldSet(audioConvertBits, AudioConstants.AUDIO_CONVERT_BITS_MP3);
//        audioConvertBits = CommonUtil.bitFieldSet(audioConvertBits, AudioConstants.AUDIO_CONVERT_BITS_AMR);
//        audioConvertBits = CommonUtil.bitFieldSet(audioConvertBits, AudioConstants.AUDIO_CONVERT_BITS_SPX);
//        System.out.println(audioConvertBits);
    }
}

package com.gin.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Info 工具类，比较杂什么都有
 */
public class Info {

    /**
     * 本地保存图片的路径,也就是你项目路径，到WebRoot
     */
    public static final String localUploadPath = null;

    /**
     * json 编码
     * @param source
     * @return
     */
    public static String jsonEncode(Object source) {
        return JSON.toJSONString(source);
    }

    /**
     * JSON 解码
     * @param source
     * @return
     */
    public static JSONObject jsonDecode(Object source) {
        if (source == null) {
            return JSON.parseObject("{}");
        }

        return JSON.parseObject(String.valueOf(source));
    }

    public static JSONArray jsonDecodeArray(Object source) {
        if (source == null) {
            return JSON.parseArray("[]");
        }
        return JSON.parseArray(String.valueOf(source));
    }

    public static List objectSplit(String exp, Object str) {
        List arr = new ArrayList();
        if (str == null) {
            return arr;
        }
        String s = String.valueOf(str);
        String[] sp = s.split(exp);
        return Arrays.asList(sp);
    }

    /**
     * 获取所有子集下的id
     * @param table 表名
     * @param pid   父级字段
     * @param value 获取的所有子集
     * @return
     */
    public static String getAllChild(String table, String pid, Object value) {
        List templists = Query.make(table).select();
        return StringUtil.join(",", getAllChild(table, pid, value, templists));
    }

    public static List getAllChild(String table, String pid, Object value, List templists) {
        List $ret = null;
        List<Map> lists = templists;
        List $result = new ArrayList();

        String parentid = String.valueOf(value);
        $result.add(parentid);
        for (Map child : lists) {
            if (child.get(pid).equals(parentid)) {
                $ret = getAllChild(table, pid, child.get("id"), templists);
                if ($ret.size() > 0) {
                    $result.addAll($ret);
                }
            }
        }
        return $result;
    }

    public static String postion(String table, String pid, String name, String value) {
        List items = new ArrayList();
        String parentid = value;
        do {
            Map mp = Query.make(table).find(parentid);
            if (mp == null || mp.isEmpty()) {
                break;
            }
            items.add(mp.get(name));
            parentid = mp.get(pid).toString();
        } while (!parentid.equals("") && !parentid.equals("0"));
        Collections.reverse(items);
        return StringUtil.join(" ", items);
    }

    public static String getTreeOption(String table, String pid, String name, Object value) {
        return postion(table, pid, name, String.valueOf(value));
    }

    /**
     * 比较两个日期大小 日期格式必须是：yyyy-MM-dd HH:mm
     * @param dateA
     * @param dateB
     * @return
     */
    public static int getBetweenDayNumber(String dateA, String dateB) {
        long dayNumber = 0;
        //1小时=60分钟=3600秒=3600000
        long mins = 60L * 1000L;
        //long day= 24L * 60L * 60L * 1000L;计算天数之差
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date d1 = df.parse(dateA);
            Date d2 = df.parse(dateB);
            dayNumber = (d2.getTime() - d1.getTime()) / mins;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) dayNumber;
    }

    /**
     * 自动生成编号
     * @return
     */
    public static synchronized String getID() {
        Random random = new Random();
        StringBuffer ret = new StringBuffer(20);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.CHINA);
        ret.append(format.format(date));
        String rand = String.valueOf(Math.abs(random.nextInt()));
        //ret.append(getDateStr());
        ret.append(rand.substring(0, 4));

        return ret.toString();
    }

    /**
     * 字符串截取，先把html 标签去除
     * @param source
     * @param length
     * @param append 使用的字符串
     * @return
     */
    public static synchronized String subStr(Object source, int length, String append) {
        String str = delHTMLTag(source.toString());

        if (str.length() > length) {
            str = (str.substring(0, length)) + append;
        }
        return str;
    }

    /**
     * 字符串截取，先把html 标签去除
     * @param source
     * @param length
     * @return
     */
    public static synchronized String subStr(Object source, int length) {
        return subStr(source, length, "...");
    }

    /**
     * 获取json 数据中的address 字段
     * @param addre
     * @return
     */
    public static String address(Object addre) {
        String add = addre.toString();
        if (add == null || add.length() == 0) {
            return "";
        }
        JSONObject json = JSONObject.parseObject(add);
        if (json != null && !json.isEmpty()) {
            return json.getString("address");
        }
        return "";
    }

    /**
     * 获取第一张图片
     * @param nImages
     * @return
     */
    public static String images(Object nImages) {
        String str = nImages == null ? "" : nImages.toString();
        if (str.indexOf(",") >= 0) {
            String[] li = str.split(",");
            return li[0];
        }
        return str;
    }

    /**
     * 获取当前日期时间
     * @return
     */
    public static String getDateStr() {
        String dateString = "";
        try { //yyyyMMddHHmmss
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date currentTime_1 = new Date();
            dateString = formatter.format(currentTime_1);
        } catch (Exception e) {}
        return dateString;
    }

    /**
     * url 编码，中文要进行编码输出
     * @param str
     * @return
     */
    public static String urlencode(Object str) {
        try {
            return java.net.URLEncoder.encode(String.valueOf(str), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    /**
     * url 解码
     * @param str
     * @return
     */
    public static String urldecode(Object str) {
        try {
            return java.net.URLDecoder.decode(String.valueOf(str), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    /**
     * 获取 UTF-8字符串
     * @param str
     * @return
     */
    public static String getUTFStr(String str) {
        if (str == null) {
            return "";
        }

        try {
            str = new String(str.getBytes("ISO-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 删除html标签
     * @param htmlStrParam
     * @return
     */
    public static String delHTMLTag(Object htmlStrParam) {
        if (htmlStrParam == null) {
            return "";
        }
        String htmlStr = htmlStrParam.toString();
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签

        return htmlStr.trim(); //返回文本字符串
    }

    /**
     * 获取多少天后的日期
     * @param date
     * @param day
     * @return
     */
    public static String getDay(String date, int day) {
        String b = date.substring(0, 10);
        String c = b.substring(0, 4);
        String d = b.substring(5, 7);
        String f = b.substring(8, 10);
        String aa = c + "/" + d + "/" + f;
        String a = "";
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        GregorianCalendar grc = new GregorianCalendar();
        grc.setTime(new Date(aa));
        grc.add(GregorianCalendar.DAY_OF_MONTH, day);
        String resu = dateFormat.format(grc.getTime());
        String t[] = resu.split("-");
        String sesuu = "";
        for (int i = 0; i < t.length; i++) {
            if (t[i].length() == 1) {
                t[i] = "0" + t[i];
            }
            sesuu += t[i] + "-";
        }
        return sesuu.substring(0, 10);
    }

    /**
     * 比较时间大小
     */
    public static long compare_datetime(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            return dt1.getTime() - dt2.getTime();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return -1;
    }

    /**
     * 比较时间大小
     */
    public static String compare_datezq(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return "big";
            } else if (dt1.getTime() < dt2.getTime()) {
                return "small";
            } else {
                return "den";
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "den";
    }

    /**
     * 将HTML 标签格式化
     * @param objsource
     * @return
     */
    public static String html(Object objsource) {
        if (objsource == null) {
            return "";
        }

        String source = String.valueOf(objsource);

        String html = "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
                case '<':
                    buffer.append("&lt;");
                    break;
                case '>':
                    buffer.append("&gt;");
                    break;
                case '&':
                    buffer.append("&amp;");
                    break;
                case '"':
                    buffer.append("&quot;");
                    break;
                default:
                    buffer.append(c);
            }
        }
        html = buffer.toString();
        return html;
    }

    /**
     * 格式化日期
     * @param format
     * @return
     */
    public static String date(String format) {
        return date(format, null);
    }

    /**
     * 根据时间戳格式化日期
     * @param format
     * @param time  时间戳 秒
     * @return
     */
    public static String date(String format, long time) {
        return date(format, new Date(time * 1000));
    }

    /**
     * 获取当前时间戳
     * @return
     */
    public static long time() {
        return Long.valueOf(new Date().getTime() / 1000);
    }

    /**
     * 根据date 类型格式化日期
     * @param format
     * @param time
     * @return
     */
    public static String date(String format, Date time) {
        if (time == null) {
            time = new Date();
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(time);
    }
}


package com.repayment.money.common.utils.pay;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.repayment.money.common.utils.pay.env.EnvConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 工具类
 */
public class BaseHelper {
    public static final String PARAM_EQUAL = "=";
    public static final String PARAM_AND = "&";

    /**
     * 流转字符串方法
     * 
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try
        {
            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                is.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 显示dialog
     * 
     * @param context 环境
     * @param strTitle 标题
     * @param strText 内容
     * @param icon 图标
     */
    public static void showDialog(Activity context, String strTitle,
                                  String strText, int icon)
    {
        try
        {
            AlertDialog.Builder tDialog = new AlertDialog.Builder(context);
            tDialog.setIcon(icon);
            tDialog.setTitle(strTitle);
            tDialog.setMessage(strText);
            tDialog.setPositiveButton("确定", null);
            tDialog.show();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 打印信息
     * 
     * @param tag 标签
     * @param info 信息
     */
    public static void log(String tag, String info)
    {
        Log.i(tag, info);
    }

    /**
     * 获取权限
     * 
     * @param permission 权限
     * @param path 路径
     */
    public static void chmod(String permission, String path)
    {
        try
        {
            String command = "chmod " + permission + " " + path;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //
    // show the progress bar.
    /**
     * 显示进度条
     * 
     * @param context 环境
     * @param title 标题
     * @param message 信息
     * @param indeterminate 确定性
     * @param cancelable 可撤销
     * @return
     */
    public static ProgressDialog showProgress(Context context,
                                              CharSequence title, CharSequence message, boolean indeterminate,
                                              boolean cancelable)
    {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setIndeterminate(indeterminate);
        dialog.setCancelable(false);

        dialog.show();
        return dialog;
    }

    /**
     * 字符串转json对象
     * 
     * @param str
     * @param split
     * @return
     */
    public static JSONObject string2JSON(String str, String split)
    {
        JSONObject json = new JSONObject();
        try
        {
            String[] arrStr = str.split(split);
            for (int i = 0; i < arrStr.length; i++)
            {
                String[] arrKeyValue = arrStr[i].split("=");
                json.put(arrKeyValue[0],
                        arrStr[i].substring(arrKeyValue[0].length() + 1));
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return json;
    }

    public static JSONObject string2JSON(String str)
    {
        try
        {
            return new JSONObject(str);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public static String toJSONString(Object obj)
    {
        JSONObject json = new JSONObject();
        try
        {
            Map<String, String> map = bean2Parameters(obj);
            for (Map.Entry<String, String> entry : map.entrySet())
            {
                json.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return json.toString();
    }
    
    public static JSONObject Object2JSON(Object obj)
    {
        JSONObject json = new JSONObject();
        try
        {
            Map<String, String> map = bean2Parameters(obj);
            for (Map.Entry<String, String> entry : map.entrySet())
            {
                json.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 将bean转换成键值对列表
     * 
     * @param bean
     * @return
     */
    public static Map<String, String> bean2Parameters(Object bean)
    {
        if (bean == null)
        {
            return null;
        }
        
		Map<String, String> parameters = new HashMap<String, String>();
		
        if(null != parameters) {
            // 取得bean所有public 方法
            Method[] Methods = bean.getClass().getMethods();
            for (Method method : Methods)
            {
                if (method != null && method.getName().startsWith("get")
                        && !method.getName().startsWith("getClass"))
                {
                    // 得到属性的类名
                    String value = "";
                    // 得到属性值
                    try
                    {
                        String className = method.getReturnType().getSimpleName();
                        if (className.equalsIgnoreCase("int"))
                        {
                            int val = 0;
                            try
                            {
                                val = (Integer) method.invoke(bean);
                            } catch (InvocationTargetException e)
                            {
                                Log.e("InvocationTargetEx", e.getMessage(), e);
                            }
                            value = String.valueOf(val);
                        } else if (className.equalsIgnoreCase("String"))
                        {
                            try
                            {
                                value = (String) method.invoke(bean);
                            } catch (InvocationTargetException e)
                            {
                                Log.e("InvocationTargetEx", e.getMessage(),
                                        e);
                            }
                        }
                        if (value != null && !value.equals(""))
                        {
                            // 添加参数
                            // 将方法名称转化为id，去除get，将方法首字母改为小写
                            String param = method.getName().replaceFirst("get", "");
                            if (param.length() > 0)
                            {
                                String first = String.valueOf(param.charAt(0)).toLowerCase();
                                param = first + param.substring(1);
                            }
                            parameters.put(param, value);
                        }
                    } catch (IllegalArgumentException e)
                    {
                        Log.e("IllegalArgumentEx", e.getMessage(), e);
                    } catch (IllegalAccessException e)
                    {
                        Log.e("IllegalAccessEx", e.getMessage(), e);
                    }
                }
            }
        }

        return parameters;
    }
    
    /**
     * 对Object进行List<NameValuePair>转换后按key进行升序排序，以key=value&...形式返回
     * 
     * @param list
     * @return
     */
    public static String sortParam(Object order)
    {
    	Map<String, String> map = bean2Parameters(order);
        return sortParam(map);
    }

    /**
     * 对对Object转换后, 以key=value&...形式返回按key进行升序排序，以key=value&...形式返回
     * 
     * @param order
     * @return
     */
    public static String sortParam(Map<String, String> order)
    {
        if (null == order){
            return null;
        }
        
		Map<String, String> parameters = new TreeMap<String, String>(
				new Comparator<String>() {
					public int compare(String obj1, String obj2) {
						return obj1.compareToIgnoreCase(obj2);
					}
				});
		parameters.putAll(order);
		
        if(null != parameters) {
            StringBuffer sb = new StringBuffer();
            for (Map.Entry<String, String> entry : parameters.entrySet())
            {

                if (null != entry.getValue() && !"".equals(entry.getValue())
                        && !entry.getKey().equals("id_type")
                        && !entry.getKey().equals("id_no")
                        && !entry.getKey().equals("acct_name")
                        && !entry.getKey().equals("flag_modify")
                        && !entry.getKey().equals("user_id")
                        && !entry.getKey().equals("no_agree")
                        && !entry.getKey().equals("card_no")
                        && !entry.getKey().equals("test_mode")
                        && !entry.getKey().equals("shareing_data")
                        && !entry.getKey().equals("payload")
                        && !entry.getKey().equals("platform")
                        && !entry.getKey().equals("product_type")
                        && !entry.getKey().equals("pay_type"))
                {
                    sb.append(entry.getKey());
                    sb.append(PARAM_EQUAL);
                    sb.append(entry.getValue());
                    sb.append(PARAM_AND);
                }
            }
            
            String params = sb.toString();
            if (sb.toString().endsWith(PARAM_AND))
            {
                params = sb.substring(0, sb.length() - 1);
            }
            Log.v("待签名串", params);
            return params;
        }
        
        return null;
    }
    
    /**
     * 对Object进行List<NameValuePair>转换后按key进行升序排序，以key=value&...形式返回
     * 
     * @param list
     * @return
     */
    public static String sortParamForSignCard(Object order)
    {
    	Map<String, String> map = bean2Parameters(order);
        return sortParamForSignCard(map);
    }

    /**
     * 对Object进行转换后按key进行升序排序，以key=value&...形式返回
     * 单独签约
     * @param object
     * @return
     */
    public static String sortParamForSignCard(Map<String, String> order)
    {
        if (null == order){
            return null;
        }
        
		Map<String, String> parameters = new TreeMap<String, String>(
				new Comparator<String>() {
					public int compare(String obj1, String obj2) {
						return obj1.compareToIgnoreCase(obj2);
					}
				});
		parameters.putAll(order);
		
        if(null != parameters) {
            StringBuffer sb = new StringBuffer();
            for (Map.Entry<String, String> entry : parameters.entrySet())
            {
                if (null != entry.getValue() && !"".equals(entry.getValue())) {
                    sb.append(entry.getKey());
                    sb.append(PARAM_EQUAL);
                    sb.append(entry.getValue());
                    sb.append(PARAM_AND);
                }
            }
            String params = sb.toString();
            if (sb.toString().endsWith(PARAM_AND))
            {
                params = sb.substring(0, sb.length() - 1);
            }
            Log.v("待签名串", params);
            return params;
        }
        
        return null;
    }

    public static void fillSign(PayOrder order, boolean isSignOrder) {

        String partner_signType = order.getSign_type();

        String sign_key = EnvConstants.getSignKey(order.getOid_partner(), order.getSign_type());

        String sign;// MD5 签名方式


        String content = null;

        if (isSignOrder){
            content = sortParamForSignCard(order);
        }
        else {
            content = sortParam(order);
        }

        // MD5 签名方式, 签名方式包括两种，一种是MD5，一种是RSA 这个在商户站管理里有对验签方式和签名Key的配置。
        if (partner_signType.equals("MD5")){
            sign = Md5Algorithm.getInstance().sign(content, sign_key);
        }
        else {
            // RSA 签名方式
            sign = Rsa.sign(content, sign_key);
        }

        order.setSign(sign);
    }
}

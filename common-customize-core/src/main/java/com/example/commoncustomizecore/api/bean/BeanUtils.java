package com.example.commoncustomizecore.api.bean;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeanUtils
{

    private static final Logger LOG = LoggerFactory.getLogger(BeanUtils.class);

    /**
     * 根据对象和属性获取属性值
     *
     * @param object
     *            被反射获取的类对象实例
     * @param field
     *            类的成员变量
     * @return the object value
     */
    public static Object getFieldValue(Object object, String field)
    {
        try
        {
            BeanUtilsBean beanUtils = BeanUtilsBean.getInstance();
            if (null != beanUtils)
            {
                return beanUtils.getPropertyUtils().getProperty(object, field);
            }
        }
        catch (IllegalAccessException e)
        {
            LOG.info("Get fieldValue faild {}", field);
            LOG.info("【getFieldValue】异常", e);
        }
        catch (InvocationTargetException e)
        {
            LOG.info("Get fieldValue faild {}", field);
            LOG.info("【getFieldValue】异常", e);
        }
        catch (NoSuchMethodException e)
        {
            LOG.info("Get fieldValue faild {}", field);
            LOG.info("【getFieldValue】异常", e);
        }

        return null;
    }

    /**
     * 获取类及父类的属性
     *
     * @Title: getDeclaredFields
     * @Description: TODO(获取类及父类的属性)
     * @param object
     *            目标对象
     * @return 参数
     * @return List<Field> 返回类型
     */
    public static List<Field> getDeclaredFields(Object object)
    {
        List<Field> fields = new ArrayList<>();

        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass())
        {
            try
            {
                fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            } catch (Exception e)
            {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }

        return fields;
    }

    /**
     * 获取类及父类的属性
     *
     * @Title: getDeclaredFields
     * @Description: TODO(获取类及父类的属性)
     * @param clazz
     *            类的类对象
     * @return 参数
     * @return List<Field> 返回类型
     */
    public static List<Field> getDeclaredFields(Class<?> clazz)
    {
        List<Field> fields = new ArrayList<>();
        for (; clazz != Object.class; clazz = clazz.getSuperclass())
        {
            try
            {
                fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            } catch (Exception e)
            {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }

        return fields;
    }

    /**
     * 根据属性名获取属性值
     * @Title: getFieldValue
     * @Description: TODO(根据属性名获取属性值)
     * @param object 目标对象
     * @param fieldName 属性名
     * @return    参数
     * @return Object    返回类型
     */
    /*public static Object getFieldValue(Object object, String fieldName)
    {
        if (StringUtils.isEmpty(fieldName))
        {
            return null;
        }
        Object value = BeanUtils.getFieldValue(object, fieldName);
        return value;
    }*/

    /**
     * 循环向上转型, 获取对象的 DeclaredField
     * @Title: getDeclaredField
     * @Description: TODO(循环向上转型, 获取对象的 DeclaredField)
     * @param object 子类对象
     * @param fieldName 父类中的属性名
     * @return    参数
     * @return Field    返回类型 父类中的属性对象
     */
    public static Field getDeclaredField(Object object, String fieldName)
    {
        Field field = null;

        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass())
        {
            try
            {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e)
            {
                // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }

        return null;
    }
}

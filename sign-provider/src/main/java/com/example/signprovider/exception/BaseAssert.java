package com.example.signprovider.exception;

import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;

public class BaseAssert {

    public static void notEmpty(String str, BaseErrorCode errorCode, Object... args) {
        if (ObjectUtils.isEmpty(str)) {
            String message = MessageFormat.format(errorCode.getMessage(), args);
            throw new BaseException(errorCode, message);
        }
    }

    public static void notEmpty(@Nullable Collection<?> collection, BaseErrorCode errorCode, Object... args) {
        if (CollectionUtils.isEmpty(collection)) {
            String message = MessageFormat.format(errorCode.getMessage(), args);
            throw new BaseException(errorCode, message);
        }
    }

    public static void notEmpty(@Nullable Object[] array, BaseErrorCode errorCode, Object... args) {
        if (ObjectUtils.isEmpty(array)) {
            String message = MessageFormat.format(errorCode.getMessage(), args);
            throw new BaseException(errorCode, message);
        }
    }

    public static void notEmpty(@Nullable Map<?, ?> map, BaseErrorCode errorCode, Object... args) {
        if (CollectionUtils.isEmpty(map)) {
            String message = MessageFormat.format(errorCode.getMessage(), args);
            throw new BaseException(errorCode, message);
        }
    }

    public static void isTrue(boolean expression, BaseErrorCode errorCode, Object... args) {
        if (!expression) {
            String message = MessageFormat.format(errorCode.getMessage(), args);
            throw new BaseException(errorCode, message);
        }
    }

    public static void isNull(@Nullable Object object, BaseErrorCode errorCode, Object... args) {
        if (object != null) {
            String message = MessageFormat.format(errorCode.getMessage(), args);
            throw new BaseException(errorCode, message);
        }
    }

    public static void notNull(@Nullable Object object, BaseErrorCode errorCode, Object... args) {
        if (object == null) {
            String message = MessageFormat.format(errorCode.getMessage(), args);
            throw new BaseException(errorCode, message);
        }
    }

    public static void hasLength(@Nullable String text, BaseErrorCode errorCode, Object... args) {
        if (!StringUtils.hasLength(text)) {
            String message = MessageFormat.format(errorCode.getMessage(), args);
            throw new BaseException(errorCode, message);
        }
    }

    public static void hasText(@Nullable String text, BaseErrorCode errorCode, Object... args) {
        if (!StringUtils.hasText(text)) {
            String message = MessageFormat.format(errorCode.getMessage(), args);
            throw new BaseException(errorCode, message);
        }
    }

    public static void noNullElements(@Nullable Object[] array, BaseErrorCode errorCode, Object... args) {
        if (array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if (element == null) {
                    String message = MessageFormat.format(errorCode.getMessage(), args);
                    throw new BaseException(errorCode, message);
                }
            }
        }

    }

}

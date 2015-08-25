package com.xyz.framework.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 提供静态方法，以便直接调用记录日志
 * @author Val
 */
public final class Logger {
    /**
     * 直接输出调试信息，不显示出现在哪个类
     * @param debugStr
     */
	public static void debug(String debugStr) {
        Log log = LogFactory.getLog(Logger.class);
        if (log.isDebugEnabled()) {
            log.debug(debugStr);
        }
    }
	/**
	 * 
	 * @param objectClass
	 * @param debugStr
	 */
    public static void debug(Class objectClass, String debugStr) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isDebugEnabled()) {
            log.debug(debugStr);
        }
    }

    public static void debug(Class objectClass, String debugStr, Throwable t) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isDebugEnabled()) {
            log.debug(debugStr, t);
        }
    }


    public static void info(Class objectClass, String infoStr) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isInfoEnabled()) {
            log.info(infoStr);
        }
    }

    public static void info(Class objectClass, String infoStr, Throwable t) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isInfoEnabled()) {
            log.info(infoStr, t);
        }
    }

    public static void warn(Class objectClass, String warnStr) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isWarnEnabled()) {
            log.warn(warnStr);
        }
    }

    public static void warn(Class objectClass, String warnStr, Throwable t) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isWarnEnabled()) {
            log.warn(warnStr, t);
        }
    }

    public static void error(Class objectClass, String infoStr) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isErrorEnabled()) {
            log.error(infoStr);
        }
    }

    public static void error(Class objectClass, String infoStr, Throwable t) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isErrorEnabled()) {
            log.error(infoStr, t);
        }
    }


    public static void fatal(Class objectClass, String infoStr) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isFatalEnabled()) {
            log.fatal(infoStr);
        }
    }

    public static void fatal(Class objectClass, String infoStr, Throwable t) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isFatalEnabled()) {
            log.fatal(infoStr, t);
        }
    }
    
    public static void debug(String objectClass, String debugStr) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isDebugEnabled()) {
            log.debug(debugStr);
        }
    }

    public static void debug(String objectClass, String debugStr, Throwable t) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isDebugEnabled()) {
            log.debug(debugStr, t);
        }
    }


    public static void info(String objectClass, String infoStr) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isInfoEnabled()) {
            log.info(infoStr);
        }
    }

    public static void info(String objectClass, String infoStr, Throwable t) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isInfoEnabled()) {
            log.info(infoStr, t);
        }
    }

    public static void warn(String objectClass, String warnStr) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isWarnEnabled()) {
            log.warn(warnStr);
        }
    }

    public static void warn(String objectClass, String warnStr, Throwable t) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isWarnEnabled()) {
            log.warn(warnStr, t);
        }
    }

    public static void error(String objectClass, String infoStr) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isErrorEnabled()) {
            log.error(infoStr);
        }
    }

    public static void error(String objectClass, String infoStr, Throwable t) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isErrorEnabled()) {
            log.error(infoStr, t);
        }
    }


    public static void fatal(String objectClass, String infoStr) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isFatalEnabled()) {
            log.fatal(infoStr);
        }
    }

    public static void fatal(String objectClass, String infoStr, Throwable t) {
        Log log = LogFactory.getLog(objectClass);
        if (log.isFatalEnabled()) {
            log.fatal(infoStr, t);
        }
    }
}

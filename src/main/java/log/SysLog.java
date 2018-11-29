package log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SysLog implements ISysLog{
    /**默认系统日志使用名称*/
    //static Logger logger = LoggerFactory.getLogger(SysLog.class);
    static Logger logger = LoggerFactory.getLogger("sysLog");
    private static SysLog log;
    private SysLog(){

    }

    public static SysLog getInstance(){
        if(log==null){
            synchronized (SysLog.class) {
                if(log==null){
                    log = new SysLog();
                }
            }
        }
        return log;
    }

    @Override
    public void debug(String msg) {
        logger.debug(prependClassAndMethodToMsg(msg));
    }

    @Override
    public void info(String msg) {
        logger.info(prependClassAndMethodToMsg(msg));
    }

    @Override
    public void warn(String msg) {
        logger.warn(prependClassAndMethodToMsg(msg));
    }

    @Override
    public void warn(String msg, Throwable e) {
        logger.warn(prependClassAndMethodToMsg(msg), e);
    }

    @Override
    public void error(String msg) {
        logger.error(prependClassAndMethodToMsg(msg));
    }

    @Override
    public void error(String msg, Throwable e) {
        logger.error(prependClassAndMethodToMsg(msg),e);
    }

    private String prependClassAndMethodToMsg(String msg) {
        StackTraceElement element = new Throwable().getStackTrace()[2];
        return String.format("[%s] [%s] - %s", element.getClassName(),
                element.getMethodName(), msg);
    }
}

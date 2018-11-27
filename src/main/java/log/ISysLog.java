package log;

public interface ISysLog {
    /**
     * 输出debug级别的日志
     * @param msg 消息
     */
    public void debug(String msg);

    /**
     * 输出info级别的日志
     * @param msg 消息
     */
    public void info(String msg);

    /**
     * 输出warn级别的日志
     * @param msg 消息
     */
    public void warn(String msg);

    /**
     * 输出warn级别的日志，异常
     * @param msg 消息
     * @param e 异常
     */
    public void warn(String msg, Throwable e);

    /**
     * 输出error级别的日志
     * @param msg 消息
     */
    public void error(String msg);

    /**
     * 输出error级别的日志，异常
     * @param msg 消息
     * @param e 异常
     */
    public void error(String msg, Throwable e);


    /**
     * 在消息前面加入类名、方法名
     * @param msg 消息
     * @return
     */
/*	private String prependClassAndMethod2Msg(String msg) {
		StackTraceElement element = new Throwable().getStackTrace()[2];
		return String.format("[%s] [%s] - %s", element.getClassName(),
				element.getMethodName(), msg);
	}*/
}

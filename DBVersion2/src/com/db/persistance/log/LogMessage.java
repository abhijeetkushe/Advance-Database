package com.db.persistance.log;

public class LogMessage {

	private enum LogMessageType{INFO,ERROR,DEBUG};
	public static void logErrorMessage(String message,String messageType)
	{
		System.out.println(message);
	}
	
	public static void logInfoMessage(String message,String messageType)
	{
		System.out.println(message);
	}
	
	public static void logDebugMessage(String message,String messageType)
	{
		System.out.println(message);
	}
	
	public static void logStackTrace(Exception e)
	{
		System.out.println(returnStackTrace(e));
	}
	
	public static String returnStackTrace(Exception e)
	{
		StringBuilder stackTrace=null;
		if(e!=null)
		{	
		
			StackTraceElement[] elementStack=e.getStackTrace();
			if(elementStack!=null && elementStack.length>0)
			{	
				stackTrace=new StringBuilder();
				for(StackTraceElement element:elementStack)
				{
					stackTrace.append(element);
					stackTrace.append("\\n");
				}
			}	
		}	
		
		return stackTrace!=null?stackTrace.toString():null;
	}
}

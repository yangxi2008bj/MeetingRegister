/**   
 * @Title: TokenDescriptException.java 
 * @Package com.lachesis.windranger.restful.exception 
 * @Description: 异常类库
 * @author Xi   
 * @date 2017年4月6日 上午11:26:24 
 * @version V1.0   
 */
package com.lachesis.windranger.restful.exception;

/** 
 * @ClassName: TokenDescriptException 
 * @Description: 解密token时出现的异常 
 * @author Xi xi.yang@lachesis-mh.com
 * @date 2017年4月6日 上午11:26:24 
 *  
 */
public class TokenDescriptException extends Exception {
	/** 
	 * @Fields serialVersionUID : 进行序列化，反序列化时使用
	 */ 
	private static final long serialVersionUID = 9160807634603414414L;

	public TokenDescriptException () {
		super ();
	}
	
	public TokenDescriptException(String msg) {
		super(msg);
	}
}

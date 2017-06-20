package interceptot;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 拦截器 拦截 登录 权限
 * @author nipengge
 * 作者:吕国朋
 * 时间:2017-2-10 上午10:49:43
 */
public class LoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		if(invocation.getInvocationContext().getSession().get("user")!=null||invocation.getInvocationContext().getSession().get("backUser")!=null){
			return invocation.invoke();
		}else {
			invocation.getInvocationContext().put("code", "login user");
			if(invocation.getInvocationContext().getSession().get("user")!=null){
				return "backsignOut";
			}else if(invocation.getInvocationContext().getSession().get("backUser")!=null) {
				return "backbackSignOut";
			}
		}
		
		return Action.LOGIN;
	}

}

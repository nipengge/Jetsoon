package interceptot;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * ������ ���� ��¼ Ȩ��
 * @author nipengge
 * ����:������
 * ʱ��:2017-2-10 ����10:49:43
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

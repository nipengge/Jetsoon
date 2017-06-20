package com.jetsoon.action;

import java.util.HashMap;
import java.util.Map;

import com.jetsoon.bean.Backstage_User;
import com.opensymphony.xwork2.Action;


public class JsonAction {
	
	private Map<String,Object> map;  
	
	private Backstage_User backstageUser;
	
	public String getjson(){
		/*try {
			
			HttpServletResponse response = ServletActionContext.getResponse();
			String jsonString="{\"user\":{\"id\":\"123\",\"name\":\"张三\",\"say\":\"Hello , i am a action to print a json!\",\"password\":\"JSON\"},\"success\":true}";  
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jsonString);
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		*/
		map = new HashMap<String, Object>();  
		Backstage_User user = new Backstage_User();  
        user.setUserName("张三");  
        user.setUserPassword("123");  
        map.put("user", user);  
        // 放入一个是否操作成功的标识  
        map.put("success", true);  
		
		
		return Action.SUCCESS;
		
	}
	
	public String test(){
		
		map = new HashMap<String, Object>();
		map.put("user",backstageUser);
		map.put("success", true);  
		
		return Action.SUCCESS;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Backstage_User getBackstageUser() {
		return backstageUser;
	}

	public void setBackstageUser(Backstage_User backstageUser) {
		this.backstageUser = backstageUser;
	}
}

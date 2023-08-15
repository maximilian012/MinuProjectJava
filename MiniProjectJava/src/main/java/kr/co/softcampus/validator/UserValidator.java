package kr.co.softcampus.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.softcampus.beans.UserBean;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		// 비밀번호 같은지 확인하는 유효성 검사
		UserBean userBean = (UserBean)target;
		
		String beanName = errors.getObjectName();
		//System.out.println(beanName);
		if(beanName.equals("joinUserBean") || beanName.equals("modifyUserBean")) {
			
			if (userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
				
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}
		}
			
			if(beanName.equals("joinUserBean")){
				
		//		if(userBean.isUserIdExist() == false) {
		//			
		//			errors.rejectValue("user_id", "DontCheckUserIdExist");
		//		}
			}
			
		
		
	}
	
	

	
}

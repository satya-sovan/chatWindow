package com.st.ichat;

public class ChatService {
	ChatBO bo=new ChatBO();
	ChatDAO dao=new ChatDAO();
	
	public boolean register(ChatDTO dto) {
		bo.setEmail(dto.getEmail());
				
		if(dao.registerCheck(bo)) {
			bo.setFirst_name(dto.getFirst_name());
			bo.setLast_name(dto.getLast_name());
			bo.setPassword(dto.getPassword());
			
			if(dao.registerInsert(bo)) {
				return true;
			}
			else
				return false;
			
		}
		else
			return false;
	}

	public boolean loginCheck(ChatDTO dto) {
		bo.setEmail(dto.getEmail());
		bo.setPassword(dto.getPassword());
		if(dao.loginCheck(bo)) {
			dto.setFirst_name(bo.getFirst_name());
			System.out.println("dao.logincheck(bo) true");
			return true;
		}
		else {
			
			System.out.println("dao.logincheck() false");
			return false;
		}
	}
}

package kr.co.tlf.ex.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MBCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response);
}

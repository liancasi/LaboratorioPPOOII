package com.PPOOII.Laboratorio.Config.Model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final String apikey; 

    public JwtResponse(String jwttoken, String apikey) { 
        this.jwttoken = jwttoken;
        this.apikey = apikey; 
    }

    public String getToken() {
        return this.jwttoken;
    }

    public String getApikey() { 
        return this.apikey;
    }

}
//	private final String user;
//
//	@JsonIgnore
//	private final String pass;
	
//	public JwtResponse(String jwttoken/*, String login, String password*/) {
//		this.jwttoken = jwttoken;
//		this.user = login;
//		this.pass = password;
	



//	public String getJwttoken() {
//		return jwttoken;
//	}
//
//	public String getUser() {
//		return user;
//	}
//
//	public String getPass() {
//		return pass;
//	}
	

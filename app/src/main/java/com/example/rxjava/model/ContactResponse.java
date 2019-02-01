package com.example.rxjava.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ContactResponse{

	@SerializedName("contacts")
	private List<ContactsItem> contacts;

	public void setContacts(List<ContactsItem> contacts){
		this.contacts = contacts;
	}

	public List<ContactsItem> getContacts(){
		return contacts;
	}
}
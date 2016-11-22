// O(n2) space O(n)

package Snapchat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UniqueContact {
	public static void main(String[] args) {
		Contact[] contacts = {
				new Contact("ABC", 123, 0),
				new Contact("ABC", 456, 1),
				new Contact("XYZ", 890, 2),
				new Contact("DEF", 456, 3),
				new Contact("DEF", 456, 3),
				new Contact("M", 890, 4)
		};
		
		UniqueContact uc = new UniqueContact();
		System.out.println(uc.groupUniqueContacts(contacts));
	}
	
	public List<List<Contact>> groupUniqueContacts(Contact[] contacts) {
		List<List<Contact>> res = new ArrayList<List<Contact>>();
		if(contacts == null || contacts.length == 0) return res;
		
		HashMap<String, List<Contact>> nameMap = new HashMap<String, List<Contact>>();
		HashMap<Integer, List<Contact>> numberMap = new HashMap<Integer, List<Contact>>();
		
		
		for(Contact contact : contacts) {
			boolean nameDup = false, numberDup = false;
			if(nameMap.containsKey(contact.name)) nameDup = true;
			if(numberMap.containsKey(contact.number)) numberDup = true;
			
			if(!nameDup && !numberDup) {
				List<Contact> uniqueContact = new ArrayList<Contact>();
				uniqueContact.add(contact);
				nameMap.put(contact.name, uniqueContact);
				numberMap.put(contact.number, uniqueContact);
				res.add(uniqueContact);
			} else if(!(nameDup && numberDup)) {
				if(nameDup) {
					nameMap.get(contact.name).add(contact);
					numberMap.put(contact.number, nameMap.get(contact.name));
				} else {
					numberMap.get(contact.number).add(contact);
					nameMap.put(contact.name, numberMap.get(contact.number));					
				}
			} else {
				List<Contact> listFromName = nameMap.get(contact.name);
				List<Contact> listFromNumber = numberMap.get(contact.number);
				if(listFromName == listFromNumber) {
					listFromName.add(contact);
				}
				else {
					for(Contact contactFromNumber : listFromNumber) {
						listFromName.add(contactFromNumber);
					}					
				}

			}
		}
		
//		for(List<Contact> uniqueContactList : nameMap.values()) {
//			List<Integer> list = new ArrayList<Integer>();
//			for(Contact contact : uniqueContactList) {
//				list.add(contact.index);
//			}
//			res.add(list);
//		}
		
		return res;
	}
}

class Contact {
	String name;
	int number;
	int index;
	
	public Contact(String name, int number, int index) {
		this.name = name;
		this.number = number;
		this.index = index;
	}
	
	@Override
	public int hashCode() {
		int prime = 29;
		return this.name.hashCode() * prime + number;
	}
	
	@Override
	public String toString() {
		return "[" + this.name + ", " + this.number + "]"; 
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(!(o instanceof Contact)) return false;
		Contact that = (Contact)o;
		return that.name.equals(that.name) && that.number == this.number;
	}
}

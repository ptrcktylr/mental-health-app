package com.revature.services;

import org.springframework.stereotype.Service;

import com.revature.daos.EntryDao;
import com.revature.models.Entry;


@Service
public class EntryService {
	
	EntryDao edao = new EntryDao();
	
	public Entry findEntryById(int entryId) {
		return edao.findEntryById(entryId);
	}

}

package com.bm.autoirrigationsys.services;

import com.bm.autoirrigationsys.dto.MessageDTO;

public interface MessageService {

	MessageDTO saveMessage(MessageDTO messageDTO);

	Boolean alertService();

}

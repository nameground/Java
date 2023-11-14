package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.JoDTO;

import mapperInterface.JoMapper;

//** interface 자동완성 
//=> Alt + Shift + T  
//=> 또는 마우스우클릭 PopUp Menu 의  Refactor - Extract Interface...

@Service
public class JoServiceImpl implements JoService {
	
	@Autowired
	JoMapper mapper;
	
	// ** selectList
	@Override
	public List<JoDTO> selectList() {
		return mapper.selectList();
	}
	// ** selectOne
	@Override
	public JoDTO selectOne(JoDTO dto) {
		return mapper.selectOne(dto);
	}
	// ** Insert
	@Override
	public int insert(JoDTO dto) {
		return mapper.insert(dto);
	}
	// ** Update
	@Override
	public int update(JoDTO dto) {
		return mapper.update(dto);
	}
	// ** Delete
	@Override
	public int delete(JoDTO dto) {
		return mapper.delete(dto);
	}

} //class

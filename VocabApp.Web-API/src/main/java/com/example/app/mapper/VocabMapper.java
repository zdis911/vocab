package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Vocab;
import com.example.app.domain.VocabType;

@Mapper
public interface VocabMapper {

	List<Vocab> selectAll();
	Vocab selectById(int id);
	List<Vocab> selectByType(int typeId);
	List<VocabType> selectAllTypes();
	void insert(Vocab vocab);
	void update(Vocab vocab);
	void delete(int id);
}

package br.com.erudio.mapper;

import java.util.List;
import java.util.ArrayList;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <O,D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}
	
	public static <O,D> List<D> parseList(List<O> originList, Class<D> destination) {
		List<D> destinationList = new ArrayList<D>();
		for(O origin : originList) {
			destinationList.add(mapper.map(origin, destination));
		}
		return destinationList;
	}
}

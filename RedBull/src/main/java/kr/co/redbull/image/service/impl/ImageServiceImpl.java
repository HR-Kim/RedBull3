package kr.co.redbull.image.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.redbull.cmn.DTO;
import kr.co.redbull.image.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ImageDaoImpl imageDaoImpl;

	@Override
	public int do_update(DTO dto) {
		return imageDaoImpl.do_update(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		return imageDaoImpl.do_delete(dto);
	}

	@Override
	public int do_save(DTO dto) {
		return imageDaoImpl.do_save(dto);
	}

	@Override
	public DTO get_selectOne(DTO dto) {
		return imageDaoImpl.get_selectOne(dto);
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		return imageDaoImpl.get_retrieve(dto);
	}
}
